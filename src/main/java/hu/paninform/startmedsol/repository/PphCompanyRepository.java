package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphCompany;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphCompanyRepository extends JpaRepository<PphCompany, Long> {
}
