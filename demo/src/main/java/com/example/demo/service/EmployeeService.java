package com.example.demo.service;

import com.example.demo.Domain.Company;
import com.example.demo.Domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findByCompany(Company company);

    void addEmployee(Employee employee);
}
