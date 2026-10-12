package org.example;

import java.util.List;
import java.util.Map;
import static org.example.TransactionAnalyzer.*;
import static org.example.TransactionReportGenerator.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "pr2.csv";

        // Виклик читання даних
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        // Загальний баланс
        double totalBalance = calculateTotalBalance(transactions);
        printBalanceReport(totalBalance);

        // Кількість транзакцій за місяць
        String monthYear = "01-2024";
        int transactionsCount = countTransactionsByMonth(transactions, monthYear);
        printTransactionsCountByMonth(monthYear, transactionsCount);

        // Топ-10 витрат
        List<Transaction> topExpenses = findTopExpenses(transactions);
        printTopExpensesReport(topExpenses);

        // Найбільша та найменша витрата за період
        String startDate = "01-01-2024";
        String endDate = "31-03-2024";
        Transaction[] minMaxExpenses = findMinMaxExpensesByPeriod(transactions, startDate, endDate);
        printMinMaxExpensesReport(startDate, endDate, minMaxExpenses);

        // Візуальні звіти із зірочками (*)
        // Звіт по категоріях
        Map<String, Double> expensesByCategory = getTotalExpensesMonth(transactions);
        printVisualReport("Витрати по категоріях", expensesByCategory);

        // Звіт по місяцях
        Map<String, Double> expensesByMonth = getTotalExpensesCategory(transactions);
        printVisualReport("Витрати по місяцях", expensesByMonth);
    }
}

