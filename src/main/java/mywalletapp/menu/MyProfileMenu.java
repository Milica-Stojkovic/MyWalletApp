package mywalletapp.menu;

import mywalletapp.User;
/**
 * My Profile Menu - renders options for managing user profile
 */
public class MyProfileMenu {
    private User user;
    private Boolean ShowPassword = false;
    private Boolean ShowMenuOptions = true;

    public void setShowPassword(Boolean showPassword) {
        ShowPassword = showPassword;
    }

    public MyProfileMenu(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("********  MOJ NOVCANIK - MOJ PROFIL ********");
        for (int i=1; i<4; i++){
            switch (i) {
                case 2:
                    String psw = user.getPasswordMasked();
                    String pswMasked = "(skrivena)";
                    if (ShowPassword) {
                        psw = user.getPassword();
                        pswMasked = "(prikazana!)";
                    }
                    sb.append("* Ime: " + user.getName());
                    sb.append("\n* Korisnicko ime: " + user.getUsername());
                    sb.append("\n* Sifra "+pswMasked+": " + psw);
                    break;
                default:
                    sb.append("\n*                                          *\n");
                    break;
            }

        }
        sb.append("********************************************\n");
        if (ShowMenuOptions) {
            for (int i = 1; i < 6; i++) {
                sb.append("* ");
                switch (i) {
                    case 2:
                        sb.append("1) Izmeni profil ");
                        sb.append("                        *\n");
                        break;
                    case 3:
                        if (!ShowPassword) {
                            sb.append("2) Prikazi sifru ");
                            sb.append("                        *\n");
                        } else
                            sb.append("                                         *\n");
                        break;
                    case 4:
                        sb.append("0) Vrati se na Glavni meni ");
                        sb.append("              *\n");
                        break;
                    default:
                        sb.append("                                         *\n");
                        break;
                }

            }
            sb.append("********************************************\n");
        }
        return sb.toString();
    }

    public void setShowMenuOptions(Boolean showMenuOptions) {
        ShowMenuOptions = showMenuOptions;
    }
}
