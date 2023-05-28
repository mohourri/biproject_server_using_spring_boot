package BI.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter

public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_consumption")
    private Integer idConsumption;

    @Column(name = "city_consumption")
    private Float cityConsumption;

    @Column(name = "highway_consumption")
    private Float highwayConsumption;

    @Column(name = "mixed_consumption")
    private Float mixedConsumption;

    @ManyToOne
    @JoinColumn(name = "Id_car")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "Id_generation")
    private Generation generation;

    @ManyToOne
    @JoinColumn(name = "Id_engine")
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "Id_operating_chars")
    private OperatingChars operatingChars;

    @ManyToOne
    @JoinColumn(name = "Id_handling_system")
    private HandlingSystem handlingSystem;

}
