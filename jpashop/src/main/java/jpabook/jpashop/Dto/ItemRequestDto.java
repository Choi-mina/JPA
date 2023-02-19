package jpabook.jpashop.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemRequestDto {
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
