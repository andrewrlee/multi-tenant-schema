package uk.co.optimisticpanda.multitenantschema.addresses;


public interface HasNoAddress extends LinkedToAddresses<Void> {
    @Override
    default Void retrieveAssociatedAddresses(AddressStore store, String customerId) {
        return null;
    }
}
