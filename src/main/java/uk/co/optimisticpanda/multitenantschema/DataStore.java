package uk.co.optimisticpanda.multitenantschema;

import static java.util.Arrays.asList;

import java.util.Map;

import uk.co.optimisticpanda.multitenantschema.addresses.AddressStore;
import uk.co.optimisticpanda.multitenantschema.addresses.LinkedToAddresses;
import uk.co.optimisticpanda.multitenantschema.basketitems.BasketStore;
import uk.co.optimisticpanda.multitenantschema.basketitems.LinkedToItems;

import com.google.common.collect.ImmutableMap;

public class DataStore {

    private final Map<String, Customer> map = ImmutableMap.<String, Customer>builder()
            .put("1", new Customer("1", 
                    asList("item-1", "item-2"),
                    asList("address-1", "address-2")))
            .build();

    
    private final BasketStore basket = new BasketStore(map::get);
    private final AddressStore addresses = new AddressStore(map::get);
    
    public <T> T basketContents(LinkedToItems<T> rel, String customerId) {
        return rel.retrieveAssociatedItems(basket, customerId);
    }
    
    public <T> T address(LinkedToAddresses<T> rel, String customerId) {
        return rel.retrieveAssociatedAddresses(addresses, customerId);
    }
}
