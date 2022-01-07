package mywalletapp.excel;

import mywalletapp.BankAccount;
import mywalletapp.ForeignAccount;
import mywalletapp.Transaction;
import mywalletapp.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class tableWallet {
    private static OutputStream fajl;
    private static Workbook wb = new XSSFWorkbook();
    private static Sheet shUsers = wb.createSheet("Users");
    private static Sheet shBankAccounts = wb.createSheet("BankAccounts");
    private static Sheet shTransactions = wb.createSheet("Transactions");

    public tableWallet() {
    }

    public static void CreateTable() {
        try{
            FileInputStream is = new FileInputStream("MyWallet.xls");
            wb = WorkbookFactory.create(is);
            shUsers = wb.getSheet("Users");
            shBankAccounts = wb.getSheet("BankAccounts");
            shTransactions = wb.getSheet("Transactions");
            is.close();
            wb.close();
        }
        catch(IOException ex) {
            try {
                OutputStream os = new FileOutputStream("MyWallet.xls");
                fajl = os;
                wb.write(fajl);
                wb.close();
                os.close();
                fajl.close();

            } catch (IOException e) {
                System.out.println(" Desila se greska pri radu sa Excel fajlom: " + e.getMessage());

            }
        }

    }

    public static void UpdateBankAccountsTable(BankAccount bankAccount, String iban) {
        try {
            FileInputStream is = new FileInputStream("MyWallet.xls");
            wb = WorkbookFactory.create(is);
            shUsers = wb.getSheetAt(0);
            shBankAccounts = wb.getSheetAt(1);
            shTransactions = wb.getSheetAt(2);
            CreateBankAccountRow(bankAccount, iban);
            is.close();

            FileOutputStream outputStream = new FileOutputStream("MyWallet.xls");
            wb.write(outputStream);
            wb.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(" Desila se greska pri radu sa Excel fajlom: " + e.getMessage());

        }

    }
    public static void UpdateBankAccountTable(ForeignAccount bankAccount, int rowNum) {
        try {
            FileInputStream is = new FileInputStream("MyWallet.xls");
            wb = WorkbookFactory.create(is);
            shBankAccounts = wb.getSheetAt(1);
            shTransactions = wb.getSheetAt(2);
            UpdateBankAccountRow(bankAccount, rowNum);
            is.close();

            FileOutputStream outputStream = new FileOutputStream("MyWallet.xls");
            wb.write(outputStream);
            wb.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(" Desila se greska pri radu sa Excel fajlom: " + e.getMessage());
        }
    }

    public static User CreateOrGetUserRow(User user){
        Boolean userExists = false;
        for(Row r : shUsers){
            if (r.getCell(1).toString().equals(user.getUsername())) {
                userExists = true;
                user = new User(r.getCell(0).toString(), r.getCell(1).toString(),r.getCell(2).toString());
                break;
            }
        }
        if (!userExists) {
            int lastRow = shUsers.getLastRowNum();
            lastRow += 1;
            Row r = shUsers.createRow(lastRow);
            Cell c1 = r.createCell(0);
            c1.setCellValue(user.getName());
            Cell c2 = r.createCell(1);
            c2.setCellValue(user.getUsername());
            Cell c3 = r.createCell(2);
            c3.setCellValue(user.getPassword());
        }
        return user;
    }

    public static User UpdateUserTable(User user) {
        try {
            FileInputStream is = new FileInputStream("MyWallet.xls");
            wb = WorkbookFactory.create(is);
            shBankAccounts = wb.getSheetAt(1);
            shTransactions = wb.getSheetAt(2);
            shUsers = wb.getSheetAt(0);
            UpdateUserRow(user);
            is.close();

            FileOutputStream outputStream = new FileOutputStream("MyWallet.xls");
            wb.write(outputStream);
            wb.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(" Desila se greska pri radu sa Excel fajlom: " + e.getMessage());
        }
        return user;
    }

    private static void UpdateUserRow(User user){
        for(Row r : shUsers){
            if (r.getCell(1).toString().equals(user.getUsername()))
            {
                Cell c1 = r.createCell(0);
                c1.setCellValue(user.getName());
                Cell c2 = r.createCell(2);
                c2.setCellValue(user.getPassword());
                return;
            }
        }
    }

    private static void CreateBankAccountRow(BankAccount bankAccount, String iban){
        int lastRow = shBankAccounts.getLastRowNum();
        lastRow +=1;
        Row r = shBankAccounts.createRow(lastRow);
        Cell c1 = r.createCell(0);
        c1.setCellValue(bankAccount.getAccountNumber());
        Cell c2 = r.createCell(1);
        c2.setCellValue(iban);
        Cell c3 = r.createCell(2);
        c3.setCellValue(bankAccount.getOwner().getUsername());
        Cell c4 = r.createCell(3);
        c4.setCellValue(bankAccount.getAmount());
        Cell c5 = r.createCell(4);
        c5.setCellValue(bankAccount.getCurrency());
    }

    private static void UpdateBankAccountRow(ForeignAccount bankAccount, int rowNum){
        Row r = shBankAccounts.getRow(rowNum);
        if (r!=null) {
            String amountString = r.getCell(3).toString();
            double amount = 0;
            try {
                amount = Double.parseDouble(amountString);
            } catch (Exception ex) {
            }
            double newAmount = bankAccount.getAmount();
            Cell c1 = r.createCell(0);
            c1.setCellValue(bankAccount.getAccountNumber());
            Cell c2 = r.createCell(1);
            c2.setCellValue(bankAccount.getIban());
            Cell c3 = r.createCell(2);
            c3.setCellValue(bankAccount.getOwner().getUsername());
            Cell c4 = r.createCell(3);
            c4.setCellValue(newAmount);
            if (amount!=newAmount)
            {
                CreateTransactionRow(bankAccount, amount, newAmount);
            }
            Cell c5 = r.createCell(4);
            c5.setCellValue(bankAccount.getCurrency());
        }
    }
/**
 * Gets Bank Account rows from the Excel table - BankAccounts for the selected user
 */
    public static ArrayList<ForeignAccount> GetBankAccountRows(User user){
        ArrayList<ForeignAccount> bankAccountRows = new ArrayList<>();
        for (Row row : shBankAccounts) {
            //Filters all Rows from sheet BankAccounts by Username
            if (row.getCell(2).toString().equals(user.getUsername())) {
                // Parse amount from String to double
                String amountString = row.getCell(3).toString();
                double amount = 0;
                try {
                    amount = Double.parseDouble(amountString);
                } catch (Exception ex) {
                }
                String iban = "";
                try {
                    iban = row.getCell(1).toString();
                } catch (Exception ex) {
                }
                bankAccountRows.add(new ForeignAccount(row.getCell(0).toString(),
                        user,
                        iban,
                        amount,
                        row.getCell(4).toString()));
            }
        }
        return bankAccountRows;
    }

    public static Boolean RemoveBankAccount(String accountNumber) {
        Boolean removed = false;
        try {
            FileInputStream is = new FileInputStream("MyWallet.xls");
            wb = WorkbookFactory.create(is);
            shBankAccounts = wb.getSheetAt(1);
            shTransactions = wb.getSheetAt(2);
            shUsers = wb.getSheetAt(0);
            removed = RemoveBankAccountRow(accountNumber);
            is.close();

            FileOutputStream outputStream = new FileOutputStream("MyWallet.xls");
            wb.write(outputStream);
            wb.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(" Desila se greska pri radu sa Excel fajlom: " + e.getMessage());
        }
        return removed;
    }

    private static Boolean RemoveBankAccountRow(String accountNumber){
        for (Row row : shBankAccounts) {
            //Filters all Rows from sheet BankAccounts to find row by accountNumber
            if (row.getCell(0).toString().equals(accountNumber)) {
                try {//if row exists, remove first all Transactions for this bank account
                    //than remove BankAccount
                    for (Row tRow : shTransactions) {
                        if (tRow.getCell(0).toString().equals(accountNumber)) {
                            shTransactions.removeRow(tRow);
                        }
                    }
                    shBankAccounts.removeRow(row);
                    return true;
                } catch (Exception ex) {
                }
            }
        }
        return false;
    }

    private static void CreateTransactionRow(BankAccount bankAccount, double amount, double newAmount){
        int lastRow = shTransactions.getLastRowNum();
        if (lastRow == -1)
            lastRow = 0;
        else
            lastRow +=1;
        Row r = shTransactions.createRow(lastRow);
        Cell c1 = r.createCell(0);
        c1.setCellValue(bankAccount.getAccountNumber());
        Cell c2 = r.createCell(1);
        c2.setCellValue(String.format("%.5f", amount));
        Cell c3 = r.createCell(2);
        c3.setCellValue(String.format("%.5f", newAmount));
        Cell c4 = r.createCell(3);
        double diff = newAmount - amount;
        String sign = "";
        if (diff<0)
            sign = "-";
        else if (diff>0)
            sign = "+";
        c4.setCellValue(sign + String.format("%.5f", Math.abs(diff)));
    }

    public static ArrayList<Transaction> GetTransactions(String bankAccountNumber){
        ArrayList<Transaction> transactionsRows = new ArrayList<>();
        for (Row row : shTransactions) {
            String accountNumber = row.getCell(0).toString();
            if (accountNumber.equals(bankAccountNumber))
                transactionsRows.add(new Transaction(row.getCell(0).toString(),
                        row.getCell(1).toString(),
                        row.getCell(2).toString(),
                        row.getCell(3).toString()));
        }
        return transactionsRows;
    }
}


