package mywalletapp.menu;

import mywalletapp.BankAccount;
import mywalletapp.ForeignAccount;
import java.util.ArrayList;

/**
 * MyAccounts menu - renders options for manipulations with user accounts
 */
public class MyAccountsMenu {
    private ArrayList<ForeignAccount> bankAccounts;

    public MyAccountsMenu() {}

    public MyAccountsMenu(ArrayList<ForeignAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public ArrayList<ForeignAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<ForeignAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("******** MOJ NOVCANIK - MOJI RACUNI ********\n");
        for (int i=1; i<7; i++){
            switch (i){
                case 2:
                    if (bankAccounts.size()>0) {
                        for (BankAccount ba : bankAccounts) {
                            sb.append("\n -> ");
                            sb.append(ba);
                        }
                    }
                    else{
                        sb.append(" Nema otvorenih racuna                 \n");
                    }
                    break;
                case 4:
                    sb.append("********************************************\n");
                    break;
                case 5:
                    if (bankAccounts.size()>0) {
                        sb.append("* 1) Obrisi racun ");
                        sb.append("                         *\n");
                    }
                    break;
                case 6:
                    sb.append("* 0) Vrati se na Glavni meni ");
                    sb.append("              *\n");
                    break;
                default:
                    sb.append("*                                          *\n");
                    break;
            }

        }
        sb.append("********************************************\n");
        return sb.toString();
    }
}
