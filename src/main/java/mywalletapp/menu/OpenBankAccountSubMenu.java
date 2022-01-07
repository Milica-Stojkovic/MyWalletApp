package mywalletapp.menu;

import mywalletapp.ForeignAccount;
import mywalletapp.BankAccount;
import mywalletapp.User;

/**
 *
 *  Open Bank account sub menu - renders header for creating (opening) a new Bank account
 *
 */
public class OpenBankAccountSubMenu {
    private Boolean foreignAccount;

    public OpenBankAccountSubMenu(){}
    public OpenBankAccountSubMenu(Boolean foreignAccount) {
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
        sb.append("** MOJ NOVCANIK - OTVORI RACUN - ");
        if (!foreignAccount)
            sb.append("Dinarski **\n");
        else
            sb.append("Devizni **\n");
        return sb.toString();
    }

    public BankAccount createBankAccount(User owner, String accountNumber, String iban){
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
