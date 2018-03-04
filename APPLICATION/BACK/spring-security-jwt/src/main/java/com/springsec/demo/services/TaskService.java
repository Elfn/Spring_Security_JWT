package com.springsec.demo.services;

import com.springsec.demo.entities.Task;

import java.util.List;

/**
 * Created by Elimane on Feb, 2018, at 04:35
 */

public interface TaskService {

    public List<Task> tasksList ();
    public Task createTask(Task task);
}
