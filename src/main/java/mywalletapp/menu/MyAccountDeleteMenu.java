package mywalletapp.menu;

/**
 *
 *  MyAccount Delete Menu - Renders header for the delete account menu
 *  Renders main menu structure ith options
 *
 */
public class MyAccountDeleteMenu {
    private Boolean foreignAccount;

    public MyAccountDeleteMenu(){}
    public MyAccountDeleteMenu(Boolean foreignAccount) {
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
        sb.append("** MOJ NOVCANIK - MOJI RACUNI - BRISANJE **");
        return sb.toString();
    }
}
