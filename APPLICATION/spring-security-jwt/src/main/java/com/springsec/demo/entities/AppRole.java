package com.springsec.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Elimane on Feb, 2018, at 22:51
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AppRole {

    @Id @GeneratedValue
    private  Long id;


    private String roleName;
}
