package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PrescriptionEesztId;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrescriptionEesztId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrescriptionEesztIdRepository extends JpaRepository<PrescriptionEesztId, Long> {
}
