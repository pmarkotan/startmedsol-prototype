package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.Provider;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Provider entity.
 */
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Query(value = "select distinct provider from Provider provider left join fetch provider.employees",
        countQuery = "select count(distinct provider) from Provider provider")
    Page<Provider> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct provider from Provider provider left join fetch provider.employees")
    List<Provider> findAllWithEagerRelationships();

    @Query("select provider from Provider provider left join fetch provider.employees where provider.id =:id")
    Optional<Provider> findOneWithEagerRelationships(@Param("id") Long id);
}
