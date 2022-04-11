package hello.jejulu.beanFind;

import hello.jejulu.web.config.WebConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    //컨테이너생성 -> 빈등록 -> 의좀관계 주입
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);

    /**
     * 등록된 빈 조회하기 테스트
     */
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean 이름 = "+beanDefinitionName +"bean 객체 = " + bean);
        }
    }
}
