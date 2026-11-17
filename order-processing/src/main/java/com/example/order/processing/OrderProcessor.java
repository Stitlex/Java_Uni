package com.example.order.processing;

import com.example.order.processing.exception.InvalidOrderException;
import com.example.order.processing.internal.OrderValidator;
import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;

public class OrderProcessor<T extends Product> {
    public void process(Order<T> order) throws InvalidOrderException {
        OrderValidator.validate(order);
        print(order);
    }

    public static void processOrder(Order<? extends Product> order) {
        try {
            OrderValidator.validate(order);
            print(order);
        } catch (InvalidOrderException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] ПОМИЛКА: " + e.getMessage());
        }
    }

    private static void print(Order<? extends Product> order) {
        System.out.printf("[%s] Замовлення %d: %s x%d = %.2f%n",
                Thread.currentThread().getName(), order.getId(),
                order.getProduct().getName(), order.getQuantity(), order.getTotal());
    }
}
