package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    /*
    enum을 쓸 때 type을 SPRING으로 지정해줘야 한다.
    default값이 ORDINAL인데, 이는 숫자로 표시한다는 말.
    근데 만약 READY를 0, COMP를 1로 했다가 다른 요구사항이 들어온다면 COMP는 3으로 밀려 로직이든 테이블이든 다 망하게 된다.
    그래서 enum을 쓸 땐 SPRING으로 지정해줘야 한다.
     */

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //DeliveryStatus: 배송상태, READY(배송준비), COMP(배송)
}
