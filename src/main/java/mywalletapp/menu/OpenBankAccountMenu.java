package mywalletapp.menu;
/**
 * Open Bank account menu - renders options for creating (openning) a new Bank account
 * Domestic or Foreign
 * Also renders option to get back to Main menu
 */
public class OpenBankAccountMenu {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("******* MOJ NOVCANIK - OTVORI RACUN ******\n");
        for (int i=1; i<8; i++){
            sb.append("* ");
            switch (i){
                case 2:
                    sb.append("1) Dinarski (RSD)");
                    sb.append("                      *\n");
                    break;
                case 3:
                    sb.append("2) Devizni (EUR)");
                    sb.append("                       *\n");
                    break;

                case 5:
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
