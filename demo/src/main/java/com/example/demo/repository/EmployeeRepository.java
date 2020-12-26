package com.example.demo.repository;

import com.example.demo.Domain.Company;
import com.example.demo.Domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {


    List<Employee> findAllByCompany(Company company);
}
