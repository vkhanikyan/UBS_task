package com.ubs.vahan.task.shoppingbasket.strategy;

import java.util.Map;

public interface ShoppingBasketStrategy {
    void calculateTotal(Map<String, Double> productCount, BigDecimalWrapper result);
}
