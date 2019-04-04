package repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class ProductRepository {
    private final Map<String, BigDecimal> prices = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public ProductRepository(){
        prices.put("Apple", new BigDecimal(0.25));
        prices.put("Orange", new BigDecimal(0.30));
        prices.put("Banana", new BigDecimal(0.15));
        prices.put("Papaya", new BigDecimal(0.50));
    }

    public BigDecimal getPrice(String name){
        if(name == null){
            return null;
        }
        return prices.get(name);
    }


}
