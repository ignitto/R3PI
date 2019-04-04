package service;

import exception.BasketServiceException;
import org.junit.Test;
import repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasketServiceTest {

    private BasketService basketService = new BasketService(new ProductRepository());
    private List<String> items = Arrays.asList("Banana", "Apple", "orange", "Papaya", "Papaya", "Papaya", "Papaya");

    @Test
    public void calculateBasketPrice() throws Exception {
        assertEquals("Price did not match",
                new BigDecimal(2.70).setScale(2,  RoundingMode.HALF_EVEN),
                basketService.calculateBasketPrice(items)
        );
    }

    @Test
    public void calculateNonExistingProduct() throws Exception {
        BigDecimal price = basketService.calculateBasketPrice(Arrays.asList("AnyItem", null, "", "  "));
        assertEquals("Price did not match", BigDecimal.ZERO.setScale(2), price);
    }

    @Test(expected = BasketServiceException.class)
    public void basketCanNotBeNull() throws Exception {
        basketService.calculateBasketPrice(null);
    }

    @Test(expected = BasketServiceException.class)
    public void basketCanNotBeEmpty() throws Exception {
        basketService.calculateBasketPrice(new ArrayList<>());
    }

    @Test
    public void test3For2Discount(){
       BigDecimal discount =  basketService.calculate3for2Discount(items, "Papaya");
       assertEquals("Discount did not applied", new BigDecimal(0.5), discount);
    }

    @Test
    public void test3For2DiscountNegatives(){
        BigDecimal discount = basketService.calculate3for2Discount(Arrays.asList("AnyItem", null, "", "  "), "Papaya");
        assertEquals("Discount must be 0.0", BigDecimal.ZERO.setScale(1), discount);
    }

}