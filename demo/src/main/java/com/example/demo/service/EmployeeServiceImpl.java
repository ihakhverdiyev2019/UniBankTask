package com.example.demo.service;

import com.example.demo.Domain.Company;
import com.example.demo.Domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public List<Employee> findByCompany(Company company) {
        return employeeRepository.findAllByCompany(company);
    }

    @Override
    public void addEmployee(Employee employee) {
         employeeRepository.save(employee);
    }
}
