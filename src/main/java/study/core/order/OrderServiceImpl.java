package study.core.order;

import study.core.discount.DiscountPolicy;
import study.core.discount.FixDiscountPolicy;
import study.core.discount.RateDiscountPolicy;
import study.core.member.Member;
import study.core.member.MemberRepository;
import study.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //ocp 위반

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);            //회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인 정책 조회

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
