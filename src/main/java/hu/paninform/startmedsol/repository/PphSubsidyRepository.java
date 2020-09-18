package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphSubsidy;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphSubsidy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphSubsidyRepository extends JpaRepository<PphSubsidy, Long> {
}
