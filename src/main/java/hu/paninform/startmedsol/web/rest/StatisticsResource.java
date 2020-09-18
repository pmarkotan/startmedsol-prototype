package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.StatisticsService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.StatisticsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.Statistics}.
 */
@RestController
@RequestMapping("/api")
public class StatisticsResource {

    private final Logger log = LoggerFactory.getLogger(StatisticsResource.class);

    private static final String ENTITY_NAME = "statistics";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StatisticsService statisticsService;

    public StatisticsResource(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * {@code POST  /statistics} : Create a new statistics.
     *
     * @param statisticsDTO the statisticsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new statisticsDTO, or with status {@code 400 (Bad Request)} if the statistics has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/statistics")
    public ResponseEntity<StatisticsDTO> createStatistics(@Valid @RequestBody StatisticsDTO statisticsDTO) throws URISyntaxException {
        log.debug("REST request to save Statistics : {}", statisticsDTO);
        if (statisticsDTO.getId() != null) {
            throw new BadRequestAlertException("A new statistics cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatisticsDTO result = statisticsService.save(statisticsDTO);
        return ResponseEntity.created(new URI("/api/statistics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /statistics} : Updates an existing statistics.
     *
     * @param statisticsDTO the statisticsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated statisticsDTO,
     * or with status {@code 400 (Bad Request)} if the statisticsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the statisticsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/statistics")
    public ResponseEntity<StatisticsDTO> updateStatistics(@Valid @RequestBody StatisticsDTO statisticsDTO) throws URISyntaxException {
        log.debug("REST request to update Statistics : {}", statisticsDTO);
        if (statisticsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StatisticsDTO result = statisticsService.save(statisticsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, statisticsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /statistics} : get all the statistics.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statistics in body.
     */
    @GetMapping("/statistics")
    public ResponseEntity<List<StatisticsDTO>> getAllStatistics(Pageable pageable) {
        log.debug("REST request to get a page of Statistics");
        Page<StatisticsDTO> page = statisticsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /statistics/:id} : get the "id" statistics.
     *
     * @param id the id of the statisticsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statisticsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statistics/{id}")
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable Long id) {
        log.debug("REST request to get Statistics : {}", id);
        Optional<StatisticsDTO> statisticsDTO = statisticsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statisticsDTO);
    }

    /**
     * {@code DELETE  /statistics/:id} : delete the "id" statistics.
     *
     * @param id the id of the statisticsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/statistics/{id}")
    public ResponseEntity<Void> deleteStatistics(@PathVariable Long id) {
        log.debug("REST request to delete Statistics : {}", id);
        statisticsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
