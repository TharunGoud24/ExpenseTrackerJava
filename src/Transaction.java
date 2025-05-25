import java.time.LocalDate;

public class Transaction {
    public enum Type {
        INCOME, EXPENSE
    }

    private Type type;
    private LocalDate date;
    private String category;
    private double amount;
    private String description;

    public Transaction(Type type, LocalDate date, String category, double amount, String description) {
        this.type = type;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String toFileFormat() {
        return type + "," + date + "," + category + "," + amount + "," + description;
    }

    public static Transaction fromFileFormat(String line) {
        String[] parts = line.split(",");
        Type type = Type.valueOf(parts[0].toUpperCase());
        LocalDate date = LocalDate.parse(parts[1]);
        String category = parts[2];
        double amount = Double.parseDouble(parts[3]);
        String description = parts[4];
        return new Transaction(type, date, category, amount, description);
    }
}
