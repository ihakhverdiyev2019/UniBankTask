package com.example.demo.service;

import com.example.demo.domains.Company;
import com.example.demo.domains.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Employee> findByID(Integer id) {
        return employeeRepository.findById(id);
    }
}
