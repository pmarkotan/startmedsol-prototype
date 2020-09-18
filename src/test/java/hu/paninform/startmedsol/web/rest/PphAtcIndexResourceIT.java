package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphAtcIndex;
import hu.paninform.startmedsol.repository.PphAtcIndexRepository;

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
 * Integration tests for the {@link PphAtcIndexResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphAtcIndexResourceIT {

    private static final String DEFAULT_ATC_CODE = "AAAAAAAA";
    private static final String UPDATED_ATC_CODE = "BBBBBBBB";

    private static final String DEFAULT_NAME_HU = "AAAAAAAAAA";
    private static final String UPDATED_NAME_HU = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_SUBSTANCE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_SUBSTANCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphAtcIndexRepository pphAtcIndexRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphAtcIndexMockMvc;

    private PphAtcIndex pphAtcIndex;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphAtcIndex createEntity(EntityManager em) {
        PphAtcIndex pphAtcIndex = new PphAtcIndex()
            .atcCode(DEFAULT_ATC_CODE)
            .nameHu(DEFAULT_NAME_HU)
            .nameEn(DEFAULT_NAME_EN)
            .activeSubstance(DEFAULT_ACTIVE_SUBSTANCE)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphAtcIndex;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphAtcIndex createUpdatedEntity(EntityManager em) {
        PphAtcIndex pphAtcIndex = new PphAtcIndex()
            .atcCode(UPDATED_ATC_CODE)
            .nameHu(UPDATED_NAME_HU)
            .nameEn(UPDATED_NAME_EN)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphAtcIndex;
    }

    @BeforeEach
    public void initTest() {
        pphAtcIndex = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphAtcIndex() throws Exception {
        int databaseSizeBeforeCreate = pphAtcIndexRepository.findAll().size();
        // Create the PphAtcIndex
        restPphAtcIndexMockMvc.perform(post("/api/pph-atc-indices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAtcIndex)))
            .andExpect(status().isCreated());

        // Validate the PphAtcIndex in the database
        List<PphAtcIndex> pphAtcIndexList = pphAtcIndexRepository.findAll();
        assertThat(pphAtcIndexList).hasSize(databaseSizeBeforeCreate + 1);
        PphAtcIndex testPphAtcIndex = pphAtcIndexList.get(pphAtcIndexList.size() - 1);
        assertThat(testPphAtcIndex.getAtcCode()).isEqualTo(DEFAULT_ATC_CODE);
        assertThat(testPphAtcIndex.getNameHu()).isEqualTo(DEFAULT_NAME_HU);
        assertThat(testPphAtcIndex.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testPphAtcIndex.getActiveSubstance()).isEqualTo(DEFAULT_ACTIVE_SUBSTANCE);
        assertThat(testPphAtcIndex.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphAtcIndexWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphAtcIndexRepository.findAll().size();

        // Create the PphAtcIndex with an existing ID
        pphAtcIndex.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphAtcIndexMockMvc.perform(post("/api/pph-atc-indices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAtcIndex)))
            .andExpect(status().isBadRequest());

        // Validate the PphAtcIndex in the database
        List<PphAtcIndex> pphAtcIndexList = pphAtcIndexRepository.findAll();
        assertThat(pphAtcIndexList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphAtcIndexRepository.findAll().size();
        // set the field null
        pphAtcIndex.setActivePuphaData(null);

        // Create the PphAtcIndex, which fails.


        restPphAtcIndexMockMvc.perform(post("/api/pph-atc-indices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAtcIndex)))
            .andExpect(status().isBadRequest());

        List<PphAtcIndex> pphAtcIndexList = pphAtcIndexRepository.findAll();
        assertThat(pphAtcIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphAtcIndices() throws Exception {
        // Initialize the database
        pphAtcIndexRepository.saveAndFlush(pphAtcIndex);

        // Get all the pphAtcIndexList
        restPphAtcIndexMockMvc.perform(get("/api/pph-atc-indices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphAtcIndex.getId().intValue())))
            .andExpect(jsonPath("$.[*].atcCode").value(hasItem(DEFAULT_ATC_CODE)))
            .andExpect(jsonPath("$.[*].nameHu").value(hasItem(DEFAULT_NAME_HU)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].activeSubstance").value(hasItem(DEFAULT_ACTIVE_SUBSTANCE)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphAtcIndex() throws Exception {
        // Initialize the database
        pphAtcIndexRepository.saveAndFlush(pphAtcIndex);

        // Get the pphAtcIndex
        restPphAtcIndexMockMvc.perform(get("/api/pph-atc-indices/{id}", pphAtcIndex.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphAtcIndex.getId().intValue()))
            .andExpect(jsonPath("$.atcCode").value(DEFAULT_ATC_CODE))
            .andExpect(jsonPath("$.nameHu").value(DEFAULT_NAME_HU))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.activeSubstance").value(DEFAULT_ACTIVE_SUBSTANCE))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphAtcIndex() throws Exception {
        // Get the pphAtcIndex
        restPphAtcIndexMockMvc.perform(get("/api/pph-atc-indices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphAtcIndex() throws Exception {
        // Initialize the database
        pphAtcIndexRepository.saveAndFlush(pphAtcIndex);

        int databaseSizeBeforeUpdate = pphAtcIndexRepository.findAll().size();

        // Update the pphAtcIndex
        PphAtcIndex updatedPphAtcIndex = pphAtcIndexRepository.findById(pphAtcIndex.getId()).get();
        // Disconnect from session so that the updates on updatedPphAtcIndex are not directly saved in db
        em.detach(updatedPphAtcIndex);
        updatedPphAtcIndex
            .atcCode(UPDATED_ATC_CODE)
            .nameHu(UPDATED_NAME_HU)
            .nameEn(UPDATED_NAME_EN)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphAtcIndexMockMvc.perform(put("/api/pph-atc-indices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphAtcIndex)))
            .andExpect(status().isOk());

        // Validate the PphAtcIndex in the database
        List<PphAtcIndex> pphAtcIndexList = pphAtcIndexRepository.findAll();
        assertThat(pphAtcIndexList).hasSize(databaseSizeBeforeUpdate);
        PphAtcIndex testPphAtcIndex = pphAtcIndexList.get(pphAtcIndexList.size() - 1);
        assertThat(testPphAtcIndex.getAtcCode()).isEqualTo(UPDATED_ATC_CODE);
        assertThat(testPphAtcIndex.getNameHu()).isEqualTo(UPDATED_NAME_HU);
        assertThat(testPphAtcIndex.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testPphAtcIndex.getActiveSubstance()).isEqualTo(UPDATED_ACTIVE_SUBSTANCE);
        assertThat(testPphAtcIndex.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphAtcIndex() throws Exception {
        int databaseSizeBeforeUpdate = pphAtcIndexRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphAtcIndexMockMvc.perform(put("/api/pph-atc-indices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAtcIndex)))
            .andExpect(status().isBadRequest());

        // Validate the PphAtcIndex in the database
        List<PphAtcIndex> pphAtcIndexList = pphAtcIndexRepository.findAll();
        assertThat(pphAtcIndexList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphAtcIndex() throws Exception {
        // Initialize the database
        pphAtcIndexRepository.saveAndFlush(pphAtcIndex);

        int databaseSizeBeforeDelete = pphAtcIndexRepository.findAll().size();

        // Delete the pphAtcIndex
        restPphAtcIndexMockMvc.perform(delete("/api/pph-atc-indices/{id}", pphAtcIndex.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphAtcIndex> pphAtcIndexList = pphAtcIndexRepository.findAll();
        assertThat(pphAtcIndexList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
