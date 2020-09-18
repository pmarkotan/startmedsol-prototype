package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.EhrDocument;
import hu.paninform.startmedsol.repository.EhrDocumentRepository;
import hu.paninform.startmedsol.service.dto.EhrDocumentDTO;
import hu.paninform.startmedsol.service.mapper.EhrDocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EhrDocument}.
 */
@Service
@Transactional
public class EhrDocumentService {

    private final Logger log = LoggerFactory.getLogger(EhrDocumentService.class);

    private final EhrDocumentRepository ehrDocumentRepository;

    private final EhrDocumentMapper ehrDocumentMapper;

    public EhrDocumentService(EhrDocumentRepository ehrDocumentRepository, EhrDocumentMapper ehrDocumentMapper) {
        this.ehrDocumentRepository = ehrDocumentRepository;
        this.ehrDocumentMapper = ehrDocumentMapper;
    }

    /**
     * Save a ehrDocument.
     *
     * @param ehrDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    public EhrDocumentDTO save(EhrDocumentDTO ehrDocumentDTO) {
        log.debug("Request to save EhrDocument : {}", ehrDocumentDTO);
        EhrDocument ehrDocument = ehrDocumentMapper.toEntity(ehrDocumentDTO);
        ehrDocument = ehrDocumentRepository.save(ehrDocument);
        return ehrDocumentMapper.toDto(ehrDocument);
    }

    /**
     * Get all the ehrDocuments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EhrDocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EhrDocuments");
        return ehrDocumentRepository.findAll(pageable)
            .map(ehrDocumentMapper::toDto);
    }


    /**
     * Get one ehrDocument by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EhrDocumentDTO> findOne(Long id) {
        log.debug("Request to get EhrDocument : {}", id);
        return ehrDocumentRepository.findById(id)
            .map(ehrDocumentMapper::toDto);
    }

    /**
     * Delete the ehrDocument by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EhrDocument : {}", id);
        ehrDocumentRepository.deleteById(id);
    }
}
