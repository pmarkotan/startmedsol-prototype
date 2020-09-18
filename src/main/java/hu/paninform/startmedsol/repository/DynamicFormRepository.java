package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.DynamicForm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DynamicForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DynamicFormRepository extends JpaRepository<DynamicForm, Long> {
}
