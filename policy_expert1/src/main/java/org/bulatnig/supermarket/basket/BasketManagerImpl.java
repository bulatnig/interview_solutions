package org.bulatnig.supermarket.basket;

import org.bulatnig.supermarket.discount.DiscountApplicator;
import org.bulatnig.supermarket.product.Product;
import org.bulatnig.supermarket.product.ProductPriceProvider;

public class BasketManagerImpl implements BasketManager {

    private final ProductPriceProvider productPriceProvider;
    private final DiscountApplicator discountApplicator;

    public BasketManagerImpl(ProductPriceProvider productPriceProvider, DiscountApplicator discountApplicator) {
        this.productPriceProvider = productPriceProvider;
        this.discountApplicator = discountApplicator;
    }

    @Override
    public Basket createBasket() {
        return new Basket();
    }

    @Override
    public void addProduct(Basket basket, Product product, int quantity) {
        int pencePerItem = productPriceProvider.getPrice(product);
        int itemPrice = product.calculatePrice(quantity, pencePerItem);
        basket.getItems().add(new BasketItem(product, pencePerItem, quantity, itemPrice));
        recalculateDiscounts(basket);
    }

    private void recalculateDiscounts(Basket basket) {
        basket.getDiscounts().clear();
        discountApplicator.applyDiscounts(basket);
    }

    @Override
    public void removeItem(Basket basket, BasketItem item) {
        basket.getItems().remove(item);
        recalculateDiscounts(basket);
    }
}
