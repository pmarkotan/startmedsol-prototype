package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PrescriptionEesztId;
import hu.paninform.startmedsol.domain.Prescription;
import hu.paninform.startmedsol.repository.PrescriptionEesztIdRepository;

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
 * Integration tests for the {@link PrescriptionEesztIdResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrescriptionEesztIdResourceIT {

    private static final String DEFAULT_EESZT_ID = "AAAAAAAAAA";
    private static final String UPDATED_EESZT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_EESZT_VERSION = 1;
    private static final Integer UPDATED_EESZT_VERSION = 2;

    @Autowired
    private PrescriptionEesztIdRepository prescriptionEesztIdRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrescriptionEesztIdMockMvc;

    private PrescriptionEesztId prescriptionEesztId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrescriptionEesztId createEntity(EntityManager em) {
        PrescriptionEesztId prescriptionEesztId = new PrescriptionEesztId()
            .eesztId(DEFAULT_EESZT_ID)
            .eesztVersion(DEFAULT_EESZT_VERSION);
        // Add required entity
        Prescription prescription;
        if (TestUtil.findAll(em, Prescription.class).isEmpty()) {
            prescription = PrescriptionResourceIT.createEntity(em);
            em.persist(prescription);
            em.flush();
        } else {
            prescription = TestUtil.findAll(em, Prescription.class).get(0);
        }
        prescriptionEesztId.setPrescription(prescription);
        return prescriptionEesztId;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrescriptionEesztId createUpdatedEntity(EntityManager em) {
        PrescriptionEesztId prescriptionEesztId = new PrescriptionEesztId()
            .eesztId(UPDATED_EESZT_ID)
            .eesztVersion(UPDATED_EESZT_VERSION);
        // Add required entity
        Prescription prescription;
        if (TestUtil.findAll(em, Prescription.class).isEmpty()) {
            prescription = PrescriptionResourceIT.createUpdatedEntity(em);
            em.persist(prescription);
            em.flush();
        } else {
            prescription = TestUtil.findAll(em, Prescription.class).get(0);
        }
        prescriptionEesztId.setPrescription(prescription);
        return prescriptionEesztId;
    }

    @BeforeEach
    public void initTest() {
        prescriptionEesztId = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrescriptionEesztId() throws Exception {
        int databaseSizeBeforeCreate = prescriptionEesztIdRepository.findAll().size();
        // Create the PrescriptionEesztId
        restPrescriptionEesztIdMockMvc.perform(post("/api/prescription-eeszt-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionEesztId)))
            .andExpect(status().isCreated());

        // Validate the PrescriptionEesztId in the database
        List<PrescriptionEesztId> prescriptionEesztIdList = prescriptionEesztIdRepository.findAll();
        assertThat(prescriptionEesztIdList).hasSize(databaseSizeBeforeCreate + 1);
        PrescriptionEesztId testPrescriptionEesztId = prescriptionEesztIdList.get(prescriptionEesztIdList.size() - 1);
        assertThat(testPrescriptionEesztId.getEesztId()).isEqualTo(DEFAULT_EESZT_ID);
        assertThat(testPrescriptionEesztId.getEesztVersion()).isEqualTo(DEFAULT_EESZT_VERSION);
    }

    @Test
    @Transactional
    public void createPrescriptionEesztIdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prescriptionEesztIdRepository.findAll().size();

        // Create the PrescriptionEesztId with an existing ID
        prescriptionEesztId.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrescriptionEesztIdMockMvc.perform(post("/api/prescription-eeszt-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionEesztId)))
            .andExpect(status().isBadRequest());

        // Validate the PrescriptionEesztId in the database
        List<PrescriptionEesztId> prescriptionEesztIdList = prescriptionEesztIdRepository.findAll();
        assertThat(prescriptionEesztIdList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEesztIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = prescriptionEesztIdRepository.findAll().size();
        // set the field null
        prescriptionEesztId.setEesztId(null);

        // Create the PrescriptionEesztId, which fails.


        restPrescriptionEesztIdMockMvc.perform(post("/api/prescription-eeszt-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionEesztId)))
            .andExpect(status().isBadRequest());

        List<PrescriptionEesztId> prescriptionEesztIdList = prescriptionEesztIdRepository.findAll();
        assertThat(prescriptionEesztIdList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrescriptionEesztIds() throws Exception {
        // Initialize the database
        prescriptionEesztIdRepository.saveAndFlush(prescriptionEesztId);

        // Get all the prescriptionEesztIdList
        restPrescriptionEesztIdMockMvc.perform(get("/api/prescription-eeszt-ids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prescriptionEesztId.getId().intValue())))
            .andExpect(jsonPath("$.[*].eesztId").value(hasItem(DEFAULT_EESZT_ID)))
            .andExpect(jsonPath("$.[*].eesztVersion").value(hasItem(DEFAULT_EESZT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getPrescriptionEesztId() throws Exception {
        // Initialize the database
        prescriptionEesztIdRepository.saveAndFlush(prescriptionEesztId);

        // Get the prescriptionEesztId
        restPrescriptionEesztIdMockMvc.perform(get("/api/prescription-eeszt-ids/{id}", prescriptionEesztId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prescriptionEesztId.getId().intValue()))
            .andExpect(jsonPath("$.eesztId").value(DEFAULT_EESZT_ID))
            .andExpect(jsonPath("$.eesztVersion").value(DEFAULT_EESZT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingPrescriptionEesztId() throws Exception {
        // Get the prescriptionEesztId
        restPrescriptionEesztIdMockMvc.perform(get("/api/prescription-eeszt-ids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrescriptionEesztId() throws Exception {
        // Initialize the database
        prescriptionEesztIdRepository.saveAndFlush(prescriptionEesztId);

        int databaseSizeBeforeUpdate = prescriptionEesztIdRepository.findAll().size();

        // Update the prescriptionEesztId
        PrescriptionEesztId updatedPrescriptionEesztId = prescriptionEesztIdRepository.findById(prescriptionEesztId.getId()).get();
        // Disconnect from session so that the updates on updatedPrescriptionEesztId are not directly saved in db
        em.detach(updatedPrescriptionEesztId);
        updatedPrescriptionEesztId
            .eesztId(UPDATED_EESZT_ID)
            .eesztVersion(UPDATED_EESZT_VERSION);

        restPrescriptionEesztIdMockMvc.perform(put("/api/prescription-eeszt-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPrescriptionEesztId)))
            .andExpect(status().isOk());

        // Validate the PrescriptionEesztId in the database
        List<PrescriptionEesztId> prescriptionEesztIdList = prescriptionEesztIdRepository.findAll();
        assertThat(prescriptionEesztIdList).hasSize(databaseSizeBeforeUpdate);
        PrescriptionEesztId testPrescriptionEesztId = prescriptionEesztIdList.get(prescriptionEesztIdList.size() - 1);
        assertThat(testPrescriptionEesztId.getEesztId()).isEqualTo(UPDATED_EESZT_ID);
        assertThat(testPrescriptionEesztId.getEesztVersion()).isEqualTo(UPDATED_EESZT_VERSION);
    }

    @Test
    @Transactional
    public void updateNonExistingPrescriptionEesztId() throws Exception {
        int databaseSizeBeforeUpdate = prescriptionEesztIdRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrescriptionEesztIdMockMvc.perform(put("/api/prescription-eeszt-ids")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionEesztId)))
            .andExpect(status().isBadRequest());

        // Validate the PrescriptionEesztId in the database
        List<PrescriptionEesztId> prescriptionEesztIdList = prescriptionEesztIdRepository.findAll();
        assertThat(prescriptionEesztIdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrescriptionEesztId() throws Exception {
        // Initialize the database
        prescriptionEesztIdRepository.saveAndFlush(prescriptionEesztId);

        int databaseSizeBeforeDelete = prescriptionEesztIdRepository.findAll().size();

        // Delete the prescriptionEesztId
        restPrescriptionEesztIdMockMvc.perform(delete("/api/prescription-eeszt-ids/{id}", prescriptionEesztId.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrescriptionEesztId> prescriptionEesztIdList = prescriptionEesztIdRepository.findAll();
        assertThat(prescriptionEesztIdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
