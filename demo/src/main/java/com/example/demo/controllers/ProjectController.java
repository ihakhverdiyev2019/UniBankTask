package com.example.demo.controllers;


import com.example.demo.errorHandling.ResponseBody;
import com.example.demo.domains.Project;
import com.example.demo.service.ProjectService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
    public ResponseBody findAll(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        ResponseBody response = null;
        response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("no.error", null, locale), projectService.findAll());


        return response;
    }


    @RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseBody addProject(@RequestHeader(name = "Accept-Language", required = false) Locale locale, @RequestBody Project project) {

        ResponseBody response = null;

        List<Project> projects = new ArrayList<>();
        projects.add(project);

        Optional<Project> project1 = projectService.findByID(project.getId());

        if (!project1.isPresent()) {
            projectService.addProject(project);
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("save.to.database", null, locale), projects);

        } else {
            response = new ResponseBody(Response.SC_OK, HttpStatus.OK, messageSource.getMessage("same.id.to.database", null, locale), projects);

        }

        return response;
    }
}
