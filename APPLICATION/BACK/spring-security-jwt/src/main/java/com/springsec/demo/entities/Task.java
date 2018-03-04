package com.springsec.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Elimane on Feb, 2018, at 04:22
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String taskName;
}
