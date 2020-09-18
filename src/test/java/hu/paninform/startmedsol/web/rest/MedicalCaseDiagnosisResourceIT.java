package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.MedicalCaseDiagnosis;
import hu.paninform.startmedsol.domain.CsDiagnosis;
import hu.paninform.startmedsol.domain.MedicalCase;
import hu.paninform.startmedsol.repository.MedicalCaseDiagnosisRepository;
import hu.paninform.startmedsol.service.MedicalCaseDiagnosisService;
import hu.paninform.startmedsol.service.dto.MedicalCaseDiagnosisDTO;
import hu.paninform.startmedsol.service.mapper.MedicalCaseDiagnosisMapper;

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
 * Integration tests for the {@link MedicalCaseDiagnosisResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalCaseDiagnosisResourceIT {

    @Autowired
    private MedicalCaseDiagnosisRepository medicalCaseDiagnosisRepository;

    @Autowired
    private MedicalCaseDiagnosisMapper medicalCaseDiagnosisMapper;

    @Autowired
    private MedicalCaseDiagnosisService medicalCaseDiagnosisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalCaseDiagnosisMockMvc;

    private MedicalCaseDiagnosis medicalCaseDiagnosis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCaseDiagnosis createEntity(EntityManager em) {
        MedicalCaseDiagnosis medicalCaseDiagnosis = new MedicalCaseDiagnosis();
        // Add required entity
        CsDiagnosis csDiagnosis;
        if (TestUtil.findAll(em, CsDiagnosis.class).isEmpty()) {
            csDiagnosis = CsDiagnosisResourceIT.createEntity(em);
            em.persist(csDiagnosis);
            em.flush();
        } else {
            csDiagnosis = TestUtil.findAll(em, CsDiagnosis.class).get(0);
        }
        medicalCaseDiagnosis.setDiagnosis(csDiagnosis);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        medicalCaseDiagnosis.setMedicalCase(medicalCase);
        return medicalCaseDiagnosis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCaseDiagnosis createUpdatedEntity(EntityManager em) {
        MedicalCaseDiagnosis medicalCaseDiagnosis = new MedicalCaseDiagnosis();
        // Add required entity
        CsDiagnosis csDiagnosis;
        if (TestUtil.findAll(em, CsDiagnosis.class).isEmpty()) {
            csDiagnosis = CsDiagnosisResourceIT.createUpdatedEntity(em);
            em.persist(csDiagnosis);
            em.flush();
        } else {
            csDiagnosis = TestUtil.findAll(em, CsDiagnosis.class).get(0);
        }
        medicalCaseDiagnosis.setDiagnosis(csDiagnosis);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createUpdatedEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        medicalCaseDiagnosis.setMedicalCase(medicalCase);
        return medicalCaseDiagnosis;
    }

    @BeforeEach
    public void initTest() {
        medicalCaseDiagnosis = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalCaseDiagnosis() throws Exception {
        int databaseSizeBeforeCreate = medicalCaseDiagnosisRepository.findAll().size();
        // Create the MedicalCaseDiagnosis
        MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO = medicalCaseDiagnosisMapper.toDto(medicalCaseDiagnosis);
        restMedicalCaseDiagnosisMockMvc.perform(post("/api/medical-case-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDiagnosisDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalCaseDiagnosis in the database
        List<MedicalCaseDiagnosis> medicalCaseDiagnosisList = medicalCaseDiagnosisRepository.findAll();
        assertThat(medicalCaseDiagnosisList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalCaseDiagnosis testMedicalCaseDiagnosis = medicalCaseDiagnosisList.get(medicalCaseDiagnosisList.size() - 1);
    }

    @Test
    @Transactional
    public void createMedicalCaseDiagnosisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalCaseDiagnosisRepository.findAll().size();

        // Create the MedicalCaseDiagnosis with an existing ID
        medicalCaseDiagnosis.setId(1L);
        MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO = medicalCaseDiagnosisMapper.toDto(medicalCaseDiagnosis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalCaseDiagnosisMockMvc.perform(post("/api/medical-case-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCaseDiagnosis in the database
        List<MedicalCaseDiagnosis> medicalCaseDiagnosisList = medicalCaseDiagnosisRepository.findAll();
        assertThat(medicalCaseDiagnosisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalCaseDiagnoses() throws Exception {
        // Initialize the database
        medicalCaseDiagnosisRepository.saveAndFlush(medicalCaseDiagnosis);

        // Get all the medicalCaseDiagnosisList
        restMedicalCaseDiagnosisMockMvc.perform(get("/api/medical-case-diagnoses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalCaseDiagnosis.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getMedicalCaseDiagnosis() throws Exception {
        // Initialize the database
        medicalCaseDiagnosisRepository.saveAndFlush(medicalCaseDiagnosis);

        // Get the medicalCaseDiagnosis
        restMedicalCaseDiagnosisMockMvc.perform(get("/api/medical-case-diagnoses/{id}", medicalCaseDiagnosis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalCaseDiagnosis.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalCaseDiagnosis() throws Exception {
        // Get the medicalCaseDiagnosis
        restMedicalCaseDiagnosisMockMvc.perform(get("/api/medical-case-diagnoses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalCaseDiagnosis() throws Exception {
        // Initialize the database
        medicalCaseDiagnosisRepository.saveAndFlush(medicalCaseDiagnosis);

        int databaseSizeBeforeUpdate = medicalCaseDiagnosisRepository.findAll().size();

        // Update the medicalCaseDiagnosis
        MedicalCaseDiagnosis updatedMedicalCaseDiagnosis = medicalCaseDiagnosisRepository.findById(medicalCaseDiagnosis.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalCaseDiagnosis are not directly saved in db
        em.detach(updatedMedicalCaseDiagnosis);
        MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO = medicalCaseDiagnosisMapper.toDto(updatedMedicalCaseDiagnosis);

        restMedicalCaseDiagnosisMockMvc.perform(put("/api/medical-case-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDiagnosisDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalCaseDiagnosis in the database
        List<MedicalCaseDiagnosis> medicalCaseDiagnosisList = medicalCaseDiagnosisRepository.findAll();
        assertThat(medicalCaseDiagnosisList).hasSize(databaseSizeBeforeUpdate);
        MedicalCaseDiagnosis testMedicalCaseDiagnosis = medicalCaseDiagnosisList.get(medicalCaseDiagnosisList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalCaseDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = medicalCaseDiagnosisRepository.findAll().size();

        // Create the MedicalCaseDiagnosis
        MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO = medicalCaseDiagnosisMapper.toDto(medicalCaseDiagnosis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalCaseDiagnosisMockMvc.perform(put("/api/medical-case-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCaseDiagnosis in the database
        List<MedicalCaseDiagnosis> medicalCaseDiagnosisList = medicalCaseDiagnosisRepository.findAll();
        assertThat(medicalCaseDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalCaseDiagnosis() throws Exception {
        // Initialize the database
        medicalCaseDiagnosisRepository.saveAndFlush(medicalCaseDiagnosis);

        int databaseSizeBeforeDelete = medicalCaseDiagnosisRepository.findAll().size();

        // Delete the medicalCaseDiagnosis
        restMedicalCaseDiagnosisMockMvc.perform(delete("/api/medical-case-diagnoses/{id}", medicalCaseDiagnosis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalCaseDiagnosis> medicalCaseDiagnosisList = medicalCaseDiagnosisRepository.findAll();
        assertThat(medicalCaseDiagnosisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
