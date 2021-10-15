package org.bulatnig.supermarket.discount;

import org.bulatnig.supermarket.basket.BasketItem;

import java.util.List;

public abstract class Discount {

    private final Type type;

    public Discount(Type type) {
        this.type = type;
    }

    public abstract boolean isApplicable(List<BasketItem> basketBasketItems);

    public abstract int apply(List<BasketItem> basketBasketItems);

    enum Type {
        MORE_FOR_LESS,
        MULTIBUY
    }

}
