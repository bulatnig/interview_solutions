package org.bulatnig.supermarket.discount;

import org.bulatnig.supermarket.basket.BasketItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Discount based on presence of single product in Busket
 */
public abstract class SingleProductDiscount extends Discount {

    private Long productId;

    public SingleProductDiscount(Type type, Long productId) {
        super(type);
        this.productId = productId;
    }

    @Override
    public boolean isApplicable(List<BasketItem> basketBasketItems) {
        return basketBasketItems.stream().anyMatch(item -> item.getProduct().getId().equals(productId));
    }

    @Override
    public int apply(List<BasketItem> allBasketItems) {
        List<BasketItem> discountBasketItems = allBasketItems.stream()
                .filter(basketItem -> basketItem.getProduct().getId().equals(productId))
                .collect(Collectors.toList());
        if (discountBasketItems.isEmpty()) {
            return 0;
        }
        int quantity = 0;
        int totalPrice = 0;
        for (BasketItem basketItem : discountBasketItems) {
            quantity += basketItem.getQuantity();
            totalPrice += basketItem.getItemPrice();
        }
        return apply(discountBasketItems.get(0).getProductPrice(), quantity, totalPrice);
    }

    protected abstract int apply(int productPrice, int quantity, int totalPriceWithoutDiscount);

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
