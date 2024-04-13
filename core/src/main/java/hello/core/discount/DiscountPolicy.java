package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    //@Return 할인 금액
    int dicount(Member member, int price);
}
