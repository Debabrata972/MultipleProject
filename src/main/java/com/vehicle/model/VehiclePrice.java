package com.vehicle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "MSRP", "Savings", "finalPrice" })
@Data
public class VehiclePrice {

	@JsonProperty("MSRP")
	private String mSRP;
	@JsonProperty("Savings")
	private String savings;
	@JsonProperty("finalPrice")
	private String finalPrice;

}
