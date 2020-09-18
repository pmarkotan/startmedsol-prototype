package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.BillingInformation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillingInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillingInformationRepository extends JpaRepository<BillingInformation, Long> {
}
