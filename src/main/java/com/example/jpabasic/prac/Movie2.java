package com.example.jpabasic.prac;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("M")
public class Movie2 extends Item2 {
    private String director;
    private String actor;
    private String name;
    private int price;
}
