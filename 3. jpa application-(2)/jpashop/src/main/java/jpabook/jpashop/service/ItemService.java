package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //아이템 등록
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //변경 감지 방식
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId); //영속 상태

        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

    }

    //전체 아이템 조회
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    //특정 아이템 조회
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
