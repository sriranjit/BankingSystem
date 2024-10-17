**Problem: Simple Banking Application**

Write a program to simulate basic banking operations like creating an account, checking the balance, depositing money, and withdrawing money.

**Requirements:**\
**Account Creation:** Prompt the user to enter a name and an initial deposit amount.
Assign a unique account number to each new account.
Operations:

**Deposit:** Allow the user to deposit money into their account.

**Withdraw:** Allow the user to withdraw money, but the withdrawal should not exceed the balance.

**Check Balance:** Display the current balance of the account.

**Account Management:** Use a menu to select the operation (1. Create Account, 2. Deposit, 3. Withdraw, 4. Check Balance, 5. Exit).

**Example:**

**Menu:**
1. Create Account
2. Deposit
3. Withdraw
4. Check Balance
5. Exit


**Sample Run:**

1. Create Account \
   Enter name: John \
   Enter initial deposit: 5000\
   Account created successfully. Your Account Number is: 1001

2. Deposit\
   Enter account number: 1001\
   Enter deposit amount: 2000\
   Deposit successful. New balance: 7000

3. Withdraw
   Enter account number: 1001\
   Enter withdrawal amount: 3000\
   Withdrawal successful. New balance: 4000

4. Check Balance
   Enter account number: 1001\
   Account Holder Name : John\
   Current balance: 4000

5. Exit


**Suggested Classes and Methods:**

**1. BankAccount Class:**\
**Fields:** accountNumber, accountHolderName, balance\
**Methods:** deposit(), withdraw(), getBalance(), createAccount()\
**2.Main Application Class:** \
main() method to display the menu and handle user input.