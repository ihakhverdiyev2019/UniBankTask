package com.example.demo.Controller;


import com.example.demo.Domain.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class ProjectController {


    private ProjectService projectService;
    private MessageSource messageSource;

    @Autowired
    public ProjectController(ProjectService projectService, MessageSource messageSource) {
        this.projectService = projectService;
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/projects", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Project> findAll() {


        return projectService.findAll();
    }


    @RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    String addProject(@RequestHeader(name = "Accept-Language", required = false) Locale locale,@RequestBody Project project) {


         projectService.addProject(project);

        return messageSource.getMessage("task.assign.successfully", null, locale);
    }
}
