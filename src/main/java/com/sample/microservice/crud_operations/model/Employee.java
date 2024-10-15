package com.sample.microservice.crud_operations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer generatedId;
	
	private Integer employeeId;

	private String employeeName;
	
	private long employeeSalary;
	
	private String employeeDepartment;
	
	private String emailId;


}
