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

import hu.paninform.startmedsol.domain.CsProcedure;
import hu.paninform.startmedsol.domain.*; // for static metamodels
import hu.paninform.startmedsol.repository.CsProcedureRepository;
import hu.paninform.startmedsol.service.dto.CsProcedureCriteria;
import hu.paninform.startmedsol.service.dto.CsProcedureDTO;
import hu.paninform.startmedsol.service.mapper.CsProcedureMapper;

/**
 * Service for executing complex queries for {@link CsProcedure} entities in the database.
 * The main input is a {@link CsProcedureCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CsProcedureDTO} or a {@link Page} of {@link CsProcedureDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CsProcedureQueryService extends QueryService<CsProcedure> {

    private final Logger log = LoggerFactory.getLogger(CsProcedureQueryService.class);

    private final CsProcedureRepository csProcedureRepository;

    private final CsProcedureMapper csProcedureMapper;

    public CsProcedureQueryService(CsProcedureRepository csProcedureRepository, CsProcedureMapper csProcedureMapper) {
        this.csProcedureRepository = csProcedureRepository;
        this.csProcedureMapper = csProcedureMapper;
    }

    /**
     * Return a {@link List} of {@link CsProcedureDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CsProcedureDTO> findByCriteria(CsProcedureCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CsProcedure> specification = createSpecification(criteria);
        return csProcedureMapper.toDto(csProcedureRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CsProcedureDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CsProcedureDTO> findByCriteria(CsProcedureCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CsProcedure> specification = createSpecification(criteria);
        return csProcedureRepository.findAll(specification, page)
            .map(csProcedureMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CsProcedureCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CsProcedure> specification = createSpecification(criteria);
        return csProcedureRepository.count(specification);
    }

    /**
     * Function to convert {@link CsProcedureCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CsProcedure> createSpecification(CsProcedureCriteria criteria) {
        Specification<CsProcedure> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CsProcedure_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CsProcedure_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CsProcedure_.description));
            }
            if (criteria.getValidityId() != null) {
                specification = specification.and(buildSpecification(criteria.getValidityId(),
                    root -> root.join(CsProcedure_.validity, JoinType.LEFT).get(Validity_.id)));
            }
        }
        return specification;
    }
}
