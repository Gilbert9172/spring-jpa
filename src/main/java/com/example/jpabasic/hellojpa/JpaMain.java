package com.example.jpabasic.hellojpa;

import com.example.jpabasic.hellojpa.domain.Member;
import com.example.jpabasic.hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();   // DB connection
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            /* 저장 */
            Team team = Team.of("TeamA");
            em.persist(team);

            Member member = Member.of("gilbert", team);
            em.persist(member);

            em.flush();
            em.clear();

            /* 조회1
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("결과 = [ " + findTeam.getId() + " || " + findTeam.getName() + " ]");
            */

            /* 조회2 */
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member member1 : members) {
                System.out.println(member1.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
