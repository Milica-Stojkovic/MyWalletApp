package mywalletapp.menu;

import mywalletapp.BankAccount;
import mywalletapp.ForeignAccount;
import mywalletapp.User;

/**
 * Transaction Deposit Menu - renders header for Deposit on user selected account
 */
public class TransactionDepositMenu {
    private Boolean foreignAccount;

    public TransactionDepositMenu(){}
    public TransactionDepositMenu(Boolean foreignAccount) {
        this.foreignAccount = foreignAccount;
    }

    public Boolean getForeignAccount() {
        return foreignAccount;
    }

    public void setForeignAccount(Boolean foreignAccount) {
        this.foreignAccount = foreignAccount;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("****  MOJ NOVCANIK - PLACANJE - UPLATA ****");
        return sb.toString();
    }

    public BankAccount getBankAccount(User owner, String accountNumber, String iban){
        BankAccount bankAccount;
        if (this.foreignAccount) {
            bankAccount = new ForeignAccount(accountNumber, owner, iban, "EUR");
        }
        else {
            bankAccount = new BankAccount(accountNumber, owner, "RSD");
        }
        return bankAccount;
    }
}
