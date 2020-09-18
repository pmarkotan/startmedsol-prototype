package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.ContactPerson;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ContactPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {
}
