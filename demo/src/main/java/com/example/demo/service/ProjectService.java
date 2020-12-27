package com.example.demo.service;

import com.example.demo.domains.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> findAll();

    void addProject(Project project);

    Optional<Project> findByID(Integer id);
}
