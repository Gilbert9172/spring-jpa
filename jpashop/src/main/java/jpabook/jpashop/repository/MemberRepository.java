package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// 스프링 빈으로 등록, JPA예외를 스프링 기반 예외로 변환
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext // EntityManager 주입
    /** spring data JAP가 없으면 아래와 같이.
     * @PersistenceContext
     * private EntityManager em;
     */
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);     // Insert Query 나가지 않는다.
    }

    // 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // JPql : Member로 반환
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
