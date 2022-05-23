package jpabook.jpashop.domain.item;

import jpabook.jpashop.excepxtion.NotEnoughStockException;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
/*
SINGLE_TABLE
한 테이블에 상속받은 클래스의 모든 필드를 다 넣는 전략
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직 ==//

    /**
     * 제고 증가
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity +=quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
