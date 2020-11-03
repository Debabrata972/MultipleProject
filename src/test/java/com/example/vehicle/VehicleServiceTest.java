package com.example.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.model.ErrorResponse;
import com.vehicle.model.ModelData;
import com.vehicle.model.Vehicle;
import com.vehicle.repo.VehiclePriceRepository;
import com.vehicle.repo.VehicleRepository;
import com.vehicle.service.VehicleService;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

	@Mock
	private VehicleRepository vehicleRepository;

	@Mock
	private VehiclePriceRepository vehiclePriceRepository;

	@InjectMocks
	private VehicleService serv;

	@Test
	void saveVehicleTest() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		when(vehicleRepository.save(Mockito.any(com.vehicle.entity.Vehicle.class))).thenReturn(entity);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32\",\r\n"
				+ "          \"vehicleFeature\": {\r\n" + "            \"Exterior\": [\r\n"
				+ "              \"Dual Exhaust System\",\r\n" + "              \"Easy Fuel Capless Filler\",\r\n"
				+ "              \"Headlamps - Autolamp\",\r\n"
				+ "              \"Headlamps- Led With Signature Lighting\"\r\n" + "            ],\r\n"
				+ "            \"Interior\": [\r\n" + "              \"Autodim Rearview Mirror\",\r\n"
				+ "              \"Center Console W/Armrest\",\r\n" + "              \"Floor Mats - Front\",\r\n"
				+ "              \"Smart Charging UsbPort(2)\"\r\n" + "            ]\r\n" + "          },\r\n"
				+ "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645.70\",\r\n" + "              \"Savings\": \"4988.20\",\r\n"
				+ "              \"finalPrice\": \"28657.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("200", body.getStatusCode().toString());
	}

	@Test
	void saveVehicleTestFail1() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017T\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32\",\r\n"
				+ "          \"vehicleFeature\": {\r\n" + "            \"Exterior\": [\r\n"
				+ "              \"Dual Exhaust System\",\r\n" + "              \"Easy Fuel Capless Filler\",\r\n"
				+ "              \"Headlamps - Autolamp\",\r\n"
				+ "              \"Headlamps- Led With Signature Lighting\"\r\n" + "            ],\r\n"
				+ "            \"Interior\": [\r\n" + "              \"Autodim Rearview Mirror\",\r\n"
				+ "              \"Center Console W/Armrest\",\r\n" + "              \"Floor Mats - Front\",\r\n"
				+ "              \"Smart Charging UsbPort(2)\"\r\n" + "            ]\r\n" + "          },\r\n"
				+ "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645.70\",\r\n" + "              \"Savings\": \"4988.20\",\r\n"
				+ "              \"finalPrice\": \"28657.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void saveVehicleTestFail2() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32hj\",\r\n"
				+ "          \"vehicleFeature\": {\r\n" + "            \"Exterior\": [\r\n"
				+ "              \"Dual Exhaust System\",\r\n" + "              \"Easy Fuel Capless Filler\",\r\n"
				+ "              \"Headlamps - Autolamp\",\r\n"
				+ "              \"Headlamps- Led With Signature Lighting\"\r\n" + "            ],\r\n"
				+ "            \"Interior\": [\r\n" + "              \"Autodim Rearview Mirror\",\r\n"
				+ "              \"Center Console W/Armrest\",\r\n" + "              \"Floor Mats - Front\",\r\n"
				+ "              \"Smart Charging UsbPort(2)\"\r\n" + "            ]\r\n" + "          },\r\n"
				+ "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645.70\",\r\n" + "              \"Savings\": \"4988.20\",\r\n"
				+ "              \"finalPrice\": \"28657.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void saveVehicleTestFail3() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32\",\r\n"
				+ "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645.70\",\r\n" + "              \"Savings\": \"4988.20\",\r\n"
				+ "              \"finalPrice\": \"28657.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void saveVehicleTestFail4() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32\",\r\n"
				+ "          \"vehicleFeature\": {\r\n" + "            \"Interior\": [\r\n"
				+ "              \"Autodim Rearview Mirror\",\r\n" + "              \"Center Console W/Armrest\",\r\n"
				+ "              \"Floor Mats - Front\",\r\n" + "              \"Smart Charging UsbPort(2)\"\r\n"
				+ "            ]\r\n" + "          },\r\n" + "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645.70\",\r\n" + "              \"Savings\": \"4988.20\",\r\n"
				+ "              \"finalPrice\": \"28657.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void saveVehicleTestFail5() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32\",\r\n"
				+ "          \"vehicleFeature\": {\r\n" + "            \"Exterior\": [\r\n"
				+ "              \"Dual Exhaust System\",\r\n" + "              \"Easy Fuel Capless Filler\",\r\n"
				+ "              \"Headlamps - Autolamp\",\r\n"
				+ "              \"Headlamps- Led With Signature Lighting\"\r\n" + "            ],\r\n"
				+ "            \"Interior\": [\r\n" + "              \"Autodim Rearview Mirror\",\r\n"
				+ "              \"Center Console W/Armrest\",\r\n" + "              \"Floor Mats - Front\",\r\n"
				+ "              \"Smart Charging UsbPort(2)\"\r\n" + "            ]\r\n" + "          },\r\n"
				+ "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645ui.70\",\r\n" + "              \"Savings\": \"4988.20\",\r\n"
				+ "              \"finalPrice\": \"28657.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void saveVehicleTestFail6() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		when(vehicleRepository.save(Mockito.any(com.vehicle.entity.Vehicle.class))).thenReturn(entity);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32\",\r\n"
				+ "          \"vehicleFeature\": {\r\n" + "            \"Exterior\": [\r\n"
				+ "              \"Dual Exhaust System\",\r\n" + "              \"Easy Fuel Capless Filler\",\r\n"
				+ "              \"Headlamps - Autolamp\",\r\n"
				+ "              \"Headlamps- Led With Signature Lighting\"\r\n" + "            ],\r\n"
				+ "            \"Interior\": [\r\n" + "              \"Autodim Rearview Mirror\",\r\n"
				+ "              \"Center Console W/Armrest\",\r\n" + "              \"Floor Mats - Front\",\r\n"
				+ "              \"Smart Charging UsbPort(2)\"\r\n" + "            ]\r\n" + "          },\r\n"
				+ "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645.70\",\r\n" + "              \"Savings\": \"4988jk.20\",\r\n"
				+ "              \"finalPrice\": \"28657.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void saveVehicleTestFail7() throws JsonMappingException, JsonProcessingException {
		com.vehicle.entity.Vehicle entity = new com.vehicle.entity.Vehicle();
		entity.setVehicleId(7);
		when(vehicleRepository.save(Mockito.any(com.vehicle.entity.Vehicle.class))).thenReturn(entity);
		String jsonString = " {\r\n" + "        \"vehicleId\": \"104\",\r\n" + "        \"vehicleDetails\": {\r\n"
				+ "          \"make\": \"Ford\",\r\n" + "          \"model\": \"mustang2\",\r\n"
				+ "          \"modelYear\": \"2017\",\r\n" + "          \"bodyStyle\": \"4D Sport Utility\",\r\n"
				+ "          \"engine\": \"V8\",\r\n" + "          \"drivetype\": \"RWD\",\r\n"
				+ "          \"color\": \"Blue Metallic\",\r\n" + "          \"MPG\": \"32\",\r\n"
				+ "          \"vehicleFeature\": {\r\n" + "            \"Exterior\": [\r\n"
				+ "              \"Dual Exhaust System\",\r\n" + "              \"Easy Fuel Capless Filler\",\r\n"
				+ "              \"Headlamps - Autolamp\",\r\n"
				+ "              \"Headlamps- Led With Signature Lighting\"\r\n" + "            ],\r\n"
				+ "            \"Interior\": [\r\n" + "              \"Autodim Rearview Mirror\",\r\n"
				+ "              \"Center Console W/Armrest\",\r\n" + "              \"Floor Mats - Front\",\r\n"
				+ "              \"Smart Charging UsbPort(2)\"\r\n" + "            ]\r\n" + "          },\r\n"
				+ "          \"vehiclePrice\": [\r\n" + "            {\r\n"
				+ "              \"MSRP\": \"33645.70\",\r\n" + "              \"Savings\": \"4988.20\",\r\n"
				+ "              \"finalPrice\": \"28657kl.50\"\r\n" + "            }\r\n" + "          ]\r\n"
				+ "        }\r\n" + "      }";
		Vehicle vehicle = new ObjectMapper().readValue(jsonString, Vehicle.class);
		ResponseEntity<?> response = serv.saveVehicle(vehicle);
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesTest() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("exter,exter2");
		vehicle.setInterior("inter,inter2");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(67.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);
		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehicles("ALL");
		assertNotNull(response);
		ModelData body = (ModelData) response.getBody();
		assertEquals("make", body.getVehicles().getVehicle().get(0).getVehicleDetails().getMake().toString());
	}

	@Test
	void getVehiclesFailTest() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setExterior("exter,exter2");
		vehicle.setInterior("inter,inter2");
		entities.add(vehicle);
		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehicles("ALL");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesFail2Test() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("exter,exter2");
		vehicle.setInterior("inter,inter2");
		entities.add(vehicle);
		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehicles("ALL");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesFail3Test() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("inter,inter2");
		entities.add(vehicle);
		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehicles("ALL");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesFail4Test() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setInterior("inter,inter2");
		entities.add(vehicle);
		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehicles("ALL");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesByModelNameTest() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("exter,exter2");
		vehicle.setInterior("inter,inter2");
		entities.add(vehicle);
		when(vehicleRepository.findByModel("test")).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehicles("test");
		assertNotNull(response);
		ModelData body = (ModelData) response.getBody();
		assertEquals("make", body.getVehicles().getVehicle().get(0).getVehicleDetails().getMake().toString());
	}

	@Test
	void getVehiclesByPriceTest() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("exter,exter2");
		vehicle.setInterior("inter,inter2");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(32.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehiclesByPrice("30", "35");
		assertNotNull(response);
		ModelData body = (ModelData) response.getBody();
		assertEquals("make", body.getVehicles().getVehicle().get(0).getVehicleDetails().getMake().toString());
	}

	@Test
	void getVehiclesByPriceFailTest() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("exter,exter2");
		vehicle.setInterior("inter,inter2");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(32.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehiclesByPrice("30", "35");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesByPriceFail2Test() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setModelYear(2017);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setExterior("exter,exter2");
		vehicle.setInterior("inter,inter2");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(32.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehiclesByPrice("30", "35");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesByPriceFail3Test() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setModelYear(2017);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setInterior("inter,inter2");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(32.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehiclesByPrice("30", "35");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getVehiclesByPriceFail4Test() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModelYear(2017);
		vehicle.setModel("2017");
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("exter,exter2");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(32.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getVehiclesByPrice("30", "35");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

	@Test
	void getByExteriorAndInteriorTest() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("test,test");
		vehicle.setInterior("test,test");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(67.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getByExteriorAndInterior("test", "test");
		assertNotNull(response);
		ModelData body = (ModelData) response.getBody();
		assertEquals("make", body.getVehicles().getVehicle().get(0).getVehicleDetails().getMake().toString());
	}

	@Test
	void getByExteriorAndInteriorFailTest() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setModelYear(2017);
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("test,test");
		vehicle.setInterior("test,test");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(67.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		ResponseEntity<?> response = serv.getByExteriorAndInterior("te", "test");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("400", body.getStatusCode().toString());
	}

	@Test
	void getByExteriorAndInteriorFail2Test() {
		List<com.vehicle.entity.Vehicle> entities = new ArrayList<com.vehicle.entity.Vehicle>();
		com.vehicle.entity.Vehicle vehicle = new com.vehicle.entity.Vehicle();
		vehicle.setVehicleId(7);
		vehicle.setMake("make");
		vehicle.setModel("2017");
		vehicle.setBodyStyle("bodyStyle");
		vehicle.setEngine("engine");
		vehicle.setDrivetype("drivetype");
		vehicle.setColor("color");
		vehicle.setMPG(20);
		vehicle.setExterior("test,test");
		vehicle.setInterior("test,test");

		List<com.vehicle.entity.VehiclePrice> vehiclePricesEntities = new ArrayList<com.vehicle.entity.VehiclePrice>();
		com.vehicle.entity.VehiclePrice vehiclePriceEntity = new com.vehicle.entity.VehiclePrice();
		vehiclePriceEntity.setFinalPrice(67.0);
		vehiclePriceEntity.setMSRP(67.0);
		vehiclePriceEntity.setSavings(89.0);
		vehiclePricesEntities.add(vehiclePriceEntity);

		vehicle.setVehiclePrice(vehiclePricesEntities);
		entities.add(vehicle);

		when(vehicleRepository.findAll()).thenReturn(entities);
		ResponseEntity<?> response = serv.getByExteriorAndInterior("test", "test");
		assertNotNull(response);
		ErrorResponse body = (ErrorResponse) response.getBody();
		assertEquals("500", body.getStatusCode().toString());
	}

}
