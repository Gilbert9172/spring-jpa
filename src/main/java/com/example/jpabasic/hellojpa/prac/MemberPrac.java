package com.example.jpabasic.hellojpa.prac;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberPrac extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

//    @Embedded
//    private Period period;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(
            name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(
//            name = "ADDRESS",
//            joinColumns = @JoinColumn(name = "MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();


    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();


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