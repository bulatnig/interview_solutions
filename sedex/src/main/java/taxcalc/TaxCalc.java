package taxcalc;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

class TaxCalc {

    private final int percent;
    private final Currency currency;

    TaxCalc(int percent, Currency currency) {
        this.percent = percent;
        this.currency = currency;
    }

    Transaction netAmount(Transaction first, Transaction... rest) throws CurrencyMismatchException {
        toStream(first, rest).forEach(transaction -> {
            if (transaction.getCurrency() != currency) {
                throw new CurrencyMismatchException(currency, transaction.getCurrency());
            }
        });
        var total = toStream(first, rest).mapToInt(Transaction::getAmount).sum();
        Double tax = total * (percent / 100d);
        var netAmount = total - tax.intValue();
        return new Transaction(netAmount, currency);
    }

    @NotNull
    private Stream<Transaction> toStream(Transaction first, Transaction... rest) {
        return Stream.concat(Stream.of(first), Stream.of(rest));
    }
}