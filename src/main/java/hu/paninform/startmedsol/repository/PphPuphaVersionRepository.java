package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphPuphaVersion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphPuphaVersion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphPuphaVersionRepository extends JpaRepository<PphPuphaVersion, Long> {
}
