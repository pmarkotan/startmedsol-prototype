package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphPuphaInstitution;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphPuphaInstitution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphPuphaInstitutionRepository extends JpaRepository<PphPuphaInstitution, Long> {
}
