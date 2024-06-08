package hello.container;

import hello.springframe.Config;
import jakarta.servlet.ServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 현재 spring container 관련 설정이 아예 없음. 따라서 spring container 를 servlet container 에 등록필요. (WAS+Spring 통합)<br>
 * 그 다음 우리가 Bean 으로 등록해준 springframe.MyController(Spring MVC controller) 를 spring container 에 등록하기 위해서 생성한 클래스<br>
 *
 * 참고)
 * - MyContainerInitV2 가 AppInit 의 구현체를 모두 가져와서 onStartup 메서드를 실행시켜준다.<br>
 * - MyContainerInitV2 는 META-INF 아래의 servlet container initializer 에 추가하여 TOMCAT 이 구동될 때 실행된다.
 *    - 관련해서 실제 해당 경로의 파일을 읽어서 초기화하는 처리는 tomcat 의 /org/apache/catalina/startup/WebAppServiceLoader.java 를 확인하면 된다.
 *    - --> 직접 tomcat 에서 debug 찍어가며 확인함
 *
 * @see hello.springframe.MyController
 * @see hello.container.MyContainerInitV2
 * @see <a href="https://github.com/apache/tomcat/blob/main/java/org/apache/catalina/startup/WebappServiceLoader.java">WebAppServiceLoader.java</a>
 */
public class AppInitV2ForSpring implements AppInit {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV2ForSpring.onStartup");

        // spring container 생성
        // 부모 객체는 ApplicationContext
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        // spring container 에 spring 관련 설정 등록
        appContext.register(Config.class); // MyController 가 Bean 으로 등록되는 config class 를 spring container 에 등록

        // Spring MVC dispatcher servlet 생성 후 spring container 에 연결
        // dispatcher servlet 이 HTTP 요청을 받아서 spring container 에 등록된 Controller Bean 들을 호출
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // servlet container 에 dispatcher servlet 을 등록 및 url mapping (root url 이 된다)
        // `/dispatcherServletForAppInitV2/*` 요청이 dispatcher servlet 을 통하도록 설정
        servletContext.addServlet("dispatcherServletForAppInitV2", dispatcherServlet) // dispatcher servlet 등록
            .addMapping("/dispatcherServletForAppInitV2/*"); // url mapping
        // ex) MyController 에서는 GET - /dispatcherServletForAppInitV2/hello-springframework 로 요청해야 함.
    }
}
