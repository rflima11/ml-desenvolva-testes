package tech.ada.introducaotestes.service;

import tech.ada.introducaotestes.domain.Employee;

import java.time.LocalDate;
import java.time.Period;

public class VacationCalculator {

    public int calculateVacationDays(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if (employee.getAdmissionDate() == null) {
            throw new IllegalArgumentException("Admission date cannot be null");
        }

        LocalDate today = LocalDate.now();
        LocalDate admissionDate = employee.getAdmissionDate();

        if (admissionDate.isAfter(today)) {
            throw new IllegalArgumentException("Admission date cannot be in the future");
        }

        Period period = Period.between(admissionDate, today);
        int years = period.getYears();
        int months = period.getMonths();

        if (years >= 1) {
            return 30; // 1 ano ou mais -> férias completas
        }

        // proporcional
        return (int) Math.round(months * (30.0 / 12.0));
    }
}
