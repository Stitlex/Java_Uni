package org.example;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class CalculatorTest {

    // Базові операції
    @Test
    void add() throws InvalidInputException {
        Assertions.assertEquals(5.0, Calculator.add(2, 3));
    }

    @Test
    void subtract() throws InvalidInputException {
        Assertions.assertEquals(-1.0, Calculator.subtract(2, 3));
    }

    @Test
    void multiply() throws InvalidInputException {
        Assertions.assertEquals(6.0, Calculator.multiply(2, 3));
    }

    @Test
    void divide() throws InvalidInputException {
        Assertions.assertEquals(2.5, Calculator.divide(5, 2));
    }

    // Ділення на нуль
    @Test
    void divideByZeroThrowsArithmeticException() {
        ArithmeticException errorMessage = Assertions.assertThrows(ArithmeticException.class, () -> Calculator.divide(10, 0));
        Assertions.assertEquals("Ділення на нуль неможливе", errorMessage.getMessage());
    }

    // Невірні вхідні дані (рядки замість чисел)
    @Test
    void stringInsteadOfNumberThrowsNumberFormatException() {
        Assertions.assertThrows(NumberFormatException.class, () -> Double.parseDouble("abc"));
    }

    @Test
    void emptyStringThrowsNumberFormatException() {
        Assertions.assertThrows(NumberFormatException.class, () -> Double.parseDouble(""));
    }

    @Test
    void validStringIsParsed() throws InvalidInputException {
        Assertions.assertEquals(12.5, Double.parseDouble("12.5"));
    }

    // Власне виключення
    @Test
    void sqrtOfNegativeThrowsInvalidInputException() {
        Assertions.assertThrows(InvalidInputException.class, () -> Calculator.sqrt(-4));
    }

    @Test
    void nanInputThrowsInvalidInputException() {
        Assertions.assertThrows(InvalidInputException.class, () -> Calculator.add(Double.NaN, 1));
    }

    @Test
    void infiniteInputThrowsInvalidInputException() {
        Assertions.assertThrows(InvalidInputException.class, () -> Calculator.multiply(Double.POSITIVE_INFINITY, 2));
    }
}