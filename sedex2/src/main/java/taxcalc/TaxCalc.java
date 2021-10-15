package taxcalc;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TaxCalc {

    int percent;

    TaxCalc(int percent) {
        this.percent = percent;
    }

    Transaction netAmount(Transaction first, Transaction... rest) {
        List<Transaction> transactions = join(first, rest);
        verifyAllSameCurrency(transactions);
        int netAmount = calculateNetAmount(transactions);
        return new Transaction(netAmount, first.getCurrency());
    }

    @NotNull
    private List<Transaction> join(Transaction first, Transaction[] rest) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(first);
        transactions.addAll(Arrays.asList(rest));
        return transactions;
    }

    private void verifyAllSameCurrency(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            return;
        }
        String currency = transactions.get(0).getCurrency();
        for (Transaction transaction : transactions) {
            if (transaction.getCurrency() != currency) {
                throw new ApplicationException();
            }
        }
    }

    private int calculateNetAmount(List<Transaction> transactions) {
        Integer totalAmount = transactions.stream().mapToInt(Transaction::getAmount).sum();
        Double taxAmount = totalAmount * (percent / 100d);
        return totalAmount - taxAmount.intValue();
    }
}