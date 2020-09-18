package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.CsDiagnosis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CsDiagnosis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsDiagnosisRepository extends JpaRepository<CsDiagnosis, Long>, JpaSpecificationExecutor<CsDiagnosis> {
}
