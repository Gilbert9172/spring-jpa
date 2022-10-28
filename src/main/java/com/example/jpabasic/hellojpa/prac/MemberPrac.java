package com.example.jpabasic.hellojpa.prac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberPrac {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }


    private MemberPrac (String username, Team team) {
        this.username = username;
        this.team = team;
    }

    private MemberPrac(String username) {
        this.username = username;
    }

    public static MemberPrac of(String username, Team team) {
        return new MemberPrac(username, team);
    }

    public static MemberPrac of(String username) {
        return new MemberPrac(username);
    }

}