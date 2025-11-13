package tech.ada.introducaotestes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tech.ada.introducaotestes.domain.Customer;


public class CustomerScoreServiceTest {

    private CustomerScoreService service = new CustomerScoreService();
    private Customer customer;


    // Use @BeforeEach to set up common objects before each test
    @BeforeEach
    void setUp() {
        service = new CustomerScoreService();
        customer = new Customer("John Doe");
    }

    // --- Scenario 1: Successful Addition (The "correctly" part) ---
    @Test
    @DisplayName("Should successfully add a positive number of points to the customer score")
    void shouldAddPositivePointsToCustomerCorrectly() {
        // Arrange (Setup is done in @BeforeEach, but defining points here)
        final int pointsToAdd = 100;
        final int initialScore = customer.getScore();

        // Act
        service.addPoints(customer, pointsToAdd);

        // Assert
        // Check if the final score is the initial score plus the added points
        assertEquals(initialScore + pointsToAdd, customer.getScore(),
            "The customer score should be increased by the added points.");
    }

    // --- Scenario 2: Exception Handling (The "negative" part) ---
    @Test
    @DisplayName("Should throw IllegalArgumentException when attempting to add negative points")
    void shouldThrowExceptionWhenAddingNegativePoints() {
        // Arrange (Customer is ready from @BeforeEach)
        final int pointsToAdd = -50;

        // Act & Assert
        // Use assertThrows to verify the correct exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            service.addPoints(customer, pointsToAdd);
        }, "Points to add must be greater than zero");

        // Optional: Assert that the customer's score was NOT changed
        assertEquals(0, customer.getScore(), "The score should remain unchanged after an exception.");
    }

    @Test
    @DisplayName("Should remove points from customer correctly")
    void shouldRemovePointsFromCustomerCorrectly() {
        final int initialPoints = 200;
        service.addPoints(customer, initialPoints);
        final int pointsToRemove = 50;

        service.removePoints(customer, pointsToRemove);
        assertEquals(initialPoints - pointsToRemove, customer.getScore(),
            "The customer score should be decreased by the removed points.");

    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when attempting to remove negative points")
    void shouldThrowExceptionWhenRemovingNegativePoints() {
        final int initialPoints = 200;
        service.addPoints(customer, initialPoints);
        final int pointsToRemove = -30;

        assertThrows(IllegalArgumentException.class, () -> {
            service.removePoints(customer, pointsToRemove);
        }, "Points to remove must be greater than zero");

        assertEquals(initialPoints, customer.getScore(), "The score should remain unchanged after an exception.");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when attempting to remove more points than customer has")
    void shouldThrowExceptionWhenRemovingTooManyPoints() {
        final int initialPoints = 100;
        service.addPoints(customer, initialPoints);
        final int pointsToRemove = 150;

        assertThrows(IllegalArgumentException.class, () -> {
            service.removePoints(customer, pointsToRemove);
        }, "Cannot remove more points than the current score");

        assertEquals(initialPoints, customer.getScore(), "The score should remain unchanged after an exception.");
    }

    @Test
    @DisplayName("Should apply loyalty bonus correctly based on years as customer")
    void shouldApplyBonusForCustomerWithThreeYears() {
        final int yearsAsCustomer = 3;
        final int expectedBonus = 50;

        service.applyLoyaltyBonus(customer, yearsAsCustomer);
        assertEquals(expectedBonus, customer.getScore(),
            "The customer score should reflect the loyalty bonus for 3 years.");
    }

    @Test
    @DisplayName("Should apply loyalty bonus correctly for 5 years as customer")
    void shouldApplyBonusForCustomerWithFiveYears() {
        final int yearsAsCustomer = 5;
        final int expectedBonus = 100;

        service.applyLoyaltyBonus(customer, yearsAsCustomer);
        assertEquals(expectedBonus, customer.getScore(),
            "The customer score should reflect the loyalty bonus for 5 years.");
    }

    @Test
    @DisplayName("Should apply loyalty bonus correctly for 7 years as customer")
    void shouldApplyBonusForCustomerWithSevenYears() {
        final int yearsAsCustomer = 7;
        final int expectedBonus = 200;

        service.applyLoyaltyBonus(customer, yearsAsCustomer);
        assertEquals(expectedBonus, customer.getScore(),
            "The customer score should reflect the loyalty bonus for 7 years.");
    }



    @Test
    @DisplayName("Should throw IllegalArgumentException when years as customer is negative")
    void shouldThrowExceptionWhenYearsAsCustomerIsNegative() {
        final int yearsAsCustomer = -2;

        assertThrows(IllegalArgumentException.class, () -> {
            service.applyLoyaltyBonus(customer, yearsAsCustomer);
        }, "Years as customer cannot be negative");

        assertEquals(0, customer.getScore(), "The score should remain unchanged after an exception.");
    }
}
