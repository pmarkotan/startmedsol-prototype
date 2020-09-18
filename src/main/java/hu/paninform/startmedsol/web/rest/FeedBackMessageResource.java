package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.service.FeedBackMessageService;
import hu.paninform.startmedsol.web.rest.errors.BadRequestAlertException;
import hu.paninform.startmedsol.service.dto.FeedBackMessageDTO;

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
 * REST controller for managing {@link hu.paninform.startmedsol.domain.FeedBackMessage}.
 */
@RestController
@RequestMapping("/api")
public class FeedBackMessageResource {

    private final Logger log = LoggerFactory.getLogger(FeedBackMessageResource.class);

    private static final String ENTITY_NAME = "feedBackMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FeedBackMessageService feedBackMessageService;

    public FeedBackMessageResource(FeedBackMessageService feedBackMessageService) {
        this.feedBackMessageService = feedBackMessageService;
    }

    /**
     * {@code POST  /feed-back-messages} : Create a new feedBackMessage.
     *
     * @param feedBackMessageDTO the feedBackMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new feedBackMessageDTO, or with status {@code 400 (Bad Request)} if the feedBackMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/feed-back-messages")
    public ResponseEntity<FeedBackMessageDTO> createFeedBackMessage(@Valid @RequestBody FeedBackMessageDTO feedBackMessageDTO) throws URISyntaxException {
        log.debug("REST request to save FeedBackMessage : {}", feedBackMessageDTO);
        if (feedBackMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new feedBackMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeedBackMessageDTO result = feedBackMessageService.save(feedBackMessageDTO);
        return ResponseEntity.created(new URI("/api/feed-back-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /feed-back-messages} : Updates an existing feedBackMessage.
     *
     * @param feedBackMessageDTO the feedBackMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated feedBackMessageDTO,
     * or with status {@code 400 (Bad Request)} if the feedBackMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the feedBackMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/feed-back-messages")
    public ResponseEntity<FeedBackMessageDTO> updateFeedBackMessage(@Valid @RequestBody FeedBackMessageDTO feedBackMessageDTO) throws URISyntaxException {
        log.debug("REST request to update FeedBackMessage : {}", feedBackMessageDTO);
        if (feedBackMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeedBackMessageDTO result = feedBackMessageService.save(feedBackMessageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, feedBackMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /feed-back-messages} : get all the feedBackMessages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of feedBackMessages in body.
     */
    @GetMapping("/feed-back-messages")
    public ResponseEntity<List<FeedBackMessageDTO>> getAllFeedBackMessages(Pageable pageable) {
        log.debug("REST request to get a page of FeedBackMessages");
        Page<FeedBackMessageDTO> page = feedBackMessageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /feed-back-messages/:id} : get the "id" feedBackMessage.
     *
     * @param id the id of the feedBackMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the feedBackMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/feed-back-messages/{id}")
    public ResponseEntity<FeedBackMessageDTO> getFeedBackMessage(@PathVariable Long id) {
        log.debug("REST request to get FeedBackMessage : {}", id);
        Optional<FeedBackMessageDTO> feedBackMessageDTO = feedBackMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feedBackMessageDTO);
    }

    /**
     * {@code DELETE  /feed-back-messages/:id} : delete the "id" feedBackMessage.
     *
     * @param id the id of the feedBackMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/feed-back-messages/{id}")
    public ResponseEntity<Void> deleteFeedBackMessage(@PathVariable Long id) {
        log.debug("REST request to delete FeedBackMessage : {}", id);
        feedBackMessageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
