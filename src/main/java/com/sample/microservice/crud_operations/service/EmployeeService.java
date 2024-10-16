package com.sample.microservice.crud_operations.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sample.microservice.crud_operations.exception.DuplicateRecordException;
import com.sample.microservice.crud_operations.main.resource.EmployeeCreateRequest;
import com.sample.microservice.crud_operations.main.resource.EmployeeCreateResponse;
import com.sample.microservice.crud_operations.main.resource.EmployeeSearchRequest;
import com.sample.microservice.crud_operations.main.resource.EmployeeSearchResponse;
import com.sample.microservice.crud_operations.model.Employee;
import com.sample.microservice.crud_operations.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public EmployeeCreateResponse createEmployee(EmployeeCreateRequest request) {

		Employee e = new Employee();
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

		Employee response = repository.save(e);
		return new EmployeeCreateResponse(response.getEmployeeId());
	}

	public EmployeeCreateResponse updateEmployee(Integer id, EmployeeCreateRequest updatedEmployee) {
		Employee employee = repository.findByEmployeeId(id);
		if (employee == null) {
			throw new DuplicateRecordException("Emloyee ID doesn't exist", null, null);
		}
		employee.setEmployeeName(updatedEmployee.getEmployeeName());
		employee.setEmployeeSalary(updatedEmployee.getEmployeeSalary());
		employee.setEmployeeDepartment(updatedEmployee.getEmployeeDepartment());
		Employee response =  repository.save(employee);
		return new EmployeeCreateResponse(response.getEmployeeId());
	}

	public EmployeeSearchResponse searchEmployee( EmployeeSearchRequest request) {
		List<Employee> emps = repository
				.findByEmployeeIdAndEmployeeNameAndEmployeeSalaryAndEmployeeDepartmentAndEmailId(
						request.getEmployeeId(),request.getEmployeeName(), request.getEmployeeSalary(), request.getEmployeeDepartment(), request.getEmailId());
		EmployeeSearchResponse response= new EmployeeSearchResponse();
		if( null != emps && !emps.isEmpty()) {
			response.setEmployees(emps);
		}
		else {
			response =null;
		}
		return response;
	}

}
