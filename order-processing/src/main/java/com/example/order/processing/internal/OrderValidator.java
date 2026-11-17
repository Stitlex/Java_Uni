package com.example.order.processing.internal;

import com.example.order.processing.exception.InvalidOrderException;
import com.example.order.processing.model.Order;
import com.example.order.processing.model.Product;

public class OrderValidator {
    public static void validate(Order<? extends Product> order) throws InvalidOrderException {
        if (order.getProduct() == null) {
            throw new InvalidOrderException("Замовлення " + order.getId() + ": товар відсутній");
        }
        if (order.getQuantity() <= 0) {
            throw new InvalidOrderException("Замовлення " + order.getId() + ": кількість має бути більшою за 0");
        }
    }
}
