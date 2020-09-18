package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PuphaLoader;
import hu.paninform.startmedsol.repository.PuphaLoaderRepository;
import hu.paninform.startmedsol.service.PuphaLoaderService;
import hu.paninform.startmedsol.service.dto.PuphaLoaderDTO;
import hu.paninform.startmedsol.service.mapper.PuphaLoaderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PuphaLoaderResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PuphaLoaderResourceIT {

    private static final String DEFAULT_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_EVENT = "BBBBBBBBBB";

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PuphaLoaderRepository puphaLoaderRepository;

    @Autowired
    private PuphaLoaderMapper puphaLoaderMapper;

    @Autowired
    private PuphaLoaderService puphaLoaderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPuphaLoaderMockMvc;

    private PuphaLoader puphaLoader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PuphaLoader createEntity(EntityManager em) {
        PuphaLoader puphaLoader = new PuphaLoader()
            .event(DEFAULT_EVENT)
            .log(DEFAULT_LOG)
            .time(DEFAULT_TIME);
        return puphaLoader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PuphaLoader createUpdatedEntity(EntityManager em) {
        PuphaLoader puphaLoader = new PuphaLoader()
            .event(UPDATED_EVENT)
            .log(UPDATED_LOG)
            .time(UPDATED_TIME);
        return puphaLoader;
    }

    @BeforeEach
    public void initTest() {
        puphaLoader = createEntity(em);
    }

    @Test
    @Transactional
    public void createPuphaLoader() throws Exception {
        int databaseSizeBeforeCreate = puphaLoaderRepository.findAll().size();
        // Create the PuphaLoader
        PuphaLoaderDTO puphaLoaderDTO = puphaLoaderMapper.toDto(puphaLoader);
        restPuphaLoaderMockMvc.perform(post("/api/pupha-loaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(puphaLoaderDTO)))
            .andExpect(status().isCreated());

        // Validate the PuphaLoader in the database
        List<PuphaLoader> puphaLoaderList = puphaLoaderRepository.findAll();
        assertThat(puphaLoaderList).hasSize(databaseSizeBeforeCreate + 1);
        PuphaLoader testPuphaLoader = puphaLoaderList.get(puphaLoaderList.size() - 1);
        assertThat(testPuphaLoader.getEvent()).isEqualTo(DEFAULT_EVENT);
        assertThat(testPuphaLoader.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testPuphaLoader.getTime()).isEqualTo(DEFAULT_TIME);
    }

    @Test
    @Transactional
    public void createPuphaLoaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = puphaLoaderRepository.findAll().size();

        // Create the PuphaLoader with an existing ID
        puphaLoader.setId(1L);
        PuphaLoaderDTO puphaLoaderDTO = puphaLoaderMapper.toDto(puphaLoader);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPuphaLoaderMockMvc.perform(post("/api/pupha-loaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(puphaLoaderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PuphaLoader in the database
        List<PuphaLoader> puphaLoaderList = puphaLoaderRepository.findAll();
        assertThat(puphaLoaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPuphaLoaders() throws Exception {
        // Initialize the database
        puphaLoaderRepository.saveAndFlush(puphaLoader);

        // Get all the puphaLoaderList
        restPuphaLoaderMockMvc.perform(get("/api/pupha-loaders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(puphaLoader.getId().intValue())))
            .andExpect(jsonPath("$.[*].event").value(hasItem(DEFAULT_EVENT)))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getPuphaLoader() throws Exception {
        // Initialize the database
        puphaLoaderRepository.saveAndFlush(puphaLoader);

        // Get the puphaLoader
        restPuphaLoaderMockMvc.perform(get("/api/pupha-loaders/{id}", puphaLoader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(puphaLoader.getId().intValue()))
            .andExpect(jsonPath("$.event").value(DEFAULT_EVENT))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPuphaLoader() throws Exception {
        // Get the puphaLoader
        restPuphaLoaderMockMvc.perform(get("/api/pupha-loaders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePuphaLoader() throws Exception {
        // Initialize the database
        puphaLoaderRepository.saveAndFlush(puphaLoader);

        int databaseSizeBeforeUpdate = puphaLoaderRepository.findAll().size();

        // Update the puphaLoader
        PuphaLoader updatedPuphaLoader = puphaLoaderRepository.findById(puphaLoader.getId()).get();
        // Disconnect from session so that the updates on updatedPuphaLoader are not directly saved in db
        em.detach(updatedPuphaLoader);
        updatedPuphaLoader
            .event(UPDATED_EVENT)
            .log(UPDATED_LOG)
            .time(UPDATED_TIME);
        PuphaLoaderDTO puphaLoaderDTO = puphaLoaderMapper.toDto(updatedPuphaLoader);

        restPuphaLoaderMockMvc.perform(put("/api/pupha-loaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(puphaLoaderDTO)))
            .andExpect(status().isOk());

        // Validate the PuphaLoader in the database
        List<PuphaLoader> puphaLoaderList = puphaLoaderRepository.findAll();
        assertThat(puphaLoaderList).hasSize(databaseSizeBeforeUpdate);
        PuphaLoader testPuphaLoader = puphaLoaderList.get(puphaLoaderList.size() - 1);
        assertThat(testPuphaLoader.getEvent()).isEqualTo(UPDATED_EVENT);
        assertThat(testPuphaLoader.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testPuphaLoader.getTime()).isEqualTo(UPDATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingPuphaLoader() throws Exception {
        int databaseSizeBeforeUpdate = puphaLoaderRepository.findAll().size();

        // Create the PuphaLoader
        PuphaLoaderDTO puphaLoaderDTO = puphaLoaderMapper.toDto(puphaLoader);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPuphaLoaderMockMvc.perform(put("/api/pupha-loaders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(puphaLoaderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PuphaLoader in the database
        List<PuphaLoader> puphaLoaderList = puphaLoaderRepository.findAll();
        assertThat(puphaLoaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePuphaLoader() throws Exception {
        // Initialize the database
        puphaLoaderRepository.saveAndFlush(puphaLoader);

        int databaseSizeBeforeDelete = puphaLoaderRepository.findAll().size();

        // Delete the puphaLoader
        restPuphaLoaderMockMvc.perform(delete("/api/pupha-loaders/{id}", puphaLoader.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PuphaLoader> puphaLoaderList = puphaLoaderRepository.findAll();
        assertThat(puphaLoaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
