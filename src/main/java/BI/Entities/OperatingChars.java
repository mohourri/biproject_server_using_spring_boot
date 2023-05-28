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

public class OperatingChars {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "Id_operating_chars")
	    private Integer idOperatingChars;

	    @Column(name = "cruising_range")
	    private String cruisingRange;

	    @Column(name = "acceleration")
	    private Float acceleration;

	    @Column(name = "max_speed")
	    private Integer maxSpeed;

	    @Column(name = "fuel")
	    private String fuel;
}
