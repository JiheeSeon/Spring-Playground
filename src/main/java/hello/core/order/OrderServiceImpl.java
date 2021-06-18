package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
//  [필드 주입] -> Anti pattern
//  @Autowired
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // DIP, 구체적인 클래스에 대해 전혀 모름


    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 확장성 때문에 member 넘김

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
/*
    [일반 setter]
    -> 흐지므르.... 어디서 막 discountPolicy가 바뀌어서 돌아다닐지 모른다구!!!

    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }

    [setter 주입 (생성자 별도로 작성 안해도 됨) ]
    아래 주석 풀고 싶으면 필드에 final 없애야 함!

    * 스프링의 2가지 라이프사이클에 따른 프로세스 *
       1. OrderServiceImpl을 컨테이너에 딱 등록한다.
       2. DI를 자동으로 주입한다.
         > setter 위에 @Autowired 가 안 달려있으면 당연히 주입 안함.

    * 생성자 주입과의 차이점 *
       생성자 주입을 사용하면 매개변수로 받는 빈들이 필요해서 결국 DI가 같이 일어남.
       즉 위 프로세스가 사실상 같이 일어남.

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }

    [일반 메서드 주입]
    거의 잘 안 씀!
    (주의 :: final field -> general field)

    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/
    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
