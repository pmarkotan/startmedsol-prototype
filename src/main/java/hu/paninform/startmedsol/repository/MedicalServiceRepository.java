package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.MedicalService;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
}
