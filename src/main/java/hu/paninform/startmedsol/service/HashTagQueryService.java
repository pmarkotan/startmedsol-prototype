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

import hu.paninform.startmedsol.domain.HashTag;
import hu.paninform.startmedsol.domain.*; // for static metamodels
import hu.paninform.startmedsol.repository.HashTagRepository;
import hu.paninform.startmedsol.service.dto.HashTagCriteria;
import hu.paninform.startmedsol.service.dto.HashTagDTO;
import hu.paninform.startmedsol.service.mapper.HashTagMapper;

/**
 * Service for executing complex queries for {@link HashTag} entities in the database.
 * The main input is a {@link HashTagCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HashTagDTO} or a {@link Page} of {@link HashTagDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HashTagQueryService extends QueryService<HashTag> {

    private final Logger log = LoggerFactory.getLogger(HashTagQueryService.class);

    private final HashTagRepository hashTagRepository;

    private final HashTagMapper hashTagMapper;

    public HashTagQueryService(HashTagRepository hashTagRepository, HashTagMapper hashTagMapper) {
        this.hashTagRepository = hashTagRepository;
        this.hashTagMapper = hashTagMapper;
    }

    /**
     * Return a {@link List} of {@link HashTagDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HashTagDTO> findByCriteria(HashTagCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<HashTag> specification = createSpecification(criteria);
        return hashTagMapper.toDto(hashTagRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HashTagDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HashTagDTO> findByCriteria(HashTagCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<HashTag> specification = createSpecification(criteria);
        return hashTagRepository.findAll(specification, page)
            .map(hashTagMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HashTagCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<HashTag> specification = createSpecification(criteria);
        return hashTagRepository.count(specification);
    }

    /**
     * Function to convert {@link HashTagCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<HashTag> createSpecification(HashTagCriteria criteria) {
        Specification<HashTag> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), HashTag_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), HashTag_.name));
            }
            if (criteria.getProviderId() != null) {
                specification = specification.and(buildSpecification(criteria.getProviderId(),
                    root -> root.join(HashTag_.provider, JoinType.LEFT).get(Provider_.id)));
            }
            if (criteria.getPatientsId() != null) {
                specification = specification.and(buildSpecification(criteria.getPatientsId(),
                    root -> root.join(HashTag_.patients, JoinType.LEFT).get(Patient_.id)));
            }
        }
        return specification;
    }
}
