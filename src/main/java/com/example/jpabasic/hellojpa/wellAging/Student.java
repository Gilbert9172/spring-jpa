package com.example.jpabasic.hellojpa.wellAging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_SEQ")
    private Long studentSeq;

    private String name;

    private String gender;

    @OneToMany(mappedBy = "student")
    private List<StudentProgram> studentPrograms = new ArrayList<>();

    public Student(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }
}
