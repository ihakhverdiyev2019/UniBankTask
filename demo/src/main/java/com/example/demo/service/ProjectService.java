package com.example.demo.service;

import com.example.demo.Domain.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    void addProject(Project project);
}
