package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인")
    void vip_o(){
        Member member = new Member(1L, "VIPmember", Grade.VIP);
        int discount = discountPolicy.dicount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인 적용 안 함")
    void vip_x(){
        Member member = new Member(2L, "BASICmember", Grade.BASIC);
        int discount = discountPolicy.dicount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}
