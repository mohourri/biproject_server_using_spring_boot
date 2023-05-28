package BI.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import BI.Entities.Car;

public interface CarRepo extends JpaRepository<Car, Integer> {
	
	
}
