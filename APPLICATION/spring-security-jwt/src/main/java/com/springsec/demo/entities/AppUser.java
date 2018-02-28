package com.springsec.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Elimane on Feb, 2018, at 22:50
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {

    @Id @GeneratedValue
    private  Long id;
    @Column(unique = true)//username must be unique in db
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> appRoles = new ArrayList<>();
}
