package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.EhrDocumentService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.EhrDocumentDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.EhrDocument}.
 */
@RestController
@RequestMapping("/api")
public class EhrDocumentResource {

    private final Logger log = LoggerFactory.getLogger(EhrDocumentResource.class);

    private static final String ENTITY_NAME = "ehrDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EhrDocumentService ehrDocumentService;

    public EhrDocumentResource(EhrDocumentService ehrDocumentService) {
        this.ehrDocumentService = ehrDocumentService;
    }

    /**
     * {@code POST  /ehr-documents} : Create a new ehrDocument.
     *
     * @param ehrDocumentDTO the ehrDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ehrDocumentDTO, or with status {@code 400 (Bad Request)} if the ehrDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ehr-documents")
    public ResponseEntity<EhrDocumentDTO> createEhrDocument(@RequestBody EhrDocumentDTO ehrDocumentDTO) throws URISyntaxException {
        log.debug("REST request to save EhrDocument : {}", ehrDocumentDTO);
        if (ehrDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new ehrDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EhrDocumentDTO result = ehrDocumentService.save(ehrDocumentDTO);
        return ResponseEntity.created(new URI("/api/ehr-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ehr-documents} : Updates an existing ehrDocument.
     *
     * @param ehrDocumentDTO the ehrDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ehrDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the ehrDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ehrDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ehr-documents")
    public ResponseEntity<EhrDocumentDTO> updateEhrDocument(@RequestBody EhrDocumentDTO ehrDocumentDTO) throws URISyntaxException {
        log.debug("REST request to update EhrDocument : {}", ehrDocumentDTO);
        if (ehrDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EhrDocumentDTO result = ehrDocumentService.save(ehrDocumentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ehrDocumentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ehr-documents} : get all the ehrDocuments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ehrDocuments in body.
     */
    @GetMapping("/ehr-documents")
    public ResponseEntity<List<EhrDocumentDTO>> getAllEhrDocuments(Pageable pageable) {
        log.debug("REST request to get a page of EhrDocuments");
        Page<EhrDocumentDTO> page = ehrDocumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ehr-documents/:id} : get the "id" ehrDocument.
     *
     * @param id the id of the ehrDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ehrDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ehr-documents/{id}")
    public ResponseEntity<EhrDocumentDTO> getEhrDocument(@PathVariable Long id) {
        log.debug("REST request to get EhrDocument : {}", id);
        Optional<EhrDocumentDTO> ehrDocumentDTO = ehrDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ehrDocumentDTO);
    }

    /**
     * {@code DELETE  /ehr-documents/:id} : delete the "id" ehrDocument.
     *
     * @param id the id of the ehrDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ehr-documents/{id}")
    public ResponseEntity<Void> deleteEhrDocument(@PathVariable Long id) {
        log.debug("REST request to delete EhrDocument : {}", id);
        ehrDocumentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
