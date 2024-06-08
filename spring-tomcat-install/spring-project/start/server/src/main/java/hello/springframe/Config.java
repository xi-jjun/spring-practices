package hello.springframe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    /**
     * MyController 를 직접 빈으로 등록
     *
     * @return controller
     */
    @Bean
    public MyController myController() {
        return new MyController();
    }
}
