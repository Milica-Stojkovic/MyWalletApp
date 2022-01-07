package mywalletapp;

public class ForeignAccount extends BankAccount {
    private String iban;

    public ForeignAccount(String accountNumber, User owner, String iban, String currency) {
        super(accountNumber, owner, currency);
        this.iban = iban;
    }

    public ForeignAccount(String accountNumber, User owner, String iban, Double amount, String currency) {
        super(accountNumber, owner, amount, currency);
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        String ibanString = "\n";
        if (iban.length()>0)
               ibanString = "\n    iban= '" + iban +"'\n";
        return
                super.toString() + ibanString;

    }
}
