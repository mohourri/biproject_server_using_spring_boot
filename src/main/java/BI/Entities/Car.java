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
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_car")
    private Integer idCar;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "serie")
    private String serie;

    @Column(name = "number_of_seater")
    private Integer numberOfSeater;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "fuel_tank_capacity")
    private Integer fuelTankCapacity;

}
