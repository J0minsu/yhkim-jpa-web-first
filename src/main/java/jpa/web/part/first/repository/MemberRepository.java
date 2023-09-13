package jpa.web.part.first.repository;

import jpa.web.part.first.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * packageName    : jpa.web.part.first.repository
 * fileName       : MemberRepository
 * author         : ms.jo
 * date           : 2023/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/13        ms.jo       최초 생성
 */
@Repository
@RequiredArgsConstructor
 public class MemberRepository {

    private final EntityManager em;

     public Long save(Member member) {
         em.persist(member);
         return member.getId();
     }
     public Member findOne(Long id) {
         return em.find(Member.class, id);
    }

    public List<Member> findAll() {
         String query = "SELECT m FROM Member m";
         return em.createQuery(query, Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
         String query = "SELECT m FROM Member m WHERE m.name = :name";
         return em.createQuery(query, Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

