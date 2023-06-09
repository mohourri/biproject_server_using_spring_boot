package BI.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Generation {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "Id_generation")
	    private Integer idGeneration;

	    @Column(name = "generation")
	    private String generation;

	    @Column(name = "year_from")
	    private Integer yearFrom;

	    @Column(name = "year_to")
	    private Integer yearTo;

}
