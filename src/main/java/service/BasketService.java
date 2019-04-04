package service;

import exception.BasketServiceException;
import repository.ProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public class BasketService {

    private final ProductRepository productRepository;

    public BasketService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BigDecimal calculateBasketPrice(List<String> items) throws Exception {
        if(items == null || items.isEmpty()){
            throw new BasketServiceException("Product list is empty");
        }
        BigDecimal total = items.stream()
                .map(productRepository::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculate3for2Discount(List<String> items, String product){
        long count = items.stream().filter(Objects::nonNull).filter(p -> p.equalsIgnoreCase(product)).count();
        BigDecimal productPrice = productRepository.getPrice(product);
        return productPrice.multiply(new BigDecimal(count/3));
    }
}
