package BI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BI.Entities.Consommation;

@Repository
public interface ConsommationRepository extends JpaRepository<Consommation, Long> {

}
