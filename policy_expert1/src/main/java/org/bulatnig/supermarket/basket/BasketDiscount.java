package org.bulatnig.supermarket.basket;

import org.bulatnig.supermarket.discount.Discount;

public class BasketDiscount {

    private Discount discount;
    private int appliedDiscountInPence;

    public BasketDiscount(Discount discount, int appliedDiscountInPence) {
        this.discount = discount;
        this.appliedDiscountInPence = appliedDiscountInPence;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getAppliedDiscountInPence() {
        return appliedDiscountInPence;
    }

    public void setAppliedDiscountInPence(int appliedDiscountInPence) {
        this.appliedDiscountInPence = appliedDiscountInPence;
    }
}
