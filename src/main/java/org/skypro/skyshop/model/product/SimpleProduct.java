package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private int price;

    public SimpleProduct(String productName, int price, UUID id) {
        super(productName, id);
        if(price <= 0) {
            throw new IllegalArgumentException("Цена продукта ноль");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
         return this.price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

//    @Override
//    public String toString() {
//        return super.toString() + getPrice();
//    }
}