package com.example.demo.controllers;


import com.example.demo.domains.Company;
import com.example.demo.domains.Employee;
import com.example.demo.errorHandling.ResponseBody;
import com.example.demo.service.CompanyService;
import com.example.demo.service.EmployeeService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Method;
import java.util.*;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private CompanyService companyService;
    private MessageSource messageSource;


    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService, MessageSource messageSource) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
    public ResponseBody findAll(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        ResponseBody response = null;
        response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("no.error", null, locale), employeeService.findAll());

        return response;
    }

    @RequestMapping(value = "/findEmployeesByCompany", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    public ResponseBody findAllByCompany(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Company company) {
        Optional<Company> company1 = companyService.findByID(company.getId());
        ResponseBody response = null;
        List<Employee> employees = employeeService.findByCompany(company);

        try {
            if (!company1.isPresent()) {
                throw new NoSuchElementException();
            } else {
                response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("no.error", null, locale), employees);

            }
        } catch (NoSuchElementException ex) {
            response = new ResponseBody(Response.SC_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("request.object.null", null, locale), employees);
        }

        return response;
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseBody createCompany(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Employee employee) {
        ResponseBody response = null;

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        Optional<Employee> employee1 = employeeService.findByID(employee.getId());

        if (!employee1.isPresent()) {
            employeeService.addEmployee(employee);
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("save.to.database", null, locale), employees);

        } else {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("same.id.to.database", null, locale), employees);

        }
        return response;

    }
}
