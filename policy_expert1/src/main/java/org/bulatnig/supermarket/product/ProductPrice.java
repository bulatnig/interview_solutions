package org.bulatnig.supermarket.product;

public class ProductPrice {

    private Long productId;
    private int priceInPence;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getPriceInPence() {
        return priceInPence;
    }

    public void setPriceInPence(int priceInPence) {
        this.priceInPence = priceInPence;
    }
}
