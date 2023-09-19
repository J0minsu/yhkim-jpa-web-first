package jpa.web.part.first.domain.req;

import jpa.web.part.first.domain.values.Address;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateMemberReq {

    @NotEmpty
    private String username;

    private Address address;

}
