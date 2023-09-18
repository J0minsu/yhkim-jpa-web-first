package jpa.web.part.first.domain.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * packageName    : jpa.web.part.first.domain.req
 * fileName       : MemberFormReq
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
public class MemberFormReq {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;

}
