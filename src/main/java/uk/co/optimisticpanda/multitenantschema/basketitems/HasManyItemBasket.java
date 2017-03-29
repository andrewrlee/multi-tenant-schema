package uk.co.optimisticpanda.multitenantschema.basketitems;

import java.util.List;

public interface HasManyItemBasket extends LinkedToItems<List<Item>> {

    @Override
    default List<Item> retrieveAssociatedItems(BasketStore store, String customerId) {
        return store.getItems(customerId);
    }
    
}
