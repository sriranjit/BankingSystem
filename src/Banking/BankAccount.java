package Banking;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BankAccount{
    Scanner sc = new Scanner(System.in);
    HashMap<Integer, Account> hm = new HashMap<>();
    Random random = new Random();
    int accountNumber;
    public void get(){
        System.out.print("Enter Your Account Number : ");
        accountNumber = sc.nextInt();
        sc.nextLine();
    }
    public void createAccount(){
        int balance = 0;
        System.out.print("Enter your name : ");
        String name = sc.nextLine();
        System.out.print("Enter your Initial Deposit amount : ");
        int amount = sc.nextInt();
        sc.nextLine();
        int accountNumber=random.nextInt(1000,2000);
        balance += amount;
        Account ac = new Account(accountNumber,name,balance);
        hm.put(accountNumber,ac);
        System.out.println("Account Created Successfully. You account number is : "+accountNumber);
    }
    public void deposit(){
        get();
        if(hm.containsKey(accountNumber)){
            Account acc = hm.get(accountNumber);
            System.out.print("Enter Your Deposit Amount : ");
            int amount = sc.nextInt();
            sc.nextLine();
            if(amount>=500){
                acc.setBalance(acc.getBalance()+amount);
                System.out.println("Deposit Successful. New Balance : "+acc.getBalance());
            }
            else{
                System.out.println("Minimum Deposit : 500");
            }
        }
        else{
            System.out.println("Account number does not exist.");
        }
    }
    public void withdraw() {
        get();
        if(hm.containsKey(accountNumber)){
            Account acc = hm.get(accountNumber);
            System.out.print("Enter Your Withdrawal Amount : ");
            int amount = sc.nextInt();
            sc.nextLine();
            if(amount>=100){
                if(acc.getBalance()-amount>=0){
                    acc.setBalance(acc.getBalance()-amount);
                    System.out.println("Withdraw Successful. New Balance : "+acc.getBalance());
                }
                else{
                    System.out.println("Insufficient Balance. Current balance : "+acc.getBalance());
                }
            }
            else{
                System.out.println("Minimum Withdrawal : 100");
            }

        }
        else{
            System.out.println("Account number does not exist.");
        }

    }
    public void checkBalance(){
        get();
        if(hm.containsKey(accountNumber)){
            Account acc = hm.get(accountNumber);
            System.out.println("Account Holder : "+acc.getAccountHolderName());
            System.out.println("Current Balance : "+acc.getBalance());
        }
        else{
            System.out.println("Account number does not exist.");
        }
    }
}