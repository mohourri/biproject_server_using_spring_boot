package BI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import BI.Entities.Generation;

public interface GenerationRepo extends JpaRepository<Generation, Integer> {

}
