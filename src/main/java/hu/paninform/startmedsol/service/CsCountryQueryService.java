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

import hu.paninform.startmedsol.domain.CsCountry;
import hu.paninform.startmedsol.domain.*; // for static metamodels
import hu.paninform.startmedsol.repository.CsCountryRepository;
import hu.paninform.startmedsol.service.dto.CsCountryCriteria;
import hu.paninform.startmedsol.service.dto.CsCountryDTO;
import hu.paninform.startmedsol.service.mapper.CsCountryMapper;

/**
 * Service for executing complex queries for {@link CsCountry} entities in the database.
 * The main input is a {@link CsCountryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CsCountryDTO} or a {@link Page} of {@link CsCountryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CsCountryQueryService extends QueryService<CsCountry> {

    private final Logger log = LoggerFactory.getLogger(CsCountryQueryService.class);

    private final CsCountryRepository csCountryRepository;

    private final CsCountryMapper csCountryMapper;

    public CsCountryQueryService(CsCountryRepository csCountryRepository, CsCountryMapper csCountryMapper) {
        this.csCountryRepository = csCountryRepository;
        this.csCountryMapper = csCountryMapper;
    }

    /**
     * Return a {@link List} of {@link CsCountryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CsCountryDTO> findByCriteria(CsCountryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CsCountry> specification = createSpecification(criteria);
        return csCountryMapper.toDto(csCountryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CsCountryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CsCountryDTO> findByCriteria(CsCountryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CsCountry> specification = createSpecification(criteria);
        return csCountryRepository.findAll(specification, page)
            .map(csCountryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CsCountryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CsCountry> specification = createSpecification(criteria);
        return csCountryRepository.count(specification);
    }

    /**
     * Function to convert {@link CsCountryCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CsCountry> createSpecification(CsCountryCriteria criteria) {
        Specification<CsCountry> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CsCountry_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CsCountry_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CsCountry_.description));
            }
            if (criteria.getValidityId() != null) {
                specification = specification.and(buildSpecification(criteria.getValidityId(),
                    root -> root.join(CsCountry_.validity, JoinType.LEFT).get(Validity_.id)));
            }
        }
        return specification;
    }
}
