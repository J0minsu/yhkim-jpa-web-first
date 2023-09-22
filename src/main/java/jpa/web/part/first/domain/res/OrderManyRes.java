package jpa.web.part.first.domain.res;

import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.entity.OrderItem;
import jpa.web.part.first.domain.enums.OrderStatus;
import jpa.web.part.first.domain.values.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : OrderManyRes
 * author         : ms.jo
 * date           : 2023/09/22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/22        ms.jo       최초 생성
 */
@Data
@NoArgsConstructor
public class OrderManyRes {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemManyRes> orderItems;

    public OrderManyRes(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
        this.orderItems = order.getOrderItems().stream().map(OrderItemManyRes::new).toList();
    }

}

@Getter
class OrderItemManyRes {
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
