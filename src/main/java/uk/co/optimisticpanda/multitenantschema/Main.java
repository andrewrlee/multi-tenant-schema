package uk.co.optimisticpanda.multitenantschema;

import static uk.co.optimisticpanda.multitenantschema.Schemas.DemoClientSchema.DEMO;
import static uk.co.optimisticpanda.multitenantschema.Schemas.PremiumClientSchema.PREMIUM;
import static uk.co.optimisticpanda.multitenantschema.Schemas.StandardClientSchema.STANDARD;
import static uk.co.optimisticpanda.multitenantschema.Schemas.TrialClientSchema.TRIAL;

import java.util.List;

import uk.co.optimisticpanda.multitenantschema.addresses.Address;
import uk.co.optimisticpanda.multitenantschema.addresses.LinkedToAddresses;
import uk.co.optimisticpanda.multitenantschema.basketitems.Item;
import uk.co.optimisticpanda.multitenantschema.basketitems.LinkedToItems;

public class Main {

    public static void main(String[] args) {

        DataStore store = new DataStore();

        // Cardinality of relationship is determined by passed in schema in a type sa   fe way:
        
        List<Item> items = store.basketContents(PREMIUM, "1");
        List<Address> addresses = store.address(PREMIUM, "1");
        
        Item standardAccountItem = store.basketContents(STANDARD, "1");
        List<Address> standardAccountAddresses = store.address(STANDARD, "1");
        
        Void nothing = store.basketContents(DEMO, "1");

        // The schema provides a view on the same underlying data:
        
        printAssociations(store, PREMIUM, "1");
        printAssociations(store, STANDARD, "1");
        printAssociations(store, TRIAL, "1");
        printAssociations(store, DEMO, "1");
    }

    private static <T extends LinkedToAddresses<?> & LinkedToItems<?>> void printAssociations(
            DataStore store, T client, String customerId) {

        System.out.println(client.getClass() + ":");
        System.out.println("\t - "
                + store.address((LinkedToAddresses<?>) client, customerId));
        System.out.println("\t - "
                + store.basketContents((LinkedToItems<?>) client, customerId));
        System.out.println();
    }

}
