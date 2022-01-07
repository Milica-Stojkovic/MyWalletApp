package mywalletapp.menu;

/**
 * Transaction Menu - renders options for manipulating with user bank account transactions
 * - Deposit
 * - Withdrawal
 * - Transactions
 */
public class TransactionMenu {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("********* MOJ NOVCANIK - PLACANJE ********\n");
        for (int i=1; i<8; i++){
            sb.append("* ");
            switch (i){
                case 2:
                    sb.append("1) Uplata na racun ");
                    sb.append("                    *\n");
                    break;
                case 3:
                    sb.append("2) Isplata sa racuna ");
                    sb.append("                  *\n");
                    break;
                case 4:
                    sb.append("3) Moje transakcije ");
                    sb.append("                   *\n");
                    break;
                case 6:
                    sb.append("0) Vrati se na Glavni meni ");
                    sb.append("            *\n");
                    break;
                default:
                    sb.append("                                       *\n");
                    break;
            }

        }
        sb.append("******************************************\n");
        return sb.toString();
    }
}
