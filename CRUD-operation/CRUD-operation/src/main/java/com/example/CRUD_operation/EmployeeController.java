package com.example.CRUD_operation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping("/employee")

	public String saveEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return "Employee record saved";
	}

	@GetMapping("/employee")
	public List<Employee> getAllEmployee() {
		List<Employee> list = employeeRepository.findAll();
		return list;
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		Optional<Employee> opt = employeeRepository.findById(id);
		if (opt.isPresent())//check
			return opt.get();//retrieve
		else
			return null;
	}
	
	
	@PutMapping("/employee")
	public String updateEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return "employee record saved";
	}

}
