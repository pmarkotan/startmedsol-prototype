package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.CsProcedureService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.CsProcedureDTO;
import hu.paninform.startmedsol.service.dto.CsProcedureCriteria;
import hu.paninform.startmedsol.service.CsProcedureQueryService;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.CsProcedure}.
 */
@RestController
@RequestMapping("/api")
public class CsProcedureResource {

    private final Logger log = LoggerFactory.getLogger(CsProcedureResource.class);

    private static final String ENTITY_NAME = "csProcedure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CsProcedureService csProcedureService;

    private final CsProcedureQueryService csProcedureQueryService;

    public CsProcedureResource(CsProcedureService csProcedureService, CsProcedureQueryService csProcedureQueryService) {
        this.csProcedureService = csProcedureService;
        this.csProcedureQueryService = csProcedureQueryService;
    }

    /**
     * {@code POST  /cs-procedures} : Create a new csProcedure.
     *
     * @param csProcedureDTO the csProcedureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new csProcedureDTO, or with status {@code 400 (Bad Request)} if the csProcedure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cs-procedures")
    public ResponseEntity<CsProcedureDTO> createCsProcedure(@Valid @RequestBody CsProcedureDTO csProcedureDTO) throws URISyntaxException {
        log.debug("REST request to save CsProcedure : {}", csProcedureDTO);
        if (csProcedureDTO.getId() != null) {
            throw new BadRequestAlertException("A new csProcedure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CsProcedureDTO result = csProcedureService.save(csProcedureDTO);
        return ResponseEntity.created(new URI("/api/cs-procedures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cs-procedures} : Updates an existing csProcedure.
     *
     * @param csProcedureDTO the csProcedureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated csProcedureDTO,
     * or with status {@code 400 (Bad Request)} if the csProcedureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the csProcedureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cs-procedures")
    public ResponseEntity<CsProcedureDTO> updateCsProcedure(@Valid @RequestBody CsProcedureDTO csProcedureDTO) throws URISyntaxException {
        log.debug("REST request to update CsProcedure : {}", csProcedureDTO);
        if (csProcedureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CsProcedureDTO result = csProcedureService.save(csProcedureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, csProcedureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cs-procedures} : get all the csProcedures.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of csProcedures in body.
     */
    @GetMapping("/cs-procedures")
    public ResponseEntity<List<CsProcedureDTO>> getAllCsProcedures(CsProcedureCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CsProcedures by criteria: {}", criteria);
        Page<CsProcedureDTO> page = csProcedureQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cs-procedures/count} : count all the csProcedures.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/cs-procedures/count")
    public ResponseEntity<Long> countCsProcedures(CsProcedureCriteria criteria) {
        log.debug("REST request to count CsProcedures by criteria: {}", criteria);
        return ResponseEntity.ok().body(csProcedureQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cs-procedures/:id} : get the "id" csProcedure.
     *
     * @param id the id of the csProcedureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the csProcedureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cs-procedures/{id}")
    public ResponseEntity<CsProcedureDTO> getCsProcedure(@PathVariable Long id) {
        log.debug("REST request to get CsProcedure : {}", id);
        Optional<CsProcedureDTO> csProcedureDTO = csProcedureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(csProcedureDTO);
    }

    /**
     * {@code DELETE  /cs-procedures/:id} : delete the "id" csProcedure.
     *
     * @param id the id of the csProcedureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cs-procedures/{id}")
    public ResponseEntity<Void> deleteCsProcedure(@PathVariable Long id) {
        log.debug("REST request to delete CsProcedure : {}", id);
        csProcedureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
