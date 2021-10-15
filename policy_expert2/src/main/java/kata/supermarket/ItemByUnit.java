package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;
    private final int units;
    private final DiscountByUnit discountByUnit;

    ItemByUnit(final Product product) {
        this(product, 1);
    }

    ItemByUnit(final Product product, final int units) {
        this.product = product;
        this.units = units;
        this.discountByUnit = null;
    }

    ItemByUnit(final Product product, final int units, final DiscountByUnit discountByUnit) {
        this.product = product;
        this.units = units;
        this.discountByUnit = discountByUnit;
    }

    @Override
    public BigDecimal priceWithDiscount() {
        return priceWithoutDiscount().subtract(discount());
    }

    public BigDecimal priceWithoutDiscount() {
        return product.pricePerUnit().multiply(BigDecimal.valueOf(units));
    }

    public BigDecimal discount() {
        return discountByUnit != null ? discountByUnit.discount(product.pricePerUnit(), units) : BigDecimal.ZERO;
    }
}
