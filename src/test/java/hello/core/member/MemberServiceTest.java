package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //현재 인터페이스와 구현 둘다 의존하며
    //추상화와 구체화 두곳에 다 의존하고있어 좋지못하다고 볼수있다. < DIP 위반
    MemberService memberService = new MemberServiceImpl();

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
