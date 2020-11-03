package com.vehicle.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class VehiclePrice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vehiclePriceID;

	private Double mSRP;
	private Double savings;
	private Double finalPrice;

	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private Vehicle vehicle;
}
