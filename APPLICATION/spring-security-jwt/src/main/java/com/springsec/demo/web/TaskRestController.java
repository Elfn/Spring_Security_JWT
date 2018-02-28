package com.springsec.demo.web;

import com.springsec.demo.entities.Task;
import com.springsec.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Elimane on Feb, 2018, at 04:34
 */
@RestController
public class TaskRestController {
    @Autowired
    private TaskService taskInterface;

    @GetMapping("/tasksList")
    public List<Task> tasks()
    {
        return taskInterface.tasksList();
    }

    @PostMapping("/addTask")
    //@RequestBody allows to put task object in the request content
    public Task createTask(@RequestBody  Task task)
    {
        return  taskInterface.createTask(task);
    }

}
