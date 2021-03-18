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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 싱글톤 관련 의문점
    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //예상결과
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //실제결과
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

//    @Autowired  MemberRepository memberRepository;

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //@autowired memberService로 대체 가능
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() { return new RateDiscountPolicy(); }

}
