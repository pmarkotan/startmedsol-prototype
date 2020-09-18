package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.CsPostalCodeService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.CsPostalCodeDTO;
import hu.paninform.startmedsol.service.dto.CsPostalCodeCriteria;
import hu.paninform.startmedsol.service.CsPostalCodeQueryService;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.CsPostalCode}.
 */
@RestController
@RequestMapping("/api")
public class CsPostalCodeResource {

    private final Logger log = LoggerFactory.getLogger(CsPostalCodeResource.class);

    private static final String ENTITY_NAME = "csPostalCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CsPostalCodeService csPostalCodeService;

    private final CsPostalCodeQueryService csPostalCodeQueryService;

    public CsPostalCodeResource(CsPostalCodeService csPostalCodeService, CsPostalCodeQueryService csPostalCodeQueryService) {
        this.csPostalCodeService = csPostalCodeService;
        this.csPostalCodeQueryService = csPostalCodeQueryService;
    }

    /**
     * {@code POST  /cs-postal-codes} : Create a new csPostalCode.
     *
     * @param csPostalCodeDTO the csPostalCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new csPostalCodeDTO, or with status {@code 400 (Bad Request)} if the csPostalCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cs-postal-codes")
    public ResponseEntity<CsPostalCodeDTO> createCsPostalCode(@Valid @RequestBody CsPostalCodeDTO csPostalCodeDTO) throws URISyntaxException {
        log.debug("REST request to save CsPostalCode : {}", csPostalCodeDTO);
        if (csPostalCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new csPostalCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CsPostalCodeDTO result = csPostalCodeService.save(csPostalCodeDTO);
        return ResponseEntity.created(new URI("/api/cs-postal-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cs-postal-codes} : Updates an existing csPostalCode.
     *
     * @param csPostalCodeDTO the csPostalCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated csPostalCodeDTO,
     * or with status {@code 400 (Bad Request)} if the csPostalCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the csPostalCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cs-postal-codes")
    public ResponseEntity<CsPostalCodeDTO> updateCsPostalCode(@Valid @RequestBody CsPostalCodeDTO csPostalCodeDTO) throws URISyntaxException {
        log.debug("REST request to update CsPostalCode : {}", csPostalCodeDTO);
        if (csPostalCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CsPostalCodeDTO result = csPostalCodeService.save(csPostalCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, csPostalCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cs-postal-codes} : get all the csPostalCodes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of csPostalCodes in body.
     */
    @GetMapping("/cs-postal-codes")
    public ResponseEntity<List<CsPostalCodeDTO>> getAllCsPostalCodes(CsPostalCodeCriteria criteria) {
        log.debug("REST request to get CsPostalCodes by criteria: {}", criteria);
        List<CsPostalCodeDTO> entityList = csPostalCodeQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /cs-postal-codes/count} : count all the csPostalCodes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/cs-postal-codes/count")
    public ResponseEntity<Long> countCsPostalCodes(CsPostalCodeCriteria criteria) {
        log.debug("REST request to count CsPostalCodes by criteria: {}", criteria);
        return ResponseEntity.ok().body(csPostalCodeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cs-postal-codes/:id} : get the "id" csPostalCode.
     *
     * @param id the id of the csPostalCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the csPostalCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cs-postal-codes/{id}")
    public ResponseEntity<CsPostalCodeDTO> getCsPostalCode(@PathVariable Long id) {
        log.debug("REST request to get CsPostalCode : {}", id);
        Optional<CsPostalCodeDTO> csPostalCodeDTO = csPostalCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(csPostalCodeDTO);
    }

    /**
     * {@code DELETE  /cs-postal-codes/:id} : delete the "id" csPostalCode.
     *
     * @param id the id of the csPostalCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cs-postal-codes/{id}")
    public ResponseEntity<Void> deleteCsPostalCode(@PathVariable Long id) {
        log.debug("REST request to delete CsPostalCode : {}", id);
        csPostalCodeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
