package com.example.jpabasic.hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /**
     * OrderItem 객체는 추가하면 DB에 들어감
     * 그러나 ordersItems는 가짜 매핑이기 때문에 읽기만 가능하다.
     * 따라서 양방향에 변경된 OrderItem 객체를 넣어줘야한다.
     * order.getOrderItems.add(orderItem) 보다는
     * 아래와 같이 연관관계 메서드를 추가해주는게 좋다.
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
