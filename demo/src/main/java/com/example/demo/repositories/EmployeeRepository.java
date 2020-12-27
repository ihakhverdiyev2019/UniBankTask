package com.example.demo.repositories;

import com.example.demo.domains.Company;
import com.example.demo.domains.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {


    List<Employee> findAllByCompany(Company company);
}
