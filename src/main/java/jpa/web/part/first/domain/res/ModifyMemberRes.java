package jpa.web.part.first.domain.res;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : jpa.web.part.first.domain.res
 * fileName       : ModifyMemberRes
 * author         : ms.jo
 * date           : 2023/09/20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/20        ms.jo       최초 생성
 */
@Data
@AllArgsConstructor
public class ModifyMemberRes {

    private Long id;
    private String name;

}
