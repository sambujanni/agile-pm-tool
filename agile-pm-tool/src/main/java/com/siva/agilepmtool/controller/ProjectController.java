package com.siva.agilepmtool.controller;

import com.siva.agilepmtool.domain.Project;
import com.siva.agilepmtool.service.ProjectService;
import com.siva.agilepmtool.utils.MapValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrors mapValidationErrors;

    @PostMapping("")
    public ResponseEntity<?> addProject(@Validated @RequestBody Project project, BindingResult result) {
        ResponseEntity<Map<String, String>> map = mapValidationErrors.mapErrors(result);
        if(map!=null) {
            return map;
        }
        Project project1 = projectService.addProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> findByProjectId(@PathVariable String projectId) {
        Project project = projectService.findByProjectId(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.getAll();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<String>("Project with Project Id '"+projectId+"' was successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable String projectId) {
        return new ResponseEntity<Project>(projectService.updateProject(project, projectId), HttpStatus.OK);
    }

}
