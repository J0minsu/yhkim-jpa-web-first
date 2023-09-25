package jpa.web.part.first.domain.res;

import jpa.web.part.first.domain.enums.OrderStatus;
import jpa.web.part.first.domain.values.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : OrderQueryRes
 * author         : ms.jo
 * date           : 2023/09/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/25        ms.jo       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryRes {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;

    private List<OrderItemQueryRes> items;

    public OrderQueryRes(Long orderId, String name, LocalDateTime orderDate, OrderStatus status, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.status = status;
        this.address = address;
    }
}
