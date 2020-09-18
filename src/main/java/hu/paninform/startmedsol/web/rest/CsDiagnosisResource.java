package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.CsDiagnosisService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.CsDiagnosisDTO;
import hu.paninform.startmedsol.service.dto.CsDiagnosisCriteria;
import hu.paninform.startmedsol.service.CsDiagnosisQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.CsDiagnosis}.
 */
@RestController
@RequestMapping("/api")
public class CsDiagnosisResource {

    private final Logger log = LoggerFactory.getLogger(CsDiagnosisResource.class);

    private static final String ENTITY_NAME = "csDiagnosis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CsDiagnosisService csDiagnosisService;

    private final CsDiagnosisQueryService csDiagnosisQueryService;

    public CsDiagnosisResource(CsDiagnosisService csDiagnosisService, CsDiagnosisQueryService csDiagnosisQueryService) {
        this.csDiagnosisService = csDiagnosisService;
        this.csDiagnosisQueryService = csDiagnosisQueryService;
    }

    /**
     * {@code POST  /cs-diagnoses} : Create a new csDiagnosis.
     *
     * @param csDiagnosisDTO the csDiagnosisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new csDiagnosisDTO, or with status {@code 400 (Bad Request)} if the csDiagnosis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cs-diagnoses")
    public ResponseEntity<CsDiagnosisDTO> createCsDiagnosis(@Valid @RequestBody CsDiagnosisDTO csDiagnosisDTO) throws URISyntaxException {
        log.debug("REST request to save CsDiagnosis : {}", csDiagnosisDTO);
        if (csDiagnosisDTO.getId() != null) {
            throw new BadRequestAlertException("A new csDiagnosis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CsDiagnosisDTO result = csDiagnosisService.save(csDiagnosisDTO);
        return ResponseEntity.created(new URI("/api/cs-diagnoses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cs-diagnoses} : Updates an existing csDiagnosis.
     *
     * @param csDiagnosisDTO the csDiagnosisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated csDiagnosisDTO,
     * or with status {@code 400 (Bad Request)} if the csDiagnosisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the csDiagnosisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cs-diagnoses")
    public ResponseEntity<CsDiagnosisDTO> updateCsDiagnosis(@Valid @RequestBody CsDiagnosisDTO csDiagnosisDTO) throws URISyntaxException {
        log.debug("REST request to update CsDiagnosis : {}", csDiagnosisDTO);
        if (csDiagnosisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CsDiagnosisDTO result = csDiagnosisService.save(csDiagnosisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, csDiagnosisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cs-diagnoses} : get all the csDiagnoses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of csDiagnoses in body.
     */
    @GetMapping("/cs-diagnoses")
    public ResponseEntity<List<CsDiagnosisDTO>> getAllCsDiagnoses(CsDiagnosisCriteria criteria) {
        log.debug("REST request to get CsDiagnoses by criteria: {}", criteria);
        List<CsDiagnosisDTO> entityList = csDiagnosisQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /cs-diagnoses/count} : count all the csDiagnoses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/cs-diagnoses/count")
    public ResponseEntity<Long> countCsDiagnoses(CsDiagnosisCriteria criteria) {
        log.debug("REST request to count CsDiagnoses by criteria: {}", criteria);
        return ResponseEntity.ok().body(csDiagnosisQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cs-diagnoses/:id} : get the "id" csDiagnosis.
     *
     * @param id the id of the csDiagnosisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the csDiagnosisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cs-diagnoses/{id}")
    public ResponseEntity<CsDiagnosisDTO> getCsDiagnosis(@PathVariable Long id) {
        log.debug("REST request to get CsDiagnosis : {}", id);
        Optional<CsDiagnosisDTO> csDiagnosisDTO = csDiagnosisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(csDiagnosisDTO);
    }

    /**
     * {@code DELETE  /cs-diagnoses/:id} : delete the "id" csDiagnosis.
     *
     * @param id the id of the csDiagnosisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cs-diagnoses/{id}")
    public ResponseEntity<Void> deleteCsDiagnosis(@PathVariable Long id) {
        log.debug("REST request to delete CsDiagnosis : {}", id);
        csDiagnosisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
