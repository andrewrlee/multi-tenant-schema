package uk.co.optimisticpanda.multitenantschema.addresses;

import uk.co.optimisticpanda.multitenantschema.Relation;

public interface LinkedToAddresses<T> extends Relation {
    T retrieveAssociatedAddresses(AddressStore store, String customerId);    
}
