package com.example.demo.repositories;

import com.example.demo.domains.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository
        <Company, Integer> {


    Optional<Company> findByCompanyName(String companyName);
}
