package com.vehicle.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vehicle" })
@Data
public class Vehicles {

	@JsonProperty("vehicle")
	private List<Vehicle> vehicle = null;
}
