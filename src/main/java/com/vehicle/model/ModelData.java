package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vehicles" })
@Data
public class ModelData {

	@JsonProperty("vehicles")
	private Vehicles vehicles;
}
