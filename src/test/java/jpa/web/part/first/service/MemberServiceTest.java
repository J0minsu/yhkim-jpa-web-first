package jpa.web.part.first.service;

import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * packageName    : jpa.web.part.first.service
 * fileName       : MemberServiceTest
 * author         : ms.jo
 * date           : 2023/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/13        ms.jo       최초 생성
 */
@SpringBootTest
//@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Transactional
@Slf4j
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        Long saveId = memberService.regist(member);
        //Then
        assertEquals(member, memberRepository.findOne(saveId));

    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //When
        memberService.regist(member1);
        try {
            memberService.regist(member2); //예외가 발생해야 한다.
        } catch (IllegalStateException e) {
            log.info("e.getMessage() :: {}", e.getMessage());
        } catch (Exception e) {
            log.error("e.getMessage() :: {}", e.getMessage());
        }
        //Then
    }
}