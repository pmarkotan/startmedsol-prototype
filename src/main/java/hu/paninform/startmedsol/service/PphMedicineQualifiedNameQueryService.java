package hu.paninform.startmedsol.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import hu.paninform.startmedsol.domain.PphMedicineQualifiedName;
import hu.paninform.startmedsol.domain.*; // for static metamodels
import hu.paninform.startmedsol.repository.PphMedicineQualifiedNameRepository;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameCriteria;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameDTO;
import hu.paninform.startmedsol.service.mapper.PphMedicineQualifiedNameMapper;

/**
 * Service for executing complex queries for {@link PphMedicineQualifiedName} entities in the database.
 * The main input is a {@link PphMedicineQualifiedNameCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PphMedicineQualifiedNameDTO} or a {@link Page} of {@link PphMedicineQualifiedNameDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PphMedicineQualifiedNameQueryService extends QueryService<PphMedicineQualifiedName> {

    private final Logger log = LoggerFactory.getLogger(PphMedicineQualifiedNameQueryService.class);

    private final PphMedicineQualifiedNameRepository pphMedicineQualifiedNameRepository;

    private final PphMedicineQualifiedNameMapper pphMedicineQualifiedNameMapper;

    public PphMedicineQualifiedNameQueryService(PphMedicineQualifiedNameRepository pphMedicineQualifiedNameRepository, PphMedicineQualifiedNameMapper pphMedicineQualifiedNameMapper) {
        this.pphMedicineQualifiedNameRepository = pphMedicineQualifiedNameRepository;
        this.pphMedicineQualifiedNameMapper = pphMedicineQualifiedNameMapper;
    }

    /**
     * Return a {@link List} of {@link PphMedicineQualifiedNameDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PphMedicineQualifiedNameDTO> findByCriteria(PphMedicineQualifiedNameCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PphMedicineQualifiedName> specification = createSpecification(criteria);
        return pphMedicineQualifiedNameMapper.toDto(pphMedicineQualifiedNameRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PphMedicineQualifiedNameDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PphMedicineQualifiedNameDTO> findByCriteria(PphMedicineQualifiedNameCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PphMedicineQualifiedName> specification = createSpecification(criteria);
        return pphMedicineQualifiedNameRepository.findAll(specification, page)
            .map(pphMedicineQualifiedNameMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PphMedicineQualifiedNameCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PphMedicineQualifiedName> specification = createSpecification(criteria);
        return pphMedicineQualifiedNameRepository.count(specification);
    }

    /**
     * Function to convert {@link PphMedicineQualifiedNameCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PphMedicineQualifiedName> createSpecification(PphMedicineQualifiedNameCriteria criteria) {
        Specification<PphMedicineQualifiedName> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PphMedicineQualifiedName_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PphMedicineQualifiedName_.name));
            }
            if (criteria.getActiveSubstance() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActiveSubstance(), PphMedicineQualifiedName_.activeSubstance));
            }
            if (criteria.getActivePuphaData() != null) {
                specification = specification.and(buildSpecification(criteria.getActivePuphaData(), PphMedicineQualifiedName_.activePuphaData));
            }
            if (criteria.getMedicineId() != null) {
                specification = specification.and(buildSpecification(criteria.getMedicineId(),
                    root -> root.join(PphMedicineQualifiedName_.medicines, JoinType.LEFT).get(PphMedicine_.id)));
            }
        }
        return specification;
    }
}
