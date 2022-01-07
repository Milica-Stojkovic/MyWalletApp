package mywalletapp;

public class Transaction {
    private String accountNumber;
    private String oldAmount;
    private String newAmount;
    private String change;

    public Transaction(String accountNumber, String oldAmount, String newAmount, String change) {
        this.accountNumber = accountNumber;
        this.oldAmount = oldAmount;
        this.newAmount = newAmount;
        this.change = change;
    }

    @Override
    public String toString() {
        return "broj racuna='" + accountNumber +"'\n"+
                "    staro stanje=" + oldAmount+"\n"+
                "    novo stanje='" + newAmount+"'"+"'\n"+
                "    promena='" + change+"'";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(String oldAmount) {
        this.oldAmount = oldAmount;
    }

    public String getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(String newAmount) {
        this.newAmount = newAmount;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}

