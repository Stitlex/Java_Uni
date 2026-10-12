package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {
    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("02-01-2023", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("03-01-2023", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testReadResourceCSV() {
        List<Transaction> transactions = TransactionCSVReader.readTransactions("pr2.csv");

        Assertions.assertNotNull(transactions, "Список транзакцій не має бути null");
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій має містити дані з файлу");
    }

    @Test
    public void testFindTopExpenses() {
        Transaction transaction1 = new Transaction("01-12-2023", -100.0, "Їжа");
        Transaction transaction2 = new Transaction("02-12-2023", -1500.0, "Техніка");
        Transaction transaction3 = new Transaction("03-12-2023", 500.0, "Зарплата");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        Assertions.assertEquals(2, topExpenses.size());
        Assertions.assertEquals(-1500.0, topExpenses.get(0).getAmount(), "Найбільша витрата має бути першою");
    }
}