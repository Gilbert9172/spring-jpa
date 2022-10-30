package com.example.jpabasic.hellojpa;

import com.example.jpabasic.hellojpa.domain.Book;
import com.example.jpabasic.hellojpa.domain.Member;
import com.example.jpabasic.hellojpa.prac.MoviePrac;
import org.hibernate.Hibernate;

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

            /* 고급매핑
            MoviePrac movie = new MoviePrac();
            movie.setDirector("감독A");
            movie.setActor("배우A");
            movie.setName("영화A");
            movie.setPrice(10000);
            em.persist(movie);

            em.flush();
            em.clear();

            MoviePrac findMovie = em.find(MoviePrac.class, movie.getId());
             */

            /* 프록시
            Member member = new Member();
            member.setName("gilbert");
            em.persist(member);

            Member member1 = new Member();
            member1.setName("gilbert1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("초기화 전 = " + refMember.getClass());

            Hibernate.initialize(refMember);
            */


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }

}
