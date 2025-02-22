package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class Controller {
	@GetMapping("/student")
	public String getStudentInfo(@RequestParam int id, @RequestParam String name) {
		return "Id :" + id + " Name :" + name;
	}
	
	
	@GetMapping("/employee/{id}/{age}")
	public String getEmployeeInfo(@PathVariable int id,@PathVariable int age) {
		return "Id :" + id + " Age :" + age;
	}
	
	@GetMapping("/employee")
	public String getEmployee(@RequestHeader int token) {
		return "My Token :"+token;
		
	}
	
	@PostMapping("/student")
	public String saveStudent(@RequestBody Student stud) {
		return stud+" ";
	}
}
