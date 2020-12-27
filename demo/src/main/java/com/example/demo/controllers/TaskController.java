package com.example.demo.controllers;

import com.example.demo.domains.Project;
import com.example.demo.errorHandling.ResponseBody;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.service.EmployeeService;
import com.example.demo.util.TaskStatus;
import com.example.demo.service.TaskService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class TaskController {

    private TaskService taskService;
    private EmployeeService employeeService;
    private MessageSource messageSource;

    @Autowired
    public TaskController(TaskService taskService, EmployeeService employeeService, MessageSource messageSource) {
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseBody addTask(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Task task) {
        ResponseBody response = null;

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        Optional<Task> task1 = taskService.findById(task.getId());

        if (!task1.isPresent()) {
            taskService.addTask(task);
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("save.to.database", null, locale), tasks);

        } else {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("same.id.to.database", null, locale), tasks);

        }

        return response;

    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = "application/json")
    public ResponseBody findAllTask(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        ResponseBody response = null;
        response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("no.error", null, locale), taskService.findAllTasks());


        return response;


    }

    @RequestMapping(value = "/assign/{taskID}", method = RequestMethod.PUT)
    public ResponseBody assignTaskToEmployee(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @PathVariable Integer taskID, @RequestBody Employee employee) {
        Optional<Task> task = taskService.findById(taskID);
        Optional<Employee> employee1 = employeeService.findByID(employee.getId());
        ResponseBody response = null;

        if (!task.isPresent()) {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), null
            );

        } else if (!employee1.isPresent()) {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), null);

        } else {
            taskService.assignEmployee(taskID, employee);

            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), taskService.findByEmployee(employee));

        }

        return response;
    }


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseBody findByEmployee(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Employee employee) {
        Optional<Employee> employee1 = employeeService.findByID(employee.getId());
        ResponseBody response = null;
        if (!employee1.isPresent()) {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), null);

        } else {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("request.object.null", null, locale), taskService.findByEmployee(employee));

        }
        return response;
    }


    @RequestMapping(value = "/findByStatus", method = RequestMethod.POST)
    public ResponseBody findByStatus(@RequestBody TaskStatus taskStatus) {
        System.out.println(taskStatus);
        return taskService.findByStatus(taskStatus);
    }


}
