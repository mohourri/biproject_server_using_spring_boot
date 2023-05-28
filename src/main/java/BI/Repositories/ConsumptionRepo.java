package BI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import BI.Entities.Consumption;

public interface ConsumptionRepo extends JpaRepository<Consumption, Integer>{

}
