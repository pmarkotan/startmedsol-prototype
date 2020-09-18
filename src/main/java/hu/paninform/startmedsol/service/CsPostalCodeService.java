package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.CsPostalCode;
import hu.paninform.startmedsol.repository.CsPostalCodeRepository;
import hu.paninform.startmedsol.service.dto.CsPostalCodeDTO;
import hu.paninform.startmedsol.service.mapper.CsPostalCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CsPostalCode}.
 */
@Service
@Transactional
public class CsPostalCodeService {

    private final Logger log = LoggerFactory.getLogger(CsPostalCodeService.class);

    private final CsPostalCodeRepository csPostalCodeRepository;

    private final CsPostalCodeMapper csPostalCodeMapper;

    public CsPostalCodeService(CsPostalCodeRepository csPostalCodeRepository, CsPostalCodeMapper csPostalCodeMapper) {
        this.csPostalCodeRepository = csPostalCodeRepository;
        this.csPostalCodeMapper = csPostalCodeMapper;
    }

    /**
     * Save a csPostalCode.
     *
     * @param csPostalCodeDTO the entity to save.
     * @return the persisted entity.
     */
    public CsPostalCodeDTO save(CsPostalCodeDTO csPostalCodeDTO) {
        log.debug("Request to save CsPostalCode : {}", csPostalCodeDTO);
        CsPostalCode csPostalCode = csPostalCodeMapper.toEntity(csPostalCodeDTO);
        csPostalCode = csPostalCodeRepository.save(csPostalCode);
        return csPostalCodeMapper.toDto(csPostalCode);
    }

    /**
     * Get all the csPostalCodes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CsPostalCodeDTO> findAll() {
        log.debug("Request to get all CsPostalCodes");
        return csPostalCodeRepository.findAll().stream()
            .map(csPostalCodeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one csPostalCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CsPostalCodeDTO> findOne(Long id) {
        log.debug("Request to get CsPostalCode : {}", id);
        return csPostalCodeRepository.findById(id)
            .map(csPostalCodeMapper::toDto);
    }

    /**
     * Delete the csPostalCode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CsPostalCode : {}", id);
        csPostalCodeRepository.deleteById(id);
    }
}
