package com.example.jpabasic.prac;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "ITEM_TYPE")
public abstract class Item2 {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
