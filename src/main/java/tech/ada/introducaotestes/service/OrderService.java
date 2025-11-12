package tech.ada.introducaotestes.service;

import tech.ada.introducaotestes.domain.OrderItem;

import java.util.List;

public class OrderService {

    public double calculateTotal(List<OrderItem> items, double discountPercent, double deliveryFee) {
        validateOrder(items, discountPercent, deliveryFee);

        double subtotal = calculateSubtotal(items);
        double discountValue = subtotal * (discountPercent / 100.0);
        double total = subtotal - discountValue + deliveryFee;

        return round(total);
    }

    private void validateOrder(List<OrderItem> items, double discountPercent, double deliveryFee) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        if (discountPercent < 0 || discountPercent > 50) {
            throw new IllegalArgumentException("Discount must be between 0 and 50%");
        }

        if (deliveryFee < 0) {
            throw new IllegalArgumentException("Delivery fee cannot be negative");
        }

        for (OrderItem item : items) {
            if (item.getPrice() < 0 || item.getQuantity() <= 0) {
                throw new IllegalArgumentException("Invalid item price or quantity");
            }
        }
    }

    private double calculateSubtotal(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
