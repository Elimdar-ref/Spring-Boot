package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private int basePrice;
    private double discount;

    public DiscountedProduct(String productName, int basePrice, double discount, UUID id) {
        super(productName, id);
        if(basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цены равна 0");
        }
            if (discount < 0 || discount > 100) {
                throw new IllegalArgumentException("Процент скидки минус или больше 100");
            }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return (int) (basePrice * (1 - discount / 100.00));
    }

    @Override
    public String toString() {
        return getProductName() + " со скидкой " + getPrice() + " : " + basePrice + " (скидка " + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}