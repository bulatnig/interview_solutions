package org.bulatnig.supermarket.discount;

public class MultibuyDiscount extends SingleProductDiscount {

    private int discountQuantity;
    private int discountPrice;

    public MultibuyDiscount(Long productId, int discountQuantity, int discountPrice) {
        super(Type.MULTIBUY, productId);
        this.discountQuantity = discountQuantity;
        this.discountPrice = discountPrice;
    }

    @Override
    protected int apply(int productPrice, int quantity, int totalPriceWithoutDiscount) {
        int productsByDiscount = quantity / discountQuantity;
        if (productsByDiscount == 0) {
            return 0;
        }
        int productsWithoutDiscount = quantity % discountQuantity;
        int priceWithDiscount = productsByDiscount * discountPrice + productsWithoutDiscount * productPrice;
        return totalPriceWithoutDiscount - priceWithDiscount;
    }
}
