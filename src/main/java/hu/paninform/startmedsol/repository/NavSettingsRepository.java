package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.NavSettings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NavSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NavSettingsRepository extends JpaRepository<NavSettings, Long> {
}
