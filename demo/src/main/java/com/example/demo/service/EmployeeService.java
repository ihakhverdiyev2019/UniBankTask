package com.example.demo.service;

import com.example.demo.domains.Company;
import com.example.demo.domains.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findByCompany(Company company);

    void addEmployee(Employee employee);

    Optional<Employee> findByID(Integer id);
}
