package kata.supermarket;

import java.math.BigDecimal;

public class BuyOneGetOneDiscount implements DiscountByUnit {
    @Override
    public BigDecimal discount(BigDecimal pricePerUnit, int units) {
        int evenUnits = units / 2;
        return pricePerUnit.multiply(BigDecimal.valueOf(evenUnits));
    }
}
