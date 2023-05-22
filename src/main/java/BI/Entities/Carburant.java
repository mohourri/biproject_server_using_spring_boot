package BI.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "Carburant")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Carburant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_carburant;

    private String carburant;

}
