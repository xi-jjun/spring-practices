package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class) // 생성한 AppInit 을 명시. 구현체들을 모두 가져온다.
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("MyContainerInitV2: onStartup");
        System.out.println("MyContainerInitV2: set = " + set);
        System.out.println("MyContainerInitV2: servletContext = " + servletContext);

        for (Class<?> aClass : set) {
            try {
                // 아래 코드까지 작성해줘야 programmatic servlet 이 servlet container 에 등록되는 것이다.
                // AppInit appInit = new AppInitV1Servlet(); 과 동일한 코드. 아래는 reflection 으로 생성한 것이다.
                AppInit appInit = (AppInit) aClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(servletContext); // 메서드를 직접 호출하여 servlet context 에 등록
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
