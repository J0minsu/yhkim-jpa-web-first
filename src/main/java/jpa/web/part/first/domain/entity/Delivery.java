package jpa.web.part.first.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpa.web.part.first.domain.enums.DeliveryStatus;
import jpa.web.part.first.domain.values.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName    : jpa.web.part.first.domain.entity
 * fileName       : Delivery
 * author         : ms.jo
 * date           : 2023/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/13        ms.jo       최초 생성
 */
@Entity
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]

}
