package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.PerformedProcedureService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.PerformedProcedureDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PerformedProcedure}.
 */
@RestController
@RequestMapping("/api")
public class PerformedProcedureResource {

    private final Logger log = LoggerFactory.getLogger(PerformedProcedureResource.class);

    private static final String ENTITY_NAME = "performedProcedure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerformedProcedureService performedProcedureService;

    public PerformedProcedureResource(PerformedProcedureService performedProcedureService) {
        this.performedProcedureService = performedProcedureService;
    }

    /**
     * {@code POST  /performed-procedures} : Create a new performedProcedure.
     *
     * @param performedProcedureDTO the performedProcedureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new performedProcedureDTO, or with status {@code 400 (Bad Request)} if the performedProcedure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/performed-procedures")
    public ResponseEntity<PerformedProcedureDTO> createPerformedProcedure(@Valid @RequestBody PerformedProcedureDTO performedProcedureDTO) throws URISyntaxException {
        log.debug("REST request to save PerformedProcedure : {}", performedProcedureDTO);
        if (performedProcedureDTO.getId() != null) {
            throw new BadRequestAlertException("A new performedProcedure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerformedProcedureDTO result = performedProcedureService.save(performedProcedureDTO);
        return ResponseEntity.created(new URI("/api/performed-procedures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /performed-procedures} : Updates an existing performedProcedure.
     *
     * @param performedProcedureDTO the performedProcedureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated performedProcedureDTO,
     * or with status {@code 400 (Bad Request)} if the performedProcedureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the performedProcedureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/performed-procedures")
    public ResponseEntity<PerformedProcedureDTO> updatePerformedProcedure(@Valid @RequestBody PerformedProcedureDTO performedProcedureDTO) throws URISyntaxException {
        log.debug("REST request to update PerformedProcedure : {}", performedProcedureDTO);
        if (performedProcedureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerformedProcedureDTO result = performedProcedureService.save(performedProcedureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, performedProcedureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /performed-procedures} : get all the performedProcedures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of performedProcedures in body.
     */
    @GetMapping("/performed-procedures")
    public ResponseEntity<List<PerformedProcedureDTO>> getAllPerformedProcedures(Pageable pageable) {
        log.debug("REST request to get a page of PerformedProcedures");
        Page<PerformedProcedureDTO> page = performedProcedureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /performed-procedures/:id} : get the "id" performedProcedure.
     *
     * @param id the id of the performedProcedureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the performedProcedureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/performed-procedures/{id}")
    public ResponseEntity<PerformedProcedureDTO> getPerformedProcedure(@PathVariable Long id) {
        log.debug("REST request to get PerformedProcedure : {}", id);
        Optional<PerformedProcedureDTO> performedProcedureDTO = performedProcedureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(performedProcedureDTO);
    }

    /**
     * {@code DELETE  /performed-procedures/:id} : delete the "id" performedProcedure.
     *
     * @param id the id of the performedProcedureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/performed-procedures/{id}")
    public ResponseEntity<Void> deletePerformedProcedure(@PathVariable Long id) {
        log.debug("REST request to delete PerformedProcedure : {}", id);
        performedProcedureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
