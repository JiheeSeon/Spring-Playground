package autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // 스프링 빈으로 등록
    }

    static class TestBean{
        /*
        Setter 주입에서 bean을 선택적으로 setting 할 수 있도록 하는 것.

        왜 Member를 인자로?
        -> 스프링 컨테이너 관리 하에 없는 빈

        1. @Autowired(required = false)
           bean이 아니므로 자동주입할 대상이 없어 (의존관계 없) Autowired를 해도 수정자 호출 자체가 안됨
        2. @Nullable
           호출은 되지만 null로 들어옴
        3. Optional
           스프링 빈이 없으면 Optional.empty로 감싸서 보냄
         */

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional -> 값이 있을 수도 있고 없을 수도 있다는 상태를 감싼 것
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
