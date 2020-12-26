package com.example.demo.Controller;


import com.example.demo.Domain.Company;
import com.example.demo.Domain.Employee;
import com.example.demo.errorHandling.ResponseBody;
import com.example.demo.service.CompanyService;
import com.example.demo.service.EmployeeService;
import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
    public @org.springframework.web.bind.annotation.ResponseBody
    List<Employee> findAll() {


        return employeeService.findAll();
    }

    @RequestMapping(value = "/findEmployeesByCompany", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    public ResponseBody findAllByCompany(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Company company) {
        Optional<Company> company1 = companyService.findByID(company.getId());
        ResponseBody response = null;
        List<Employee> employees = employeeService.findByCompany(company);

        try {
            if (!company1.isPresent()) {
                throw new NoSuchElementException();
            }
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), employees);

        } catch (NoSuchElementException ex) {
            response = new ResponseBody(Response.SC_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("request.object.null", null, locale), employees);
        } catch (HttpClientErrorException.MethodNotAllowed ex){
            response = new ResponseBody(Response.SC_METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED, messageSource.getMessage("method.not.allow", null, locale), employees);
        }


        return response;
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @org.springframework.web.bind.annotation.ResponseBody
    String createCompany(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Employee employee) {


        employeeService.addEmployee(employee);

        return messageSource.getMessage("task.assign.successfully", null, locale);

    }
}
