package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.MedicalInvoice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalInvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalInvoiceRepository extends JpaRepository<MedicalInvoice, Long> {
}
