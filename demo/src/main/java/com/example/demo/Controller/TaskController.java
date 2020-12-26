package com.example.demo.Controller;


import com.example.demo.Domain.Employee;
import com.example.demo.Domain.Task;
import com.example.demo.util.TaskStatus;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.Locale;

@RestController
public class TaskController {

    private TaskService taskService;
    private MessageSource messageSource;


    @Autowired
    public TaskController(MessageSource messageSource, TaskService taskService) {
        this.taskService = taskService;
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    String addTask(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Task task) {


        taskService.addTask(task);
        return messageSource.getMessage("task.assign.successfully", null, locale);

    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Task> findAllTask() {


        return taskService.findAllTasks();
    }

    @RequestMapping(value = "/assign/{taskID}", method = RequestMethod.PUT)
    public @ResponseBody
    String assignTaskToEmployee(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @PathVariable Integer taskID, @RequestBody Employee employee) {
//       try{
//
//           throw new InvalidPathException();
//
//       }catch (HttpClientErrorException.MethodNotAllowed)


        taskService.assignEmployee(taskID, employee);
        return messageSource.getMessage("task.assign.successfully", null, locale);
    }


    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public @ResponseBody
    List<Task> findByEmployee(@RequestBody Employee employee) {
        return taskService.findByEmployee(employee);
    }


    @RequestMapping(value = "/findByStatus", method = RequestMethod.POST)
    public @ResponseBody
    List<Task> findByStatus(@RequestBody TaskStatus taskStatus) {
        System.out.println(taskStatus);
        return taskService.findByStatus(taskStatus);
    }


}
