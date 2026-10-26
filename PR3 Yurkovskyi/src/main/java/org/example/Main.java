package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine());

            System.out.print("Оберіть операцію (+, -, *, /, sqrt — корінь з першого числа): ");
            String operation = scanner.nextLine().trim();

            if (operation.isEmpty()) {
                throw new InvalidInputException("Операцію не введено");
            }
            if (!operation.matches("[+\\-*/]|sqrt")) {
                throw new InvalidInputException("Невідома операція: " + operation);
            }

            double b = 0;
            if (!operation.equals("sqrt")) {
                System.out.print("Введіть друге число: ");
                b = Double.parseDouble(scanner.nextLine());
            }

            double result = calculate(a, b, operation);
            System.out.println("Результат: " + result);
        } catch (NumberFormatException errorMessage) {
            System.out.println("Помилка введення: потрібно ввести число");
        } catch (ArithmeticException errorMessage) {
            System.out.println("Арифметична помилка: " + errorMessage.getMessage());
        } catch (InvalidInputException errorMessage) {
            System.out.println("Помилка введення: " + errorMessage.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено.");
            scanner.close();
        }
    }

    static double calculate(double a, double b, String operation)
            throws InvalidInputException {
        return switch (operation) {
            case "+" -> Calculator.add(a, b);
            case "-" -> Calculator.subtract(a, b);
            case "*" -> Calculator.multiply(a, b);
            case "/" -> Calculator.divide(a, b);
            case "sqrt" -> Calculator.sqrt(a);
            default -> throw new InvalidInputException("Невідома операція: " + operation);
        };
    }
}