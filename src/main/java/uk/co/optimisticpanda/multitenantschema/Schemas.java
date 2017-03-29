package uk.co.optimisticpanda.multitenantschema;

import uk.co.optimisticpanda.multitenantschema.addresses.HasManyAddresses;
import uk.co.optimisticpanda.multitenantschema.addresses.HasNoAddress;
import uk.co.optimisticpanda.multitenantschema.addresses.HasSingleAddress;
import uk.co.optimisticpanda.multitenantschema.basketitems.HasManyItemBasket;
import uk.co.optimisticpanda.multitenantschema.basketitems.HasNoBasket;
import uk.co.optimisticpanda.multitenantschema.basketitems.HasSingleItemBasket;

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
