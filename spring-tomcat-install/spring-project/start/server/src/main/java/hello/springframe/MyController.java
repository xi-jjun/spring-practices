package hello.springframe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    /**
     * 해당 메서드 하나만 선언되어서는 앱 실행해도 해당 url로의 요청을 받지 못한다.<br>
     * => spring container 가 등록되지 않았기 때문.<br>
     *
     * @return 문구
     * @see hello.springframe.Config Bean 등록 관련
     * @see hello.container.AppInitV2ForSpring Spring container를 WAS(tomcat)에 등록 관련
     */
    @GetMapping("/hello-springframework")
    public String helloSpringFramework() {
        System.out.println("MyController.helloSpringFramework");
        return "Hello Spring Framework!";
    }
}
