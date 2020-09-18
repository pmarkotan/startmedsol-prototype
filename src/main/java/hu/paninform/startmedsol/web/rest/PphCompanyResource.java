package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphCompany;
import hu.paninform.startmedsol.repository.PphCompanyRepository;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphCompany}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphCompanyResource {

    private final Logger log = LoggerFactory.getLogger(PphCompanyResource.class);

    private static final String ENTITY_NAME = "pphCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphCompanyRepository pphCompanyRepository;

    public PphCompanyResource(PphCompanyRepository pphCompanyRepository) {
        this.pphCompanyRepository = pphCompanyRepository;
    }

    /**
     * {@code POST  /pph-companies} : Create a new pphCompany.
     *
     * @param pphCompany the pphCompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphCompany, or with status {@code 400 (Bad Request)} if the pphCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-companies")
    public ResponseEntity<PphCompany> createPphCompany(@Valid @RequestBody PphCompany pphCompany) throws URISyntaxException {
        log.debug("REST request to save PphCompany : {}", pphCompany);
        if (pphCompany.getId() != null) {
            throw new BadRequestAlertException("A new pphCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphCompany result = pphCompanyRepository.save(pphCompany);
        return ResponseEntity.created(new URI("/api/pph-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-companies} : Updates an existing pphCompany.
     *
     * @param pphCompany the pphCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphCompany,
     * or with status {@code 400 (Bad Request)} if the pphCompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-companies")
    public ResponseEntity<PphCompany> updatePphCompany(@Valid @RequestBody PphCompany pphCompany) throws URISyntaxException {
        log.debug("REST request to update PphCompany : {}", pphCompany);
        if (pphCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphCompany result = pphCompanyRepository.save(pphCompany);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphCompany.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-companies} : get all the pphCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphCompanies in body.
     */
    @GetMapping("/pph-companies")
    public List<PphCompany> getAllPphCompanies() {
        log.debug("REST request to get all PphCompanies");
        return pphCompanyRepository.findAll();
    }

    /**
     * {@code GET  /pph-companies/:id} : get the "id" pphCompany.
     *
     * @param id the id of the pphCompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphCompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-companies/{id}")
    public ResponseEntity<PphCompany> getPphCompany(@PathVariable Long id) {
        log.debug("REST request to get PphCompany : {}", id);
        Optional<PphCompany> pphCompany = pphCompanyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphCompany);
    }

    /**
     * {@code DELETE  /pph-companies/:id} : delete the "id" pphCompany.
     *
     * @param id the id of the pphCompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-companies/{id}")
    public ResponseEntity<Void> deletePphCompany(@PathVariable Long id) {
        log.debug("REST request to delete PphCompany : {}", id);
        pphCompanyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
