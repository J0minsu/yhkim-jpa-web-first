package jpa.web.part.first.domain.res;

import jpa.web.part.first.domain.values.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : MemberListRes
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
@AllArgsConstructor
public class MemberListRes {

    private Long id;
    private String name;
    private Address address;

}
