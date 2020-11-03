package com.vehicle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.entity.VehiclePrice;

public interface VehiclePriceRepository extends JpaRepository<VehiclePrice, Integer> {

}