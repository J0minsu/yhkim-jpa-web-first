package jpa.web.part.first.repository;

import annotation.TestClass;
import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.domain.values.Address;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * packageName    : jpa.web.part.first.repository
 * fileName       : MemberRepositoryTest
 * author         : ms.jo
 * date           : 2023/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/13        ms.jo       최초 생성
 */
@TestClass
@Slf4j
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void save() throws Exception {
    
        //given
        Member member = new Member();
        Address addressA = new Address("Incheon", "Anaji-ro", "21116");
        member.setName("MemberA");
        member.setAddress(addressA);

        //when
        memberRepository.save(member);

        List<Member> findMembers = memberRepository.findByName(member.getName());
        for (Member findMember : findMembers) {
            log.info("findMember :: {}", findMember.getId());
        }


        //then
        Assertions.assertThat(findMembers.size()).isEqualTo(1);
    }
}