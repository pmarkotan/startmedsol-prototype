package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.FeedBackMessage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FeedBackMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeedBackMessageRepository extends JpaRepository<FeedBackMessage, Long> {
}
