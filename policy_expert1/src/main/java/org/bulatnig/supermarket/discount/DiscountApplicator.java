package org.bulatnig.supermarket.discount;

import org.bulatnig.supermarket.basket.Basket;

public interface DiscountApplicator {

    void applyDiscounts(Basket basket);

}
