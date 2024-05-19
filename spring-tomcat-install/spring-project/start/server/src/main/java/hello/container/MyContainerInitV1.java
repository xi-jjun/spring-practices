package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.util.Set;

/**
 * 1. Servlet container 를 초기화해주는 코드를 아래와 같이 작성
 * 2. Servlet container 에게 초기화 메서드는 이거라고 알려줘야 함
 *   - resources/META-INF/services/jakarta.servlet.ServletContainerInitializer 파일 생성
 *   - 생성한 파일에 아래 메서드를 등록. => hello.container.MyContainerInitV1
 */
public class MyContainerInitV1 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MyContainerInitV1: onStartup()");
        System.out.println("MyContainerInitV1: set classed = " + set);
        System.out.println("MyContainerInitV1: servletContext = " + servletContext);
    }
}
