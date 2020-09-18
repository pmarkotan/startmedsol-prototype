package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphSpecBudget;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphSpecBudget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphSpecBudgetRepository extends JpaRepository<PphSpecBudget, Long> {
}
