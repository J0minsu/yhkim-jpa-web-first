package jpa.web.part.first.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * packageName    : jpa.web.part.first.domain.req
 * fileName       : ModifyMemberReq
 * author         : ms.jo
 * date           : 2023/09/20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/20        ms.jo       최초 생성
 */
@Data
public class ModifyMemberReq {

    @NotEmpty
    private String name;

}
