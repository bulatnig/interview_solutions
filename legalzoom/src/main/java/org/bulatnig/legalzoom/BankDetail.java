package org.bulatnig.legalzoom;

public class BankDetail {

    private String name;
    private String cardNumber;
    private String expiryDate;

    public BankDetail() {
    }

    public BankDetail(String name, String cardNumber, String expiryDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
