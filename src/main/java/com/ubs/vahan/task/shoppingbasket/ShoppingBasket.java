package com.ubs.vahan.task.shoppingbasket;

import com.ubs.vahan.task.shoppingbasket.strategy.BigDecimalWrapper;
import com.ubs.vahan.task.shoppingbasket.strategy.ShoppingBasketStrategy;
import com.ubs.vahan.task.shoppingbasket.strategy.SummingStrategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Requirements
 * Shopping basket
 * Create shopping basket application in Java. It should have functionality for adding products and pricing them. User should be able to add products from predefined set and specify quantity or weight. After each change in basket's content total price should be calculated.
 * Pricing should allow processing scenarios like:
 * - three for a dollar
 * - $1.99/pound
 * - buy two, get one free
 * The class in not thread safe
 */
public class ShoppingBasket {

    // this is our database for products and prices
    public static Map<String, BigDecimal> PRODUCT_PRICE_MAP = new HashMap();

    static {
        PRODUCT_PRICE_MAP.put("Orange", BigDecimal.valueOf(15.9));
        PRODUCT_PRICE_MAP.put("Apple", BigDecimal.valueOf(20.6));
    }

    private Map<String, Double> productQuantity = new HashMap();
    private Set<ShoppingBasketStrategy> strategies = new HashSet();

    public ShoppingBasket() {
        this.strategies.add(new SummingStrategy());
    }

    public ShoppingBasket(ShoppingBasketStrategy discountStrategy) {
        this();
        this.strategies.add(discountStrategy);
    }

    public void addProduct(String product, Double quantity) {
        if (product == null || product.isEmpty() || !PRODUCT_PRICE_MAP.containsKey(product)) {
            throw new IllegalArgumentException("Wrong product");
        }
        productQuantity.put(product, quantity);
    }

    public BigDecimal calculateTotal() {
        BigDecimalWrapper result = new BigDecimalWrapper();

        for (ShoppingBasketStrategy strategy : strategies) {
            strategy.calculateTotal(productQuantity, result);
        }

        return result.number;
    }
}
