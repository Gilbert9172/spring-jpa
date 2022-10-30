package com.example.jpabasic.hellojpa.prac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("M")
public class MoviePrac extends ItemPrac {

    private String director;
    private String actor;
    private String name;
    private int price;

}
