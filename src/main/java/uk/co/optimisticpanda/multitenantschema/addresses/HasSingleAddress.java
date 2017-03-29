package uk.co.optimisticpanda.multitenantschema.addresses;


public interface HasSingleAddress extends LinkedToAddresses<Address> {

    @Override
    default Address retrieveAssociatedAddresses(AddressStore store, String customerId) {
        return store.getAddress(customerId);
    }
}
