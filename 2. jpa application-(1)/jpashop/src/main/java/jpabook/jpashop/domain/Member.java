package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //양방향일때 owner 정해줌 -> order의 member에 의해 매핑됨 = 읽기 전용
    private List<Order> orders = new ArrayList<>(); //초기화를 필드에서 하는게 안전

}
