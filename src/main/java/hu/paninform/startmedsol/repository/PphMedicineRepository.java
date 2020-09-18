package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.PphMedicine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the PphMedicine entity.
 */
@Repository
public interface PphMedicineRepository extends JpaRepository<PphMedicine, Long> {

    @Query(value = "select distinct pphMedicine from PphMedicine pphMedicine left join fetch pphMedicine.euScores",
        countQuery = "select count(distinct pphMedicine) from PphMedicine pphMedicine")
    Page<PphMedicine> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct pphMedicine from PphMedicine pphMedicine left join fetch pphMedicine.euScores")
    List<PphMedicine> findAllWithEagerRelationships();

    @Query("select pphMedicine from PphMedicine pphMedicine left join fetch pphMedicine.euScores where pphMedicine.id =:id")
    Optional<PphMedicine> findOneWithEagerRelationships(@Param("id") Long id);
}
