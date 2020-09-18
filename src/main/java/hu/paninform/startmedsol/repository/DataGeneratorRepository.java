package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.DataGenerator;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DataGenerator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataGeneratorRepository extends JpaRepository<DataGenerator, Long> {
}
