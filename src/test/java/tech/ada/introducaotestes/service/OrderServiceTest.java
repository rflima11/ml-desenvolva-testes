package tech.ada.introducaotestes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.ada.introducaotestes.domain.OrderItem;

import java.util.Arrays;
import java.util.List;

public class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void shouldCalculateTotalPriceWithValidOrder() {
        //Cenário
        List<OrderItem> items = Arrays.asList(
         new OrderItem("Celular", 500.00, 1)
        );
        double discount = 10;
        double deliveryFee = 20;

        //Ação
        double total = orderService.calculateTotal(items, discount, deliveryFee);

        //Validação
        Assertions.assertEquals(470, total);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithInvalidOrderItems() {
        //Cenário
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Celular", 500.00, 0)
        );
        double discount = 10;
        double deliveryFee = 20;

        //Execução && Validação
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderService.calculateTotal(items, discount, deliveryFee));
    }

}
