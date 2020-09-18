package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.CodeSetLoad;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CodeSetLoad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodeSetLoadRepository extends JpaRepository<CodeSetLoad, Long> {
}
