package jpabook.jpashop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDto {
    private String name;
    private int price;
    private int stockQuantity;
}
