package com.example.demo.service;

import com.example.demo.domains.Project;
import com.example.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return (List<Project>) projectRepository.findAll();
    }

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Optional<Project> findByID(Integer id) {
        return projectRepository.findById(id);
    }
}
