package com.example.jpabasic.hellojpa.prac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookPrac extends ItemPrac {

    private String author;
    private String isbn;
}
