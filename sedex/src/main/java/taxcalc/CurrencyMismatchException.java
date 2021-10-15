package taxcalc;

public class CurrencyMismatchException extends ApplicationException {

    public CurrencyMismatchException(Currency expected, Currency actual) {
        super("Expected currency " + expected + " but provided " + actual);
    }
}
