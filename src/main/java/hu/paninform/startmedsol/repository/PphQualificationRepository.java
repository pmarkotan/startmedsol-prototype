package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphQualification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphQualification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphQualificationRepository extends JpaRepository<PphQualification, Long> {
}
