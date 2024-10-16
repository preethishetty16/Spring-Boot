package com.sample.microservice.crud_operations.controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservice.crud_operations.main.resource.EmployeeCreateRequest;
import com.sample.microservice.crud_operations.main.resource.EmployeeCreateResponse;
import com.sample.microservice.crud_operations.main.resource.EmployeeSearchRequest;
import com.sample.microservice.crud_operations.main.resource.EmployeeSearchResponse;
import com.sample.microservice.crud_operations.resource.Response;
import com.sample.microservice.crud_operations.resource.ServiceMessage;
import com.sample.microservice.crud_operations.resource.ValidatorErrorResponse;
import com.sample.microservice.crud_operations.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/employee")
public class Maincontroller {
	
	@Autowired
	EmployeeService service;
	
	@PostMapping(value="/create")
	public ResponseEntity<Response<EmployeeCreateResponse>> createEmployee(
			@RequestHeader(name="Country" , required= true) String country,
			@RequestHeader(name ="UserId", required = true) String userId,
			@RequestBody @Valid EmployeeCreateRequest emloyeeCreateRequest ){
		
		EmployeeCreateResponse response= service.createEmployee(emloyeeCreateRequest);
		ServiceMessage serviceMessage;
		HttpStatus httpStatus;
		if(null == response) {
			serviceMessage = ServiceMessage.createDuplicateDataServiceMessage();
			httpStatus= HttpStatus.CONFLICT;
			serviceMessage.getErrors().add(new ValidatorErrorResponse(null, "CODE","EMPLOYEE EXISTS"));
		}else {
			serviceMessage = ServiceMessage.successMessageForCreated();
			httpStatus= HttpStatus.CREATED;
			serviceMessage.getMessages().add(new ValidatorErrorResponse(null,"CODE","EMPLOYEE CREATED"));
		}
				return new ResponseEntity<>(Response.createResponse(serviceMessage, response), httpStatus);
		
	}
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<Response<EmployeeCreateResponse>> editEmployee(
			@RequestHeader(name="Country" , required= true) String country,
			@RequestHeader(name ="UserId", required = true) String userId,
			@PathVariable Integer id,
			@RequestBody @Valid EmployeeCreateRequest emloyeeCreateRequest ){
		
		EmployeeCreateResponse response= service.updateEmployee(id, emloyeeCreateRequest);
		ServiceMessage serviceMessage;
		HttpStatus httpStatus;
		if(null == response) {
			serviceMessage = ServiceMessage.createDuplicateDataServiceMessage();
			httpStatus= HttpStatus.CONFLICT;
			serviceMessage.getErrors().add(new ValidatorErrorResponse(null, "CODE","EMPLOYEE EXISTS"));
		}else {
			serviceMessage = ServiceMessage.successMessageForCreated();
			httpStatus= HttpStatus.CREATED;
			serviceMessage.getMessages().add(new ValidatorErrorResponse(null,"CODE","EMPLOYEE CREATED"));
		}
				return new ResponseEntity<>(Response.createResponse(serviceMessage, response), httpStatus);
		
	}

	@PostMapping(value="/search")
	public ResponseEntity<Response<EmployeeSearchResponse>> searchEmployee(
			@RequestHeader(name="Country" , required= true) String country,
			@RequestHeader(name ="UserId", required = true) String userId,
			@RequestParam(name="pagenumber", defaultValue ="1") int pageNumber,
			@RequestParam(name = "limit", defaultValue = "10")int limit,
			@RequestBody @Valid EmployeeSearchRequest emloyeeSearchRequest
			){
		EmployeeSearchResponse response= service.searchEmployee(emloyeeSearchRequest);
		ServiceMessage serviceMessage;
		HttpStatus httpStatus;
		if(null == response ) {
			serviceMessage = ServiceMessage.createBadRequestServiceMessage();
			httpStatus= HttpStatus.NOT_FOUND;
			serviceMessage.getErrors().add(new ValidatorErrorResponse(null, "CODE","No data found"));
		}else {
			serviceMessage = ServiceMessage.successMessageForCreated();
			httpStatus= HttpStatus.CREATED;
			serviceMessage.getMessages().add(new ValidatorErrorResponse(null,"CODE","Succesfully found"));
		}
		return new ResponseEntity<>(Response.createResponse(serviceMessage, response), httpStatus);
		
	}
}
