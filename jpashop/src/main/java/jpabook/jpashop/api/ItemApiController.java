package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.Dto.ItemDto;
import jpabook.jpashop.Dto.ItemRequestDto;
import jpabook.jpashop.Dto.ItemResponseDto;
import jpabook.jpashop.Dto.MemberRequestDto;
import jpabook.jpashop.Entity.Item.Book;
import jpabook.jpashop.Entity.Item.Item;
import jpabook.jpashop.Entity.ResultEntity;
import jpabook.jpashop.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    /**
     * Item 전체 조회
     */
    @GetMapping("/api/items")
    public ResultEntity<List<ItemResponseDto>> ItemList() {
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            List<Item> items = itemService.findItems();
            if(!(items.size() == 0)) {
                for(int i = 0; i < items.size(); i++) {
                    ItemResponseDto itemResponseDto = new ItemResponseDto();
                    itemResponseDto.setId(items.get(i).getId());
                    itemResponseDto.setName(items.get(i).getName());
                    itemResponseDto.setPrice(items.get(i).getPrice());
                    itemResponseDto.setStockQuantity(items.get(i).getStockQuantity());
                    itemResponseDtos.add(i, itemResponseDto);
                }
            }
        } catch (Exception e) {
            return new ResultEntity<>(9999, "조회된 상품이 없습니다.");
        }

        return new ResultEntity<>(itemResponseDtos);
    }

    /**
     * Item 단일 조회
     */
    @GetMapping("/api/items/{id}")
    public ResultEntity<ItemResponseDto> ItemDetail(@PathVariable("id") Long id) {
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            Item item = itemService.findOne(id);
            itemResponseDto.setId(item.getId());
            itemResponseDto.setName(item.getName());
            itemResponseDto.setPrice(item.getPrice());
            itemResponseDto.setStockQuantity(item.getStockQuantity());
        } catch (Exception e) {
            return new ResultEntity<>(9999, "조회된 상품이 없습니다.");
        }

        return new ResultEntity<>(itemResponseDto);
    }

    /**
     * Item 생성
     */
    @PostMapping("/api/items")
    public ResultEntity<ItemResponseDto> ItemCreate(@RequestBody @Valid ItemRequestDto itemRequestDto) {
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Book book = new Book();
        book.setName(itemRequestDto.getName());
        book.setPrice(itemRequestDto.getPrice());
        book.setStockQuantity(itemRequestDto.getStockQuantity());
        book.setAuthor(itemRequestDto.getAuthor());
        book.setIsbn(itemRequestDto.getIsbn());
        try {
            Item item = itemService.saveItem(book);
            itemResponseDto.setId(item.getId());
            itemResponseDto.setName(item.getName());
            itemResponseDto.setPrice(item.getPrice());
            itemResponseDto.setStockQuantity(item.getStockQuantity());
        } catch (Exception e) {
            return new ResultEntity<>(9999, "Item 생성에 실패했습니다.");
        }

        return new ResultEntity<>(itemResponseDto);
    }

    /**
     * Item 수정
     */
    @PostMapping("/api/items/{id}")
    public ResultEntity<ItemResponseDto> ItemUpdate(@PathVariable("id") Long id, @RequestBody @Valid ItemDto itemDto) {
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            Item item = itemService.updateItem(id, itemDto);
            itemResponseDto.setId(item.getId());
            itemResponseDto.setName(item.getName());
            itemResponseDto.setPrice(item.getPrice());
            itemResponseDto.setStockQuantity(item.getStockQuantity());
        } catch (Exception e) {
            return new ResultEntity<>(9999, "Item 수정에 실패했습니다.");
        }

        return new ResultEntity<>(itemResponseDto);
    }

}
