package com.example.demo.service;

import com.example.demo.domains.Company;

import java.util.List;
import java.util.Optional;


public interface CompanyService {

    List<Company> findAll();

    void companyCreate(Company company);

    Optional<Company> findByID(Integer id);

    Optional<Company> findByCompanyName(String companyName);

}
