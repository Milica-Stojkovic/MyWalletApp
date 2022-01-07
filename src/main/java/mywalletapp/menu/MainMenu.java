package mywalletapp.menu;
/**
 * /
 * Main Menu
 * Renders main menu structure ith options
 */
public class MainMenu {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("************** MOJ NOVCANIK **************\n");
        for (int i=1; i<8; i++){
            sb.append("* ");
            switch (i){
                case 2:
                    sb.append("1) Otvori racun ");
                    sb.append("                       *\n");
                    break;
                case 3:
                    sb.append("2) Moji racuni ");
                    sb.append("                        *\n");
                    break;
                case 4:
                    sb.append("3) Placanje ");
                    sb.append("                           *\n");
                    break;
                case 5:
                    sb.append("4) Moj profil ");
                    sb.append("                         *\n");
                    break;
                case 6:
                    sb.append("5) Izloguj se ");
                    sb.append("                         *\n");
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
