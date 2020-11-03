package com.vehicle.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Exterior", "Interior" })
@Data
public class VehicleFeature {

	@JsonProperty("Exterior")
	private List<String> exterior = null;
	@JsonProperty("Interior")
	private List<String> interior = null;
}
