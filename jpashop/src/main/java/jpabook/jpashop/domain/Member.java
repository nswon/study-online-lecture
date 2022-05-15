package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String name;

    @Embedded
    private Address address;

    /*
    양방향 참조를 하게 되면 객체는 변경할 곳이 2곳이지만
    테이블은 한 곳, 즉 FK쪽만 바꾸면 되기 때문에 두 곳중 하나를 주인으로 정해줘야 한다. -> 연관관계 주인
    주인은 FK가 있는 테이블을 주인으로 정한다. 그럼 나머지 한 곳은 mappedBy를 적어준다.

    mappedBy 란 ?
    mappedBy = "member" 을 해석하면, 나는 order테이블에 있는  member필드에 의해서 매핑된 거울이라는 뜻 -> 읽기 전용
    읽기 전용이 되면 값을 바꿔도 데이터베이스에 반영되지 않는다.
     */

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
