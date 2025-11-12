package tech.ada.introducaotestes.domain;

import java.time.LocalDate;

public class Employee {

    private String name;
    private LocalDate admissionDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }
}
