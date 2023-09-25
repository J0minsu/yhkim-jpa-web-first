package jpa.web.part.first.domain.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : OrderItemQueryRes
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
public class OrderItemQueryRes {

    private Long id;
    private String itemName;
    private int orderPrice;
    private int count;
}

