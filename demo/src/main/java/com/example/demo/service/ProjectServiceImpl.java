package com.example.demo.service;

import com.example.demo.Domain.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
