package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.NavSettingsService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.NavSettingsDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.NavSettings}.
 */
@RestController
@RequestMapping("/api")
public class NavSettingsResource {

    private final Logger log = LoggerFactory.getLogger(NavSettingsResource.class);

    private static final String ENTITY_NAME = "navSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NavSettingsService navSettingsService;

    public NavSettingsResource(NavSettingsService navSettingsService) {
        this.navSettingsService = navSettingsService;
    }

    /**
     * {@code POST  /nav-settings} : Create a new navSettings.
     *
     * @param navSettingsDTO the navSettingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new navSettingsDTO, or with status {@code 400 (Bad Request)} if the navSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nav-settings")
    public ResponseEntity<NavSettingsDTO> createNavSettings(@Valid @RequestBody NavSettingsDTO navSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save NavSettings : {}", navSettingsDTO);
        if (navSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new navSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NavSettingsDTO result = navSettingsService.save(navSettingsDTO);
        return ResponseEntity.created(new URI("/api/nav-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nav-settings} : Updates an existing navSettings.
     *
     * @param navSettingsDTO the navSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated navSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the navSettingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the navSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nav-settings")
    public ResponseEntity<NavSettingsDTO> updateNavSettings(@Valid @RequestBody NavSettingsDTO navSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update NavSettings : {}", navSettingsDTO);
        if (navSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NavSettingsDTO result = navSettingsService.save(navSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, navSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nav-settings} : get all the navSettings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of navSettings in body.
     */
    @GetMapping("/nav-settings")
    public ResponseEntity<List<NavSettingsDTO>> getAllNavSettings(Pageable pageable) {
        log.debug("REST request to get a page of NavSettings");
        Page<NavSettingsDTO> page = navSettingsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nav-settings/:id} : get the "id" navSettings.
     *
     * @param id the id of the navSettingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the navSettingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nav-settings/{id}")
    public ResponseEntity<NavSettingsDTO> getNavSettings(@PathVariable Long id) {
        log.debug("REST request to get NavSettings : {}", id);
        Optional<NavSettingsDTO> navSettingsDTO = navSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(navSettingsDTO);
    }

    /**
     * {@code DELETE  /nav-settings/:id} : delete the "id" navSettings.
     *
     * @param id the id of the navSettingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nav-settings/{id}")
    public ResponseEntity<Void> deleteNavSettings(@PathVariable Long id) {
        log.debug("REST request to delete NavSettings : {}", id);
        navSettingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
