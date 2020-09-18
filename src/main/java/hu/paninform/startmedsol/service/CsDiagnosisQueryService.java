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

import hu.paninform.startmedsol.domain.CsDiagnosis;
import hu.paninform.startmedsol.domain.*; // for static metamodels
import hu.paninform.startmedsol.repository.CsDiagnosisRepository;
import hu.paninform.startmedsol.service.dto.CsDiagnosisCriteria;
import hu.paninform.startmedsol.service.dto.CsDiagnosisDTO;
import hu.paninform.startmedsol.service.mapper.CsDiagnosisMapper;

/**
 * Service for executing complex queries for {@link CsDiagnosis} entities in the database.
 * The main input is a {@link CsDiagnosisCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CsDiagnosisDTO} or a {@link Page} of {@link CsDiagnosisDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CsDiagnosisQueryService extends QueryService<CsDiagnosis> {

    private final Logger log = LoggerFactory.getLogger(CsDiagnosisQueryService.class);

    private final CsDiagnosisRepository csDiagnosisRepository;

    private final CsDiagnosisMapper csDiagnosisMapper;

    public CsDiagnosisQueryService(CsDiagnosisRepository csDiagnosisRepository, CsDiagnosisMapper csDiagnosisMapper) {
        this.csDiagnosisRepository = csDiagnosisRepository;
        this.csDiagnosisMapper = csDiagnosisMapper;
    }

    /**
     * Return a {@link List} of {@link CsDiagnosisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CsDiagnosisDTO> findByCriteria(CsDiagnosisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CsDiagnosis> specification = createSpecification(criteria);
        return csDiagnosisMapper.toDto(csDiagnosisRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CsDiagnosisDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CsDiagnosisDTO> findByCriteria(CsDiagnosisCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CsDiagnosis> specification = createSpecification(criteria);
        return csDiagnosisRepository.findAll(specification, page)
            .map(csDiagnosisMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CsDiagnosisCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CsDiagnosis> specification = createSpecification(criteria);
        return csDiagnosisRepository.count(specification);
    }

    /**
     * Function to convert {@link CsDiagnosisCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CsDiagnosis> createSpecification(CsDiagnosisCriteria criteria) {
        Specification<CsDiagnosis> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CsDiagnosis_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CsDiagnosis_.code));
            }
            if (criteria.getReport() != null) {
                specification = specification.and(buildSpecification(criteria.getReport(), CsDiagnosis_.report));
            }
            if (criteria.getDescr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescr(), CsDiagnosis_.descr));
            }
            if (criteria.getSex() != null) {
                specification = specification.and(buildSpecification(criteria.getSex(), CsDiagnosis_.sex));
            }
            if (criteria.getAgeMin() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgeMin(), CsDiagnosis_.ageMin));
            }
            if (criteria.getAgeMax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAgeMax(), CsDiagnosis_.ageMax));
            }
            if (criteria.getValidityId() != null) {
                specification = specification.and(buildSpecification(criteria.getValidityId(),
                    root -> root.join(CsDiagnosis_.validity, JoinType.LEFT).get(Validity_.id)));
            }
        }
        return specification;
    }
}
