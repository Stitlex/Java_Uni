package com.example.order.threads;

import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class OrderExecutor {
    private final ExecutorService pool;
    private final AtomicInteger processed = new AtomicInteger();

    public OrderExecutor(int threads) {
        pool = Executors.newFixedThreadPool(threads);
    }

    public <O extends Order<? extends Product>> void processAll(List<O> orders, Consumer<O> handler) {
        List<Future<?>> tasks = new ArrayList<>();
        for (O order : orders) {
            tasks.add(pool.submit(() -> {
                handler.accept(order);
                processed.incrementAndGet();
            }));
        }
        for (Future<?> task : tasks) {
            try {
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Помилка потоку: " + e.getMessage());
            }
        }
    }

    public int getProcessed() {
        return processed.get();
    }

    public void shutdown() {
        pool.shutdown();
    }
}
