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

import BI.Entities.OperatingChars;
import BI.Repositories.OperatingCharsRepo;

@RestController
public class OperatingCharsController {

	@Autowired
	private OperatingCharsRepo operatingCharsRepository;

	
	@PostMapping("/operatingchars")
	public ResponseEntity<?> addOperatingChars(@RequestBody OperatingChars operatingChars) {
	    try {
	        operatingCharsRepository.save(operatingChars);
	        return ResponseEntity.status(HttpStatus.OK).body(operatingChars);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@GetMapping("/operatingchars")
	public ResponseEntity<?> getOperatingChars() {
	    try {
	        List<OperatingChars> operatingCharsList = operatingCharsRepository.findAll();
	        return ResponseEntity.status(HttpStatus.OK).body(operatingCharsList);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has occurred!");
	    }
	}

	@GetMapping("/operatingchars/{id}")
	public ResponseEntity<?> getOperatingCharsById(@PathVariable String id) {
	    try {
	        OperatingChars operatingChars = operatingCharsRepository.findById(Integer.parseInt(id)).orElse(null);
	        ResponseEntity<?> responseEntity = null;
	        if (operatingChars != null) {
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(operatingChars);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Cannot find the operating characters you are looking for!");
	        }
	        return responseEntity;
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@DeleteMapping("/operatingchars/{id}")
	public ResponseEntity<?> deleteOperatingChars(@PathVariable String id) {
	    ResponseEntity<?> responseEntity = null;
	    try {
	        OperatingChars operatingChars = operatingCharsRepository.findById(Integer.parseInt(id)).orElse(null);
	        if (operatingChars != null) {
	            operatingCharsRepository.delete(operatingChars);
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(operatingChars);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The operating characters are not found!");
	        }
	    } catch (Exception e) {
	        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
	    }
	    return responseEntity;
	}

	@PutMapping("/operatingchars/{id}")
	public ResponseEntity<OperatingChars> updateOperatingChars(@PathVariable String id,
	        @RequestBody OperatingChars updatedOperatingChars) {
	    Optional<OperatingChars> optionalOperatingChars = operatingCharsRepository.findById(Integer.parseInt(id));
	    if (optionalOperatingChars.isPresent()) {
	        OperatingChars operatingChars = optionalOperatingChars.get();
	        operatingChars.setCruisingRange(updatedOperatingChars.getCruisingRange());
	        operatingChars.setAcceleration(updatedOperatingChars.getAcceleration());
	        operatingChars.setMaxSpeed(updatedOperatingChars.getMaxSpeed());
	        operatingChars.setFuel(updatedOperatingChars.getFuel());
	        OperatingChars savedOperatingChars = operatingCharsRepository.save(operatingChars);
	        return ResponseEntity.ok(savedOperatingChars);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
