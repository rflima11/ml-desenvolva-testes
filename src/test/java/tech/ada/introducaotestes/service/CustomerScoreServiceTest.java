package tech.ada.introducaotestes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.ada.introducaotestes.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerScoreServiceTest {

    private CustomerScoreService service;
    private Customer customer;

    @BeforeEach
    void setUp() {
        service = new CustomerScoreService();
        customer = new Customer("Juninho");
    }

    // --- Casos de uso principais ---

    @DisplayName("Should add points successfully to customer")
    @Test
    void shouldAddPointsToCustomerCorrectly() {
        // Arrange
        final int pointsToAdd = 100;
        final int initialScore = customer.getScore();

        // Act
        service.addPoints(customer, pointsToAdd);

        // Assert
        assertEquals(initialScore + pointsToAdd, customer.getScore(),
                "The customer score should be increased by the added points.");    }

    @DisplayName("Should remove points from customer successfully")
    @Test
    void shouldRemovePointsFromCustomerCorrectly() {
        // Arrange
        customer.setScore(150);
        final int pointsToRemove = 50;

        // Act
        service.removePoints(customer, pointsToRemove);

        // Assert
        assertEquals(100, customer.getScore());
    }

    @DisplayName("Should apply 50-point loyalty bonus for customer with 3 years")
    @Test
    void shouldApplyBonusForCustomerWithThreeYears() {
        // Arrange
        final int years = 3;

        // Act
        service.applyLoyaltyBonus(customer, years);

        // Assert
        assertEquals(50, customer.getScore());
    }

    @DisplayName("Should apply 200-point loyalty bonus for customer with 7 years")
    @Test
    void shouldApplyBonusForCustomerWithSevenYears() {
        // Arrange
        final int years = 7;

        // Act
        service.applyLoyaltyBonus(customer, years);

        // Assert
        assertEquals(200, customer.getScore());
    }

    // --- Casos de erro / exceção ---

    @DisplayName("Should throw exception when adding zero points")
    @Test
    void shouldThrowExceptionWhenAddingZeroPoints() {
        // Arrange
        int invalidPoints = 0;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.addPoints(customer, invalidPoints));
    }

    @DisplayName("Should throw exception when removing more points than customer has")
    @Test
    void shouldThrowExceptionWhenRemovingMorePointsThanCustomerHas() {
        // Arrange
        customer.setScore(30);

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.removePoints(customer, 50));
    }

    @DisplayName("Should throw exception when years as customer is negative")
    @Test
    void shouldThrowExceptionWhenYearsAsCustomerIsNegative() {
        // Arrange
        int invalidYears = -1;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.applyLoyaltyBonus(customer, invalidYears));
    }

    @DisplayName("Should not allow negative score via setter")
    @Test
    void shouldNotAllowNegativeScoreViaSetter() {
        // Arrange
        int negativeScore = -10;

        // Act + Assert
        assertThrows(IllegalArgumentException.class,
                () -> customer.setScore(negativeScore));
    }
}
