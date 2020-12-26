package com.example.demo.repository;

import com.example.demo.Domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository
        <Company, Integer> {


    Optional<Company> findByCompanyName(String companyName);
}
