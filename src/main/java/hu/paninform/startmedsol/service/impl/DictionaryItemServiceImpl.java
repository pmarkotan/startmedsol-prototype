package hu.paninform.startmedsol.service.impl;

import hu.paninform.startmedsol.service.DictionaryItemService;
import hu.paninform.startmedsol.domain.DictionaryItem;
import hu.paninform.startmedsol.repository.DictionaryItemRepository;
import hu.paninform.startmedsol.service.dto.DictionaryItemDTO;
import hu.paninform.startmedsol.service.mapper.DictionaryItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DictionaryItem}.
 */
@Service
@Transactional
public class DictionaryItemServiceImpl implements DictionaryItemService {

    private final Logger log = LoggerFactory.getLogger(DictionaryItemServiceImpl.class);

    private final DictionaryItemRepository dictionaryItemRepository;

    private final DictionaryItemMapper dictionaryItemMapper;

    public DictionaryItemServiceImpl(DictionaryItemRepository dictionaryItemRepository, DictionaryItemMapper dictionaryItemMapper) {
        this.dictionaryItemRepository = dictionaryItemRepository;
        this.dictionaryItemMapper = dictionaryItemMapper;
    }

    @Override
    public DictionaryItemDTO save(DictionaryItemDTO dictionaryItemDTO) {
        log.debug("Request to save DictionaryItem : {}", dictionaryItemDTO);
        DictionaryItem dictionaryItem = dictionaryItemMapper.toEntity(dictionaryItemDTO);
        dictionaryItem = dictionaryItemRepository.save(dictionaryItem);
        return dictionaryItemMapper.toDto(dictionaryItem);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DictionaryItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DictionaryItems");
        return dictionaryItemRepository.findAll(pageable)
            .map(dictionaryItemMapper::toDto);
    }


    public Page<DictionaryItemDTO> findAllWithEagerRelationships(Pageable pageable) {
        return dictionaryItemRepository.findAllWithEagerRelationships(pageable).map(dictionaryItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DictionaryItemDTO> findOne(Long id) {
        log.debug("Request to get DictionaryItem : {}", id);
        return dictionaryItemRepository.findOneWithEagerRelationships(id)
            .map(dictionaryItemMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DictionaryItem : {}", id);
        dictionaryItemRepository.deleteById(id);
    }
}
