package com.sample.microservice.crud_operations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.microservice.crud_operations.exception.DuplicateRecordException;
import com.sample.microservice.crud_operations.main.resource.EmployeeCreateRequest;
import com.sample.microservice.crud_operations.main.resource.EmployeeCreateResponse;
import com.sample.microservice.crud_operations.model.Employee;
import com.sample.microservice.crud_operations.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	public EmployeeCreateResponse createEmployee(EmployeeCreateRequest request) {
		
		Employee e= new Employee();
		e.setEmployeeDepartment(request.getEmployeeDepartment());
		e.setEmailId(request.getEmailId());
		e.setEmployeeId(request.getEmployeeId());
		e.setEmployeeName(request.getEmployeeName());
		e.setEmployeeSalary(request.getEmployeeSalary());
		
		if (repository.existsByEmailId(e.getEmailId())) {
	        throw new DuplicateRecordException("Email already exists", null, null);
	    }
		if (repository.existsByEmployeeId(e.getEmployeeId())) {
	        throw new DuplicateRecordException("Emloyee ID already exists", null, null);
	    }
		
		Employee response= repository.save(e);
		return new EmployeeCreateResponse(response.getEmployeeId());
	}

}
