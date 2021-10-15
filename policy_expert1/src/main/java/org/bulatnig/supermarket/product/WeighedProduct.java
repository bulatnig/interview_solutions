package org.bulatnig.supermarket.product;

public class WeighedProduct extends Product {
    public WeighedProduct() {
        super(Type.WEIGHED);
    }

    @Override
    public int calculatePrice(int weightInGrams, int pencePerKg) {
        return weightInGrams * pencePerKg / 1000;
    }
}
