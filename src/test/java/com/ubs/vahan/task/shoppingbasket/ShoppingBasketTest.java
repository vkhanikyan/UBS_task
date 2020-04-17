package com.ubs.vahan.task.shoppingbasket;

import com.ubs.vahan.task.shoppingbasket.strategy.DiscountStrategyApples;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ShoppingBasketTest {

    @Test
    public void calculateTotal_oneItemCase() {
        ShoppingBasket shoppingbasket = new ShoppingBasket();
        shoppingbasket.addProduct("Orange", 1.2);
        assertEquals(ShoppingBasket.PRODUCT_PRICE_MAP.get("Orange").multiply(BigDecimal.valueOf(1.2)), shoppingbasket.calculateTotal());
    }

    @Test
    public void calculateTotal_2TimesTheSameItemCase() {
        ShoppingBasket shoppingbasket = new ShoppingBasket();
        shoppingbasket.addProduct("Orange", 1.2);
        shoppingbasket.addProduct("Orange", 1.2);
        assertEquals(ShoppingBasket.PRODUCT_PRICE_MAP.get("Orange").multiply(BigDecimal.valueOf(2.4)), shoppingbasket.calculateTotal());
    }

    @Test
    public void calculateTotal_AppleDiscount() {
        ShoppingBasket shoppingbasket = new ShoppingBasket(new DiscountStrategyApples());
        shoppingbasket.addProduct("Apple", 2.5);
        assertEquals(ShoppingBasket.PRODUCT_PRICE_MAP.get("Apple").multiply(BigDecimal.valueOf(2.5/2)), shoppingbasket.calculateTotal());
    }

}