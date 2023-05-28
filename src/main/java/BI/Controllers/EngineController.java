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

import BI.Entities.Engine;
import BI.Repositories.EngineRepo;

@RestController
public class EngineController {
	@Autowired
	private EngineRepo engineRepository;

	@PostMapping("/engines")
	public ResponseEntity<?> addEngine(@RequestBody Engine engine) {
	    try {
	        engineRepository.save(engine);
	        return ResponseEntity.status(HttpStatus.OK).body(engine);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@GetMapping("/engines")
	public ResponseEntity<?> getEngines() {
	    try {
	        List<Engine> engineList = engineRepository.findAll();
	        return ResponseEntity.status(HttpStatus.OK).body(engineList);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has occurred!");
	    }
	}

	@GetMapping("/engines/{id}")
	public ResponseEntity<?> getEngineById(@PathVariable String id) {
	    try {
	        Engine engine = engineRepository.findById(Integer.parseInt(id)).orElse(null);
	        ResponseEntity<?> responseEntity = null;
	        if (engine != null) {
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(engine);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Cannot find the engine you are looking for!");
	        }
	        return responseEntity;
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@DeleteMapping("/engines/{id}")
	public ResponseEntity<?> deleteEngine(@PathVariable String id) {
	    ResponseEntity<?> responseEntity = null;
	    try {
	        Engine engine = engineRepository.findById(Integer.parseInt(id)).orElse(null);
	        if (engine != null) {
	            engineRepository.delete(engine);
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(engine);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The engine is not found!");
	        }
	    } catch (Exception e) {
	        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
	    }
	    return responseEntity;
	}

	@PutMapping("/engines/{id}")
	public ResponseEntity<Engine> updateEngine(@PathVariable String id, @RequestBody Engine updatedEngine) {
	    Optional<Engine> optionalEngine = engineRepository.findById(Integer.parseInt(id));
	    if (optionalEngine.isPresent()) {
	        Engine engine = optionalEngine.get();
	        engine.setEngineType(updatedEngine.getEngineType());
	        engine.setEnginePower(updatedEngine.getEnginePower());
	        engine.setNumberOfCylinders(updatedEngine.getNumberOfCylinders());
	        engine.setCapacity(updatedEngine.getCapacity());
	        engine.setMaxPower(updatedEngine.getMaxPower());
	        engine.setMaxTorque(updatedEngine.getMaxTorque());
	        engine.setInjectionType(updatedEngine.getInjectionType());
	        Engine savedEngine = engineRepository.save(engine);
	        return ResponseEntity.ok(savedEngine);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	
}
