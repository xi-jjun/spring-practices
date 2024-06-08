package hello.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * `@WebServlet` 어노테이션 없이 프로그래밍 방식으로 servlet container 가 초기화 될 때 해당 servlet 을 등록할 것이다.
 * 1. AppInit 인터페이스 구현
 * 2. 구현체 구현
 * 3. Servlet container init 코드에 등록을 위해서, MyContainerV2 에 iter 돌아가며 onStartup 메서드 직접 호출
 */
public class ProgrammaticServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProgrammaticServlet.service");
        resp.getWriter().println("ProgrammaticServlet.service called!");
    }
}
