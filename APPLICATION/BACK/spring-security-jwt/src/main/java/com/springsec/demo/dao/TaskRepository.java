package com.springsec.demo.dao;

import com.springsec.demo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by Elimane on Feb, 2018, at 04:24
 */
//@RepositoryRestResource
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
