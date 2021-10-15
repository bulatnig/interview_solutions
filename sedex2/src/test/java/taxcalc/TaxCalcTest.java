package taxcalc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaxCalcTest {

    @Test
    public void canCalculateTax() throws Exception {
        Integer first = new TaxCalc(10).netAmount(
                new Transaction(40, "GBP"),
                new Transaction(50, "GBP"),
                new Transaction(60, "GBP")
        ).getAmount();
        assertEquals(135, first.intValue());
    }

    @Test
    public void canCalculateZeroes() throws Exception {
        Integer first = new TaxCalc(0).netAmount(
                new Transaction(0, "GBP"),
                new Transaction(0, "GBP"),
                new Transaction(0, "GBP")
        ).getAmount();
        assertEquals(0, first.intValue());
    }

    @Test
    public void canCalculateNegativeAmounts() throws Exception {
        Integer first = new TaxCalc(-5).netAmount(
                new Transaction(-5, "GBP"),
                new Transaction(-10, "GBP"),
                new Transaction(-15, "GBP")
        ).getAmount();
        assertEquals(0, first.intValue());
    }

    @Test
    public void cannotSumDifferentCurrencies() throws Exception {
        try {
            new TaxCalc(10).netAmount(
                    new Transaction(4, "GBP"),
                    new Transaction(5, "USD")
            );
            fail("didn't throw");
        } catch (Exception e) {

        }
    }
}