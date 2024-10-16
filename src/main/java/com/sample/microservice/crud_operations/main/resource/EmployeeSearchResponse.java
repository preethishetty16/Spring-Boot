package com.sample.microservice.crud_operations.main.resource;

import java.util.List;

import com.sample.microservice.crud_operations.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSearchResponse {
	
	List<Employee> employees;

}
