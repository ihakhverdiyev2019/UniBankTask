package com.example.demo.Controller;


import com.example.demo.Domain.Company;
import com.example.demo.service.CompanyService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import com.example.demo.errorHandling.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import net.guides.springboot2.springboot2jpacrudexample.model.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public List<Company> findAll() {


        return companyService.findAll();
    }

    @RequestMapping(value = "/createCompany", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseBody createCompany(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Company company) {
        Optional<Company> company1 = companyService.findByCompanyName(company.getCompanyName());
        ResponseBody response = null;

        try {
            if (company1.isPresent()) {
                throw new ();
            }
            companyService.companyCreate(company);
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), (List<?>) company);

        } catch (NoSuchElementException ex) {
            response = new ResponseBody(Response.SC_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("request.object.null", null, locale), (List<?>) company);
        }
        return response;

    }
}
