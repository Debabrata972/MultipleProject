package com.vehicle.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "make", "model", "modelYear", "bodyStyle", "engine", "drivetype", "color", "MPG", "vehicleFeature",
		"vehiclePrice" })
@Data
public class VehicleDetails {

	@JsonProperty("make")
	private String make;
	@JsonProperty("model")
	private String model;
	@JsonProperty("modelYear")
	private String modelYear;
	@JsonProperty("bodyStyle")
	private String bodyStyle;
	@JsonProperty("engine")
	private String engine;
	@JsonProperty("drivetype")
	private String drivetype;
	@JsonProperty("color")
	private String color;
	@JsonProperty("MPG")
	private String mPG;
	@JsonProperty("vehicleFeature")
	private VehicleFeature vehicleFeature;
	@JsonProperty("vehiclePrice")
	private List<VehiclePrice> vehiclePrice = null;
}
