package com.example.jpabasic.hellojpa.prac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy="team")
    private List<MemberPrac> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public static Team of(String name) {
        return new Team(name);
    }

}
