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

public class HandlingSystem {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "Id_handling_system")
	    private Integer idHandlingSystem;

	    @Column(name = "gearbox_type")
	    private String gearboxType;

	    @Column(name = "drive_wheels")
	    private String driveWheels;

	    @Column(name = "number_of_gears")
	    private Integer numberOfGears;

}
