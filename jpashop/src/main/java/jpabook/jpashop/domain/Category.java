package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    /*
    기본적으로 ManyToOne, OneToOne에는 fetchType이 EAGER이다.
    EAGER(즉시로딩) 을 쓰면 안되는 이유 ?
    1. 예측이 어렵고 어떤 SQL이 실행될지 추적하기 어렵다.
    2. 특히 JPQL을 실행할 떄 N + 1문제가 자주 발생한다.

    결론: XtoOne의 fetchType은 지연로딩(LAZY)으로 바꾸자
    만약 트랜잭션 문제로 LAZY에 문제가 생기면 ? "궁극의 패치조인"을 사용하자.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 편입 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

}
