package com.sample.microservice.crud_operations.main.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreateRequest {
	
	private Integer employeeId;

	@NotBlank(message="employeeName cannot be empty")
	private String employeeName;
	
	@NotBlank(message="emailId cannot be empty")
	private String emailId;
	
	@Min(value = 0, message = "Salary must be a positive number")
	private long employeeSalary;
	
	@NotBlank(message="employeeDepartment cannot be empty")
	private String employeeDepartment;

}
