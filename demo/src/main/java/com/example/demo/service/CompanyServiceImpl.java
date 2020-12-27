package com.example.demo.service;

import com.example.demo.domains.Company;
import com.example.demo.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public List<Company> findAll() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public void companyCreate(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Optional<Company> findByID(Integer id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }
}
