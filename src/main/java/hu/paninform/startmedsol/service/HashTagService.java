package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.HashTag;
import hu.paninform.startmedsol.repository.HashTagRepository;
import hu.paninform.startmedsol.service.dto.HashTagDTO;
import hu.paninform.startmedsol.service.mapper.HashTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HashTag}.
 */
@Service
@Transactional
public class HashTagService {

    private final Logger log = LoggerFactory.getLogger(HashTagService.class);

    private final HashTagRepository hashTagRepository;

    private final HashTagMapper hashTagMapper;

    public HashTagService(HashTagRepository hashTagRepository, HashTagMapper hashTagMapper) {
        this.hashTagRepository = hashTagRepository;
        this.hashTagMapper = hashTagMapper;
    }

    /**
     * Save a hashTag.
     *
     * @param hashTagDTO the entity to save.
     * @return the persisted entity.
     */
    public HashTagDTO save(HashTagDTO hashTagDTO) {
        log.debug("Request to save HashTag : {}", hashTagDTO);
        HashTag hashTag = hashTagMapper.toEntity(hashTagDTO);
        hashTag = hashTagRepository.save(hashTag);
        return hashTagMapper.toDto(hashTag);
    }

    /**
     * Get all the hashTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HashTagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HashTags");
        return hashTagRepository.findAll(pageable)
            .map(hashTagMapper::toDto);
    }


    /**
     * Get one hashTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HashTagDTO> findOne(Long id) {
        log.debug("Request to get HashTag : {}", id);
        return hashTagRepository.findById(id)
            .map(hashTagMapper::toDto);
    }

    /**
     * Delete the hashTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HashTag : {}", id);
        hashTagRepository.deleteById(id);
    }
}
