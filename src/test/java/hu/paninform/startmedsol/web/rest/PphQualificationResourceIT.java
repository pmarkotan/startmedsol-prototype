package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphQualification;
import hu.paninform.startmedsol.repository.PphQualificationRepository;

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
 * Integration tests for the {@link PphQualificationResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphQualificationResourceIT {

    private static final Integer DEFAULT_EXTERNAL_ID = 1;
    private static final Integer UPDATED_EXTERNAL_ID = 2;

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NEW_CODE = 1;
    private static final Integer UPDATED_NEW_CODE = 2;

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphQualificationRepository pphQualificationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphQualificationMockMvc;

    private PphQualification pphQualification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphQualification createEntity(EntityManager em) {
        PphQualification pphQualification = new PphQualification()
            .externalId(DEFAULT_EXTERNAL_ID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .newCode(DEFAULT_NEW_CODE)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphQualification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphQualification createUpdatedEntity(EntityManager em) {
        PphQualification pphQualification = new PphQualification()
            .externalId(UPDATED_EXTERNAL_ID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .newCode(UPDATED_NEW_CODE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphQualification;
    }

    @BeforeEach
    public void initTest() {
        pphQualification = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphQualification() throws Exception {
        int databaseSizeBeforeCreate = pphQualificationRepository.findAll().size();
        // Create the PphQualification
        restPphQualificationMockMvc.perform(post("/api/pph-qualifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualification)))
            .andExpect(status().isCreated());

        // Validate the PphQualification in the database
        List<PphQualification> pphQualificationList = pphQualificationRepository.findAll();
        assertThat(pphQualificationList).hasSize(databaseSizeBeforeCreate + 1);
        PphQualification testPphQualification = pphQualificationList.get(pphQualificationList.size() - 1);
        assertThat(testPphQualification.getExternalId()).isEqualTo(DEFAULT_EXTERNAL_ID);
        assertThat(testPphQualification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPphQualification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPphQualification.getNewCode()).isEqualTo(DEFAULT_NEW_CODE);
        assertThat(testPphQualification.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphQualificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphQualificationRepository.findAll().size();

        // Create the PphQualification with an existing ID
        pphQualification.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphQualificationMockMvc.perform(post("/api/pph-qualifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualification)))
            .andExpect(status().isBadRequest());

        // Validate the PphQualification in the database
        List<PphQualification> pphQualificationList = pphQualificationRepository.findAll();
        assertThat(pphQualificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphQualificationRepository.findAll().size();
        // set the field null
        pphQualification.setActivePuphaData(null);

        // Create the PphQualification, which fails.


        restPphQualificationMockMvc.perform(post("/api/pph-qualifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualification)))
            .andExpect(status().isBadRequest());

        List<PphQualification> pphQualificationList = pphQualificationRepository.findAll();
        assertThat(pphQualificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphQualifications() throws Exception {
        // Initialize the database
        pphQualificationRepository.saveAndFlush(pphQualification);

        // Get all the pphQualificationList
        restPphQualificationMockMvc.perform(get("/api/pph-qualifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphQualification.getId().intValue())))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].newCode").value(hasItem(DEFAULT_NEW_CODE)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphQualification() throws Exception {
        // Initialize the database
        pphQualificationRepository.saveAndFlush(pphQualification);

        // Get the pphQualification
        restPphQualificationMockMvc.perform(get("/api/pph-qualifications/{id}", pphQualification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphQualification.getId().intValue()))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.newCode").value(DEFAULT_NEW_CODE))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphQualification() throws Exception {
        // Get the pphQualification
        restPphQualificationMockMvc.perform(get("/api/pph-qualifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphQualification() throws Exception {
        // Initialize the database
        pphQualificationRepository.saveAndFlush(pphQualification);

        int databaseSizeBeforeUpdate = pphQualificationRepository.findAll().size();

        // Update the pphQualification
        PphQualification updatedPphQualification = pphQualificationRepository.findById(pphQualification.getId()).get();
        // Disconnect from session so that the updates on updatedPphQualification are not directly saved in db
        em.detach(updatedPphQualification);
        updatedPphQualification
            .externalId(UPDATED_EXTERNAL_ID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .newCode(UPDATED_NEW_CODE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphQualificationMockMvc.perform(put("/api/pph-qualifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphQualification)))
            .andExpect(status().isOk());

        // Validate the PphQualification in the database
        List<PphQualification> pphQualificationList = pphQualificationRepository.findAll();
        assertThat(pphQualificationList).hasSize(databaseSizeBeforeUpdate);
        PphQualification testPphQualification = pphQualificationList.get(pphQualificationList.size() - 1);
        assertThat(testPphQualification.getExternalId()).isEqualTo(UPDATED_EXTERNAL_ID);
        assertThat(testPphQualification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPphQualification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPphQualification.getNewCode()).isEqualTo(UPDATED_NEW_CODE);
        assertThat(testPphQualification.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphQualification() throws Exception {
        int databaseSizeBeforeUpdate = pphQualificationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphQualificationMockMvc.perform(put("/api/pph-qualifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualification)))
            .andExpect(status().isBadRequest());

        // Validate the PphQualification in the database
        List<PphQualification> pphQualificationList = pphQualificationRepository.findAll();
        assertThat(pphQualificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphQualification() throws Exception {
        // Initialize the database
        pphQualificationRepository.saveAndFlush(pphQualification);

        int databaseSizeBeforeDelete = pphQualificationRepository.findAll().size();

        // Delete the pphQualification
        restPphQualificationMockMvc.perform(delete("/api/pph-qualifications/{id}", pphQualification.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphQualification> pphQualificationList = pphQualificationRepository.findAll();
        assertThat(pphQualificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
