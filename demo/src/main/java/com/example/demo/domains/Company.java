package com.example.demo.domains;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;


    @OneToMany(mappedBy = "company")
    private Set<Employee> employeeSet;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company(int id) {
        this.id = id;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }
}
