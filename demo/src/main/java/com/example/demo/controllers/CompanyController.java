package com.example.demo.controllers;


import com.example.demo.domains.Company;
import com.example.demo.service.CompanyService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import com.example.demo.errorHandling.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@RestController
public class CompanyController {


    private CompanyService companyService;
    private MessageSource messageSource;

    @Autowired
    public CompanyController(CompanyService companyService, MessageSource messageSource) {
        this.companyService = companyService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/companies", method = RequestMethod.GET, produces = "application/json")
    public ResponseBody findAll(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        ResponseBody response = null;

        response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("no.error", null, locale), companyService.findAll());

        return response;
    }

    @RequestMapping(value = "/createCompany", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseBody createCompany(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Company company) {
        Optional<Company> company1 = companyService.findByCompanyName(company.getCompanyName());
        ResponseBody response = null;
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        try {
            if (company1.isPresent()) {
                throw new NoSuchElementException();
            }
            companyService.companyCreate(company);
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("no.error", null, locale), companies);

        } catch (NoSuchElementException ex) {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), companies);
        }

        return response;

    }
}
