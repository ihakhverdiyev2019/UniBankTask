package com.example.demo.service;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.util.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void addTask(Task task);

    List<Task> findAllTasks();

    void assignEmployee(Integer taskID, Employee employee);

    List<Task> findByEmployee(Employee employee);

    Optional<Task> findById(Integer id);

    List<Task> findByStatus(TaskStatus taskStatus);

}
