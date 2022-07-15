package jpabook.service;

import jpabook.jpashop.JpashopApplication;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @RunWith(SpringRunner.class) : 스프링 테스트 통합
 * @SpringBootTest : 스프링 부트 띄우고 테스트 (없으면 @Autowired 다 실패)
 * 스프링의 @Transactional은 기본적으로 rollBack을 한다.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpashopApplication.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;
    
    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("KIM");

        //when
        Long saveId = memberService.join(member);
        
        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("에외가 발생해야 한다.");
    }
}