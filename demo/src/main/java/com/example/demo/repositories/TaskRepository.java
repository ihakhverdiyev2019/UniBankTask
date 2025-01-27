package com.example.demo.repositories;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.util.TaskStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> findAllByEmployee(Employee employee);

    List<Task> findAllByTaskStatus(TaskStatus taskStatus);


}
