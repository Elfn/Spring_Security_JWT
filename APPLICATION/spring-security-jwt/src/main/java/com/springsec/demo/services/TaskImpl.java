package com.springsec.demo.services;

import com.springsec.demo.dao.TaskRepository;
import com.springsec.demo.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Elimane on Feb, 2018, at 04:35
 */
@Service
public class TaskImpl implements TaskInterface {

    private TaskRepository taskRepository;

    public TaskImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> tasksList() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
}
