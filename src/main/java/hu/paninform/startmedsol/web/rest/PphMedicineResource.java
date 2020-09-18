package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.PphMedicineService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.PphMedicineDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphMedicine}.
 */
@RestController
@RequestMapping("/api")
public class PphMedicineResource {

    private final Logger log = LoggerFactory.getLogger(PphMedicineResource.class);

    private static final String ENTITY_NAME = "pphMedicine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphMedicineService pphMedicineService;

    public PphMedicineResource(PphMedicineService pphMedicineService) {
        this.pphMedicineService = pphMedicineService;
    }

    /**
     * {@code POST  /pph-medicines} : Create a new pphMedicine.
     *
     * @param pphMedicineDTO the pphMedicineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphMedicineDTO, or with status {@code 400 (Bad Request)} if the pphMedicine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-medicines")
    public ResponseEntity<PphMedicineDTO> createPphMedicine(@Valid @RequestBody PphMedicineDTO pphMedicineDTO) throws URISyntaxException {
        log.debug("REST request to save PphMedicine : {}", pphMedicineDTO);
        if (pphMedicineDTO.getId() != null) {
            throw new BadRequestAlertException("A new pphMedicine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphMedicineDTO result = pphMedicineService.save(pphMedicineDTO);
        return ResponseEntity.created(new URI("/api/pph-medicines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-medicines} : Updates an existing pphMedicine.
     *
     * @param pphMedicineDTO the pphMedicineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphMedicineDTO,
     * or with status {@code 400 (Bad Request)} if the pphMedicineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphMedicineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-medicines")
    public ResponseEntity<PphMedicineDTO> updatePphMedicine(@Valid @RequestBody PphMedicineDTO pphMedicineDTO) throws URISyntaxException {
        log.debug("REST request to update PphMedicine : {}", pphMedicineDTO);
        if (pphMedicineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphMedicineDTO result = pphMedicineService.save(pphMedicineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphMedicineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-medicines} : get all the pphMedicines.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphMedicines in body.
     */
    @GetMapping("/pph-medicines")
    public ResponseEntity<List<PphMedicineDTO>> getAllPphMedicines(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of PphMedicines");
        Page<PphMedicineDTO> page;
        if (eagerload) {
            page = pphMedicineService.findAllWithEagerRelationships(pageable);
        } else {
            page = pphMedicineService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pph-medicines/:id} : get the "id" pphMedicine.
     *
     * @param id the id of the pphMedicineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphMedicineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-medicines/{id}")
    public ResponseEntity<PphMedicineDTO> getPphMedicine(@PathVariable Long id) {
        log.debug("REST request to get PphMedicine : {}", id);
        Optional<PphMedicineDTO> pphMedicineDTO = pphMedicineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pphMedicineDTO);
    }

    /**
     * {@code DELETE  /pph-medicines/:id} : delete the "id" pphMedicine.
     *
     * @param id the id of the pphMedicineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-medicines/{id}")
    public ResponseEntity<Void> deletePphMedicine(@PathVariable Long id) {
        log.debug("REST request to delete PphMedicine : {}", id);
        pphMedicineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
