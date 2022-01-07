package mywalletapp.menu;

import mywalletapp.BankAccount;
import mywalletapp.ForeignAccount;
import mywalletapp.User;
/*
//  Transaction Withdrawal Menu - renders header for Withdrawal from user selected account
 */
public class TransactionWithdrawalMenu {
    private Boolean foreignAccount;

    public TransactionWithdrawalMenu(){}
    public TransactionWithdrawalMenu(Boolean foreignAccount) {
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
        sb.append("**** MOJ NOVCANIK - PLACANJE - ISPLATA ****");
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
