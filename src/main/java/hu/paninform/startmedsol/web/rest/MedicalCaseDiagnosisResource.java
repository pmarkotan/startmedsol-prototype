package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.MedicalCaseDiagnosisService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.MedicalCaseDiagnosisDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.MedicalCaseDiagnosis}.
 */
@RestController
@RequestMapping("/api")
public class MedicalCaseDiagnosisResource {

    private final Logger log = LoggerFactory.getLogger(MedicalCaseDiagnosisResource.class);

    private static final String ENTITY_NAME = "medicalCaseDiagnosis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalCaseDiagnosisService medicalCaseDiagnosisService;

    public MedicalCaseDiagnosisResource(MedicalCaseDiagnosisService medicalCaseDiagnosisService) {
        this.medicalCaseDiagnosisService = medicalCaseDiagnosisService;
    }

    /**
     * {@code POST  /medical-case-diagnoses} : Create a new medicalCaseDiagnosis.
     *
     * @param medicalCaseDiagnosisDTO the medicalCaseDiagnosisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalCaseDiagnosisDTO, or with status {@code 400 (Bad Request)} if the medicalCaseDiagnosis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-case-diagnoses")
    public ResponseEntity<MedicalCaseDiagnosisDTO> createMedicalCaseDiagnosis(@Valid @RequestBody MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO) throws URISyntaxException {
        log.debug("REST request to save MedicalCaseDiagnosis : {}", medicalCaseDiagnosisDTO);
        if (medicalCaseDiagnosisDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicalCaseDiagnosis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalCaseDiagnosisDTO result = medicalCaseDiagnosisService.save(medicalCaseDiagnosisDTO);
        return ResponseEntity.created(new URI("/api/medical-case-diagnoses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-case-diagnoses} : Updates an existing medicalCaseDiagnosis.
     *
     * @param medicalCaseDiagnosisDTO the medicalCaseDiagnosisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalCaseDiagnosisDTO,
     * or with status {@code 400 (Bad Request)} if the medicalCaseDiagnosisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalCaseDiagnosisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-case-diagnoses")
    public ResponseEntity<MedicalCaseDiagnosisDTO> updateMedicalCaseDiagnosis(@Valid @RequestBody MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO) throws URISyntaxException {
        log.debug("REST request to update MedicalCaseDiagnosis : {}", medicalCaseDiagnosisDTO);
        if (medicalCaseDiagnosisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalCaseDiagnosisDTO result = medicalCaseDiagnosisService.save(medicalCaseDiagnosisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, medicalCaseDiagnosisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-case-diagnoses} : get all the medicalCaseDiagnoses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalCaseDiagnoses in body.
     */
    @GetMapping("/medical-case-diagnoses")
    public ResponseEntity<List<MedicalCaseDiagnosisDTO>> getAllMedicalCaseDiagnoses(Pageable pageable) {
        log.debug("REST request to get a page of MedicalCaseDiagnoses");
        Page<MedicalCaseDiagnosisDTO> page = medicalCaseDiagnosisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-case-diagnoses/:id} : get the "id" medicalCaseDiagnosis.
     *
     * @param id the id of the medicalCaseDiagnosisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalCaseDiagnosisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-case-diagnoses/{id}")
    public ResponseEntity<MedicalCaseDiagnosisDTO> getMedicalCaseDiagnosis(@PathVariable Long id) {
        log.debug("REST request to get MedicalCaseDiagnosis : {}", id);
        Optional<MedicalCaseDiagnosisDTO> medicalCaseDiagnosisDTO = medicalCaseDiagnosisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalCaseDiagnosisDTO);
    }

    /**
     * {@code DELETE  /medical-case-diagnoses/:id} : delete the "id" medicalCaseDiagnosis.
     *
     * @param id the id of the medicalCaseDiagnosisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-case-diagnoses/{id}")
    public ResponseEntity<Void> deleteMedicalCaseDiagnosis(@PathVariable Long id) {
        log.debug("REST request to delete MedicalCaseDiagnosis : {}", id);
        medicalCaseDiagnosisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
