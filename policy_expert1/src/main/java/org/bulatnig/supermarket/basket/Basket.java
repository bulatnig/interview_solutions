package org.bulatnig.supermarket.basket;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<BasketItem> items = new ArrayList<>();
    private List<BasketDiscount> discounts = new ArrayList<>();

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public List<BasketDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<BasketDiscount> discounts) {
        this.discounts = discounts;
    }
}
