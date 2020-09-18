package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.FeedBackMessage;
import hu.paninform.startmedsol.repository.FeedBackMessageRepository;
import hu.paninform.startmedsol.service.dto.FeedBackMessageDTO;
import hu.paninform.startmedsol.service.mapper.FeedBackMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FeedBackMessage}.
 */
@Service
@Transactional
public class FeedBackMessageService {

    private final Logger log = LoggerFactory.getLogger(FeedBackMessageService.class);

    private final FeedBackMessageRepository feedBackMessageRepository;

    private final FeedBackMessageMapper feedBackMessageMapper;

    public FeedBackMessageService(FeedBackMessageRepository feedBackMessageRepository, FeedBackMessageMapper feedBackMessageMapper) {
        this.feedBackMessageRepository = feedBackMessageRepository;
        this.feedBackMessageMapper = feedBackMessageMapper;
    }

    /**
     * Save a feedBackMessage.
     *
     * @param feedBackMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public FeedBackMessageDTO save(FeedBackMessageDTO feedBackMessageDTO) {
        log.debug("Request to save FeedBackMessage : {}", feedBackMessageDTO);
        FeedBackMessage feedBackMessage = feedBackMessageMapper.toEntity(feedBackMessageDTO);
        feedBackMessage = feedBackMessageRepository.save(feedBackMessage);
        return feedBackMessageMapper.toDto(feedBackMessage);
    }

    /**
     * Get all the feedBackMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FeedBackMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FeedBackMessages");
        return feedBackMessageRepository.findAll(pageable)
            .map(feedBackMessageMapper::toDto);
    }


    /**
     * Get one feedBackMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FeedBackMessageDTO> findOne(Long id) {
        log.debug("Request to get FeedBackMessage : {}", id);
        return feedBackMessageRepository.findById(id)
            .map(feedBackMessageMapper::toDto);
    }

    /**
     * Delete the feedBackMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FeedBackMessage : {}", id);
        feedBackMessageRepository.deleteById(id);
    }
}
