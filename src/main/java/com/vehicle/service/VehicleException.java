package com.vehicle.service;

public class VehicleException extends RuntimeException {

	private static final long serialVersionUID = 4380617443637475806L;

	public VehicleException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
