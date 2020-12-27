package com.example.demo.service;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.util.TaskStatus;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public void assignEmployee(Integer taskID, Employee employee) {
        Task task = taskRepository.findById(taskID).get();


        task.setEmployee(employee);
        taskRepository.save(task);
    }

    @Override
    public List<Task> findByEmployee(Employee employee) {
        return taskRepository.findAllByEmployee(employee);
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findByStatus(TaskStatus taskStatus) {
        return taskRepository.findAllByTaskStatus(taskStatus);
    }
}
