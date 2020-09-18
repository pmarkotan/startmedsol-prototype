package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.CaseTextDocumentation;
import hu.paninform.startmedsol.repository.CaseTextDocumentationRepository;
import hu.paninform.startmedsol.service.dto.CaseTextDocumentationDTO;
import hu.paninform.startmedsol.service.mapper.CaseTextDocumentationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseTextDocumentation}.
 */
@Service
@Transactional
public class CaseTextDocumentationService {

    private final Logger log = LoggerFactory.getLogger(CaseTextDocumentationService.class);

    private final CaseTextDocumentationRepository caseTextDocumentationRepository;

    private final CaseTextDocumentationMapper caseTextDocumentationMapper;

    public CaseTextDocumentationService(CaseTextDocumentationRepository caseTextDocumentationRepository, CaseTextDocumentationMapper caseTextDocumentationMapper) {
        this.caseTextDocumentationRepository = caseTextDocumentationRepository;
        this.caseTextDocumentationMapper = caseTextDocumentationMapper;
    }

    /**
     * Save a caseTextDocumentation.
     *
     * @param caseTextDocumentationDTO the entity to save.
     * @return the persisted entity.
     */
    public CaseTextDocumentationDTO save(CaseTextDocumentationDTO caseTextDocumentationDTO) {
        log.debug("Request to save CaseTextDocumentation : {}", caseTextDocumentationDTO);
        CaseTextDocumentation caseTextDocumentation = caseTextDocumentationMapper.toEntity(caseTextDocumentationDTO);
        caseTextDocumentation = caseTextDocumentationRepository.save(caseTextDocumentation);
        return caseTextDocumentationMapper.toDto(caseTextDocumentation);
    }

    /**
     * Get all the caseTextDocumentations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseTextDocumentationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseTextDocumentations");
        return caseTextDocumentationRepository.findAll(pageable)
            .map(caseTextDocumentationMapper::toDto);
    }


    /**
     * Get one caseTextDocumentation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CaseTextDocumentationDTO> findOne(Long id) {
        log.debug("Request to get CaseTextDocumentation : {}", id);
        return caseTextDocumentationRepository.findById(id)
            .map(caseTextDocumentationMapper::toDto);
    }

    /**
     * Delete the caseTextDocumentation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CaseTextDocumentation : {}", id);
        caseTextDocumentationRepository.deleteById(id);
    }
}
