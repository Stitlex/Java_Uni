package com.example.order.processing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Order<T extends Product> {
    private final int id;
    private final T product;
    private final int quantity;

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}
