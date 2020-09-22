package com.siva.agilepmtool.controller;

import com.siva.agilepmtool.domain.ProjectTask;
import com.siva.agilepmtool.service.ProjectTaskService;
import com.siva.agilepmtool.utils.MapValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrors mapValidationErrors;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Validated @RequestBody ProjectTask projectTask,
                                            BindingResult result,
                                            @PathVariable String backlog_id) {
        ResponseEntity<?> errorMap = mapValidationErrors.mapErrors(result);
        if(errorMap != null) {
            return errorMap;
        }

        ProjectTask projectTask1 = projectTaskService.addTask(backlog_id, projectTask);
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.CREATED);

    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<List<ProjectTask>>  getProjectBacklog(@PathVariable String backlog_id) {
        return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBacklogById(backlog_id), HttpStatus.OK);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getPtByProjectSequence(@PathVariable String backlog_id,
                                                    @PathVariable String pt_id) {
        ProjectTask projectTask = projectTaskService.findPtByProjectSequence(backlog_id, pt_id);
        return new ResponseEntity<>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlog_id, @PathVariable String pt_id ){

        ResponseEntity<?> errorMap = mapValidationErrors.mapErrors(result);
        if (errorMap != null) return errorMap;

        ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask,backlog_id,pt_id);

        return new ResponseEntity<ProjectTask>(updatedTask,HttpStatus.OK);

    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {
        projectTaskService.deleteProjectTask(backlog_id, pt_id);
        return new ResponseEntity<>("Project task "+ pt_id + " was deleted successfully", HttpStatus.OK);
    }
}
