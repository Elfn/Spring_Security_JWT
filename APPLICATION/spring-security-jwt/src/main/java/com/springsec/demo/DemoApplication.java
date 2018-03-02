package com.springsec.demo;

import com.springsec.demo.entities.AppRole;
import com.springsec.demo.entities.AppUser;
import com.springsec.demo.entities.RoleTypes;
import com.springsec.demo.entities.Task;
import com.springsec.demo.services.AccountService;
import com.springsec.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
//CommandLineRunner is an Interface used to indicate that a bean should run when it is contained within a SpringApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private TaskService taskInterface;

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
		@Bean
		public BCryptPasswordEncoder getBCPE ()
		{
			return new BCryptPasswordEncoder();
		}


		@Override
		public void run (String...strings) throws Exception {

			String adminRoleName = RoleTypes.ADMIN.toString();
			String userRoleName = RoleTypes.USER.toString();

			accountService.saveRole(new AppRole(null,userRoleName));
			accountService.saveRole(new AppRole(null,adminRoleName));
			accountService.saveUser(new AppUser("admin","1234",null));
			accountService.saveUser(new AppUser("user","12345",null));

			AppRole admin = accountService.findRoleByName(adminRoleName);
			AppRole user = accountService.findRoleByName(userRoleName);


			accountService.addRoleToUser(admin.getRoleName(), "admin");
			accountService.addRoleToUser(user.getRoleName(), "user");



			Stream.of("Task1", "Task2", "Task3", "Task4").forEach(task -> {
				taskInterface.createTask(new Task(null, task));
			});

			taskInterface.tasksList().forEach(task -> {
				System.out.println(task.getTaskName());
			});

			accountService.findAppUsers().stream().forEach(appUser -> {
				System.out.println(appUser.getUsername()+" "+appUser.getPassword());
			});
		}
	}

