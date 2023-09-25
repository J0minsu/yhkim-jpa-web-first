package jpa.web.part.first.domain.res;

import jpa.web.part.first.domain.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : OrderItemManyRes
 * author         : ms.jo
 * date           : 2023/09/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/25        ms.jo       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemManyRes {
    private Long itemId;
    private String name;
    private int price;
    private int count;

    public OrderItemManyRes(OrderItem orderItem) {
        this.itemId = orderItem.getId();
        this.name = orderItem.getItem().getName();
        this.price = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }

}

