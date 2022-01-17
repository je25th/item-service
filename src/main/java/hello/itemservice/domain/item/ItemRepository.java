package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static  //실제에서는 HashMap 쓰면 안된다!(동시접근) --> ConcurrntHashMap
    private static long sequnce = 0L; //static  //long도 마찬가지 --> atomicLong??뭐 이런거 써야함

    public Item save(Item item) {
        item.setId(++sequnce);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); //store에는 변함이 없도록 하기 위해 감싼것!
    }

    /*정석인 방법
    * UpdateDto 클래스 안에 수정할 수 있는 값만 딱 넣어두고 updateDto를 파라미터로 받기
    * String itemName;
    * Integer price;
    * Integer quantity;
    */
    public void update(Long itemId, Item updateParam) {
        Item findItem = store.get(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
