package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.DynamicFormService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.DynamicFormDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.DynamicForm}.
 */
@RestController
@RequestMapping("/api")
public class DynamicFormResource {

    private final Logger log = LoggerFactory.getLogger(DynamicFormResource.class);

    private static final String ENTITY_NAME = "dynamicForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DynamicFormService dynamicFormService;

    public DynamicFormResource(DynamicFormService dynamicFormService) {
        this.dynamicFormService = dynamicFormService;
    }

    /**
     * {@code POST  /dynamic-forms} : Create a new dynamicForm.
     *
     * @param dynamicFormDTO the dynamicFormDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dynamicFormDTO, or with status {@code 400 (Bad Request)} if the dynamicForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dynamic-forms")
    public ResponseEntity<DynamicFormDTO> createDynamicForm(@Valid @RequestBody DynamicFormDTO dynamicFormDTO) throws URISyntaxException {
        log.debug("REST request to save DynamicForm : {}", dynamicFormDTO);
        if (dynamicFormDTO.getId() != null) {
            throw new BadRequestAlertException("A new dynamicForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DynamicFormDTO result = dynamicFormService.save(dynamicFormDTO);
        return ResponseEntity.created(new URI("/api/dynamic-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dynamic-forms} : Updates an existing dynamicForm.
     *
     * @param dynamicFormDTO the dynamicFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dynamicFormDTO,
     * or with status {@code 400 (Bad Request)} if the dynamicFormDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dynamicFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dynamic-forms")
    public ResponseEntity<DynamicFormDTO> updateDynamicForm(@Valid @RequestBody DynamicFormDTO dynamicFormDTO) throws URISyntaxException {
        log.debug("REST request to update DynamicForm : {}", dynamicFormDTO);
        if (dynamicFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DynamicFormDTO result = dynamicFormService.save(dynamicFormDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dynamicFormDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dynamic-forms} : get all the dynamicForms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dynamicForms in body.
     */
    @GetMapping("/dynamic-forms")
    public ResponseEntity<List<DynamicFormDTO>> getAllDynamicForms(Pageable pageable) {
        log.debug("REST request to get a page of DynamicForms");
        Page<DynamicFormDTO> page = dynamicFormService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dynamic-forms/:id} : get the "id" dynamicForm.
     *
     * @param id the id of the dynamicFormDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dynamicFormDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dynamic-forms/{id}")
    public ResponseEntity<DynamicFormDTO> getDynamicForm(@PathVariable Long id) {
        log.debug("REST request to get DynamicForm : {}", id);
        Optional<DynamicFormDTO> dynamicFormDTO = dynamicFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dynamicFormDTO);
    }

    /**
     * {@code DELETE  /dynamic-forms/:id} : delete the "id" dynamicForm.
     *
     * @param id the id of the dynamicFormDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dynamic-forms/{id}")
    public ResponseEntity<Void> deleteDynamicForm(@PathVariable Long id) {
        log.debug("REST request to delete DynamicForm : {}", id);
        dynamicFormService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
