package Banking;

public class Account{
    final private int accountNumber;
    private int balance;
    final private String accountHolderName;
    public Account(int accountNumber, String accountHolderName, int initialDeposit){
        this.accountHolderName=accountHolderName;
        this.accountNumber=accountNumber;
        this.balance=initialDeposit;
    }
    public int getAccountNumber(){
        return accountNumber;
    }
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public int getBalance(){
        return balance;
    }
    public void setBalance(int balance){
        this.balance=balance;
    }
}