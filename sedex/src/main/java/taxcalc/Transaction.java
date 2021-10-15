package taxcalc;

public class Transaction {

    private int amount;
    private Currency currency;

    public Transaction(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
