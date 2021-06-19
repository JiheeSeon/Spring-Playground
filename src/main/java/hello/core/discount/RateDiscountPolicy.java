package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
    private final int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        return member.getGrade() == Grade.VIP ? price * discountRate / 100 : 0;
    }
}
