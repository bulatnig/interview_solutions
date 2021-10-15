package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new ItemByUnit(new Product(price)).priceWithDiscount());
    }

    @Test
    void multipleUnitItemHasExpectedPrice() {
        final BigDecimal price = new BigDecimal("3.87");
        ItemByUnit item = new ItemByUnit(new Product(price), 3);
        assertEquals(new BigDecimal("11.61"), item.priceWithDiscount());
    }

    @Test
    void itemPriceWithDiscountCalculatedCorrectly() {
        final BigDecimal price = new BigDecimal("4.17");
        ItemByUnit item = new ItemByUnit(new Product(price), 3, new BuyOneGetOneDiscount());
        assertEquals(price, item.discount());
        assertEquals(price.multiply(BigDecimal.valueOf(3)), item.priceWithoutDiscount());
        assertEquals(price.multiply(BigDecimal.valueOf(2)), item.priceWithDiscount());
    }
}