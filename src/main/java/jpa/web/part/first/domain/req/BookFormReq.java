package jpa.web.part.first.domain.req;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : jpa.web.part.first.domain.req
 * fileName       : BookFormReq
 * author         : ms.jo
 * date           : 2023/09/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/18        ms.jo       최초 생성
 */
@Getter
@Setter
public class BookFormReq {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
