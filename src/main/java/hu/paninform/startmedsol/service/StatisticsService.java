package hu.paninform.startmedsol.service;

import hu.paninform.startmedsol.domain.Statistics;
import hu.paninform.startmedsol.repository.StatisticsRepository;
import hu.paninform.startmedsol.service.dto.StatisticsDTO;
import hu.paninform.startmedsol.service.mapper.StatisticsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Statistics}.
 */
@Service
@Transactional
public class StatisticsService {

    private final Logger log = LoggerFactory.getLogger(StatisticsService.class);

    private final StatisticsRepository statisticsRepository;

    private final StatisticsMapper statisticsMapper;

    public StatisticsService(StatisticsRepository statisticsRepository, StatisticsMapper statisticsMapper) {
        this.statisticsRepository = statisticsRepository;
        this.statisticsMapper = statisticsMapper;
    }

    /**
     * Save a statistics.
     *
     * @param statisticsDTO the entity to save.
     * @return the persisted entity.
     */
    public StatisticsDTO save(StatisticsDTO statisticsDTO) {
        log.debug("Request to save Statistics : {}", statisticsDTO);
        Statistics statistics = statisticsMapper.toEntity(statisticsDTO);
        statistics = statisticsRepository.save(statistics);
        return statisticsMapper.toDto(statistics);
    }

    /**
     * Get all the statistics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StatisticsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Statistics");
        return statisticsRepository.findAll(pageable)
            .map(statisticsMapper::toDto);
    }


    /**
     * Get one statistics by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StatisticsDTO> findOne(Long id) {
        log.debug("Request to get Statistics : {}", id);
        return statisticsRepository.findById(id)
            .map(statisticsMapper::toDto);
    }

    /**
     * Delete the statistics by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Statistics : {}", id);
        statisticsRepository.deleteById(id);
    }
}
