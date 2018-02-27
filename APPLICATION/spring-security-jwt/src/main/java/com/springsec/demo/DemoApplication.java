package com.springsec.demo;

import com.springsec.demo.entities.Task;
import com.springsec.demo.services.TaskInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication
//CommandLineRunner is an Interface used to indicate that a bean should run when it is contained within a SpringApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private TaskInterface taskInterface;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Stream.of("Task1","Task2","Task3","Task4").forEach(task -> { taskInterface.createTask(new Task(null,task)); } );

		taskInterface.tasksList().forEach(task -> {
			System.out.println(task.getTaskName());
		});
	}
}
