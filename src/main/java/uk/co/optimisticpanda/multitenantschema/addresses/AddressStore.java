package uk.co.optimisticpanda.multitenantschema.addresses;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import uk.co.optimisticpanda.multitenantschema.Customer;

public class AddressStore {

    private Function<String, Customer> customerRetriever;

    public AddressStore(Function<String, Customer> customerRetriever) {
        this.customerRetriever = customerRetriever;
    }

    
    List<Address> getAddresses(String customerId) {
        return customerRetriever.apply(customerId).getItemIds().stream().map(Address::new).collect(toList());
    }

    Address getAddress(String customerId) {
        return findAddress(customerId).orElseThrow(() -> new IllegalStateException("error!")) ;
    }

    Optional<Address> findAddress(String customerId) {
        return getAddresses(customerId).stream().findFirst();
    }
}
