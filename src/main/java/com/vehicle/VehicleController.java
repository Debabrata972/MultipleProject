package com.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.model.Vehicle;
import com.vehicle.service.VehicleService;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping(value = "/vehicleInfomation/submitVehicle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle) {
		return vehicleService.saveVehicle(vehicle);
	}

	@RequestMapping(value = "/getVehicleInfomation", method = RequestMethod.GET)
	public ResponseEntity<?> allVehicles() {
		return vehicleService.getVehicles("ALL");
	}

	@RequestMapping(value = "/getVehicleModelName/{modelName}", method = RequestMethod.GET)
	public ResponseEntity<?> allVehiclesByModelName(@PathVariable String modelName) {
		return vehicleService.getVehicles(modelName);
	}

	@RequestMapping(value = "/getVehiclePrice/{From}/{TO}", method = RequestMethod.GET)
	public ResponseEntity<?> allVehiclesByPriceRange(@PathVariable String from, @PathVariable String to) {
		return vehicleService.getVehiclesByPrice(from, to);
	}

	@RequestMapping(value = "/getVehicleByFeatures/{exterior}/{interior}", method = RequestMethod.GET)
	public ResponseEntity<?> allVehiclesByExteriorAndInterior(@PathVariable String exterior,
			@PathVariable String interior) {
		return vehicleService.getByExteriorAndInterior(exterior, interior);
	}
}
