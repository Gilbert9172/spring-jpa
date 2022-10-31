package com.example.jpabasic.hellojpa;

import com.example.jpabasic.hellojpa.domain.Book;
import com.example.jpabasic.hellojpa.domain.Member;
import com.example.jpabasic.hellojpa.prac.MemberPrac;
import com.example.jpabasic.hellojpa.prac.MoviePrac;
import com.example.jpabasic.hellojpa.prac.Team;
import org.hibernate.Hibernate;

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

            /* Lazy Loading  */
            Team team1 = new Team();
            team1.setName("Team1");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("Team2");
            em.persist(team2);

            Team team3 = new Team();
            team3.setName("Team3");
            em.persist(team3);

            Team team4 = new Team();
            team4.setName("Team4");
            em.persist(team4);

            Team team5 = new Team();
            team5.setName("Team5");
            em.persist(team5);


            MemberPrac member1 = new MemberPrac();
            member1.setUsername("Gilbert1");
            member1.setTeam(team1);
            em.persist(member1);

            MemberPrac member2 = new MemberPrac();
            member2.setUsername("Gilbert2");
            member2.setTeam(team2);
            em.persist(member2);

            MemberPrac member3 = new MemberPrac();
            member3.setUsername("Gilbert3");
            member3.setTeam(team3);
            em.persist(member3);

            MemberPrac member4 = new MemberPrac();
            member4.setUsername("Gilbert4");
            member4.setTeam(team4);
            em.persist(member4);

            MemberPrac member5 = new MemberPrac();
            member5.setUsername("Gilbert5");
            member5.setTeam(team5);
            em.persist(member5);

            em.flush();
            em.clear();

            List<MemberPrac> memberList = em.createQuery("SELECT m FROM MemberPrac m join fetch m.team", MemberPrac.class).getResultList();

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
