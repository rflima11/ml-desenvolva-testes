package tech.ada.introducaotestes.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.ada.introducaotestes.domain.Employee;
import tech.ada.introducaotestes.utils.EmployeeBuilder;

import java.time.LocalDate;

class VacationCalculatorTest {

    private VacationCalculator vacationCalculator;
    private EmployeeBuilder employeeBuilder = new EmployeeBuilder();

    @BeforeEach
    public void setUp() {
        vacationCalculator = new VacationCalculator();
    }

    @DisplayName("Deve retornar 30 dias quando o empregado tem pelo menos um ano de firma")
    @Test
    void shouldReturn30DaysForEmployeeWithMoreThanOneYear() {
        //Cenário
        LocalDate admissionDate = LocalDate.now().minusYears(1);
        Employee employee = employeeBuilder
                .withAdmissionDate(admissionDate)
                .withName("Gilberto")
                .build();

        //Ação
        int days = vacationCalculator.calculateVacationDays(employee);

        //Validação
        Assertions.assertEquals(30, days);
    }

    @DisplayName("Deve retornar quantidade proporcional de dias quando o empregado tem menos de um ano de firma")
    @Test
    void shouldReturnProportionalVacationDaysForLessThanOneYear() {
        //Cenário
        LocalDate admissionDate = LocalDate.now().minusMonths(6);
        Employee employee = employeeBuilder.withAdmissionDate(admissionDate).build();

        //Ação
        int days = vacationCalculator.calculateVacationDays(employee);

        //Validação
        Assertions.assertEquals(15, days);
    }

    @DisplayName("Deve lançar IllegalArgumentException quando a data de admissão for no futuro.")
    @Test
    void shouldThrowIllegalArgumentExceptionWhenAdmissionDateIsFuture() {
        //Cenário
        Employee employee = employeeBuilder.withAdmissionDate(LocalDate.now().plusDays(1)).build();

        //Execução && Validação
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> vacationCalculator.calculateVacationDays(employee));

        Assertions.assertEquals("Admission date cannot be in the future",
                exception.getMessage());
    }

    @DisplayName("Deve lançar IllegalArgumentException quando o empregado for nulo.")
    @Test
    void shouldThrowIllegalArgumentExceptionWhenEmployeeIsNull() {
        //Cenário
        Employee employee = null;

        //Execução && Validação
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> vacationCalculator.calculateVacationDays(employee));

        Assertions.assertEquals("Employee cannot be null", exception.getMessage());
    }

    @DisplayName("Deve lançar IllegalArgumentException quando a data de admissão for nula.")
    @Test
    void shouldThrowIllegalArgumentExceptionWhenAdmissionDateIsNull() {
        //Cenário
        Employee employee = employeeBuilder.withAdmissionDate(null).build();

        //Execução && Validação
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> vacationCalculator.calculateVacationDays(employee));

        Assertions.assertEquals("Admission date cannot be null", exception.getMessage());
    }


}