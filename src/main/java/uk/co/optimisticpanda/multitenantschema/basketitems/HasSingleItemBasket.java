package uk.co.optimisticpanda.multitenantschema.basketitems;


public interface HasSingleItemBasket extends LinkedToItems<Item> {

    @Override
    default Item retrieveAssociatedItems(BasketStore store, String customerId) {
        return store.getItem(customerId);
    }   
}
