package jpabook.jpashop.Entity;

import jakarta.persistence.*;
import jpabook.jpashop.Enum.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;  //ENUM [READY(준비), COMP(배송)]
}
