package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        // given
        Member newMember = new Member(1L, "javaMemberA", Grade.VIP);

        // when
        memberService.join(newMember);
        Member foundMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(newMember).isEqualTo(foundMember);
    }
}
