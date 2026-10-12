package org.example;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {
    // Виведення загального балансу
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    // Виведення кількості транзакцій за певний місяць
    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    // Виведення топ-10 найбільших витрат
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    // Виведення найбільшої та найменшої витрати за період
    public static void printMinMaxExpensesReport(String startDate, String endDate, Transaction[] expense) {
        System.out.println("\n=== Найбільша та найменша витрата за період " + startDate + " — " + endDate + " ===");

        if (expense[0] != null) {
            System.out.printf("Найбільша витрата: %s (%.2f грн) — %s%n",
                    expense[0].getDescription(), expense[0].getAmount(), expense[0].getDate());
        } else {
            System.out.println("Найбільша витрата: немає даних за вказаний період");
        }

        if (expense[1] != null) {
            System.out.printf("Найменша витрата: %s (%.2f грн) — %s%n",
                    expense[1].getDescription(), expense[1].getAmount(), expense[1].getDate());
        } else {
            System.out.println("Найменша витрата: немає даних за вказаний період");
        }
    }

    // Виведення звіту
    public static void printVisualReport(String title, Map<String, Double> data) {
        System.out.println("\n=== " + title + "(1 символ (*) = 1000 грн) ===");
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            int starsCount = (int) (entry.getValue() / 1000);
            String stars = "*".repeat(Math.max(0, starsCount));
            System.out.printf("%-20s : %.2f [%s]\n", entry.getKey(), entry.getValue(), stars);
        }
    }
}
