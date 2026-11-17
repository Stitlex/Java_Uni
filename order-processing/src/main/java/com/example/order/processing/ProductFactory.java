package com.example.order.processing;

import com.example.order.processing.model.Clothing;
import com.example.order.processing.model.Electronics;
import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;
import com.github.javafaker.Faker;

public class ProductFactory {
    private static final Faker faker = new Faker();
    private static int nextId = 1;

    public static Electronics electronics() {
        return Electronics.builder()
                .name(faker.commerce().productName())
                .price(faker.number().randomDouble(2, 100, 5000))
                .brand(faker.company().name())
                .build();
    }

    public static Clothing clothing() {
        return Clothing.builder()
                .name(faker.commerce().productName())
                .price(faker.number().randomDouble(2, 10, 500))
                .size(faker.options().option("S", "M", "L", "XL"))
                .build();
    }

    public static <T extends Product> Order<T> order(T product) {
        return new Order<>(nextId++, product, faker.number().numberBetween(1, 5));
    }
}
