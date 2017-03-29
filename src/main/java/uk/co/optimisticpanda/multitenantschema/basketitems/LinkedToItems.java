package uk.co.optimisticpanda.multitenantschema.basketitems;

import uk.co.optimisticpanda.multitenantschema.Relation;

public interface LinkedToItems<T> extends Relation {

    T retrieveAssociatedItems(BasketStore store, String customerId);
    
}
