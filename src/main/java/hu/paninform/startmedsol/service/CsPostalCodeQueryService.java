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

import hu.paninform.startmedsol.domain.CsPostalCode;
import hu.paninform.startmedsol.domain.*; // for static metamodels
import hu.paninform.startmedsol.repository.CsPostalCodeRepository;
import hu.paninform.startmedsol.service.dto.CsPostalCodeCriteria;
import hu.paninform.startmedsol.service.dto.CsPostalCodeDTO;
import hu.paninform.startmedsol.service.mapper.CsPostalCodeMapper;

/**
 * Service for executing complex queries for {@link CsPostalCode} entities in the database.
 * The main input is a {@link CsPostalCodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CsPostalCodeDTO} or a {@link Page} of {@link CsPostalCodeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CsPostalCodeQueryService extends QueryService<CsPostalCode> {

    private final Logger log = LoggerFactory.getLogger(CsPostalCodeQueryService.class);

    private final CsPostalCodeRepository csPostalCodeRepository;

    private final CsPostalCodeMapper csPostalCodeMapper;

    public CsPostalCodeQueryService(CsPostalCodeRepository csPostalCodeRepository, CsPostalCodeMapper csPostalCodeMapper) {
        this.csPostalCodeRepository = csPostalCodeRepository;
        this.csPostalCodeMapper = csPostalCodeMapper;
    }

    /**
     * Return a {@link List} of {@link CsPostalCodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CsPostalCodeDTO> findByCriteria(CsPostalCodeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CsPostalCode> specification = createSpecification(criteria);
        return csPostalCodeMapper.toDto(csPostalCodeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CsPostalCodeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CsPostalCodeDTO> findByCriteria(CsPostalCodeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CsPostalCode> specification = createSpecification(criteria);
        return csPostalCodeRepository.findAll(specification, page)
            .map(csPostalCodeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CsPostalCodeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CsPostalCode> specification = createSpecification(criteria);
        return csPostalCodeRepository.count(specification);
    }

    /**
     * Function to convert {@link CsPostalCodeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CsPostalCode> createSpecification(CsPostalCodeCriteria criteria) {
        Specification<CsPostalCode> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CsPostalCode_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CsPostalCode_.code));
            }
            if (criteria.getSettlement() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSettlement(), CsPostalCode_.settlement));
            }
            if (criteria.getPart() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPart(), CsPostalCode_.part));
            }
            if (criteria.getStreet() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStreet(), CsPostalCode_.street));
            }
            if (criteria.getKind() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKind(), CsPostalCode_.kind));
            }
            if (criteria.getRangeType() != null) {
                specification = specification.and(buildSpecification(criteria.getRangeType(), CsPostalCode_.rangeType));
            }
            if (criteria.getRangeLo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRangeLo(), CsPostalCode_.rangeLo));
            }
            if (criteria.getRangeHi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRangeHi(), CsPostalCode_.rangeHi));
            }
            if (criteria.getDistrict() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDistrict(), CsPostalCode_.district));
            }
            if (criteria.getValidityId() != null) {
                specification = specification.and(buildSpecification(criteria.getValidityId(),
                    root -> root.join(CsPostalCode_.validity, JoinType.LEFT).get(Validity_.id)));
            }
        }
        return specification;
    }
}
