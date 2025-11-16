import java.util.*;
import java.time.LocalDate;

public class ExpenseTracker 
{
    private static List<Map<String, Object>> expenses = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static void addExpense() 
    {
        System.out.print("Enter category (e.g. Food, Travel): ");
        String category = sc.nextLine().trim();
        if (category.isEmpty()) 
        {
            System.out.println("Category can't be empty.");
            return;
        }

        System.out.print("Enter amount: ");
        String amtStr = sc.nextLine().trim();
        double amount;
        try 
        {
            amount = Double.parseDouble(amtStr);
            if (amount <= 0) 
            {
                System.out.println("Amount should be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        Map<String, Object> entry = new HashMap<>();
        entry.put("category", category);
        entry.put("amount", amount);
        entry.put("date", LocalDate.now().toString());
        expenses.add(entry);
        System.out.println("Expense added.");
       }

    private static void showAll() 
    {
        if (expenses.isEmpty()) 
        {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\nAll Expenses:");
        int i = 1;
        for (Map<String, Object> e : expenses) 
        {
            System.out.printf("%d) %s | %s | %.2f%n",
                    i++,
                    e.get("date"),
                    e.get("category"),
                    (double) e.get("amount"));
        }
    }

    private static void showSummary() 
    {
        double total = 0;
        for (Map<String, Object> e : expenses)
        {
            total += (double) e.get("amount");
        }
        System.out.printf("Total Spent: %.2f%n", total);
    }

    private static void showCategorySummary() {
        if (expenses.isEmpty()) 
        {
            System.out.println("No expenses recorded.");
            return;
        }
        Map<String, Double> catSum = new HashMap<>();
        for (Map<String, Object> e : expenses) 
         {
            String cat = (String) e.get("category");
            double amt = (double) e.get("amount");
            catSum.put(cat, catSum.getOrDefault(cat, 0.0) + amt);
        }
        System.out.println("\nCategory-wise Summary:");
        for (Map.Entry<String, Double> kv : catSum.entrySet()) 
         {
            System.out.printf("%s : %.2f%n", kv.getKey(), kv.getValue());
        }
    }

    public static void main(String[] args) 
    {
        System.out.println("==== Expense Tracker ====");
        while (true)
         {
            System.out.println("\nMenu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Show All Expenses");
            System.out.println("3. Show Total Spent");
            System.out.println("4. Show Category-wise Summary");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            switch (choice)
             {
                case "1": addExpense(); break;
                case "2": showAll(); break;
                case "3": showSummary(); break;
                case "4": showCategorySummary(); break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
