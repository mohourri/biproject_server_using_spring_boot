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

import BI.Entities.Generation;
import BI.Repositories.GenerationRepo;

@RestController
public class GenerationController {
	@Autowired
	private GenerationRepo generationRepository;

	@PostMapping("/generations")
	public ResponseEntity<?> addGeneration(@RequestBody Generation generation) {
	    try {
	        generationRepository.save(generation);
	        return ResponseEntity.status(HttpStatus.OK).body(generation);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@GetMapping("/generations")
	public ResponseEntity<?> getGenerations() {
	    try {
	        List<Generation> generations = generationRepository.findAll();
	        return ResponseEntity.status(HttpStatus.OK).body(generations);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has occurred!");
	    }
	}

	@GetMapping("/generations/{id}")
	public ResponseEntity<?> getGenerationById(@PathVariable String id) {
	    try {
	        Generation generation = generationRepository.findById(Integer.parseInt(id)).orElse(null);
	        ResponseEntity<?> responseEntity = null;
	        if (generation != null) {
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(generation);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Cannot find the generation you are looking for!");
	        }
	        return responseEntity;
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
	    }
	}

	@DeleteMapping("/generations/{id}")
	public ResponseEntity<?> deleteGeneration(@PathVariable String id) {
	    ResponseEntity<?> responseEntity = null;
	    try {
	        Generation generation = generationRepository.findById(Integer.parseInt(id)).orElse(null);
	        if (generation != null) {
	            generationRepository.delete(generation);
	            responseEntity = ResponseEntity.status(HttpStatus.OK).body(generation);
	        } else {
	            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("The generation is not found!");
	        }
	    } catch (Exception e) {
	        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
	    }
	    return responseEntity;
	}

	@PutMapping("/generations/{id}")
	public ResponseEntity<Generation> updateGeneration(@PathVariable String id, @RequestBody Generation updatedGeneration) {
	    Optional<Generation> optionalGeneration = generationRepository.findById(Integer.parseInt(id));
	    if (optionalGeneration.isPresent()) {
	        Generation generation = optionalGeneration.get();
	        generation.setGeneration(updatedGeneration.getGeneration());
	        generation.setYearFrom(updatedGeneration.getYearFrom());
	        generation.setYearTo(updatedGeneration.getYearTo());
	        Generation savedGeneration = generationRepository.save(generation);
	        return ResponseEntity.ok(savedGeneration);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
