# multi-tenant-schema
Imposing different levels of cardinality through use of mixin/trait based schemas when querying

(When not using an ORM that allows specifying different types of relations for different subclasses of a common ancestor...)  

Requires steps:

  1.) Define and interface that describes that a relationship exists between 2 entities, e.g:

```java
public interface LinkedToAddresses<T> {
  T retrieveAssociatedAddresses(AddressStore store, String customerId);    
}
```

(where the parameters provide enough information to look up the associated entities)

 2.) Define interface subtypes that express cardinality:

```java
  public interface HasManyAddresses extends LinkedToAddresses<List<Address>> {
    @Override
    default List<Address> retrieveAssociatedAddresses(AddressStore store, String customerId) {
      return store.getAddresses(customerId);
    }
  }
  
  public interface HasNoAddress extends LinkedToAddresses<Void> {
    @Override
    default Void retrieveAssociatedAddresses(AddressStore store, String customerId) {
      return null;
    }
  }
```

(Could also do `MayHaveAnAddress`, `HasSingleAddress` etc..)
     
  3.) Then add generic methods for each type of relationship to some form of DAO like object, e.g:

```java
  public <T> T address(LinkedToAddresses<T> rel, String customerId) {
    return rel.retrieveAssociatedAddresses(addresses, customerId);
  }
```     

  4.) Given this you can define different schema objects that implement different interfaces to enforce different relationship types:      

```java
  public class Schemas {

    public enum PremiumClientSchema
    
      implements HasManyItemBasket, HasManyAddresses {
  
      PREMIUM;
    }

    public enum StandardClientSchema 
    
      implements HasSingleItemBasket, HasManyAddresses {
  
      STANDARD;
    }

    public enum TrialClientSchema 
    
      implements HasSingleItemBasket, HasSingleAddress {
  
      TRIAL;
    }

    public enum DemoClientSchema 
    
      implements HasNoBasket, HasNoAddress {
  
      DEMO
    }
  }
```

  5.) An example of this in action:

```java
  DataStore store = new DataStore();

  // Cardinality of relationship is determined by passed in schema.
  // Get compile time safety regarding type of the returned values:
  
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

```

Prints the following:

```
class uk.co.optimisticpanda.multitenantschema.Schemas$PremiumClientSchema:
     - [Address [id=item-1], Address [id=item-2]]
     - [Item [id=item-1], Item [id=item-2]]

class uk.co.optimisticpanda.multitenantschema.Schemas$StandardClientSchema:
     - [Address [id=item-1], Address [id=item-2]]
     - Item [id=item-1]

class uk.co.optimisticpanda.multitenantschema.Schemas$TrialClientSchema:
     - Address [id=item-1]
     - Item [id=item-1]

class uk.co.optimisticpanda.multitenantschema.Schemas$DemoClientSchema:
     - null
     - null
```