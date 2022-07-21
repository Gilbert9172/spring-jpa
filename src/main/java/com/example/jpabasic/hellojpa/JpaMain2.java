package com.example.jpabasic.hellojpa;

import com.example.jpabasic.hellojpa.domain.Order;
import com.example.jpabasic.hellojpa.domain.OrderItem;
import org.aspectj.weaver.ast.Or;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   // DB connection
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /* 여기에 코드를 쓴다. */

        try {
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem1 = new OrderItem();
            order.addOrderItem(orderItem1);
            em.persist(orderItem1);

            tx.commit(); //=> 이 시점에서 영속성 컨텍스트에 있는 애가 DB 쿼리로 날아간다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
