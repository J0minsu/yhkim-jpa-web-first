package jpa.web.part.first.domain.res;

import jpa.web.part.first.domain.entity.Order;
import jpa.web.part.first.domain.entity.OrderItem;
import jpa.web.part.first.domain.enums.OrderStatus;
import jpa.web.part.first.domain.values.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : FindOrderRes
 * author         : ms.jo
 * date           : 2023/09/21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/21        ms.jo       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindOrderRes {

    private Long id;
    private String memberName;
    private int price;
    private int stockQuantity;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public FindOrderRes(Order order) {
        id = order.getId();
        memberName = order.getMember().getName();
        price = order.getTotalPrice();
        stockQuantity = order.getOrderItems().stream().mapToInt(OrderItem::getCount).sum();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();

    }

    public FindOrderRes(Long id, String memberName, Long price, Long stockQuantity, LocalDateTime orderDate, OrderStatus status, Address address) {
        this.id = id;
        this.memberName = memberName;
        this.price = price.intValue();
        this.stockQuantity = stockQuantity.intValue();
        this.orderDate = orderDate;
        this.orderStatus = status;
        this.address = address;

    }

}
