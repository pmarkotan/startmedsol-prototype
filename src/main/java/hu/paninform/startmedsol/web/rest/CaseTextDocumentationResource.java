package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.CaseTextDocumentationService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.CaseTextDocumentationDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.CaseTextDocumentation}.
 */
@RestController
@RequestMapping("/api")
public class CaseTextDocumentationResource {

    private final Logger log = LoggerFactory.getLogger(CaseTextDocumentationResource.class);

    private static final String ENTITY_NAME = "caseTextDocumentation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseTextDocumentationService caseTextDocumentationService;

    public CaseTextDocumentationResource(CaseTextDocumentationService caseTextDocumentationService) {
        this.caseTextDocumentationService = caseTextDocumentationService;
    }

    /**
     * {@code POST  /case-text-documentations} : Create a new caseTextDocumentation.
     *
     * @param caseTextDocumentationDTO the caseTextDocumentationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseTextDocumentationDTO, or with status {@code 400 (Bad Request)} if the caseTextDocumentation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-text-documentations")
    public ResponseEntity<CaseTextDocumentationDTO> createCaseTextDocumentation(@Valid @RequestBody CaseTextDocumentationDTO caseTextDocumentationDTO) throws URISyntaxException {
        log.debug("REST request to save CaseTextDocumentation : {}", caseTextDocumentationDTO);
        if (caseTextDocumentationDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseTextDocumentation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseTextDocumentationDTO result = caseTextDocumentationService.save(caseTextDocumentationDTO);
        return ResponseEntity.created(new URI("/api/case-text-documentations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-text-documentations} : Updates an existing caseTextDocumentation.
     *
     * @param caseTextDocumentationDTO the caseTextDocumentationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseTextDocumentationDTO,
     * or with status {@code 400 (Bad Request)} if the caseTextDocumentationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseTextDocumentationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-text-documentations")
    public ResponseEntity<CaseTextDocumentationDTO> updateCaseTextDocumentation(@Valid @RequestBody CaseTextDocumentationDTO caseTextDocumentationDTO) throws URISyntaxException {
        log.debug("REST request to update CaseTextDocumentation : {}", caseTextDocumentationDTO);
        if (caseTextDocumentationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseTextDocumentationDTO result = caseTextDocumentationService.save(caseTextDocumentationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseTextDocumentationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-text-documentations} : get all the caseTextDocumentations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseTextDocumentations in body.
     */
    @GetMapping("/case-text-documentations")
    public ResponseEntity<List<CaseTextDocumentationDTO>> getAllCaseTextDocumentations(Pageable pageable) {
        log.debug("REST request to get a page of CaseTextDocumentations");
        Page<CaseTextDocumentationDTO> page = caseTextDocumentationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /case-text-documentations/:id} : get the "id" caseTextDocumentation.
     *
     * @param id the id of the caseTextDocumentationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseTextDocumentationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-text-documentations/{id}")
    public ResponseEntity<CaseTextDocumentationDTO> getCaseTextDocumentation(@PathVariable Long id) {
        log.debug("REST request to get CaseTextDocumentation : {}", id);
        Optional<CaseTextDocumentationDTO> caseTextDocumentationDTO = caseTextDocumentationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseTextDocumentationDTO);
    }

    /**
     * {@code DELETE  /case-text-documentations/:id} : delete the "id" caseTextDocumentation.
     *
     * @param id the id of the caseTextDocumentationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-text-documentations/{id}")
    public ResponseEntity<Void> deleteCaseTextDocumentation(@PathVariable Long id) {
        log.debug("REST request to delete CaseTextDocumentation : {}", id);
        caseTextDocumentationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
