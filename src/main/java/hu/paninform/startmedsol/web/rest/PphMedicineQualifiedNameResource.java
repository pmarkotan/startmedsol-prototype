package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.PphMedicineQualifiedNameService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameDTO;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameCriteria;
import hu.paninform.startmedsol.service.PphMedicineQualifiedNameQueryService;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphMedicineQualifiedName}.
 */
@RestController
@RequestMapping("/api")
public class PphMedicineQualifiedNameResource {

    private final Logger log = LoggerFactory.getLogger(PphMedicineQualifiedNameResource.class);

    private static final String ENTITY_NAME = "pphMedicineQualifiedName";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphMedicineQualifiedNameService pphMedicineQualifiedNameService;

    private final PphMedicineQualifiedNameQueryService pphMedicineQualifiedNameQueryService;

    public PphMedicineQualifiedNameResource(PphMedicineQualifiedNameService pphMedicineQualifiedNameService, PphMedicineQualifiedNameQueryService pphMedicineQualifiedNameQueryService) {
        this.pphMedicineQualifiedNameService = pphMedicineQualifiedNameService;
        this.pphMedicineQualifiedNameQueryService = pphMedicineQualifiedNameQueryService;
    }

    /**
     * {@code POST  /pph-medicine-qualified-names} : Create a new pphMedicineQualifiedName.
     *
     * @param pphMedicineQualifiedNameDTO the pphMedicineQualifiedNameDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphMedicineQualifiedNameDTO, or with status {@code 400 (Bad Request)} if the pphMedicineQualifiedName has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-medicine-qualified-names")
    public ResponseEntity<PphMedicineQualifiedNameDTO> createPphMedicineQualifiedName(@Valid @RequestBody PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO) throws URISyntaxException {
        log.debug("REST request to save PphMedicineQualifiedName : {}", pphMedicineQualifiedNameDTO);
        if (pphMedicineQualifiedNameDTO.getId() != null) {
            throw new BadRequestAlertException("A new pphMedicineQualifiedName cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphMedicineQualifiedNameDTO result = pphMedicineQualifiedNameService.save(pphMedicineQualifiedNameDTO);
        return ResponseEntity.created(new URI("/api/pph-medicine-qualified-names/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-medicine-qualified-names} : Updates an existing pphMedicineQualifiedName.
     *
     * @param pphMedicineQualifiedNameDTO the pphMedicineQualifiedNameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphMedicineQualifiedNameDTO,
     * or with status {@code 400 (Bad Request)} if the pphMedicineQualifiedNameDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphMedicineQualifiedNameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-medicine-qualified-names")
    public ResponseEntity<PphMedicineQualifiedNameDTO> updatePphMedicineQualifiedName(@Valid @RequestBody PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO) throws URISyntaxException {
        log.debug("REST request to update PphMedicineQualifiedName : {}", pphMedicineQualifiedNameDTO);
        if (pphMedicineQualifiedNameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphMedicineQualifiedNameDTO result = pphMedicineQualifiedNameService.save(pphMedicineQualifiedNameDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphMedicineQualifiedNameDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-medicine-qualified-names} : get all the pphMedicineQualifiedNames.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphMedicineQualifiedNames in body.
     */
    @GetMapping("/pph-medicine-qualified-names")
    public ResponseEntity<List<PphMedicineQualifiedNameDTO>> getAllPphMedicineQualifiedNames(PphMedicineQualifiedNameCriteria criteria) {
        log.debug("REST request to get PphMedicineQualifiedNames by criteria: {}", criteria);
        List<PphMedicineQualifiedNameDTO> entityList = pphMedicineQualifiedNameQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /pph-medicine-qualified-names/count} : count all the pphMedicineQualifiedNames.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/pph-medicine-qualified-names/count")
    public ResponseEntity<Long> countPphMedicineQualifiedNames(PphMedicineQualifiedNameCriteria criteria) {
        log.debug("REST request to count PphMedicineQualifiedNames by criteria: {}", criteria);
        return ResponseEntity.ok().body(pphMedicineQualifiedNameQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pph-medicine-qualified-names/:id} : get the "id" pphMedicineQualifiedName.
     *
     * @param id the id of the pphMedicineQualifiedNameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphMedicineQualifiedNameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-medicine-qualified-names/{id}")
    public ResponseEntity<PphMedicineQualifiedNameDTO> getPphMedicineQualifiedName(@PathVariable Long id) {
        log.debug("REST request to get PphMedicineQualifiedName : {}", id);
        Optional<PphMedicineQualifiedNameDTO> pphMedicineQualifiedNameDTO = pphMedicineQualifiedNameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pphMedicineQualifiedNameDTO);
    }

    /**
     * {@code DELETE  /pph-medicine-qualified-names/:id} : delete the "id" pphMedicineQualifiedName.
     *
     * @param id the id of the pphMedicineQualifiedNameDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-medicine-qualified-names/{id}")
    public ResponseEntity<Void> deletePphMedicineQualifiedName(@PathVariable Long id) {
        log.debug("REST request to delete PphMedicineQualifiedName : {}", id);
        pphMedicineQualifiedNameService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
