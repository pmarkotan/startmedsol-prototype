package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.CsCountry;
import hu.paninform.startmedsol.repository.CsCountryRepository;
import hu.paninform.startmedsol.service.dto.CsCountryDTO;
import hu.paninform.startmedsol.service.mapper.CsCountryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CsCountry}.
 */
@Service
@Transactional
public class CsCountryService {

    private final Logger log = LoggerFactory.getLogger(CsCountryService.class);

    private final CsCountryRepository csCountryRepository;

    private final CsCountryMapper csCountryMapper;

    public CsCountryService(CsCountryRepository csCountryRepository, CsCountryMapper csCountryMapper) {
        this.csCountryRepository = csCountryRepository;
        this.csCountryMapper = csCountryMapper;
    }

    /**
     * Save a csCountry.
     *
     * @param csCountryDTO the entity to save.
     * @return the persisted entity.
     */
    public CsCountryDTO save(CsCountryDTO csCountryDTO) {
        log.debug("Request to save CsCountry : {}", csCountryDTO);
        CsCountry csCountry = csCountryMapper.toEntity(csCountryDTO);
        csCountry = csCountryRepository.save(csCountry);
        return csCountryMapper.toDto(csCountry);
    }

    /**
     * Get all the csCountries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CsCountryDTO> findAll() {
        log.debug("Request to get all CsCountries");
        return csCountryRepository.findAll().stream()
            .map(csCountryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one csCountry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CsCountryDTO> findOne(Long id) {
        log.debug("Request to get CsCountry : {}", id);
        return csCountryRepository.findById(id)
            .map(csCountryMapper::toDto);
    }

    /**
     * Delete the csCountry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CsCountry : {}", id);
        csCountryRepository.deleteById(id);
    }
}
