package com.springsec.demo.dao;

import com.springsec.demo.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Elimane on Feb, 2018, at 22:59
 */
public interface RoleRepository extends JpaRepository<AppRole,Long> {
    public AppRole findByRoleName(String rolename);
}
