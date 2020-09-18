package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.MedicalCaseDiagnosis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalCaseDiagnosis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalCaseDiagnosisRepository extends JpaRepository<MedicalCaseDiagnosis, Long> {
}
