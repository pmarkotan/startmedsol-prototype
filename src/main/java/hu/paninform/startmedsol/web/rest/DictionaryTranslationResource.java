package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.DictionaryTranslationService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.DictionaryTranslationDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.DictionaryTranslation}.
 */
@RestController
@RequestMapping("/api")
public class DictionaryTranslationResource {

    private final Logger log = LoggerFactory.getLogger(DictionaryTranslationResource.class);

    private static final String ENTITY_NAME = "dictionaryTranslation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DictionaryTranslationService dictionaryTranslationService;

    public DictionaryTranslationResource(DictionaryTranslationService dictionaryTranslationService) {
        this.dictionaryTranslationService = dictionaryTranslationService;
    }

    /**
     * {@code POST  /dictionary-translations} : Create a new dictionaryTranslation.
     *
     * @param dictionaryTranslationDTO the dictionaryTranslationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dictionaryTranslationDTO, or with status {@code 400 (Bad Request)} if the dictionaryTranslation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dictionary-translations")
    public ResponseEntity<DictionaryTranslationDTO> createDictionaryTranslation(@Valid @RequestBody DictionaryTranslationDTO dictionaryTranslationDTO) throws URISyntaxException {
        log.debug("REST request to save DictionaryTranslation : {}", dictionaryTranslationDTO);
        if (dictionaryTranslationDTO.getId() != null) {
            throw new BadRequestAlertException("A new dictionaryTranslation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DictionaryTranslationDTO result = dictionaryTranslationService.save(dictionaryTranslationDTO);
        return ResponseEntity.created(new URI("/api/dictionary-translations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dictionary-translations} : Updates an existing dictionaryTranslation.
     *
     * @param dictionaryTranslationDTO the dictionaryTranslationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dictionaryTranslationDTO,
     * or with status {@code 400 (Bad Request)} if the dictionaryTranslationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dictionaryTranslationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dictionary-translations")
    public ResponseEntity<DictionaryTranslationDTO> updateDictionaryTranslation(@Valid @RequestBody DictionaryTranslationDTO dictionaryTranslationDTO) throws URISyntaxException {
        log.debug("REST request to update DictionaryTranslation : {}", dictionaryTranslationDTO);
        if (dictionaryTranslationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DictionaryTranslationDTO result = dictionaryTranslationService.save(dictionaryTranslationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dictionaryTranslationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dictionary-translations} : get all the dictionaryTranslations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dictionaryTranslations in body.
     */
    @GetMapping("/dictionary-translations")
    public ResponseEntity<List<DictionaryTranslationDTO>> getAllDictionaryTranslations(Pageable pageable) {
        log.debug("REST request to get a page of DictionaryTranslations");
        Page<DictionaryTranslationDTO> page = dictionaryTranslationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dictionary-translations/:id} : get the "id" dictionaryTranslation.
     *
     * @param id the id of the dictionaryTranslationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dictionaryTranslationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dictionary-translations/{id}")
    public ResponseEntity<DictionaryTranslationDTO> getDictionaryTranslation(@PathVariable Long id) {
        log.debug("REST request to get DictionaryTranslation : {}", id);
        Optional<DictionaryTranslationDTO> dictionaryTranslationDTO = dictionaryTranslationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dictionaryTranslationDTO);
    }

    /**
     * {@code DELETE  /dictionary-translations/:id} : delete the "id" dictionaryTranslation.
     *
     * @param id the id of the dictionaryTranslationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dictionary-translations/{id}")
    public ResponseEntity<Void> deleteDictionaryTranslation(@PathVariable Long id) {
        log.debug("REST request to delete DictionaryTranslation : {}", id);
        dictionaryTranslationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
