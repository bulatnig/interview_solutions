package kata.supermarket;

import java.math.BigDecimal;

public interface Item {

    default BigDecimal priceWithDiscount() {
        return priceWithoutDiscount().subtract(discount());
    }

    BigDecimal priceWithoutDiscount();

    default BigDecimal discount() {
        return BigDecimal.ZERO;
    }
}
