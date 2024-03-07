package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setName("kim");

        Long savedId = memberService.join(member);

        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member1.setName("Kim");

        memberService.join(member1);
        try {
            memberService.join(member2); //예외 발생
        } catch (IllegalStateException e) {
            return;
        }
//        fail("예외가 발생해야 한다.");
    }

}