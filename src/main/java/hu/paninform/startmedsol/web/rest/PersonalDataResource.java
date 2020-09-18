package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.PersonalDataService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.PersonalDataDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hu.paninform.startmedsol.domain.PersonalData}.
 */
@RestController
@RequestMapping("/api")
public class PersonalDataResource {

    private final Logger log = LoggerFactory.getLogger(PersonalDataResource.class);

    private static final String ENTITY_NAME = "personalData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PersonalDataService personalDataService;

    public PersonalDataResource(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    /**
     * {@code POST  /personal-data} : Create a new personalData.
     *
     * @param personalDataDTO the personalDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personalDataDTO, or with status {@code 400 (Bad Request)} if the personalData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personal-data")
    public ResponseEntity<PersonalDataDTO> createPersonalData(@Valid @RequestBody PersonalDataDTO personalDataDTO) throws URISyntaxException {
        log.debug("REST request to save PersonalData : {}", personalDataDTO);
        if (personalDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new personalData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonalDataDTO result = personalDataService.save(personalDataDTO);
        return ResponseEntity.created(new URI("/api/personal-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personal-data} : Updates an existing personalData.
     *
     * @param personalDataDTO the personalDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personalDataDTO,
     * or with status {@code 400 (Bad Request)} if the personalDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personalDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personal-data")
    public ResponseEntity<PersonalDataDTO> updatePersonalData(@Valid @RequestBody PersonalDataDTO personalDataDTO) throws URISyntaxException {
        log.debug("REST request to update PersonalData : {}", personalDataDTO);
        if (personalDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PersonalDataDTO result = personalDataService.save(personalDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personalDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /personal-data} : get all the personalData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personalData in body.
     */
    @GetMapping("/personal-data")
    public List<PersonalDataDTO> getAllPersonalData() {
        log.debug("REST request to get all PersonalData");
        return personalDataService.findAll();
    }

    /**
     * {@code GET  /personal-data/:id} : get the "id" personalData.
     *
     * @param id the id of the personalDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personalDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personal-data/{id}")
    public ResponseEntity<PersonalDataDTO> getPersonalData(@PathVariable Long id) {
        log.debug("REST request to get PersonalData : {}", id);
        Optional<PersonalDataDTO> personalDataDTO = personalDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personalDataDTO);
    }

    /**
     * {@code DELETE  /personal-data/:id} : delete the "id" personalData.
     *
     * @param id the id of the personalDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personal-data/{id}")
    public ResponseEntity<Void> deletePersonalData(@PathVariable Long id) {
        log.debug("REST request to delete PersonalData : {}", id);
        personalDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
