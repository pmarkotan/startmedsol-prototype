package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.ContactPersonService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.ContactPersonDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.ContactPerson}.
 */
@RestController
@RequestMapping("/api")
public class ContactPersonResource {

    private final Logger log = LoggerFactory.getLogger(ContactPersonResource.class);

    private static final String ENTITY_NAME = "contactPerson";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactPersonService contactPersonService;

    public ContactPersonResource(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    /**
     * {@code POST  /contact-people} : Create a new contactPerson.
     *
     * @param contactPersonDTO the contactPersonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactPersonDTO, or with status {@code 400 (Bad Request)} if the contactPerson has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-people")
    public ResponseEntity<ContactPersonDTO> createContactPerson(@Valid @RequestBody ContactPersonDTO contactPersonDTO) throws URISyntaxException {
        log.debug("REST request to save ContactPerson : {}", contactPersonDTO);
        if (contactPersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new contactPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactPersonDTO result = contactPersonService.save(contactPersonDTO);
        return ResponseEntity.created(new URI("/api/contact-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-people} : Updates an existing contactPerson.
     *
     * @param contactPersonDTO the contactPersonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactPersonDTO,
     * or with status {@code 400 (Bad Request)} if the contactPersonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactPersonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-people")
    public ResponseEntity<ContactPersonDTO> updateContactPerson(@Valid @RequestBody ContactPersonDTO contactPersonDTO) throws URISyntaxException {
        log.debug("REST request to update ContactPerson : {}", contactPersonDTO);
        if (contactPersonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactPersonDTO result = contactPersonService.save(contactPersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactPersonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contact-people} : get all the contactPeople.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactPeople in body.
     */
    @GetMapping("/contact-people")
    public ResponseEntity<List<ContactPersonDTO>> getAllContactPeople(Pageable pageable) {
        log.debug("REST request to get a page of ContactPeople");
        Page<ContactPersonDTO> page = contactPersonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contact-people/:id} : get the "id" contactPerson.
     *
     * @param id the id of the contactPersonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactPersonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-people/{id}")
    public ResponseEntity<ContactPersonDTO> getContactPerson(@PathVariable Long id) {
        log.debug("REST request to get ContactPerson : {}", id);
        Optional<ContactPersonDTO> contactPersonDTO = contactPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactPersonDTO);
    }

    /**
     * {@code DELETE  /contact-people/:id} : delete the "id" contactPerson.
     *
     * @param id the id of the contactPersonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-people/{id}")
    public ResponseEntity<Void> deleteContactPerson(@PathVariable Long id) {
        log.debug("REST request to delete ContactPerson : {}", id);
        contactPersonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
