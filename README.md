# MyWalletApp
My Wallet application - Bootcamp test project for Java

Hello,
This is my first Java project commited to Git Hub.
Please refer to the /DOC/index.html for the project java doc documentation.

Shortly, MyWalletApp is Java console application that simulates a bank application
where user can manipulate with the accounts, but also perform some basic account transactions.

There is no user login (simulates windows (or AD) logged in user), but the user profile is being created on the application start, 
and the username is being in use as a primary key for relations within the Excel database tables.

User can therefor edit user profile details, but only Name and Password are editable.

On first application start, a new database (Excel) will be created and will be in use for data storing.
While excel file exists, all data will be read from the file initially, but also new changes will be stored or updated.

Available options are:
- Account Creation
  -  Domestic (RSD)
  -  Foreign (EUR)
- My Accounts
  - Accounts list
  - Delete an account 
- Transactions
  - Deposit
  - Withdrawal
  - Transactions list
- My Prfofile
  - Update profile
  - Show password
- LogOut



Salute,
Milica
