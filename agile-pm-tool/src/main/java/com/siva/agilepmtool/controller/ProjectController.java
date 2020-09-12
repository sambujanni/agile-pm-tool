package com.siva.agilepmtool.controller;

import com.siva.agilepmtool.domain.Project;
import com.siva.agilepmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project project1 = projectService.addProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    /*@GetMapping("/all")
    public List<Project> getAll() {
        return projectService.
    }*/

}
