package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.ExternalDocumentService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.ExternalDocumentDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.ExternalDocument}.
 */
@RestController
@RequestMapping("/api")
public class ExternalDocumentResource {

    private final Logger log = LoggerFactory.getLogger(ExternalDocumentResource.class);

    private static final String ENTITY_NAME = "externalDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExternalDocumentService externalDocumentService;

    public ExternalDocumentResource(ExternalDocumentService externalDocumentService) {
        this.externalDocumentService = externalDocumentService;
    }

    /**
     * {@code POST  /external-documents} : Create a new externalDocument.
     *
     * @param externalDocumentDTO the externalDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new externalDocumentDTO, or with status {@code 400 (Bad Request)} if the externalDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/external-documents")
    public ResponseEntity<ExternalDocumentDTO> createExternalDocument(@Valid @RequestBody ExternalDocumentDTO externalDocumentDTO) throws URISyntaxException {
        log.debug("REST request to save ExternalDocument : {}", externalDocumentDTO);
        if (externalDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new externalDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExternalDocumentDTO result = externalDocumentService.save(externalDocumentDTO);
        return ResponseEntity.created(new URI("/api/external-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /external-documents} : Updates an existing externalDocument.
     *
     * @param externalDocumentDTO the externalDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated externalDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the externalDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the externalDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/external-documents")
    public ResponseEntity<ExternalDocumentDTO> updateExternalDocument(@Valid @RequestBody ExternalDocumentDTO externalDocumentDTO) throws URISyntaxException {
        log.debug("REST request to update ExternalDocument : {}", externalDocumentDTO);
        if (externalDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExternalDocumentDTO result = externalDocumentService.save(externalDocumentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, externalDocumentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /external-documents} : get all the externalDocuments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of externalDocuments in body.
     */
    @GetMapping("/external-documents")
    public ResponseEntity<List<ExternalDocumentDTO>> getAllExternalDocuments(Pageable pageable) {
        log.debug("REST request to get a page of ExternalDocuments");
        Page<ExternalDocumentDTO> page = externalDocumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /external-documents/:id} : get the "id" externalDocument.
     *
     * @param id the id of the externalDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the externalDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/external-documents/{id}")
    public ResponseEntity<ExternalDocumentDTO> getExternalDocument(@PathVariable Long id) {
        log.debug("REST request to get ExternalDocument : {}", id);
        Optional<ExternalDocumentDTO> externalDocumentDTO = externalDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(externalDocumentDTO);
    }

    /**
     * {@code DELETE  /external-documents/:id} : delete the "id" externalDocument.
     *
     * @param id the id of the externalDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/external-documents/{id}")
    public ResponseEntity<Void> deleteExternalDocument(@PathVariable Long id) {
        log.debug("REST request to delete ExternalDocument : {}", id);
        externalDocumentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
