package uk.co.optimisticpanda.multitenantschema.basketitems;

public interface HasNoBasket extends LinkedToItems<Void> {

    default Void retrieveAssociatedItems(BasketStore store, String customerId) {
        return null;
    }
}