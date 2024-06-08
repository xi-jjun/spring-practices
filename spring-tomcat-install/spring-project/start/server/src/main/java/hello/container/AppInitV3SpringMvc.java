package hello.container;

import hello.springframe.Config;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 이전 강의와 달리, WebApplicationInitializer 를 사용하면 자동으로 spring 에 등록된다. (별도의 AppInit 과 같은 인터페이스를 구성해서 MyContainerInitV2 와 같은 파일을 안만들어도 됨)<br>
 * <br>
 * 이 때까지 연습을 위해서 dispatcher servlet 과 spring container 를 2개씩 만들어봤는데, 일반적으로는 1개씩만 존재한다고 함.<br>
 * <br>
 * 그렇다면 해당 AppInitV3SpringMvc 는 어떻게 초기화 될 수 있을까?!<br>
 * => `org.springframework:spring-web:6.0.4` 의 `services` 디렉토리 하위를 보면 우리가 똑같이 등록했던 `jakarta.servlet.ServletContainerInitializer` 가 존재
 *
 * @see jakarta.servlet.ServletContainerInitializer
 * @see org.springframework.web.SpringServletContainerInitializer 우리가 AppInit 인터페이스 만들어서 초기화한 것과 비슷한 구조로 spring 에서도 초기화를 하는 중
 * @see <a href="https://xi-jjun.github.io/2024-05-25/tomcat_servletContainerInitializer">blog posting(Tomcat 에서 ServletContainerInitializer 를 초기화 하는 코드 확인</a>
 */
public class AppInitV3SpringMvc implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("AppInitV3SpringMvc.onStartup");

        // spring container 생성 (AppInitV2ForSpring 에서 1개 만들어서 이번이 2번째 spring container)
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(Config.class); // MyController 가 Bean 으로 등록되는 config class 를 spring container 에 등록

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // servlet container 에 dispatcher servlet 을 등록 및 url mapping
        servletContext.addServlet("dispatcherServletForAppInitV3SpringMvc", dispatcherServlet) // dispatcher servlet 등록
            .addMapping("/"); // 모든 요청이 dispatcher servlet 을 통하도록 url mapping
    }
}
