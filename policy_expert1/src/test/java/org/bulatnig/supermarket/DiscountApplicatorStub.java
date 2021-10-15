package org.bulatnig.supermarket;

import org.bulatnig.supermarket.basket.Basket;
import org.bulatnig.supermarket.basket.BasketDiscount;
import org.bulatnig.supermarket.discount.Discount;
import org.bulatnig.supermarket.discount.DiscountApplicator;

import java.util.List;

public class DiscountApplicatorStub implements DiscountApplicator {

    private final List<Discount> discounts;

    public DiscountApplicatorStub(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public void applyDiscounts(Basket basket) {
        for (Discount discount : discounts) {
            if (!discount.isApplicable(basket.getItems())) {
                continue;
            }
            int discountInPence = discount.apply(basket.getItems());
            if (discountInPence > 0) {
                basket.getDiscounts().add(new BasketDiscount(discount, discountInPence));
            }
        }
    }
}
