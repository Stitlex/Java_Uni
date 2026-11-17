package com.example.order.processing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public abstract class Product {
    private final String name;
    private final double price;
}
