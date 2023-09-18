package jpa.web.part.first.domain.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : ItemListRes
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
@NoArgsConstructor
@AllArgsConstructor
public class BookListRes {

    public Long id;
    public String name;
    public int price;
    public int stockQuantity;
}
