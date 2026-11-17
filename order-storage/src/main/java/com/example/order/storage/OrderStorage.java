package com.example.order.storage;

import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderStorage<T extends Product> {
    private final List<Order<? extends T>> orders = new CopyOnWriteArrayList<>();

    public void add(Order<? extends T> order) {
        orders.add(order);
    }

    public List<Order<? extends T>> getAll() {
        return orders;
    }

    public List<Order<? extends T>> filter(Predicate<Order<? extends T>> condition) {
        return orders.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public <U extends T> List<Order<U>> findByType(Class<U> type) {
        return orders.stream()
                .filter(o -> type.isInstance(o.getProduct()))
                .map(o -> (Order<U>) o) // тип уже перевірено через isInstance
                .collect(Collectors.toList());
    }
}
