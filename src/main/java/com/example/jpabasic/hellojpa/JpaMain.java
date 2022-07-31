package com.example.jpabasic.hellojpa;

import com.example.jpabasic.hellojpa.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   // DB connection
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /* 여기에 코드를 쓴다. */

        try {
            /* 추가
            Member member = new Member();
            member.setId(1L);
            member.setName("testA");
            em.persist(member);
            */

            /* 단건 조회
            Member findMember = em.find(Member.class, 1L);
            */

            /* 삭제
            em.remove(member);
            */

            /* 수정
            Member member = em.find(Member.class, 1L);
            System.out.println("member = " + member.getName());
            System.out.println("member = " + member.getId());
            member.setName("testA");
            //em.persist(member); => 쓸필요 없음
            */

            /* 다중 조회
            * 객체를 대상으로 쿼리를 한다.

            List<Member> memberList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : memberList) {
                System.out.println(member.getName());
            }
            */

            /* 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("gil");
            */

            /* 영속
            System.out.println("=== Before ===");
            em.persist(member);
            System.out.println("=== After ===");
            */

            /* 동일성 보장
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            System.out.println(findMember1 == findMember2);
            */

            /* 준영속, 삭제
            em.detach(member);
            */

            /* 버터링
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(151L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("======================================");
            */

            /* flush()
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();

            System.out.println("======================================");
            */

            /* 준영속 상태
            Member member = em.find(Member.class, 150L);
            member.setName("update_150)"); // 현재는 영속상태

            em.detach(member);
            // em.clear();
            // em.close();
            */

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);


            tx.commit(); //=> 이 시점에서 영속성 컨텍스트에 있는 애가 DB 쿼리로 날아간다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
