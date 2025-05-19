package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 설정에 등록하기 위한 어노테이션
@Configuration
public class AppConfig {

    //스프링 컨테이너에 등록하기 위한 어노테이션
    //아래와 같이 이름을 명시적으로 지정할수 있다.
//    @Bean(name = "memberservicetest")
//    @Bean
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());
//    }
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//    @Bean
//    public OrderService orderService(){
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//    @Bean
//    public DiscountPolicy discountPolicy() {
//        //return new FixDiscountPolicy();
//        //실행 비즈니스 로직 변경
//        return new RateDiscountPolicy();
//    }
}
