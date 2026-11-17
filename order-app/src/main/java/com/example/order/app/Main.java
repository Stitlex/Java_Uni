package com.example.order.app;

import com.example.order.processing.OrderProcessor;
import com.example.order.processing.ProductFactory;
import com.example.order.processing.exception.InvalidOrderException;
import com.example.order.processing.model.Clothing;
import com.example.order.processing.model.Electronics;
import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;
import com.example.order.storage.OrderStorage;
import com.example.order.threads.OrderExecutor;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OrderStorage<Product> storage = new OrderStorage<>();

        for (int i = -1; i < 3; i++) {
            storage.add(ProductFactory.order(ProductFactory.electronics()));
            storage.add(ProductFactory.order(ProductFactory.clothing()));
        }
        storage.add(new Order<>(0, ProductFactory.electronics(), 0)); // невалідне замовлення

        System.out.println("=== Усі замовлення ===");
        storage.getAll().forEach(System.out::println);

        List<Order<? extends Product>> expensive = storage.filter(o -> o.getTotal() > 1000);
        System.out.println("\nЗамовлень дорожче 1000: " + expensive.size());

        List<Order<Electronics>> electronics = storage.findByType(Electronics.class);
        List<Order<Clothing>> clothing = storage.findByType(Clothing.class);

        OrderExecutor executor = new OrderExecutor(3);
        OrderProcessor<Electronics> processor = new OrderProcessor<>();

        System.out.println("\n=== Електроніка (лямбда) ===");
        executor.processAll(electronics, order -> {
            try {
                processor.process(order);
            } catch (InvalidOrderException e) {
                System.out.println("[" + Thread.currentThread().getName() + "] ПОМИЛКА: " + e.getMessage());
            }
        });

        System.out.println("\n=== Одяг (посилання на метод) ===");
        executor.processAll(clothing, OrderProcessor::processOrder);

        executor.shutdown();
        System.out.println("\nОброблено потоками: " + executor.getProcessed());

        System.out.println("\n=== Окремий потік ===");
        Runnable task = () -> System.out.println("Processing order in thread " + Thread.currentThread().getName());
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
    }
}
