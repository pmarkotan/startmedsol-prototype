package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.domain.PphAidISOBook;
import hu.paninform.startmedsol.repository.PphAidISOBookRepository;
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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PphAidISOBook}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PphAidISOBookResource {

    private final Logger log = LoggerFactory.getLogger(PphAidISOBookResource.class);

    private static final String ENTITY_NAME = "pphAidISOBook";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PphAidISOBookRepository pphAidISOBookRepository;

    public PphAidISOBookResource(PphAidISOBookRepository pphAidISOBookRepository) {
        this.pphAidISOBookRepository = pphAidISOBookRepository;
    }

    /**
     * {@code POST  /pph-aid-iso-books} : Create a new pphAidISOBook.
     *
     * @param pphAidISOBook the pphAidISOBook to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pphAidISOBook, or with status {@code 400 (Bad Request)} if the pphAidISOBook has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pph-aid-iso-books")
    public ResponseEntity<PphAidISOBook> createPphAidISOBook(@Valid @RequestBody PphAidISOBook pphAidISOBook) throws URISyntaxException {
        log.debug("REST request to save PphAidISOBook : {}", pphAidISOBook);
        if (pphAidISOBook.getId() != null) {
            throw new BadRequestAlertException("A new pphAidISOBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PphAidISOBook result = pphAidISOBookRepository.save(pphAidISOBook);
        return ResponseEntity.created(new URI("/api/pph-aid-iso-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pph-aid-iso-books} : Updates an existing pphAidISOBook.
     *
     * @param pphAidISOBook the pphAidISOBook to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pphAidISOBook,
     * or with status {@code 400 (Bad Request)} if the pphAidISOBook is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pphAidISOBook couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pph-aid-iso-books")
    public ResponseEntity<PphAidISOBook> updatePphAidISOBook(@Valid @RequestBody PphAidISOBook pphAidISOBook) throws URISyntaxException {
        log.debug("REST request to update PphAidISOBook : {}", pphAidISOBook);
        if (pphAidISOBook.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PphAidISOBook result = pphAidISOBookRepository.save(pphAidISOBook);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pphAidISOBook.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pph-aid-iso-books} : get all the pphAidISOBooks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pphAidISOBooks in body.
     */
    @GetMapping("/pph-aid-iso-books")
    public List<PphAidISOBook> getAllPphAidISOBooks() {
        log.debug("REST request to get all PphAidISOBooks");
        return pphAidISOBookRepository.findAll();
    }

    /**
     * {@code GET  /pph-aid-iso-books/:id} : get the "id" pphAidISOBook.
     *
     * @param id the id of the pphAidISOBook to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pphAidISOBook, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pph-aid-iso-books/{id}")
    public ResponseEntity<PphAidISOBook> getPphAidISOBook(@PathVariable Long id) {
        log.debug("REST request to get PphAidISOBook : {}", id);
        Optional<PphAidISOBook> pphAidISOBook = pphAidISOBookRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pphAidISOBook);
    }

    /**
     * {@code DELETE  /pph-aid-iso-books/:id} : delete the "id" pphAidISOBook.
     *
     * @param id the id of the pphAidISOBook to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pph-aid-iso-books/{id}")
    public ResponseEntity<Void> deletePphAidISOBook(@PathVariable Long id) {
        log.debug("REST request to delete PphAidISOBook : {}", id);
        pphAidISOBookRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
