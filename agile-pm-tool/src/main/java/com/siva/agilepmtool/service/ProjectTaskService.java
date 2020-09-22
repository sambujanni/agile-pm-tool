package com.siva.agilepmtool.service;

import com.siva.agilepmtool.domain.Backlog;
import com.siva.agilepmtool.domain.ProjectTask;
import com.siva.agilepmtool.exceptions.ProjectNotFoundException;
import com.siva.agilepmtool.repository.BacklogRepository;
import com.siva.agilepmtool.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository taskRepository;

    public ProjectTask addTask(String projectIdentifier, ProjectTask projectTask) {

        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        if (backlog == null) {
            throw new ProjectNotFoundException("Found no project with id '"+ projectIdentifier +"'");
        }
        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence = backlogSequence+1;
        backlog.setPTSequence(backlogSequence);
        projectTask.setBacklog(backlog);
        projectTask.setProjectSequence(backlog.getProjectIdentifier()+"-"+ backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if(projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        if(projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }
    return taskRepository.save(projectTask);
    }

    public List<ProjectTask> findBacklogById(String backlog_id) {
        return taskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findPtByProjectSequence(String backlog_id, String pt_id) {
        //make sure we are searching on an existing backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog == null) {
            throw new ProjectNotFoundException("Project with Id: '"+ backlog_id+"' does not exist");
        }
        //make sure that our task exists
        ProjectTask projectTask = taskRepository.findByProjectSequence(pt_id);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project task '"+ pt_id+"' not found");
        }

        //make sure that the backlog/project id in the path correspond to the right project
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project task '"+ pt_id+"' does not exist in project: '"+ backlog_id + "'");
        }
        return projectTask;
    }
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id){

        ProjectTask projectTask = findPtByProjectSequence(backlog_id, pt_id);
        projectTask = updatedTask;

        return taskRepository.save(updatedTask);
    }

    public void deleteProjectTask(String backlog_id, String pt_id) {
        ProjectTask projectTask = findPtByProjectSequence(backlog_id, pt_id);
        taskRepository.delete(projectTask);
    }
    //Update project task

    //find existing project task

    //replace it with updated task

    //save update
}
