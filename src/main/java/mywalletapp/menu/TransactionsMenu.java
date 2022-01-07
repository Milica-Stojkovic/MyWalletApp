package mywalletapp.menu;

/**
 * Transactions Menu - renders user selected account transactions (list)
 */
public class TransactionsMenu {
    private Boolean foreignAccount;

    public TransactionsMenu(){}
    public TransactionsMenu(Boolean foreignAccount) {
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
        sb.append("** MOJ NOVCANIK - PLACANJE - TRANSAKCIJE **");
        return sb.toString();
    }

}
