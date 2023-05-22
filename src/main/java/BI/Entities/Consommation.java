package BI.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Consommation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consommation;

    @ManyToOne
    @JoinColumn(name = "id_marque")
    private Marque mark;

    @ManyToOne
    @JoinColumn(name = "id_modele")
    private Modele model;

    @ManyToOne
    @JoinColumn(name = "id_carburant")
    private Carburant fuel;

    

    private BigDecimal consommation;
    private String marque;
    private String modele;
    private String carburant;

}
