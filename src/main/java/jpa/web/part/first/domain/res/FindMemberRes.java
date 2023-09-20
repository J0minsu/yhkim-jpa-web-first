package jpa.web.part.first.domain.res;

import jpa.web.part.first.domain.values.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMemberRes {

    private Long id;
    private String name;
    private Address address;

}
