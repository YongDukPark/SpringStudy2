package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        SpringApplication.run(CoreApplication.class, args);

        // 스프링은 모든게 애플리케이션 컨텍스트라는 것응로 시작을 한다.
        // 이를 스프링 컨테이너라고 보면 된다.
        // 아래와 같은 형식으로 Appconfig를 파라미터로 넣어주면
        // AppConfig안에 있는 Bean들을 스프링 컨테이너에 생성된 객체들을 등록하고 관리를 해준다.
        // 그럼 각 빈의 이름은 매서드 이름으로 등록이 된다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //이와같이 빈의 이름을 통해 가져온다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        
    }
}
