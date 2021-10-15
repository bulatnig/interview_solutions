package taxcalc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaxCalcTest {

    @Test
    public void canCalculateTax() {
        Transaction net = new TaxCalc(10, Currency.GBP).netAmount(
                new Transaction(40, Currency.GBP),
                new Transaction(50, Currency.GBP),
                new Transaction(60, Currency.GBP)
        );
        assertEquals(135, net.getAmount());
        assertEquals(Currency.GBP, net.getCurrency());
    }

    @Test
    public void cannotSumDifferentCurrencies() {
        assertThrows(CurrencyMismatchException.class, () ->
                new TaxCalc(10, Currency.GBP).netAmount(
                        new Transaction(4, Currency.GBP),
                        new Transaction(5, Currency.USD)
                )
        );
    }
}