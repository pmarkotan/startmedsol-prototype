package hu.paninform.startmedsol.service.impl;

import hu.paninform.startmedsol.service.DictionaryTranslationService;
import hu.paninform.startmedsol.domain.DictionaryTranslation;
import hu.paninform.startmedsol.repository.DictionaryTranslationRepository;
import hu.paninform.startmedsol.service.dto.DictionaryTranslationDTO;
import hu.paninform.startmedsol.service.mapper.DictionaryTranslationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DictionaryTranslation}.
 */
@Service
@Transactional
public class DictionaryTranslationServiceImpl implements DictionaryTranslationService {

    private final Logger log = LoggerFactory.getLogger(DictionaryTranslationServiceImpl.class);

    private final DictionaryTranslationRepository dictionaryTranslationRepository;

    private final DictionaryTranslationMapper dictionaryTranslationMapper;

    public DictionaryTranslationServiceImpl(DictionaryTranslationRepository dictionaryTranslationRepository, DictionaryTranslationMapper dictionaryTranslationMapper) {
        this.dictionaryTranslationRepository = dictionaryTranslationRepository;
        this.dictionaryTranslationMapper = dictionaryTranslationMapper;
    }

    @Override
    public DictionaryTranslationDTO save(DictionaryTranslationDTO dictionaryTranslationDTO) {
        log.debug("Request to save DictionaryTranslation : {}", dictionaryTranslationDTO);
        DictionaryTranslation dictionaryTranslation = dictionaryTranslationMapper.toEntity(dictionaryTranslationDTO);
        dictionaryTranslation = dictionaryTranslationRepository.save(dictionaryTranslation);
        return dictionaryTranslationMapper.toDto(dictionaryTranslation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DictionaryTranslationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DictionaryTranslations");
        return dictionaryTranslationRepository.findAll(pageable)
            .map(dictionaryTranslationMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DictionaryTranslationDTO> findOne(Long id) {
        log.debug("Request to get DictionaryTranslation : {}", id);
        return dictionaryTranslationRepository.findById(id)
            .map(dictionaryTranslationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DictionaryTranslation : {}", id);
        dictionaryTranslationRepository.deleteById(id);
    }
}
