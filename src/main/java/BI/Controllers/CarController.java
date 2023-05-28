package BI.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BI.Entities.Car;
import BI.Repositories.CarRepo;

@RestController
public class CarController {
	
	@Autowired
	CarRepo carRepository;
	
	@PostMapping("/cars")
	public ResponseEntity<?> addCar(@RequestBody Car car) {
	    try {
	        carRepository.save(car);
	        return ResponseEntity.status(HttpStatus.OK).body(car);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@GetMapping("/cars")
	public ResponseEntity<?> getCars() {
	    try {
	        List<Car> cars = carRepository.findAll();
	        return ResponseEntity.status(HttpStatus.OK).body(cars);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has occurred!");
	    }
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<?> getCarById(@PathVariable String id) {
	    try {
	        Car car = carRepository.findById(Integer.parseInt(id)).orElse(null);
	        ResponseEntity<?> responseEntity = null;
	        if (car != null) {
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(car);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Cannot find the car you are looking for!");
	        }
	        return responseEntity;
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable String id) {
	    ResponseEntity<?> responseEntity = null;
	    try {
	        Car car = carRepository.findById(Integer.parseInt(id)).orElse(null);
	        if (car != null) {
	            carRepository.delete(car);
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(car);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The car is not found!");
	        }
	    } catch (Exception e) {
	        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
	    }
	    return responseEntity;
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable String id, @RequestBody Car updatedCar) {
	    Optional<Car> optionalCar = carRepository.findById(Integer.parseInt(id));
	    if (optionalCar.isPresent()) {
	        Car car = optionalCar.get();
	        car.setMake(updatedCar.getMake());
	        car.setModel(updatedCar.getModel());
	        car.setSerie(updatedCar.getSerie());
	        car.setNumberOfSeater(updatedCar.getNumberOfSeater());
	        car.setBodyType(updatedCar.getBodyType());
	        car.setFuelTankCapacity(updatedCar.getFuelTankCapacity());
	        Car savedCar = carRepository.save(car);
	        return ResponseEntity.ok(savedCar);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
	
}
