import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();

        while (true) {
            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Load Transactions from File");
            System.out.println("4. Save Transactions to File");
            System.out.println("5. View Monthly Summary");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine(); 

            if (option == 1 || option == 2) {
                Transaction.Type type = (option == 1) ? Transaction.Type.INCOME : Transaction.Type.EXPENSE;

                System.out.print("Enter date (yyyy-mm-dd): ");
                LocalDate date = LocalDate.parse(sc.nextLine());

                System.out.print("Enter category (e.g. Salary, Food, Rent, Travel): ");
                String category = sc.nextLine();

                System.out.print("Enter amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();

                System.out.print("Enter description: ");
                String description = sc.nextLine();

                Transaction t = new Transaction(type, date, category, amount, description);
                manager.addTransaction(t);
                System.out.println("Transaction added successfully.");
            }

            else if (option == 3) {
                System.out.print("Enter filename to load from: ");
                String file = sc.nextLine();
                manager.loadFromFile(file);
            }

            else if (option == 4) {
                System.out.print("Enter filename to save to: ");
                String file = sc.nextLine();
                manager.saveToFile(file);
            }

            else if (option == 5) {
                System.out.print("Enter year (e.g. 2025): ");
                int year = sc.nextInt();
                System.out.print("Enter month (1-12): ");
                int month = sc.nextInt();
                manager.showMonthlySummary(year, month);
            }

            else if (option == 6) {
                System.out.println("Exiting... Thank you!");
                break;
            }

            else {
                System.out.println("Invalid option, please try again.");
            }
        }
        sc.close();
    }
}
