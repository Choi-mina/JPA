package jpabook.jpashop.Dto;

import jpabook.jpashop.Entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRequestDto {
    private String name;
    private Address address;
}
