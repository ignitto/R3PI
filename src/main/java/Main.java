import repository.ProductRepository;
import service.BasketService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        BasketService basketService = new BasketService(new ProductRepository());
        List<String> productList = Arrays.asList("Banana", "Apple", "orange", "Papaya", "Papaya", "Papaya", "Papaya", "Papaya");
        BigDecimal totalBasket = basketService.calculateBasketPrice(productList);
        BigDecimal totalDiscount = basketService.calculate3for2Discount(productList, "Papaya");

        System.out.println("Basket items:");
        productList.forEach(System.out::println);
        if(totalDiscount.compareTo(BigDecimal.ZERO) > 0){
            System.out.println("Discount 3 for 2 applied: -" + totalDiscount);
        }
        System.out.println("Total Pay: " + totalBasket.subtract(totalDiscount));


    }
}
