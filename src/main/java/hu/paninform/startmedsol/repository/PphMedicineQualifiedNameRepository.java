package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphMedicineQualifiedName;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphMedicineQualifiedName entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphMedicineQualifiedNameRepository extends JpaRepository<PphMedicineQualifiedName, Long>, JpaSpecificationExecutor<PphMedicineQualifiedName> {
}
