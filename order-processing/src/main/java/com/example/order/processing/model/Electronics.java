package com.example.order.processing.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Electronics extends Product {
    private final String brand;

    @Builder // на конструкторі, щоб білдер містив і поля Product
    public Electronics(String name, double price, String brand) {
        super(name, price);
        this.brand = brand;
    }
}
