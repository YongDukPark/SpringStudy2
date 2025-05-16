package scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.login();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.login();
        // 스코프 프로토타입임에도 불구하고 이와같이 2로 기재가 된다.
        // 이는 의도와 다른 결과를 도출시킨다.
        Assertions.assertThat(count2).isEqualTo(2);
    }

    // singleton은 default이기에 하지 않아도 되지만 확실하게 예제를 보이기위해 기입
    @Scope("singleton")
    static class ClientBean{
//        private final PrototypeBean prototypeBean; //생성시점에 주입

        // 필드 주입은 비추천한다 가능한 생성자 주입으로 진행
        // 현재는 테스트이기에 필드주입으로 진행한다.
        // 예전에는 ObjectFactory를 사용하긴 했으나 같다.
        // 추가적으로 기능을 제공하는게 ObjectProvider이다.
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int login() {
            // getObject 를 사용 시점에서 스프링 컨테이너에서 프로토타입빈을 찾아서 반환을 해준다.
            // 이와같이 할경우 필요할때마다 찾아오기에 이전 요청 method에서 컨테이너 주입 하는 방식과 같은 결과를 낼수있다.
            // 그러나 스프링에게 보다 덜 의존적이다.
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init() {
            //this는 현재 나의 참조값을 보여준다.
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destory");
        }
    }
}
