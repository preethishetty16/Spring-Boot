package com.sample.microservice.crud_operations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.microservice.crud_operations.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	boolean existsByEmailId(String emailId);

	boolean existsByEmployeeId(Integer employeeId);

	Employee findByEmployeeId(Integer id);

	List<Employee> findByEmployeeIdAndEmployeeNameAndEmployeeSalaryAndEmployeeDepartmentAndEmailId(Integer employeeId,
			String employeeName, long employeeSalary, String employeeDepartment,String emailId);

}
