package BI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BI.Entities.Marque;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Long> {

}
