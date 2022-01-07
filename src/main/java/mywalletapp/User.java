package mywalletapp;

public class User {
    private String Name;
    private String username;
    private String password;

    public User(String name, String username, String password) {
        Name = name;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public String getPasswordMasked(){
        char psw1 = password.charAt(0);
        String newPsw = String.valueOf(psw1);
        for (int i = 1; i<password.length(); i++)
        {
            newPsw+="*";
        }
        return newPsw;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public String printAsOwner() {
        return Name ;
    }
}
