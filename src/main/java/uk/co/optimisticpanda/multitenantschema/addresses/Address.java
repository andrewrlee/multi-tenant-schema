package uk.co.optimisticpanda.multitenantschema.addresses;

public class Address {

    private final String id;

    public Address(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + "]";
    }
}
