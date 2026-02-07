import java.util.Scanner;
public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== BANKING SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Credit");
            System.out.println("4. Debit");
            System.out.println("5. Transfer");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Email: ");
                    String email = sc.next();
                    System.out.print("Password: ");
                    String pass = sc.next();
                    Register.register(name, email, pass);
                    break;
                case 2:
                    System.out.print("Email: ");
                    String e = sc.next();
                    System.out.print("Password: ");
                    String p = sc.next();
                    if (login.authenticate(e, p))
                        System.out.println("Login Successful");
                    else
                        System.out.println("Invalid Credentials");
                    break;
                case 3:
                    System.out.print("Account ID: ");
                    CreditService.credit(sc.nextInt(), sc.nextDouble());
                    break;
                case 4:
                    System.out.print("Account ID: ");
                    DebitService.debit(sc.nextInt(), sc.nextDouble());
                    break;
                case 5:
                    System.out.print("From Account ID: ");
                    int from = sc.nextInt();
                    System.out.print("To Account ID: ");
                    int to = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    transfer.Transfer(from, to, amt);
                    break;

                case 6:
                    System.out.print("Account ID: ");
                    check.checkBalance(sc.nextInt());
                    break;

                case 7:
                    System.out.println("Thank You");
                    sc.close(); 
                    System.exit(0);

            }
        }
    }
}
