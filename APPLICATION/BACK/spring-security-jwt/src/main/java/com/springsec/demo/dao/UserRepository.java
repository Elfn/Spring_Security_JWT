package com.springsec.demo.dao;

import com.springsec.demo.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Elimane on Feb, 2018, at 22:57
 */
public interface UserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findByUsername(String username);
}
