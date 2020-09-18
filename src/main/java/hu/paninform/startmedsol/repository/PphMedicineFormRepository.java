package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphMedicineForm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphMedicineForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphMedicineFormRepository extends JpaRepository<PphMedicineForm, Long> {
}
