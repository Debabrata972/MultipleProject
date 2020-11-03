package com.vehicle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	List<Vehicle> findByModel(String model);

}