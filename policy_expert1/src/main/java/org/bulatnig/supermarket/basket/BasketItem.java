package org.bulatnig.supermarket.basket;

import org.bulatnig.supermarket.product.Product;

public class BasketItem {
    private Product product;
    private int productPrice;
    private int quantity;
    private int itemPrice;

    public BasketItem(Product product, int productPrice, int quantity, int itemPrice) {
        this.product = product;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
