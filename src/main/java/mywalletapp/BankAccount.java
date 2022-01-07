package mywalletapp;

public class BankAccount {
    private String accountNumber;
    private User owner;
    private double amount;
    private String currency;

    public BankAccount(String accountNumber, User owner, String currency) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.currency = currency;
    }

    public BankAccount(String accountNumber, User owner, double amount, String currency) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.amount = amount;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "broj racuna='" + accountNumber +"'\n"+
                "    stanje=" + amount+"\n"+
                "    valuta='" + currency+"'\n"+
                "    vlasnik='" + owner.printAsOwner()+"'";
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

