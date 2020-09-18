package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphEuIndication;
import hu.paninform.startmedsol.repository.PphEuIndicationRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphEuIndication}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphEuIndicationResource {

    private final Logger log = LoggerFactory.getLogger(PphEuIndicationResource.class);

    private static final String ENTITY_NAME = "pphEuIndication";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphEuIndicationRepository pphEuIndicationRepository;

    public PphEuIndicationResource(PphEuIndicationRepository pphEuIndicationRepository) {
        this.pphEuIndicationRepository = pphEuIndicationRepository;
    }

    /**
     * {@code POST  /pph-eu-indications} : Create a new pphEuIndication.
     *
     * @param pphEuIndication the pphEuIndication to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphEuIndication, or with status {@code 400 (Bad Request)} if the pphEuIndication has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-eu-indications")
    public ResponseEntity<PphEuIndication> createPphEuIndication(@Valid @RequestBody PphEuIndication pphEuIndication) throws URISyntaxException {
        log.debug("REST request to save PphEuIndication : {}", pphEuIndication);
        if (pphEuIndication.getId() != null) {
            throw new BadRequestAlertException("A new pphEuIndication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphEuIndication result = pphEuIndicationRepository.save(pphEuIndication);
        return ResponseEntity.created(new URI("/api/pph-eu-indications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-eu-indications} : Updates an existing pphEuIndication.
     *
     * @param pphEuIndication the pphEuIndication to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphEuIndication,
     * or with status {@code 400 (Bad Request)} if the pphEuIndication is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphEuIndication couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-eu-indications")
    public ResponseEntity<PphEuIndication> updatePphEuIndication(@Valid @RequestBody PphEuIndication pphEuIndication) throws URISyntaxException {
        log.debug("REST request to update PphEuIndication : {}", pphEuIndication);
        if (pphEuIndication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphEuIndication result = pphEuIndicationRepository.save(pphEuIndication);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphEuIndication.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-eu-indications} : get all the pphEuIndications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphEuIndications in body.
     */
    @GetMapping("/pph-eu-indications")
    public List<PphEuIndication> getAllPphEuIndications() {
        log.debug("REST request to get all PphEuIndications");
        return pphEuIndicationRepository.findAll();
    }

    /**
     * {@code GET  /pph-eu-indications/:id} : get the "id" pphEuIndication.
     *
     * @param id the id of the pphEuIndication to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphEuIndication, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-eu-indications/{id}")
    public ResponseEntity<PphEuIndication> getPphEuIndication(@PathVariable Long id) {
        log.debug("REST request to get PphEuIndication : {}", id);
        Optional<PphEuIndication> pphEuIndication = pphEuIndicationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphEuIndication);
    }

    /**
     * {@code DELETE  /pph-eu-indications/:id} : delete the "id" pphEuIndication.
     *
     * @param id the id of the pphEuIndication to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-eu-indications/{id}")
    public ResponseEntity<Void> deletePphEuIndication(@PathVariable Long id) {
        log.debug("REST request to delete PphEuIndication : {}", id);
        pphEuIndicationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
