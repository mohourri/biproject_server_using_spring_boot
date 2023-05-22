package BI.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BI.Entities.Marque;
import BI.Entities.Modele;
import BI.Repositories.MarqueRepository;
import BI.Repositories.ModeleRepository;
@RestController
public class ModeleController {
	@Autowired
	private ModeleRepository mr;

    // Add new Modele
    @PostMapping("/modeles")
    public ResponseEntity<?> addModele(@RequestBody Modele m) {
        try {
            mr.save(m);
            return ResponseEntity.status(HttpStatus.OK).body(m);
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
        }
    }
    
	// Get all Modele
	@GetMapping("/modeles")
	public ResponseEntity<?> getModeles(){
		try {
			List<Modele> conss= mr.findAll();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(conss);

		}catch(Exception e) {
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has been occured !");

		}
	}
	
	 // Get Modele by ID
    @GetMapping("/modeles/{id}")
    public ResponseEntity<?> getModeleByID(@PathVariable String id) {
        try {
        	Modele m = mr.findById(Long.parseLong(id)).orElse(null);
            ResponseEntity<?> responseEntity = null;
            if (m != null) {
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(m);
            } else {
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cannot find the marque that you are looking for!");
            }
            return responseEntity;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
        }
    }
    
 // Delete modele
    @Transactional
    @DeleteMapping("/modele/{id}")
    public ResponseEntity<?> deleteModele(@PathVariable String id) {
        ResponseEntity<?> responseEntity = null;
        try {
        	Modele m = mr.findById(Long.parseLong(id)).orElse(null);
            if (m != null) {
                mr.delete(m);
                responseEntity = ResponseEntity.status(200).body(m);
            } else {
                responseEntity = ResponseEntity.status(404).body("The marque is not found!");
            }
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error has occurred!");
        }
        return responseEntity;
    }
    
 // Update modele
    @Transactional
    @PutMapping("/modeles/{id}")
    public ResponseEntity<Modele> updateMarque(@PathVariable Long id,
            @RequestBody Modele updatedModele) {
        Optional<Modele> optionalModele = mr.findById(id);
        if (optionalModele.isPresent()) {
        	Modele m = optionalModele.get();
            m.setModele(updatedModele.getModele());
            Modele savedModele = mr.save(m);
            return ResponseEntity.ok(savedModele);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
