package uk.co.optimisticpanda.multitenantschema.basketitems;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import uk.co.optimisticpanda.multitenantschema.Customer;

public class BasketStore {

    private Function<String, Customer> customerRetriever;

    public BasketStore(Function<String, Customer> customerRetriever) {
        this.customerRetriever = customerRetriever;
    }
    
    List<Item> getItems(String customerId) {
        return customerRetriever.apply(customerId).getItemIds().stream().map(Item::new).collect(toList());
    }

    Item getItem(String customerId) {
        return findItem(customerId).orElseThrow(() -> new IllegalStateException("error!")) ;
    }

    Optional<Item> findItem(String customerId) {
        return getItems(customerId).stream().findFirst();
    }
}
