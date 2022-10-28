package com.example.jpabasic.hellojpa.wellAging;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Simulator1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Program program1 = new Program("축구교실", "호날두");
            em.persist(program1);

            Student student1 = new Student("길버트1", "M");
            Student student2 = new Student("길버트2", "M");
            Student student3 = new Student("길버트3", "M");
            em.persist(student1);
            em.persist(student2);
            em.persist(student3);

            StudentProgram studentProgram1 = new StudentProgram(student1, program1);
            StudentProgram studentProgram2 = new StudentProgram(student2, program1);
            StudentProgram studentProgram3 = new StudentProgram(student3, program1);
            em.persist(studentProgram1);
            em.persist(studentProgram2);
            em.persist(studentProgram3);

            program1.addStudentProgram(studentProgram1);
            program1.addStudentProgram(studentProgram2);
            program1.addStudentProgram(studentProgram3);

            em.flush();
            em.clear();

            Program findProgram = em.find(Program.class, program1.getProgramSeq());
            List<StudentProgram> studentPrograms = findProgram.getStudentPrograms();

            System.out.println("=====================");
            System.out.println("studentPrograms = " + studentPrograms.size());
            for (StudentProgram program : studentPrograms) {
                String name = program.getStudent().getName();
                System.out.println("이름 = " + name);
            }
            System.out.println("=====================");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
