package jpa.web.part.first.service;

import jpa.web.part.first.domain.entity.Member;
import jpa.web.part.first.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : jpa.web.part.first.service
 * fileName       : MemberService
 * author         : ms.jo
 * date           : 2023/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/13        ms.jo       최초 생성
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long regist(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member fineOne(Long id) {
        return memberRepository.findOne(id);
    }

    private void validateDuplicateMember(Member member) {
        if(!memberRepository.findByName(member.getName()).isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
