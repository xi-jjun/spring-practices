package hello.container;

import hello.servlet.ProgrammaticServlet;
import hello.servlet.ProgrammaticServlet2;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

public class AppInitV1Servlet implements AppInit {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV1Servlet.onStartup");

        // servlet 코드 등록
        // 아래 경로를 조건에 따라 수정한다거나.. 하기 위해서 프로그래밍 방식으로도 등록하는 것이다.
        ServletRegistration.Dynamic programmaticServlet =
            servletContext.addServlet("programmaticServlet", new ProgrammaticServlet());
        programmaticServlet.addMapping("/programmatic-servlet");

        servletContext.addServlet("programmatic2Servlet", new ProgrammaticServlet2())
            .addMapping("/programmatic2-servlet");
    }
}
