package com.example.order.processing.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Clothing extends Product {
    private final String size;

    @Builder // на конструкторі, щоб білдер містив і поля Product
    public Clothing(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }
}
