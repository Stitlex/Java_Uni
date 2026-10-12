package org.example;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Метод для підрахунку транзакцій за конкретний місяць і рік
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            if (date.format(DateTimeFormatter.ofPattern("MM-yyyy")).equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    // Визначення найбільших топ-10 витрат
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    // Визначення найбільшої та найменшої витрати за вказаний період
    public static Transaction[] findMinMaxExpensesByPeriod(List<Transaction> transactions, String startDateStr, String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

        List<Transaction> expensesInPeriod = transactions.stream().filter(t -> t.getAmount() < 0).filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
                    return !date.isBefore(startDate) && !date.isAfter(endDate);
                }).toList();

        if (expensesInPeriod.isEmpty()) {
            return new Transaction[]{null, null};
        }

        // Найбільша витрата
        Transaction maxExpense = expensesInPeriod.stream().min(Comparator.comparing(Transaction::getAmount)).orElse(null);
        // Найменша витрата
        Transaction minExpense = expensesInPeriod.stream().max(Comparator.comparing(Transaction::getAmount)).orElse(null);

        return new Transaction[]{maxExpense, minExpense};
    }

    // Сумарні витрати по категоріях
    public static Map<String, Double> getTotalExpensesCategory(List<Transaction> transactions) {
        return transactions.stream().filter(t -> t.getAmount() < 0).collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }

    // Сумарні витрати по місяцях
    public static Map<String, Double> getTotalExpensesMonth(List<Transaction> transactions) {
        return transactions.stream().filter(t -> t.getAmount() < 0).collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), dateFormatter).format(DateTimeFormatter.ofPattern("MM-yyyy")),
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
    }
}

