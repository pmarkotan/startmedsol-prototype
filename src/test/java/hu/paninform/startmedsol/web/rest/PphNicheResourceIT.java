package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphNiche;
import hu.paninform.startmedsol.repository.PphNicheRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PphNicheResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphNicheResourceIT {

    private static final Integer DEFAULT_EXTERNAL_ID = 1;
    private static final Integer UPDATED_EXTERNAL_ID = 2;

    private static final Integer DEFAULT_EQUIVALENCE_GROUP_ID = 1;
    private static final Integer UPDATED_EQUIVALENCE_GROUP_ID = 2;

    private static final String DEFAULT_EQUIVALENT_MEDICINE = "AAAAAAAAAA";
    private static final String UPDATED_EQUIVALENT_MEDICINE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphNicheRepository pphNicheRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphNicheMockMvc;

    private PphNiche pphNiche;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphNiche createEntity(EntityManager em) {
        PphNiche pphNiche = new PphNiche()
            .externalId(DEFAULT_EXTERNAL_ID)
            .equivalenceGroupId(DEFAULT_EQUIVALENCE_GROUP_ID)
            .equivalentMedicine(DEFAULT_EQUIVALENT_MEDICINE)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphNiche;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphNiche createUpdatedEntity(EntityManager em) {
        PphNiche pphNiche = new PphNiche()
            .externalId(UPDATED_EXTERNAL_ID)
            .equivalenceGroupId(UPDATED_EQUIVALENCE_GROUP_ID)
            .equivalentMedicine(UPDATED_EQUIVALENT_MEDICINE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphNiche;
    }

    @BeforeEach
    public void initTest() {
        pphNiche = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphNiche() throws Exception {
        int databaseSizeBeforeCreate = pphNicheRepository.findAll().size();
        // Create the PphNiche
        restPphNicheMockMvc.perform(post("/api/pph-niches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphNiche)))
            .andExpect(status().isCreated());

        // Validate the PphNiche in the database
        List<PphNiche> pphNicheList = pphNicheRepository.findAll();
        assertThat(pphNicheList).hasSize(databaseSizeBeforeCreate + 1);
        PphNiche testPphNiche = pphNicheList.get(pphNicheList.size() - 1);
        assertThat(testPphNiche.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphNiche.getEquivalenceGroupId()).isEqualTo(DEFAULT_EQUIVALENCE_GROUP_ID);
        assertThat(testPphNiche.getEquivalentMedicine()).isEqualTo(DEFAULT_EQUIVALENT_MEDICINE);
        assertThat(testPphNiche.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphNicheWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphNicheRepository.findAll().size();

        // Create the PphNiche with an existing ID
        pphNiche.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphNicheMockMvc.perform(post("/api/pph-niches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphNiche)))
            .andExpect(status().isBadRequest());

        // Validate the PphNiche in the database
        List<PphNiche> pphNicheList = pphNicheRepository.findAll();
        assertThat(pphNicheList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphNicheRepository.findAll().size();
        // set the field null
        pphNiche.setActivePuphaData(null);

        // Create the PphNiche, which fails.


        restPphNicheMockMvc.perform(post("/api/pph-niches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphNiche)))
            .andExpect(status().isBadRequest());

        List<PphNiche> pphNicheList = pphNicheRepository.findAll();
        assertThat(pphNicheList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphNiches() throws Exception {
        // Initialize the database
        pphNicheRepository.saveAndFlush(pphNiche);

        // Get all the pphNicheList
        restPphNicheMockMvc.perform(get("/api/pph-niches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphNiche.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].equivalenceGroupId").value(hasItem(DEFAULT_EQUIVALENCE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].equivalentMedicine").value(hasItem(DEFAULT_EQUIVALENT_MEDICINE)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphNiche() throws Exception {
        // Initialize the database
        pphNicheRepository.saveAndFlush(pphNiche);

        // Get the pphNiche
        restPphNicheMockMvc.perform(get("/api/pph-niches/{id}", pphNiche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphNiche.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.equivalenceGroupId").value(DEFAULT_EQUIVALENCE_GROUP_ID))
            .andExpect(jsonPath("$.equivalentMedicine").value(DEFAULT_EQUIVALENT_MEDICINE))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphNiche() throws Exception {
        // Get the pphNiche
        restPphNicheMockMvc.perform(get("/api/pph-niches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphNiche() throws Exception {
        // Initialize the database
        pphNicheRepository.saveAndFlush(pphNiche);

        int databaseSizeBeforeUpdate = pphNicheRepository.findAll().size();

        // Update the pphNiche
        PphNiche updatedPphNiche = pphNicheRepository.findById(pphNiche.getId()).get();
        // Disconnect from session so that the updates on updatedPphNiche are not directly saved in db
        em.detach(updatedPphNiche);
        updatedPphNiche
            .externalId(UPDATED_EXTERNAL_ID)
            .equivalenceGroupId(UPDATED_EQUIVALENCE_GROUP_ID)
            .equivalentMedicine(UPDATED_EQUIVALENT_MEDICINE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphNicheMockMvc.perform(put("/api/pph-niches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphNiche)))
            .andExpect(status().isOk());

        // Validate the PphNiche in the database
        List<PphNiche> pphNicheList = pphNicheRepository.findAll();
        assertThat(pphNicheList).hasSize(databaseSizeBeforeUpdate);
        PphNiche testPphNiche = pphNicheList.get(pphNicheList.size() - 1);
        assertThat(testPphNiche.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphNiche.getEquivalenceGroupId()).isEqualTo(UPDATED_EQUIVALENCE_GROUP_ID);
        assertThat(testPphNiche.getEquivalentMedicine()).isEqualTo(UPDATED_EQUIVALENT_MEDICINE);
        assertThat(testPphNiche.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphNiche() throws Exception {
        int databaseSizeBeforeUpdate = pphNicheRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphNicheMockMvc.perform(put("/api/pph-niches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphNiche)))
            .andExpect(status().isBadRequest());

        // Validate the PphNiche in the database
        List<PphNiche> pphNicheList = pphNicheRepository.findAll();
        assertThat(pphNicheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphNiche() throws Exception {
        // Initialize the database
        pphNicheRepository.saveAndFlush(pphNiche);

        int databaseSizeBeforeDelete = pphNicheRepository.findAll().size();

        // Delete the pphNiche
        restPphNicheMockMvc.perform(delete("/api/pph-niches/{id}", pphNiche.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphNiche> pphNicheList = pphNicheRepository.findAll();
        assertThat(pphNicheList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
