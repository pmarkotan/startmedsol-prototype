package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.SpecialistsAdvice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SpecialistsAdvice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecialistsAdviceRepository extends JpaRepository<SpecialistsAdvice, Long> {
}
