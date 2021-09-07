package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired EntityManager em;

    @Test
    //@Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("jimin");
        //when
        Long join = memberService.join(member);
        Member findMember = memberRepository.findOne(join);
        //then
        em.flush(); //데이터베이스에 반영
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member memberA = new Member();
        memberA.setName("test");

        Member memberB = new Member();
        memberB.setName("test");

        //when
        memberService.join(memberA);
        memberService.join(memberB);

        //then
        fail("예외가 발생해야 한다.");

    }
}