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

import BI.Entities.Consumption;
import BI.Repositories.ConsumptionRepo;

@RestController
public class ConsumptionController {

	@Autowired
	private ConsumptionRepo consumptionRepository;
	
	@PostMapping("/consumptions")
	public ResponseEntity<?> addConsumption(@RequestBody Consumption consumption) {
	    try {
	        consumptionRepository.save(consumption);
	        return ResponseEntity.status(HttpStatus.OK).body(consumption);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@GetMapping("/consumptions")
	public ResponseEntity<?> getConsumptions() {
	    try {
	        List<Consumption> consumptions = consumptionRepository.findAll();
	        return ResponseEntity.status(HttpStatus.OK).body(consumptions);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has occurred!");
	    }
	}

	@GetMapping("/consumptions/{id}")
	public ResponseEntity<?> getConsumptionById(@PathVariable String id) {
	    try {
	        Consumption consumption = consumptionRepository.findById(Integer.parseInt(id)).orElse(null);
	        ResponseEntity<?> responseEntity = null;
	        if (consumption != null) {
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(consumption);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Cannot find the consumption you are looking for!");
	        }
	        return responseEntity;
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@DeleteMapping("/consumptions/{id}")
	public ResponseEntity<?> deleteConsumption(@PathVariable String id) {
	    ResponseEntity<?> responseEntity = null;
	    try {
	        Consumption consumption = consumptionRepository.findById(Integer.parseInt(id)).orElse(null);
	        if (consumption != null) {
	            consumptionRepository.delete(consumption);
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(consumption);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The consumption is not found!");
	        }
	    } catch (Exception e) {
	        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
	    }
	    return responseEntity;
	}

	@PutMapping("/consumptions/{id}")
	public ResponseEntity<Consumption> updateConsumption(@PathVariable String id,
	        @RequestBody Consumption updatedConsumption) {
	    Optional<Consumption> optionalConsumption = consumptionRepository.findById(Integer.parseInt(id));
	    if (optionalConsumption.isPresent()) {
	        Consumption consumption = optionalConsumption.get();
	        consumption.setCityConsumption(updatedConsumption.getCityConsumption());
	        consumption.setHighwayConsumption(updatedConsumption.getHighwayConsumption());
	        consumption.setMixedConsumption(updatedConsumption.getMixedConsumption());
	        // Set other properties as needed
	        Consumption savedConsumption = consumptionRepository.save(consumption);
	        return ResponseEntity.ok(savedConsumption);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


}
