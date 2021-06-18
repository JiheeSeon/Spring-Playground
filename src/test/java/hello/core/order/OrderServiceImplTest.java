package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        /*
        setter 주입을 사용하고 생성자에 인자가 없을 경우
        테스트에 오류남 -> 의존관계에 뭐가 들어가는지 안 보이기 때문에 인자 세팅을 안해줘서

        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.createOrder(1L, "itemA, 10000");
         */

        // 생성자 주입
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}