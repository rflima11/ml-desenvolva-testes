package tech.ada.introducaotestes.service;

import org.junit.jupiter.api.Test;

public class CustomerScoreServiceTest {

    private final CustomerScoreService service = new CustomerScoreService();

    // --- Casos de uso principais ---

    @Test
    void shouldAddPointsToCustomerCorrectly() {
        // TODO: implementar teste
    }

    @Test
    void shouldRemovePointsFromCustomerCorrectly() {
        // TODO: implementar teste
    }

    @Test
    void shouldApplyBonusForCustomerWithThreeYears() {
        // TODO: implementar teste
    }

    @Test
    void shouldApplyBonusForCustomerWithSevenYears() {
        // TODO: implementar teste
    }

    // --- Casos de erro / exceção ---

    @Test
    void shouldThrowExceptionWhenAddingZeroPoints() {
        // TODO: implementar teste
    }

    @Test
    void shouldThrowExceptionWhenRemovingMorePointsThanCustomerHas() {
        // TODO: implementar teste
    }

    @Test
    void shouldThrowExceptionWhenYearsAsCustomerIsNegative() {
        // TODO: implementar teste
    }

    @Test
    void shouldNotAllowNegativeScoreViaSetter() {
        // TODO: implementar teste
    }
}
