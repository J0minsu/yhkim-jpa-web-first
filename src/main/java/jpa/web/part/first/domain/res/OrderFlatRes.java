package jpa.web.part.first.domain.res;

import jpa.web.part.first.domain.enums.OrderStatus;
import jpa.web.part.first.domain.values.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : OrderFlatRes
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
public class OrderFlatRes {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;

    private Long itemId;
    private String itemName;
    private int orderPrice;
    private int count;

}
