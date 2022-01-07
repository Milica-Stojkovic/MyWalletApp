package mywalletapp;

import mywalletapp.menu.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import mywalletapp.excel.tableWallet;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static MainMenu mainMenu = new MainMenu();
    public static OpenBankAccountMenu openBankAccountMenu = new OpenBankAccountMenu();
    public static MyAccountsMenu myAccountsMenu = new MyAccountsMenu();
    public static TransactionMenu transactionMenu = new TransactionMenu();
    public static User user = new User ("Milica Stojkovic", "milica", "181175");
    public static tableWallet tableWallet = new tableWallet();
    public static ForeignAccount activeAccount;
    public static int activeAccountRowNumber;

    /**
     * Main method
     * Creates MyWallet database in Excel
     * with logged in User as an active user
     * Prints MainMenu
     */
    public static void main(String[] args) {
        tableWallet.CreateTable();
        user = tableWallet.CreateOrGetUserRow(user);
        Menu(0);

    }
    /**
     * Main Menu - Prints MainMenu options
     */
    public static void Menu (int option){
        clrscr();
        switch (option) {
            case 0://Main Menu
                System.out.print(mainMenu);
                System.out.print(" Izaberite opciju iz menija: ");
                option = sc.nextInt();
                Menu(option);
                break;
            case 1://Create new Bank account
                clrscr();
                System.out.print(openBankAccountMenu);
                System.out.print(" Izaberite opciju iz menija: ");
                option = sc.nextInt();
                SubMenu(option, 1);
                break;
            case 2://My accounts
                clrscr();
                ArrayList<ForeignAccount> bankAccounts = tableWallet.GetBankAccountRows(user);
                myAccountsMenu.setBankAccounts(bankAccounts);
                System.out.print(myAccountsMenu);
                System.out.print(" Izaberite opciju iz menija: ");
                option = sc.nextInt();
                SubMenu(option, 2);
                break;
            case 3://Transactions
                clrscr();
                System.out.print(transactionMenu);
                System.out.print(" Izaberite opciju iz menija: ");
                option = sc.nextInt();
                SubMenu(option, 3);
                break;
            case 4://MyProfile
                clrscr();
                System.out.print(new MyProfileMenu(user));
                System.out.print(" Izaberite opciju iz menija: ");
                option = sc.nextInt();
                SubMenu(option, 4);
                break;
        }
    }
    /**
     * Prints SubMenus for all MainMenu options
     */
    public static void SubMenu(int option, int menu){
        switch (menu){
            case 1://Create new Bank Account
                switch (option) {
                    case 0:
                        Menu(0);
                        break;
                    case 1:
                        clrscr();
                        createBankAccount(false);
                        break;
                    case 2:
                        clrscr();
                        createBankAccount(true);
                        break;
                }
                break;
            case 2://My accounts
                switch (option) {
                    case 0:
                        Menu(0);
                        break;
                    case 1:
                        clrscr();
                        deleteBankAccount();
                        break;
                }
                break;
            case 3://Transacitions
                switch (option) {
                    case 0:
                        Menu(0);
                        break;
                    case 1:
                        clrscr();
                        transactions(1);
                        break;
                    case 2:
                        clrscr();
                        transactions(2);
                        break;
                    case 3:
                        clrscr();
                        transactions(3);
                        break;
                }
                break;
            case 4://MyProfile
                switch (option) {
                    case 0:
                        Menu(0);
                        break;
                    case 1:
                        clrscr();
                        updateProfile();
                        break;
                    case 2:
                        clrscr();
                        showPassword();
                        break;
                }
                break;
        }
    }

    /**
     * Prints options and enables user to Create a new bank account
     */
    public static void createBankAccount(Boolean foreignAccount){
        //Prints Open bank account header for the sub menu
        OpenBankAccountSubMenu obasm = new OpenBankAccountSubMenu(foreignAccount);
        System.out.println(obasm);

        System.out.print("* Unesite broj racuna: ");
        String accountNumber = sc.next();
        String iban = null;
        if (foreignAccount)
        {
            System.out.print("* Unesite IBAN broj: ");
            iban = sc.next();
        }
        System.out.print("\n*************************************************\n");
        System.out.print(" Unesite 0 da odustanete, 1 da kreirate novi racun: ");
        int option = sc.nextInt();
        if (option == 1) {
            //Creates a BankAccount and saves data to the Excel table - BankAccounts
            // for the current user
            BankAccount ba = obasm.createBankAccount(user, accountNumber, iban);
            tableWallet.UpdateBankAccountsTable(ba, iban);
            Menu(1);
        }
        else if (option == 0)
            //Returns to previous menu
            Menu(1);
    }

    public static void deleteBankAccount() {
        MyAccountDeleteMenu deleteAccountMenu = new MyAccountDeleteMenu();
        System.out.println(deleteAccountMenu);
        generalAccountInfo();
        System.out.print("******************************************************\n");
        System.out.print(" Unesite 0 da odustanete, 1 da obrisete odabrani racun: ");
        int option = sc.nextInt();
        performDeletion(option);
    }

    private static void performDeletion(int option){
        //option 1 - confirmed action -> delete account
        // -> Removes the bank account from the Wallet db
        if (option == 1) {
            Boolean removed = tableWallet.RemoveBankAccount(activeAccount.getAccountNumber());
            if (removed)
                Menu(2);
            else {
                System.out.println("\n Brisanje racuna nije uspelo!");
                System.out.print("\n******************************************************\n");
                System.out.print(" Unesite 0 da odustanete, 1 da pokusate ponovo: ");
                option = sc.nextInt();
                //retry
                performDeletion(option);
            }
        }
            // option 0 -> Back to Main Menu -> MyAccounts
        else if (option == 0)
                Menu(2);
    }

    /**
     * Prints Transactions menu with options to:
     *  - Deposit
     *  - Withdrawal
     *  - List transactions
     */
    public static void transactions(int option) {
        boolean perform = false;
        switch (option) {
            case (1): //Deposit
                TransactionDepositMenu depositMenu = new TransactionDepositMenu();
                System.out.println(depositMenu);
                generalAccountInfo();
                System.out.print("* Iznos koji se uplacuje: ");
                double deposit = sc.nextDouble();
                double amount = activeAccount.getAmount();
                if (deposit>0){
                    activeAccount.setAmount(amount + deposit);
                    perform = true;
                }
                else{
                    System.out.println("* Nedozvoljen unos!\n");
                }
                break;
            case (2): //Withdrawal
                TransactionWithdrawalMenu withdrawalMenu = new TransactionWithdrawalMenu();
                System.out.println(withdrawalMenu);
                generalAccountInfo();
                System.out.print("* Iznos koji se isplacuje: ");
                double withdrawal = sc.nextDouble();
                amount = activeAccount.getAmount();
                if (withdrawal>0 && withdrawal<=activeAccount.getAmount()){
                    activeAccount.setAmount(amount - withdrawal);
                    perform = true;
                }
                else{
                    System.out.println("* Nedozvoljen unos!\n");
                }
                break;
            case (3): //List transactions
                TransactionsMenu transactionsMenu = new TransactionsMenu();
                System.out.println(transactionsMenu);
                generalAccountInfo();
                System.out.println("* Sve transakcije:"+"\n");
                listTransactions(activeAccount.getAccountNumber());
                break;
        }
        //If either deposit or withdrawal, prints options to perform transaction
        // or returns to previous menu (cancel)
        if (perform) {
            System.out.print("\n******************************************************\n");
            System.out.print(" Unesite 0 da odustanete, 1 da izvrsite transakciju: ");
        }
        //else it's transaction list, prints option to get back to previous menu
        else{
            System.out.print("\n*************************************************\n");
            System.out.print(" Unesite 0 da se vratite u prethodni meni: ");
        }
        option = sc.nextInt();
        //option 1 - confirmed transaction (either deposit ot withdrawal)
        // -> Updates amount on Bank Account
        if (option == 1) {
            tableWallet.UpdateBankAccountTable(activeAccount, activeAccountRowNumber);
            Menu(3);
        }
        // option 0 -> Back to Main Menu -> Transactions
        else if (option == 0)
            Menu(3);
    }

    /**
     *Prints general information about user bank accounts
     * with the option to select an Active account
     * and present the active account balance
     * Prints message if there is no bank accounts
     */
    public static void generalAccountInfo(){
        System.out.println("*******************************************");
        System.out.println("* Vasi racuni \n");
        ArrayList<ForeignAccount> accounts = mywalletapp.excel.tableWallet.GetBankAccountRows(user);
        if (accounts.size() > 0) {
            int i = 1;
            for (ForeignAccount account : accounts) {
                System.out.print("* " + i + ") " + account.getAccountNumber() + " (" + account.getCurrency() + ")\n");
                i++;
            }
            System.out.println("\n*******************************************");
            System.out.print(" Izaberite racun: ");
            int accountNum = sc.nextInt();
            activeAccountRowNumber = accountNum-1;
            activeAccount = accounts.get(activeAccountRowNumber);
        }
        else
        {
            System.out.println("* Nemate otvorenih racuna!\n");
        }

        if (activeAccount != null) {
            System.out.println("\n* Stanje na racunu: "+activeAccount.getAmount()+"\n");
        }
    }

    /**
     * Lists all transactions for the Active account
     * Prints message if there is no transactions made for the Active account
     */
    public static void listTransactions(String accountNumber) {
        ArrayList<Transaction> transactions = tableWallet.GetTransactions(accountNumber);
        if (transactions.size()>0) {
            System.out.println("-------------------------------------------------");
            System.out.printf(" %10s %13s %12s", "staro stanje", "novo stanje", "promena");
            System.out.println();
            System.out.println("-------------------------------------------------");
            //iterates over the list of transactions
            for (Transaction transaction : transactions) {
                {
                    System.out.format("%8s %14s %14s", transaction.getOldAmount(), transaction.getNewAmount(), transaction.getChange());
                    System.out.println();
                }
            }
            System.out.println("-------------------------------------------------");
        }
        else
            System.out.println("* Na racunu nema izvrsenih transakcija! \n");
    }

    /**
     * Changes visibility of the user password on MyProfile menu
     */
    public static void showPassword(){
        MyProfileMenu myProfileMenu = new MyProfileMenu(user);
        myProfileMenu.setShowPassword(true);
        System.out.print(myProfileMenu);
        System.out.print(" Izaberite opciju iz menija: ");
        int option = sc.nextInt();
        SubMenu(option, 4);
    }

    /**
     *  Let user update user profile information on MyProfile menu
     */
    public static void updateProfile() {
        MyProfileMenu myProfileMenu = new MyProfileMenu(user);
        myProfileMenu.setShowMenuOptions(false);
        System.out.print(myProfileMenu);
        System.out.print("\n Novo ime (prazno da preskocite): ");
        String newName = sc.next();
        System.out.print(" Nova sifra (prazno da preskocite): ");
        String newPassword = sc.next();
        System.out.print("\n******************************************************\n");
        System.out.print(" Unesite 0 da odustanete, 1 da sacuvate profil: ");

        int option = sc.nextInt();
        if (option == 1) {
            Boolean userNameChanged = false;
            if (!newName.equals("") && !newName.equals(user.getName())) {
                //UPDATE USER TABLE
                user = tableWallet.UpdateUserTable(new User(newName, user.getUsername(), user.getPassword()));
                userNameChanged = true;
            }
            if (!newPassword.equals("") && !newPassword.equals(user.getPassword())) {
                if (!userNameChanged)
                    newName = user.getName();
                //UPDATE USER TABLE
                user = tableWallet.UpdateUserTable(new User(newName, user.getUsername(), newPassword));
            }
            Menu(4);
        } else if (option == 0)
            Menu(4);
    }

    public static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Clears screen (console)
     */
    public static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}
