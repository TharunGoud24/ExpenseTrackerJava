import java.io.File;
import java.io.FileWriter;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void loadFromFile(String filename) {
        try {
            String fullPath = "data/" + filename + ".txt";
            File file = new File(fullPath);

            if (!file.exists()) {
                System.out.println("File not found: " + fullPath);
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Transaction t = Transaction.fromFileFormat(line);
                transactions.add(t);
            }

            scanner.close();
            System.out.println("Loaded transactions from " + fullPath);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
    
    public void saveToFile(String filename) {
        try {
            String fullPath = "data/" + filename + ".txt";
            FileWriter writer = new FileWriter(fullPath);
            for (Transaction t : transactions) {
                writer.write(t.toFileFormat() + "\n");
            }
            writer.close();
            System.out.println("Transactions saved to " + fullPath);
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void showMonthlySummary(int year, int month) {
        double incomeTotal = 0;
        double expenseTotal = 0;

        System.out.println("\n--- Summary for " + year + "-" + (month < 10 ? "0" + month : month) + " ---");

        for (Transaction t : transactions) {
            YearMonth ym = YearMonth.from(t.getDate());
            if (ym.getYear() == year && ym.getMonthValue() == month) {
                if (t.getType() == Transaction.Type.INCOME) {
                    incomeTotal += t.getAmount();
                } else {
                    expenseTotal += t.getAmount();
                }
                System.out.println(t.getDate() + " | " + t.getType() + " | " + t.getCategory() + " | " + t.getAmount()
                        + " | " + t.getDescription());
            }
        }

        System.out.println("Total Income: " + incomeTotal);
        System.out.println("Total Expenses: " + expenseTotal);
        System.out.println("Net Balance: " + (incomeTotal - expenseTotal));
    }
}
