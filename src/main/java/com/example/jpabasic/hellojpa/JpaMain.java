package com.example.jpabasic.hellojpa;

import com.example.jpabasic.hellojpa.prac.MemberPrac;
import com.example.jpabasic.hellojpa.prac.Team;

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

        try {

            /* 저장
            Team team = Team.of("TeamA");
            em.persist(team);

            Student member = Student.of("gilbert", team);
            em.persist(member);

            em.flush();
            em.clear();
             */

            /* 조회1
            Student findMember = em.find(Student.class, member.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("결과 = [ " + findTeam.getId() + " || " + findTeam.getName() + " ]");
            */

            /* 조회2
            Student findMember = em.find(Student.class, member.getId());
            List<Student> members = findMember.getTeam().getMembers();

            for (Student member1 : members) {
                System.out.println(member1.getUsername());
            }
            */

            /* 양방향 연관관계 주의점
            Team team = Team.of("TeamA");
            em.persist(team);

            Student member = Student.of("gilbert");
            member.setTeam(team);
            em.persist(member);

            team.getMembers().add(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());

            System.out.println("=======");
            System.out.println(findTeam.getId());
            System.out.println(findTeam.getName());
            System.out.println(findTeam.getMembers());
            System.out.println("=======");
             */

            /* 양방향 연관관계
            Team team = Team.of("TeamA");
            em.persist(team);

            MemberPrac member = MemberPrac.of("gilbert");
            member.changeTeam(team);
            em.persist(member);
             */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
