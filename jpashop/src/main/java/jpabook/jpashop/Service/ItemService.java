package jpabook.jpashop.Service;

import jpabook.jpashop.Dto.ItemDto;
import jpabook.jpashop.Entity.Item.Book;
import jpabook.jpashop.Entity.Item.Item;
import jpabook.jpashop.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item saveItem(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Transactional
    public Item updateItem(Long itemId, ItemDto itemDto) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(itemDto.getPrice());
        findItem.setName(itemDto.getName());
        findItem.setStockQuantity(itemDto.getStockQuantity());

        return findItem;
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
