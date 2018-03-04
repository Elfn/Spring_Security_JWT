package com.springsec.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
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
//@Data @AllArgsConstructor @NoArgsConstructor//We removed lombok metadata in order to ignore JSON deserialization and serialization straightaway on setters and getters
public class AppUser {

    @Id @GeneratedValue
    private  Long id;
    @Column(unique = true)//username must be unique in db
    private String username;
    //@JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> appRoles = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore//Marker annotation that indicates that the annotated method or field is to be ignored by introspection-based serialization and deserialization functionality
    //Here we do ignore that field during object deserialization to the side of the client
    public String getPassword() {
        return password;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<AppRole> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Collection<AppRole> appRoles) {
        this.appRoles = appRoles;
    }
}
