package com.siva.agilepmtool.repository;

import com.siva.agilepmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByProjectIdentifier(String id);
}
