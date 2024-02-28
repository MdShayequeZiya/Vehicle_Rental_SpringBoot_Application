package com.ff.rentalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ff.rentalApp.dao.VehicleDao;
import com.ff.rentalApp.dto.ResponseStructure;
import com.ff.rentalApp.entity.Vehicle;
import com.ff.rentalApp.exception.ApplicationException;

@Service
public class VehicleService {
	@Autowired
	private VehicleDao vehicleDao;

	public ResponseEntity<ResponseStructure<Vehicle>> saveVehicle(Vehicle vehicle) {
		Vehicle receivedVehicle = vehicleDao.saveVehicle(vehicle);
		ResponseStructure<Vehicle> response = new ResponseStructure<Vehicle>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("vehicle is added");
		response.setData(receivedVehicle);
		return new ResponseEntity<ResponseStructure<Vehicle>>(response, HttpStatus.CREATED);

	}

	// update vehicle
	public ResponseEntity<ResponseStructure<Vehicle>> updateVehicle(int id, Vehicle vehicle) {
		Vehicle receivedVehicle = vehicleDao.updateVehicle(id, vehicle);
		if (receivedVehicle != null) {
			ResponseStructure<Vehicle> response = new ResponseStructure<Vehicle>();
			response.setData(receivedVehicle);
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Vehicle is updated");
			return new ResponseEntity<ResponseStructure<Vehicle>>(response, HttpStatus.CREATED);
		} else
			throw new ApplicationException(("Vehicle not found with ID: " + id));

	}
	
	
	//get vehicle details
	public ResponseEntity<ResponseStructure<Vehicle>>findVehicle(int id){
	Vehicle receivedVehicle=	vehicleDao.findVehicle(id);
	if(receivedVehicle!=null) {
		ResponseStructure<Vehicle> response = new ResponseStructure<Vehicle>();
		response.setData(receivedVehicle);
		response.setStatusCode(HttpStatus.FOUND.value());
		response.setMessage("Vehicle is found");
		return new ResponseEntity<ResponseStructure<Vehicle>>(response, HttpStatus.FOUND);
	} else
		throw new ApplicationException(("Vehicle not found with ID: " + id));

	}
	}
	
	
	

