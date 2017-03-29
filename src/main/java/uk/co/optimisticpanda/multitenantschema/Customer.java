package uk.co.optimisticpanda.multitenantschema;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final String id;
    private final List<String> itemIds = new ArrayList<>();
    private List<String> addresses = new ArrayList<>();
    
    public Customer(String id, List<String> productIds, List<String> addresses) {
        this.id = id;
        this.itemIds.addAll(productIds);
        this.addresses.addAll(addresses);
    }

    public String getId() {
        return id;
    }

    public List<String> getItemIds() {
        return unmodifiableList(itemIds);
    }

    public List<String> getAddressIds() {
        return unmodifiableList(addresses);
    }

}
