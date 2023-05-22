package BI.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BI.Entities.Consommation;
import BI.Repositories.ConsommationRepository;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
public class MainController {
	@Autowired
	private ConsommationRepository cr;


    // Add new Consommation
    @PostMapping("/consommations")
    public ResponseEntity<?> addConsommation(@RequestBody Consommation consommation) {
        try {
            cr.save(consommation);
            return ResponseEntity.status(HttpStatus.OK).body(consommation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
        }
    }
    
	// Get all Consommations
	@GetMapping("/consommations")
	public ResponseEntity<?> getConsommations(){
		System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey");
		try {
			List<Consommation> conss= cr.findAll();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(conss);

		}catch(Exception e) {
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An errorr hasssss beeeen occured !");

		}
	}
	
	 // Get Consommation by ID
    @GetMapping("/consommations/{id}")
    public ResponseEntity<?> getConsommationByID(@PathVariable String id) {
        try {
            Consommation consommation = cr.findById(Long.parseLong(id)).orElse(null);
            ResponseEntity<?> responseEntity = null;
            if (consommation != null) {
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(consommation);
            } else {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cannot find the consommation you are looking for!");
            }
            return responseEntity;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
        }
    }
    
 // Delete Consommation
    @Transactional
    @DeleteMapping("/consommations/{id}")
    public ResponseEntity<?> deleteConsommation(@PathVariable String id) {
        ResponseEntity<?> responseEntity = null;
        try {
            Consommation consommation = cr.findById(Long.parseLong(id)).orElse(null);
            if (consommation != null) {
                cr.delete(consommation);
                responseEntity = ResponseEntity.status(200).body(consommation);
            } else {
                responseEntity = ResponseEntity.status(404).body("The consommation is not found!");
            }
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
        }
        return responseEntity;
    }
    
 // Update Consommation
    @Transactional
    @PutMapping("/consommations/{id}")
    public ResponseEntity<Consommation> updateConsommation(@PathVariable Long id,
            @RequestBody Consommation updatedConsommation) {
        Optional<Consommation> optionalConsommation = cr.findById(id);
        if (optionalConsommation.isPresent()) {
            Consommation consommation = optionalConsommation.get();
            consommation.setMark(updatedConsommation.getMark());
            consommation.setModel(updatedConsommation.getModel());
            consommation.setFuel(updatedConsommation.getFuel());
            consommation.setConsommation(updatedConsommation.getConsommation());
            consommation.setMarque(updatedConsommation.getMarque());
            consommation.setModele(updatedConsommation.getModele());
            consommation.setCarburant(updatedConsommation.getCarburant());
            Consommation savedConsommation = cr.save(consommation);
            return ResponseEntity.ok(savedConsommation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
