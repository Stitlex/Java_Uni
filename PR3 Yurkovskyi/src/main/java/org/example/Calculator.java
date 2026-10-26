package org.example;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator {

    public static double add(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a + b;
    }

    public static double subtract(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a - b;
    }

    public static double multiply(double a, double b) throws InvalidInputException {
        validate(a, b);
        return a * b;
    }

    public static double divide(double a, double b) throws InvalidInputException {
        validate(a, b);
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе");
        }
        return a / b;
    }

    public static double sqrt(double a) throws InvalidInputException {
        validate(a);
        if (a < 0) {
            throw new InvalidInputException("Число для квадратного кореня не може бути від'ємним: " + a);
        }
        return Math.sqrt(a);
    }

    private static void validate(double... values) throws InvalidInputException {
        for (double v : values) {
            if (Double.isNaN(v) || Double.isInfinite(v)) {
                throw new InvalidInputException("Некоректне вхідне значення: " + v);
            }
        }
    }
}