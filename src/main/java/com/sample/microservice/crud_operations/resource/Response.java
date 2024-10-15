package com.sample.microservice.crud_operations.resource;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
	
	private ServiceMessage serviceMessage;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private T extendnedresponse;
	
	public static<T> Response<T> createResponse(ServiceMessage message ,T extendnedresponse){
		return new Response<T>(message, extendnedresponse);
	}

}
