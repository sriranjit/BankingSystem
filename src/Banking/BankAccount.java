package Banking;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccount{
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    int accountNumber;
    final private static Logger logger = Logger.getLogger(BankAccount.class.getName());

    public Connection getConnection()throws SQLException{
        String url = "jdbc:mysql://localhost:3306/banking_system";
        String user = "root";
        String password = "2618";

        return DriverManager.getConnection(url,user,password);
    }
    public void logTransaction(String transaction){
        try{
            FileWriter writer = new FileWriter("bank_transaction.txt",true);
            writer.write(new Date()+" - "+transaction+"\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }
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

        try(Connection conn =getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (account_number, name, balance) VALUES( ?, ?, ?)")){
            stmt.setInt(1, accountNumber);
            stmt.setString(2,name);
            stmt.setInt(3,balance);
            stmt.executeUpdate();
            System.out.println("Account Created Successfully. You account number is : "+accountNumber);
            logTransaction("Created Account : "+accountNumber+", Account Holder Name : "+name+", Initial Deposit : "+amount);
        }
        catch(SQLException e){
            logger.log(Level.SEVERE,"Error occurred while creating account.",e);
        }
    }
    public void deposit(){
        get();
        try(Connection conn = getConnection()){
            String checkQuery = "SELECT * FROM users WHERE account_number = ?";
            String balQuery = "UPDATE users SET balance = ? WHERE account_number = ?";
            String depQuery = "INSERT INTO transactions (account_number, transaction_type, amount) VALUES (?, ?, ?)";

            try(PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            PreparedStatement balStmt = conn.prepareStatement(balQuery);
            PreparedStatement depStmt = conn.prepareStatement(depQuery)){
                checkStmt.setInt(1, accountNumber);
                ResultSet rs = checkStmt.executeQuery();

                if(rs.next()){
                    System.out.print("Enter Your Deposit Amount : ");
                    int amount = sc.nextInt();
                    sc.nextLine();
                    int balance = rs.getInt("balance");
                    if(amount>=500){
                        int new_bal = balance+amount;
                        balStmt.setInt(1,new_bal);
                        balStmt.setInt(2,accountNumber);
                        balStmt.executeUpdate();

                        depStmt.setInt(1,accountNumber);
                        depStmt.setString(2, "DEPOSIT");
                        depStmt.setInt(3, amount);
                        depStmt.executeUpdate();
                        System.out.println("Deposit Successful. New Balance : "+new_bal);
                        logTransaction("Deposited to Account :"+accountNumber+", Amount : "+amount+", Account Balance : "+new_bal);
                    }
                    else{
                        System.out.println("Minimum Deposit : 500");
                    }
                }
                else {
                    System.out.println("Invalid Account Number.");
                }
            }
        }
        catch(SQLException e){
            logger.log(Level.SEVERE,"Error occurred while depositing amount.",e);
        }
    }
    public void withdraw() {
        get();
        try(Connection conn = getConnection()){
            String checkQuery = "SELECT * FROM users WHERE account_number = ?";
            String balQuery = "UPDATE users SET balance = ? WHERE account_number = ?";
            String wdQuery = "INSERT INTO transactions (account_number, transaction_type, amount) VALUES (?, ?, ?)";
            try(PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            PreparedStatement balStmt = conn.prepareStatement(balQuery);
            PreparedStatement wdStmt = conn.prepareStatement(wdQuery)){
                checkStmt.setInt(1,accountNumber);
                ResultSet rs = checkStmt.executeQuery();

                if(rs.next()){
                    System.out.print("Enter Your Withdrawal Amount : ");
                    int amount = sc.nextInt();
                    sc.nextLine();
                    int balance = rs.getInt("balance");
                    if(amount>=100){
                        if(balance>=amount){
                            int new_bal = balance-amount;
                            balStmt.setInt(1,new_bal);
                            balStmt.setInt(2,accountNumber);
                            balStmt.executeUpdate();
                            wdStmt.setInt(1,accountNumber);
                            wdStmt.setString(2,"WITHDRAW");
                            wdStmt.setInt(3,amount);
                            wdStmt.executeUpdate();
                            System.out.println("Withdraw Successful. New Balance : "+new_bal);
                            logTransaction("Withdrawn to Account :"+accountNumber+", Amount : "+amount+", Account Balance : "+new_bal);
                        }
                        else{
                            System.out.println("Insufficient Balance. Current Balance : "+balance);
                        }
                    }
                    else{
                        System.out.println("Minimum Withdrawal : 100");
                    }
                }
                else {
                    System.out.println("Invalid Account Number.");
                }
            }

        }
        catch(SQLException e){
            logger.log(Level.SEVERE,"Error occurred while withdrawing amount.",e);
        }
    }
    public void checkBalance(){
        get();
        try(Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            String checkQuery = "SELECT * FROM users WHERE account_number = "+accountNumber;
            ResultSet rs = stmt.executeQuery(checkQuery);

            if(rs.next()){
                System.out.println("Account Number : "+accountNumber);
                System.out.println("Account Holder : "+rs.getString("name"));
                System.out.println("Current Balance : "+rs.getInt("balance"));
            }
            else {
                System.out.println("Invalid Account Number.");
            }
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,"Error occurred while checking balance.");
        }
    }
}