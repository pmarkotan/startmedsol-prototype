package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphQualificationDoctorAll;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PphQualificationDoctorAll entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PphQualificationDoctorAllRepository extends JpaRepository<PphQualificationDoctorAll, Long> {
}
