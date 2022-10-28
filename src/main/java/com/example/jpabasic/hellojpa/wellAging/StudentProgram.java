package com.example.jpabasic.hellojpa.wellAging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentProgram {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registerSeq;

    @ManyToOne
    @JoinColumn(name = "STUDENT_SEQ")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "PROGRAM_SEQ")
    private Program program;

    public StudentProgram(Student student, Program program) {
        this.student = student;
        this.program = program;
    }
}
