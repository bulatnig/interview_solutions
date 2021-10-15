package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyOneGetOneDiscountTest {

    @Test
    void singleUnitGivesNoDiscount() {
        assertEquals(new BigDecimal("0.00"), new BuyOneGetOneDiscount().discount(new BigDecimal("1.00"), 1));
    }

    @Test
    void twoUnitsGiveDiscountForOne() {
        BigDecimal pricePerUnit = new BigDecimal("1.00");
        assertEquals(pricePerUnit, new BuyOneGetOneDiscount().discount(pricePerUnit, 2));
    }

    @Test
    void everyEvenUnitIsFree() {
        BigDecimal pricePerUnit = new BigDecimal("1.00");
        assertEquals(pricePerUnit.multiply(BigDecimal.valueOf(2)), new BuyOneGetOneDiscount().discount(pricePerUnit, 4));
    }

    @Test
    void everyOddUnitIsNotFree() {
        BigDecimal pricePerUnit = new BigDecimal("1.00");
        assertEquals(pricePerUnit.multiply(BigDecimal.valueOf(2)), new BuyOneGetOneDiscount().discount(pricePerUnit, 5));
    }
}
