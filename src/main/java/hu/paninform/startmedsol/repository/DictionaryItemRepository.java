package hu.paninform.startmedsol.repository;

import hu.paninform.startmedsol.domain.DictionaryItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DictionaryItem entity.
 */
@Repository
public interface DictionaryItemRepository extends JpaRepository<DictionaryItem, Long> {

    @Query(value = "select distinct dictionaryItem from DictionaryItem dictionaryItem left join fetch dictionaryItem.parents",
        countQuery = "select count(distinct dictionaryItem) from DictionaryItem dictionaryItem")
    Page<DictionaryItem> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct dictionaryItem from DictionaryItem dictionaryItem left join fetch dictionaryItem.parents")
    List<DictionaryItem> findAllWithEagerRelationships();

    @Query("select dictionaryItem from DictionaryItem dictionaryItem left join fetch dictionaryItem.parents where dictionaryItem.id =:id")
    Optional<DictionaryItem> findOneWithEagerRelationships(@Param("id") Long id);
}
