package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //현재 인터페이스와 구현 둘다 의존하며
    //추상화와 구체화 두곳에 다 의존하고있어 좋지못하다고 볼수있다. < DIP 위반
    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    //각 메서드가 실행될때마다 실행되는 어노테이션
    @BeforeEach
    public void beforeEach(){
        //Config 객체 생성
        AppConfig appConfig = new AppConfig();
        //Config 객체에서 생성한 객체에 의존후 해당 memberService 변수에 주입
        this.memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given : 어떠한 환경이 주어졌을때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : 이렇게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 이렇게 된다.
        Assertions.assertThat(member).isEqualTo(findMember);

    }

}
