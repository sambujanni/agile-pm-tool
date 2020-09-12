package com.siva.agilepmtool.service;

import com.siva.agilepmtool.domain.Project;
import com.siva.agilepmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    /*public List<Project> getAll() {
        return projectRepository.findAll().;
    }*/
}
