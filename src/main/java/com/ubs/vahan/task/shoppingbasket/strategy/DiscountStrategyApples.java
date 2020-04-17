package com.ubs.vahan.task.shoppingbasket.strategy;

import com.ubs.vahan.task.shoppingbasket.ShoppingBasket;

import java.math.BigDecimal;
import java.util.Map;

public class DiscountStrategyApples implements ShoppingBasketStrategy {
    @Override
    public void calculateTotal(Map<String, Double> productCount, BigDecimalWrapper result) {
        if (productCount.containsKey("Apple") && productCount.get("Apple") > 2) {
            BigDecimal appleDiscount = ShoppingBasket.PRODUCT_PRICE_MAP.get("Apple").multiply(BigDecimal.valueOf(productCount.get("Apple") / 2));
            result.number = result.number.subtract(appleDiscount);
        }
    }
}
