package jpabook.jpashop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemResponseDto {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
