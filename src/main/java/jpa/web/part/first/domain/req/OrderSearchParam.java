package jpa.web.part.first.domain.req;

import jpa.web.part.first.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : jpa.web.part.first.domain.req
 * fileName       : OrderSearchParam
 * author         : ms.jo
 * date           : 2023/09/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/14        ms.jo       최초 생성
 */

@Getter
@Setter
@ToString
public class OrderSearchParam {

    private OrderStatus orderStatus;
    private String keyword = "";
}
