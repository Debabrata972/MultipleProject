package com.vehicle.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vehicle.entity.VehiclePrice;
import com.vehicle.model.ErrorResponse;
import com.vehicle.model.ModelData;
import com.vehicle.model.Vehicle;
import com.vehicle.model.VehicleDetails;
import com.vehicle.model.VehicleFeature;
import com.vehicle.model.Vehicles;
import com.vehicle.repo.VehiclePriceRepository;
import com.vehicle.repo.VehicleRepository;

@Service
public class VehicleService {

	public static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private VehiclePriceRepository vehiclePriceRepository;

	public ResponseEntity<?> saveVehicle(Vehicle vehicle) {
		try {
			com.vehicle.entity.Vehicle vehicleEntity = new com.vehicle.entity.Vehicle();
			vehicleEntity.setMake(vehicle.getVehicleDetails().getMake());
			vehicleEntity.setModel(vehicle.getVehicleDetails().getModel());
			try {
				vehicleEntity.setModelYear(Integer.parseInt(vehicle.getVehicleDetails().getModelYear().strip()));
			} catch (Exception e) {
				throw new VehicleException("Exception in model year during vehicle persistance", e);
			}
			vehicleEntity.setBodyStyle(vehicle.getVehicleDetails().getBodyStyle());
			vehicleEntity.setEngine(vehicle.getVehicleDetails().getEngine());
			vehicleEntity.setDrivetype(vehicle.getVehicleDetails().getDrivetype());
			vehicleEntity.setColor(vehicle.getVehicleDetails().getColor());
			try {
				vehicleEntity.setMPG(Integer.parseInt(vehicle.getVehicleDetails().getMPG()));
			} catch (Exception e) {
				throw new VehicleException("Exception in MPG during vehicle persistance", e);
			}
			try {
				String interior = vehicle.getVehicleDetails().getVehicleFeature().getInterior().toString();
				vehicleEntity.setInterior(interior.substring(1, interior.length() - 1));
			} catch (Exception e) {
				throw new VehicleException("Exception in interior during vehicle persistance", e);
			}
			try {
				String exterior = vehicle.getVehicleDetails().getVehicleFeature().getExterior().toString();
				vehicleEntity.setExterior(exterior.substring(1, exterior.length() - 1));
			} catch (Exception e) {
				throw new VehicleException("Exception in exterior during vehicle persistance", e);
			}
			vehicleEntity = vehicleRepository.save(vehicleEntity);
			com.vehicle.entity.VehiclePrice VehiclePrice = new VehiclePrice();
			VehiclePrice.setVehicle(vehicleEntity);

			List<com.vehicle.model.VehiclePrice> vehiclePrices = vehicle.getVehicleDetails().getVehiclePrice();
			for (com.vehicle.model.VehiclePrice vehiclePrice : vehiclePrices) {
				VehiclePrice vehiclePriceEntity = new VehiclePrice();
				vehiclePriceEntity.setVehicle(vehicleEntity);
				try {
					vehiclePriceEntity.setFinalPrice(Double.parseDouble(vehiclePrice.getFinalPrice().strip()));
				} catch (Exception e) {
					throw new VehicleException("Exception in final price during vehicle persistance", e);
				}
				try {
					vehiclePriceEntity.setMSRP(Double.parseDouble(vehiclePrice.getMSRP().strip()));
				} catch (Exception e) {
					throw new VehicleException("Exception in MSRP price during vehicle persistance", e);
				}
				try {
					vehiclePriceEntity.setSavings(Double.parseDouble(vehiclePrice.getSavings().strip()));
				} catch (Exception e) {
					throw new VehicleException("Exception in savings price during vehicle persistance", e);
				}
				vehiclePriceRepository.save(vehiclePriceEntity);
			}
			ErrorResponse resp = new ErrorResponse();
			resp.setStatus(HttpStatus.OK.name());
			resp.setStatusCode(HttpStatus.OK.value());
			resp.setMessage(vehicleEntity.getVehicleId() + "’s submitted to database successfully");
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse resp = new ErrorResponse();
			resp.setMessage(e.getMessage());
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
			resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getVehicles(String modelName) {
		try {
			List<com.vehicle.model.Vehicle> vehicles = new ArrayList<Vehicle>();
			List<com.vehicle.entity.Vehicle> vehicleEntities = null;
			if ("ALL".equals(modelName)) {
				vehicleEntities = vehicleRepository.findAll();
			} else {
				vehicleEntities = vehicleRepository.findByModel(modelName);
			}
			for (com.vehicle.entity.Vehicle vehicleEntity : vehicleEntities) {
				com.vehicle.model.Vehicle vehicle = new Vehicle();
				VehicleDetails vehicleDetail = new VehicleDetails();
				vehicleDetail.setMake(vehicleEntity.getMake());
				vehicleDetail.setModel(vehicleEntity.getModel());
				try {
					vehicleDetail.setModelYear(vehicleEntity.getModelYear().toString());
				} catch (Exception e) {
					throw new VehicleException("Exception in model year of vehicle id " + vehicleEntity.getVehicleId(),
							e);
				}
				vehicleDetail.setBodyStyle(vehicleEntity.getBodyStyle());
				vehicleDetail.setEngine(vehicleEntity.getEngine());
				vehicleDetail.setDrivetype(vehicleEntity.getDrivetype());
				vehicleDetail.setColor(vehicleEntity.getColor());
				try {
					vehicleDetail.setMPG(vehicleEntity.getMPG().toString());
				} catch (Exception e) {
					throw new VehicleException("Exception in MPG of vehicle id " + vehicleEntity.getVehicleId(), e);
				}

				VehicleFeature vehicleFeature = new VehicleFeature();
				try {
					vehicleFeature.setInterior(Arrays.asList(vehicleEntity.getInterior().split("\\s*,\\s*")));
				} catch (Exception e) {
					throw new VehicleException("Exception in interior of vehicle id " + vehicleEntity.getVehicleId(),
							e);
				}
				try {
					vehicleFeature.setExterior(Arrays.asList(vehicleEntity.getExterior().split("\\s*,\\s*")));
				} catch (Exception e) {
					throw new VehicleException("Exception in exterior of vehicle id " + vehicleEntity.getVehicleId(),
							e);
				}

				vehicleDetail.setVehicleFeature(vehicleFeature);
				List<com.vehicle.model.VehiclePrice> vehiclePrices = new ArrayList<com.vehicle.model.VehiclePrice>();
				List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = vehicleEntity.getVehiclePrice();
				if (vehiclePricesEntities != null) {
					for (com.vehicle.entity.VehiclePrice vehiclePriceEntity : vehiclePricesEntities) {
						com.vehicle.model.VehiclePrice vehiclePrice = new com.vehicle.model.VehiclePrice();
						vehiclePrice.setFinalPrice("$" + vehiclePriceEntity.getFinalPrice().toString());
						vehiclePrice.setMSRP("$" + vehiclePriceEntity.getMSRP().toString());
						vehiclePrice.setSavings("$" + vehiclePriceEntity.getSavings().toString());
						vehiclePrices.add(vehiclePrice);
					}
					vehicleDetail.setVehiclePrice(vehiclePrices);
				}
				vehicle.setVehicleDetails(vehicleDetail);
				vehicles.add(vehicle);
			}
			Vehicles vehiclesPojo = new Vehicles();
			vehiclesPojo.setVehicle(vehicles);
			ModelData modelData = new ModelData();
			modelData.setVehicles(vehiclesPojo);
			return new ResponseEntity<ModelData>(modelData, HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse resp = new ErrorResponse();
			resp.setMessage(e.getMessage());
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
			resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getVehiclesByPrice(String from, String tO) {
		try {
			Double fromDouble = Double.parseDouble(from);
			Double ToDouble = Double.parseDouble(tO);
			Boolean check = false;
			List<com.vehicle.model.Vehicle> vehicles = new ArrayList<Vehicle>();
			List<com.vehicle.entity.Vehicle> vehicleEntities = vehicleRepository.findAll();
			for (com.vehicle.entity.Vehicle vehicleEntity : vehicleEntities) {
				com.vehicle.model.Vehicle vehicle = new Vehicle();
				VehicleDetails vehicleDetail = new VehicleDetails();
				vehicleDetail.setMake(vehicleEntity.getMake());
				vehicleDetail.setModel(vehicleEntity.getModel());
				try {
					vehicleDetail.setModelYear(vehicleEntity.getModelYear().toString());
				} catch (Exception e) {
					throw new VehicleException("Exception in model year of vehicle id " + vehicleEntity.getVehicleId(),
							e);
				}
				vehicleDetail.setBodyStyle(vehicleEntity.getBodyStyle());
				vehicleDetail.setEngine(vehicleEntity.getEngine());
				vehicleDetail.setDrivetype(vehicleEntity.getDrivetype());
				vehicleDetail.setColor(vehicleEntity.getColor());
				try {
					vehicleDetail.setMPG(vehicleEntity.getMPG().toString());
				} catch (Exception e) {
					throw new VehicleException("Exception in MPG of vehicle id " + vehicleEntity.getVehicleId(), e);
				}
				VehicleFeature vehicleFeature = new VehicleFeature();
				try {
					vehicleFeature.setInterior(Arrays.asList(vehicleEntity.getInterior().split("\\s*,\\s*")));
				} catch (Exception e) {
					throw new VehicleException("Exception in interior of vehicle id " + vehicleEntity.getVehicleId(),
							e);
				}
				try {
					vehicleFeature.setExterior(Arrays.asList(vehicleEntity.getExterior().split("\\s*,\\s*")));
				} catch (Exception e) {
					throw new VehicleException("Exception in exterior of vehicle id " + vehicleEntity.getVehicleId(),
							e);
				}
				vehicleDetail.setVehicleFeature(vehicleFeature);
				List<com.vehicle.model.VehiclePrice> vehiclePrices = new ArrayList<com.vehicle.model.VehiclePrice>();
				List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = vehicleEntity.getVehiclePrice();
				if (vehiclePricesEntities != null) {
					for (com.vehicle.entity.VehiclePrice vehiclePriceEntity : vehiclePricesEntities) {
						com.vehicle.model.VehiclePrice vehiclePrice = new com.vehicle.model.VehiclePrice();
						vehiclePrice.setFinalPrice("$" + vehiclePriceEntity.getFinalPrice().toString());
						vehiclePrice.setMSRP("$" + vehiclePriceEntity.getMSRP().toString());
						vehiclePrice.setSavings("$" + vehiclePriceEntity.getSavings().toString());
						if (vehiclePriceEntity.getFinalPrice() >= fromDouble
								&& vehiclePriceEntity.getFinalPrice() <= ToDouble) {
							check = true;
						}
						vehiclePrices.add(vehiclePrice);
					}
					vehicleDetail.setVehiclePrice(vehiclePrices);
				}
				vehicle.setVehicleDetails(vehicleDetail);
				if (check) {
					vehicles.add(vehicle);
				}
			}
			Vehicles vehiclesPojo = new Vehicles();
			vehiclesPojo.setVehicle(vehicles);
			ModelData modelData = new ModelData();
			modelData.setVehicles(vehiclesPojo);
			return new ResponseEntity<ModelData>(modelData, HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse resp = new ErrorResponse();
			resp.setMessage(e.getMessage());
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
			resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getByExteriorAndInterior(String exterior, String interior) {
		try {
			if (exterior.length() >= 3 && interior.length() >= 3) {
				Boolean check = false;
				List<com.vehicle.model.Vehicle> vehicles = new ArrayList<Vehicle>();
				List<com.vehicle.entity.Vehicle> vehicleEntities = vehicleRepository.findAll();
				for (com.vehicle.entity.Vehicle vehicleEntity : vehicleEntities) {
					com.vehicle.model.Vehicle vehicle = new Vehicle();
					VehicleDetails vehicleDetail = new VehicleDetails();
					vehicleDetail.setMake(vehicleEntity.getMake());
					vehicleDetail.setModel(vehicleEntity.getModel());
					try {
						vehicleDetail.setModelYear(vehicleEntity.getModelYear().toString());
					} catch (Exception e) {
						throw new VehicleException(
								"Exception in model year of vehicle id " + vehicleEntity.getVehicleId(), e);
					}
					vehicleDetail.setBodyStyle(vehicleEntity.getBodyStyle());
					vehicleDetail.setEngine(vehicleEntity.getEngine());
					vehicleDetail.setDrivetype(vehicleEntity.getDrivetype());
					vehicleDetail.setColor(vehicleEntity.getColor());
					try {
						vehicleDetail.setMPG(vehicleEntity.getMPG().toString());
					} catch (Exception e) {
						throw new VehicleException("Exception in MPG of vehicle id " + vehicleEntity.getVehicleId(), e);
					}
					VehicleFeature vehicleFeature = new VehicleFeature();
					try {
						List<String> inter = Arrays.asList(vehicleEntity.getInterior().split("\\s*,\\s*"));
						for (String interVar : inter) {
							if (interVar.contains(interior))
								check = true;
						}
						vehicleFeature.setInterior(inter);
					} catch (Exception e) {
						throw new VehicleException(
								"Exception in interior of vehicle id " + vehicleEntity.getVehicleId(), e);
					}
					try {
						List<String> extr = Arrays.asList(vehicleEntity.getExterior().split("\\s*,\\s*"));
						for (String extrvar : extr) {
							if (extrvar.contains(exterior))
								check = true;
						}
						vehicleFeature.setExterior(extr);
					} catch (Exception e) {
						throw new VehicleException(
								"Exception in exterior of vehicle id " + vehicleEntity.getVehicleId(), e);
					}
					vehicleDetail.setVehicleFeature(vehicleFeature);
					List<com.vehicle.model.VehiclePrice> vehiclePrices = new ArrayList<com.vehicle.model.VehiclePrice>();
					List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = vehicleEntity.getVehiclePrice();
					for (com.vehicle.entity.VehiclePrice vehiclePriceEntity : vehiclePricesEntities) {
						com.vehicle.model.VehiclePrice vehiclePrice = new com.vehicle.model.VehiclePrice();
						vehiclePrice.setFinalPrice("$" + vehiclePriceEntity.getFinalPrice().toString());
						vehiclePrice.setMSRP("$" + vehiclePriceEntity.getMSRP().toString());
						vehiclePrice.setSavings("$" + vehiclePriceEntity.getSavings().toString());
						vehiclePrices.add(vehiclePrice);
					}
					vehicleDetail.setVehiclePrice(vehiclePrices);
					vehicle.setVehicleDetails(vehicleDetail);
					if (check) {
						vehicles.add(vehicle);
					}
				}
				Vehicles vehiclesPojo = new Vehicles();
				vehiclesPojo.setVehicle(vehicles);
				ModelData modelData = new ModelData();
				modelData.setVehicles(vehiclesPojo);
				return new ResponseEntity<ModelData>(modelData, HttpStatus.OK);
			} else {
				ErrorResponse resp = new ErrorResponse();
				resp.setMessage("exterior Variable and interior Variable must greater than 3");
				resp.setStatus(HttpStatus.BAD_REQUEST.name());
				resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
				return new ResponseEntity<ErrorResponse>(resp, HttpStatus.OK);
			}
		} catch (Exception e) {
			ErrorResponse resp = new ErrorResponse();
			resp.setMessage(e.getMessage());
			resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
			resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<ErrorResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
