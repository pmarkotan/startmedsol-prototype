package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphSpecBudget;
import hu.paninform.startmedsol.repository.PphSpecBudgetRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphSpecBudget}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphSpecBudgetResource {

    private final Logger log = LoggerFactory.getLogger(PphSpecBudgetResource.class);

    private static final String ENTITY_NAME = "pphSpecBudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphSpecBudgetRepository pphSpecBudgetRepository;

    public PphSpecBudgetResource(PphSpecBudgetRepository pphSpecBudgetRepository) {
        this.pphSpecBudgetRepository = pphSpecBudgetRepository;
    }

    /**
     * {@code POST  /pph-spec-budgets} : Create a new pphSpecBudget.
     *
     * @param pphSpecBudget the pphSpecBudget to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphSpecBudget, or with status {@code 400 (Bad Request)} if the pphSpecBudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-spec-budgets")
    public ResponseEntity<PphSpecBudget> createPphSpecBudget(@Valid @RequestBody PphSpecBudget pphSpecBudget) throws URISyntaxException {
        log.debug("REST request to save PphSpecBudget : {}", pphSpecBudget);
        if (pphSpecBudget.getId() != null) {
            throw new BadRequestAlertException("A new pphSpecBudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphSpecBudget result = pphSpecBudgetRepository.save(pphSpecBudget);
        return ResponseEntity.created(new URI("/api/pph-spec-budgets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-spec-budgets} : Updates an existing pphSpecBudget.
     *
     * @param pphSpecBudget the pphSpecBudget to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphSpecBudget,
     * or with status {@code 400 (Bad Request)} if the pphSpecBudget is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphSpecBudget couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-spec-budgets")
    public ResponseEntity<PphSpecBudget> updatePphSpecBudget(@Valid @RequestBody PphSpecBudget pphSpecBudget) throws URISyntaxException {
        log.debug("REST request to update PphSpecBudget : {}", pphSpecBudget);
        if (pphSpecBudget.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphSpecBudget result = pphSpecBudgetRepository.save(pphSpecBudget);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphSpecBudget.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-spec-budgets} : get all the pphSpecBudgets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphSpecBudgets in body.
     */
    @GetMapping("/pph-spec-budgets")
    public List<PphSpecBudget> getAllPphSpecBudgets() {
        log.debug("REST request to get all PphSpecBudgets");
        return pphSpecBudgetRepository.findAll();
    }

    /**
     * {@code GET  /pph-spec-budgets/:id} : get the "id" pphSpecBudget.
     *
     * @param id the id of the pphSpecBudget to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphSpecBudget, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-spec-budgets/{id}")
    public ResponseEntity<PphSpecBudget> getPphSpecBudget(@PathVariable Long id) {
        log.debug("REST request to get PphSpecBudget : {}", id);
        Optional<PphSpecBudget> pphSpecBudget = pphSpecBudgetRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphSpecBudget);
    }

    /**
     * {@code DELETE  /pph-spec-budgets/:id} : delete the "id" pphSpecBudget.
     *
     * @param id the id of the pphSpecBudget to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-spec-budgets/{id}")
    public ResponseEntity<Void> deletePphSpecBudget(@PathVariable Long id) {
        log.debug("REST request to delete PphSpecBudget : {}", id);
        pphSpecBudgetRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
