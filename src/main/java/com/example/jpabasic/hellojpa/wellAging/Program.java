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
public class Program {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROGRAM_SEQ")
    private Long programSeq;

    private String programName;

    private String programInstructor;

    @OneToMany(mappedBy = "program")
    private List<StudentProgram> studentPrograms = new ArrayList<>();

    public void addStudentProgram(StudentProgram studentProgram) {
        studentPrograms.add(studentProgram);
        studentProgram.setProgram(this);
    }

    public Program(String programName, String programInstructor) {
        this.programName = programName;
        this.programInstructor = programInstructor;
    }
}
