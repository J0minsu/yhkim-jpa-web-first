package jpa.web.part.first.domain.values;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * packageName    : jpa.web.part.first.domain.values
 * fileName       : Address
 * author         : ms.jo
 * date           : 2023/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/13        ms.jo       최초 생성
 */
@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
    protected Address() {}
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
