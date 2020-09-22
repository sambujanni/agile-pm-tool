package com.siva.agilepmtool.repository;

import com.siva.agilepmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {
    Backlog findByProjectIdentifier(String projectIdentifier);
}
