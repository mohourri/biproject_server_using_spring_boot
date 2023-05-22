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
import BI.Repositories.MarqueRepository;
@RestController
public class MarqueController {
	@Autowired
	private MarqueRepository mr;

    // Add new Marque
    @PostMapping("/marques")
    public ResponseEntity<?> addConsommation(@RequestBody Marque m) {
        try {
            mr.save(m);
            return ResponseEntity.status(HttpStatus.OK).body(m);
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong!");
        }
    }
    
	// Get all Marques
	@GetMapping("/marques")
	public ResponseEntity<?> getMarques(){
		try {
			List<Marque> conss= mr.findAll();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(conss);

		}catch(Exception e) {
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("An error has been occured !");

		}
	}
	
	 // Get marque by ID
    @GetMapping("/marques/{id}")
    public ResponseEntity<?> getMarquenByID(@PathVariable String id) {
        try {
        	Marque m = mr.findById(Long.parseLong(id)).orElse(null);
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
    
 // Delete marque
    @Transactional
    @DeleteMapping("/marques/{id}")
    public ResponseEntity<?> deleteMarque(@PathVariable String id) {
        ResponseEntity<?> responseEntity = null;
        try {
        	Marque m = mr.findById(Long.parseLong(id)).orElse(null);
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
    
 // Update marques
    @Transactional
    @PutMapping("/marques/{id}")
    public ResponseEntity<Marque> updateMarque(@PathVariable Long id,
            @RequestBody Marque updatedMarque) {
        Optional<Marque> optionalMarque = mr.findById(id);
        if (optionalMarque.isPresent()) {
        	Marque m = optionalMarque.get();
            m.setMarque(updatedMarque.getMarque());
            Marque savedMarque = mr.save(m);
            return ResponseEntity.ok(savedMarque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
