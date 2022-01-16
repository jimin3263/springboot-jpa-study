package helloJpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @OneToMany(mappedBy = "team")
    //1대다에서 연결되어있는 것 작성 member의 team과 연결되었다라는 의미 ..
    //ArrayList로 초기화해주기
    private List<Member> members = new ArrayList<>();
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member){
        member.setTeam(this);
        members.add(member);
    }
}
