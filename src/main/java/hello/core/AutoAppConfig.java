package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
@Configuration을 타고 들어가면 @Component 라는 애가 있음
-> 컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록
-> 앞서 만들어두었던 AppConfig, TestConfig 등의 설정 정보도 함께 등록됨
*/
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig { //수동으로 빈 등록하는 경우에만 코드 작성

    /*
    // @Autowired 필드 DI 사용 가능한 경우 시범용

    @Autowired MemberRepository memberRepository();
    @Autowired DiscountPolicy discountPolicy();

    @Bean
    OrderService orderService(){
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }

    // 수동빈 등록과 자동빈 등록이 충돌나는 경우
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    */
}
