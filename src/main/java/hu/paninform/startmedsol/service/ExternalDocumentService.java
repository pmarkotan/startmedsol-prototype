package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.ExternalDocument;
import hu.paninform.startmedsol.repository.ExternalDocumentRepository;
import hu.paninform.startmedsol.service.dto.ExternalDocumentDTO;
import hu.paninform.startmedsol.service.mapper.ExternalDocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ExternalDocument}.
 */
@Service
@Transactional
public class ExternalDocumentService {

    private final Logger log = LoggerFactory.getLogger(ExternalDocumentService.class);

    private final ExternalDocumentRepository externalDocumentRepository;

    private final ExternalDocumentMapper externalDocumentMapper;

    public ExternalDocumentService(ExternalDocumentRepository externalDocumentRepository, ExternalDocumentMapper externalDocumentMapper) {
        this.externalDocumentRepository = externalDocumentRepository;
        this.externalDocumentMapper = externalDocumentMapper;
    }

    /**
     * Save a externalDocument.
     *
     * @param externalDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    public ExternalDocumentDTO save(ExternalDocumentDTO externalDocumentDTO) {
        log.debug("Request to save ExternalDocument : {}", externalDocumentDTO);
        ExternalDocument externalDocument = externalDocumentMapper.toEntity(externalDocumentDTO);
        externalDocument = externalDocumentRepository.save(externalDocument);
        return externalDocumentMapper.toDto(externalDocument);
    }

    /**
     * Get all the externalDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ExternalDocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ExternalDocuments");
        return externalDocumentRepository.findAll(pageable)
            .map(externalDocumentMapper::toDto);
    }


    /**
     * Get one externalDocument by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ExternalDocumentDTO> findOne(Long id) {
        log.debug("Request to get ExternalDocument : {}", id);
        return externalDocumentRepository.findById(id)
            .map(externalDocumentMapper::toDto);
    }

    /**
     * Delete the externalDocument by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ExternalDocument : {}", id);
        externalDocumentRepository.deleteById(id);
    }
}
