package com.Mangalji.Mangal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/jsp")
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);

		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(employee);

		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	@GetMapping("/employee")
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployee() {
		List<Employee> list = employeeRepository.findAll();

		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		structure.setStatusCode(HttpStatus.FOUND.value());
		structure.setMessage("Success");
		structure.setData(list);

		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);

//        return employeeRepository.findAll();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(@PathVariable int id) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Optional<Employee> opt = employeeRepository.findById(id);

		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMessage("Success    ");
			structure.setData(opt.get());

			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		} else
			structure.setStatusCode(HttpStatus.FAILED_DEPENDENCY.value());
		structure.setMessage("ID not present...");
		structure.setData(null);

		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FAILED_DEPENDENCY);
	}

	@PutMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Updatted");
		structure.setData(employee);

		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@PathVariable int id) {
		Optional<Employee> opt = employeeRepository.findById(id);

		if (opt.isPresent()) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setStatusCode(HttpStatus.RESET_CONTENT.value());
			structure.setMessage("Deleted");
//    		structure.setData(opt.get());
			employeeRepository.delete(opt.get());
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.RESET_CONTENT);
		} else {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Record not Found");
//            structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
	}
}