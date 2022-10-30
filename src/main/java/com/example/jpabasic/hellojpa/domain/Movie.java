package com.example.jpabasic.hellojpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Item {

    private String director;
    private String actor;

}