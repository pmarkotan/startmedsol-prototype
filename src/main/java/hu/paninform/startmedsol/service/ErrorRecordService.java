package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.ErrorRecord;
import hu.paninform.startmedsol.repository.ErrorRecordRepository;
import hu.paninform.startmedsol.service.dto.ErrorRecordDTO;
import hu.paninform.startmedsol.service.mapper.ErrorRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ErrorRecord}.
 */
@Service
@Transactional
public class ErrorRecordService {

    private final Logger log = LoggerFactory.getLogger(ErrorRecordService.class);

    private final ErrorRecordRepository errorRecordRepository;

    private final ErrorRecordMapper errorRecordMapper;

    public ErrorRecordService(ErrorRecordRepository errorRecordRepository, ErrorRecordMapper errorRecordMapper) {
        this.errorRecordRepository = errorRecordRepository;
        this.errorRecordMapper = errorRecordMapper;
    }

    /**
     * Save a errorRecord.
     *
     * @param errorRecordDTO the entity to save.
     * @return the persisted entity.
     */
    public ErrorRecordDTO save(ErrorRecordDTO errorRecordDTO) {
        log.debug("Request to save ErrorRecord : {}", errorRecordDTO);
        ErrorRecord errorRecord = errorRecordMapper.toEntity(errorRecordDTO);
        errorRecord = errorRecordRepository.save(errorRecord);
        return errorRecordMapper.toDto(errorRecord);
    }

    /**
     * Get all the errorRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ErrorRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ErrorRecords");
        return errorRecordRepository.findAll(pageable)
            .map(errorRecordMapper::toDto);
    }


    /**
     * Get one errorRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ErrorRecordDTO> findOne(Long id) {
        log.debug("Request to get ErrorRecord : {}", id);
        return errorRecordRepository.findById(id)
            .map(errorRecordMapper::toDto);
    }

    /**
     * Delete the errorRecord by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ErrorRecord : {}", id);
        errorRecordRepository.deleteById(id);
    }
}
