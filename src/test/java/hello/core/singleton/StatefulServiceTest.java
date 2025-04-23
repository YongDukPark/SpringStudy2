package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadA : B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        //TjreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        // 여기서 2만원이 나오게 된다 왜냐하면 Order method에서 전역변수의 값을 지정하기 때문이다.
        // 인스턴스가 1개인 상태에서 진행을 하기에 userB가 주문한 금액이 이후 나오게 되는거다.
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}