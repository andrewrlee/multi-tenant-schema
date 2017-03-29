package uk.co.optimisticpanda.multitenantschema.basketitems;

public class Item {

    private final String id;

    public Item(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + "]";
    }
    
}
