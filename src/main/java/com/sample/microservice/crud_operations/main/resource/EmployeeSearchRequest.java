package com.sample.microservice.crud_operations.main.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSearchRequest {

	private Integer employeeId;

	private String employeeName;
	
	private long employeeSalary;
	
	private String employeeDepartment;
	
	private String emailId;
	
	private String sortOrder;
	
	private String sortColumn;
}

