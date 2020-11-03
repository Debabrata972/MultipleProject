package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vehicleId", "vehicleDetails" })
@Data
public class Vehicle {

	@JsonProperty("vehicleId")
	private String vehicleId;
	@JsonProperty("vehicleDetails")
	private VehicleDetails vehicleDetails;
}
