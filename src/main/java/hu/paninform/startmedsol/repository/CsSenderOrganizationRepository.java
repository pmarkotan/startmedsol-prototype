package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.CsSenderOrganization;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CsSenderOrganization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CsSenderOrganizationRepository extends JpaRepository<CsSenderOrganization, Long> {
}
