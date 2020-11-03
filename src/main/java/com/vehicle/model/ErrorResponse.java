package com.vehicle.model;

import lombok.Data;

@Data
public class ErrorResponse {

	private String status;
	private Integer statusCode;
	private String message;
}
