package kata.supermarket;

import java.math.BigDecimal;

public interface DiscountByUnit {

    BigDecimal discount(BigDecimal pricePerUnit, int units);

}
