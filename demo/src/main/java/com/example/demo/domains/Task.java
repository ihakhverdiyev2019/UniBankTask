package com.example.demo.domains;


import com.example.demo.util.TaskStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String taskName;

    private Date deadline;

    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;


    public Task(String taskName, TaskStatus taskStatus, Employee employee) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.employee = employee;
    }


    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
