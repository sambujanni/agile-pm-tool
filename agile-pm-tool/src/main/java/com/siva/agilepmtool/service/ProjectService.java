package com.siva.agilepmtool.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siva.agilepmtool.domain.Project;
import com.siva.agilepmtool.exceptions.ProjectIdentifierException;
import com.siva.agilepmtool.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project addProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdentifierException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists" );
        }
    }

    public Project findByProjectId(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null) {
            throw new ProjectIdentifierException("Project Id '" + projectId.toUpperCase() + "' does not exist" );
        }
        return project;
    }

    public Iterable<Project> getAll() {
        return projectRepository.findAll();
    }

    public void deleteProject(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdentifierException("Found no project with Project Id '" + projectId.toUpperCase() + "' to delete" );
        }
        projectRepository.delete(project);
    }

    public Project updateProject(Project project, String projectId) {
        Project dbProject = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdentifierException("Found no project with Project Id '" + projectId.toUpperCase() + "' to update" );
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> projectMap = mapper.convertValue(project, Map.class);
        String[] strings = projectMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == null)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
        BeanUtils.copyProperties(project, dbProject, strings);
        try {
            return projectRepository.save(dbProject);
        } catch (Exception e) {
            throw new ProjectIdentifierException("Could not update Project Id");
        }
    }
}
