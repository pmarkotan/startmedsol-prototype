package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.ValidityService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.ValidityDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.Validity}.
 */
@RestController
@RequestMapping("/api")
public class ValidityResource {

    private final Logger log = LoggerFactory.getLogger(ValidityResource.class);

    private static final String ENTITY_NAME = "validity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValidityService validityService;

    public ValidityResource(ValidityService validityService) {
        this.validityService = validityService;
    }

    /**
     * {@code POST  /validities} : Create a new validity.
     *
     * @param validityDTO the validityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new validityDTO, or with status {@code 400 (Bad Request)} if the validity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/validities")
    public ResponseEntity<ValidityDTO> createValidity(@RequestBody ValidityDTO validityDTO) throws URISyntaxException {
        log.debug("REST request to save Validity : {}", validityDTO);
        if (validityDTO.getId() != null) {
            throw new BadRequestAlertException("A new validity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ValidityDTO result = validityService.save(validityDTO);
        return ResponseEntity.created(new URI("/api/validities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /validities} : Updates an existing validity.
     *
     * @param validityDTO the validityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validityDTO,
     * or with status {@code 400 (Bad Request)} if the validityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the validityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/validities")
    public ResponseEntity<ValidityDTO> updateValidity(@RequestBody ValidityDTO validityDTO) throws URISyntaxException {
        log.debug("REST request to update Validity : {}", validityDTO);
        if (validityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ValidityDTO result = validityService.save(validityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, validityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /validities} : get all the validities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of validities in body.
     */
    @GetMapping("/validities")
    public List<ValidityDTO> getAllValidities() {
        log.debug("REST request to get all Validities");
        return validityService.findAll();
    }

    /**
     * {@code GET  /validities/:id} : get the "id" validity.
     *
     * @param id the id of the validityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the validityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/validities/{id}")
    public ResponseEntity<ValidityDTO> getValidity(@PathVariable Long id) {
        log.debug("REST request to get Validity : {}", id);
        Optional<ValidityDTO> validityDTO = validityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(validityDTO);
    }

    /**
     * {@code DELETE  /validities/:id} : delete the "id" validity.
     *
     * @param id the id of the validityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/validities/{id}")
    public ResponseEntity<Void> deleteValidity(@PathVariable Long id) {
        log.debug("REST request to delete Validity : {}", id);
        validityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
