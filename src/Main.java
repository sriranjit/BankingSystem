import java.util.*;
import Banking.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount ba = new BankAccount();
        boolean bool = true;
        while(bool){
            System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Check Balance\n5. Exit");
            int opt = sc.nextInt();
            switch(opt){
                case 1:
                    ba.createAccount();
                    break;
                case 2:
                    ba.deposit();
                    break;
                case 3:
                    ba.withdraw();
                    break;
                case 4:
                    ba.checkBalance();
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Do you want to exit?(y/n): ");
                    String confirmation = sc.nextLine().toLowerCase();
                    if(!confirmation.equals("n")){
                        bool = false;
                        break;
                    }
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}