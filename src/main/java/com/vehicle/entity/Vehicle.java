package com.vehicle.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vehicleId;

	private String make;
	private String model;
	private Integer modelYear;
	private String bodyStyle;
	private String engine;
	private String drivetype;
	private String color;
	private Integer mPG;
	private String exterior = null;
	private String interior = null;

	@OneToMany(mappedBy = "vehicle")
	private List<VehiclePrice> vehiclePrice = null;
}
