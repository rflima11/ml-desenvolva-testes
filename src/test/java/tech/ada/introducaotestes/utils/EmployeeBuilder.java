package tech.ada.introducaotestes.utils;

import tech.ada.introducaotestes.domain.Employee;

import java.time.LocalDate;

public class EmployeeBuilder {
    private String name = "Rodolfo";
    private LocalDate admissionDate = LocalDate.of(2020, 1, 1);

    public EmployeeBuilder withAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
        return this;
    }

    public EmployeeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Employee build() {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAdmissionDate(admissionDate);
        return employee;
    }
}
