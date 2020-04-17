package com.ubs.vahan.task.shoppingbasket.strategy;

import java.math.BigDecimal;
import java.util.Map;

import static com.ubs.vahan.task.shoppingbasket.ShoppingBasket.PRODUCT_PRICE_MAP;

public class SummingStrategy implements ShoppingBasketStrategy {
    @Override
    public void calculateTotal(Map<String, Double> productCount, BigDecimalWrapper result) {
        result.number = productCount.entrySet().stream()
                .map(o -> PRODUCT_PRICE_MAP.get(o.getKey()).multiply(BigDecimal.valueOf(o.getValue())))
                .reduce((o1, o2) -> BigDecimal.valueOf(0.0).add(o2)).get();
    }
}
