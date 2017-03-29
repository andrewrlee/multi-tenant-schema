package uk.co.optimisticpanda.multitenantschema.addresses;

import java.util.List;

public interface HasManyAddresses extends LinkedToAddresses<List<Address>> {
    @Override
    default List<Address> retrieveAssociatedAddresses(AddressStore store, String customerId) {
        return store.getAddresses(customerId);
    }
}
