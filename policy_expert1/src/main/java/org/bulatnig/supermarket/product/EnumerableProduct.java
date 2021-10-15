package org.bulatnig.supermarket.product;

public class EnumerableProduct extends Product {
    public EnumerableProduct() {
        super(Type.ENUMERABLE);
    }

    @Override
    public int calculatePrice(int numberOfItems, int pencePerItem) {
        return numberOfItems * pencePerItem;
    }
}
