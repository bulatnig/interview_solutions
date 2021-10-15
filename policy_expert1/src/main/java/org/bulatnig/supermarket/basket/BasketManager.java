package org.bulatnig.supermarket.basket;

import org.bulatnig.supermarket.product.Product;

public interface BasketManager {

    Basket createBasket();

    void addProduct(Basket basket, Product product, int quantity);

    void removeItem(Basket basket, BasketItem item);

}
