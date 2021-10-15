package org.bulatnig.supermarket;

import org.bulatnig.supermarket.basket.Basket;
import org.bulatnig.supermarket.basket.BasketManager;
import org.bulatnig.supermarket.basket.BasketManagerImpl;
import org.bulatnig.supermarket.discount.DiscountApplicator;
import org.bulatnig.supermarket.discount.MoreForLessDiscount;
import org.bulatnig.supermarket.discount.MultibuyDiscount;
import org.bulatnig.supermarket.product.EnumerableProduct;
import org.bulatnig.supermarket.product.Product;
import org.bulatnig.supermarket.product.ProductPriceProvider;
import org.bulatnig.supermarket.product.WeighedProduct;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsageExample {

    private ProductPriceProvider productPriceProvider;
    private DiscountApplicator discountApplicator;
    private BasketManager basketManager;

    Product beans;
    Product coke;
    Product oranges;

    @Before
    public void setUp() {
        productPriceProvider = mock(ProductPriceProvider.class);

        beans = new EnumerableProduct();
        beans.setId(1L);
        beans.setName("Beans");
        when(productPriceProvider.getPrice(beans)).thenReturn(50);
        coke = new EnumerableProduct();
        coke.setId(2L);
        coke.setName("Coke");
        when(productPriceProvider.getPrice(coke)).thenReturn(70);
        oranges = new WeighedProduct();
        oranges.setId(3L);
        oranges.setName("Oranges");
        when(productPriceProvider.getPrice(oranges)).thenReturn(199);

        discountApplicator = new DiscountApplicatorStub(Arrays.asList(
                new MoreForLessDiscount(beans.getId(), 3, 2),
                new MultibuyDiscount(coke.getId(), 2, 100)
        ));

        basketManager = new BasketManagerImpl(productPriceProvider, discountApplicator);
    }

    @Test
    public void test() {
        Basket basket = basketManager.createBasket();
        basketManager.addProduct(basket, beans, 1);
        basketManager.addProduct(basket, beans, 1);
        basketManager.addProduct(basket, beans, 1);
        basketManager.addProduct(basket, coke, 1);
        basketManager.addProduct(basket, coke, 1);
        basketManager.addProduct(basket, oranges, 200);
    }

}
