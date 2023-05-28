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

import BI.Entities.HandlingSystem;
import BI.Repositories.HandlingSystemRepo;


@RestController
public class HandlingSystemController {
	
	@Autowired
	private HandlingSystemRepo handlingSystemRepository;


	@PostMapping("/handlingsystems")
	public ResponseEntity<?> addHandlingSystem(@RequestBody HandlingSystem handlingSystem) {
	    try {
	        handlingSystemRepository.save(handlingSystem);
	        return ResponseEntity.status(HttpStatus.OK).body(handlingSystem);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@GetMapping("/handlingsystems")
	public ResponseEntity<?> getHandlingSystems() {
	    try {
	        List<HandlingSystem> handlingSystems = handlingSystemRepository.findAll();
	        return ResponseEntity.status(HttpStatus.OK).body(handlingSystems);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has occurred!");
	    }
	}

	@GetMapping("/handlingsystems/{id}")
	public ResponseEntity<?> getHandlingSystemById(@PathVariable String id) {
	    try {
	        HandlingSystem handlingSystem = handlingSystemRepository.findById(Integer.parseInt(id)).orElse(null);
	        ResponseEntity<?> responseEntity = null;
	        if (handlingSystem != null) {
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(handlingSystem);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Cannot find the handling system you are looking for!");
	        }
	        return responseEntity;
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@DeleteMapping("/handlingsystems/{id}")
	public ResponseEntity<?> deleteHandlingSystem(@PathVariable String id) {
	    ResponseEntity<?> responseEntity = null;
	    try {
	        HandlingSystem handlingSystem = handlingSystemRepository.findById(Integer.parseInt(id)).orElse(null);
	        if (handlingSystem != null) {
	            handlingSystemRepository.delete(handlingSystem);
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(handlingSystem);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The handling system is not found!");
	        }
	    } catch (Exception e) {
	        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
	    }
	    return responseEntity;
	}

	@PutMapping("/handlingsystems/{id}")
	public ResponseEntity<HandlingSystem> updateHandlingSystem(@PathVariable String id,
	        @RequestBody HandlingSystem updatedHandlingSystem) {
	    Optional<HandlingSystem> optionalHandlingSystem = handlingSystemRepository.findById(Integer.parseInt(id));
	    if (optionalHandlingSystem.isPresent()) {
	        HandlingSystem handlingSystem = optionalHandlingSystem.get();
	        handlingSystem.setGearboxType(updatedHandlingSystem.getGearboxType());
	        handlingSystem.setDriveWheels(updatedHandlingSystem.getDriveWheels());
	        handlingSystem.setNumberOfGears(updatedHandlingSystem.getNumberOfGears());
	        HandlingSystem savedHandlingSystem = handlingSystemRepository.save(handlingSystem);
	        return ResponseEntity.ok(savedHandlingSystem);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


}
