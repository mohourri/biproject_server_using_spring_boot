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

public class Engine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_engine")
    private Integer idEngine;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "engine_power")
    private Integer enginePower;

    @Column(name = "number_of_cylinders")
    private Integer numberOfCylinders;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "max_power")
    private String maxPower;

    @Column(name = "max_torque")
    private Integer maxTorque;

    @Column(name = "injection_type")
    private String injectionType;

}
