package tech.ada.introducaotestes.service;

import tech.ada.introducaotestes.domain.Customer;

public class CustomerScoreService {

    public void addPoints(Customer customer, int points) {
        if (points <= 0) {
            throw new IllegalArgumentException("Points to add must be greater than zero");
        }
        customer.setScore(customer.getScore() + points);
    }

    public void removePoints(Customer customer, int points) {
        if (points <= 0) {
            throw new IllegalArgumentException("Points to remove must be greater than zero");
        }
        if (points > customer.getScore()) {
            throw new IllegalArgumentException("Cannot remove more points than the current score");
        }
        customer.setScore(customer.getScore() - points);
    }

    public void applyLoyaltyBonus(Customer customer, int yearsAsCustomer) {
        if (yearsAsCustomer < 0) {
            throw new IllegalArgumentException("Years as customer cannot be negative");
        }

        int bonus = 0;
        if (yearsAsCustomer >= 1 && yearsAsCustomer <= 3) bonus = 50;
        else if (yearsAsCustomer >= 4 && yearsAsCustomer <= 6) bonus = 100;
        else if (yearsAsCustomer > 6) bonus = 200;

        customer.setScore(customer.getScore() + bonus);
    }
}
