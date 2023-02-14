package jpabook.jpashop.Repository;

import jpabook.jpashop.Enum.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;  // 회원 이름
    private OrderStatus orderStatus;    // 주문 삳태[ORDER, CANCEL]
}
