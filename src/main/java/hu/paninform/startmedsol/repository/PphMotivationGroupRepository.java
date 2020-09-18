package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphMotivationGroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphMotivationGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphMotivationGroupRepository extends JpaRepository<PphMotivationGroup, Long> {
}
