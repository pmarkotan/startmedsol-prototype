package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.Patient;
import hu.paninform.startmedsol.repository.PatientRepository;
import hu.paninform.startmedsol.service.PatientService;
import hu.paninform.startmedsol.service.dto.PatientDTO;
import hu.paninform.startmedsol.service.mapper.PatientMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PatientResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PatientResourceIT {

    private static final String DEFAULT_PATIENT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_PATIENT_IDENTIFIER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PRIVACY_STATEMENT = false;
    private static final Boolean UPDATED_PRIVACY_STATEMENT = true;

    private static final Boolean DEFAULT_ANONYMISED = false;
    private static final Boolean UPDATED_ANONYMISED = true;

    private static final Boolean DEFAULT_CLASSIFIED = false;
    private static final Boolean UPDATED_CLASSIFIED = true;

    @Autowired
    private PatientRepository patientRepository;

    @Mock
    private PatientRepository patientRepositoryMock;

    @Autowired
    private PatientMapper patientMapper;

    @Mock
    private PatientService patientServiceMock;

    @Autowired
    private PatientService patientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPatientMockMvc;

    private Patient patient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createEntity(EntityManager em) {
        Patient patient = new Patient()
            .patientIdentifier(DEFAULT_PATIENT_IDENTIFIER)
            .privacyStatement(DEFAULT_PRIVACY_STATEMENT)
            .anonymised(DEFAULT_ANONYMISED)
            .classified(DEFAULT_CLASSIFIED);
        return patient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createUpdatedEntity(EntityManager em) {
        Patient patient = new Patient()
            .patientIdentifier(UPDATED_PATIENT_IDENTIFIER)
            .privacyStatement(UPDATED_PRIVACY_STATEMENT)
            .anonymised(UPDATED_ANONYMISED)
            .classified(UPDATED_CLASSIFIED);
        return patient;
    }

    @BeforeEach
    public void initTest() {
        patient = createEntity(em);
    }

    @Test
    @Transactional
    public void createPatient() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();
        // Create the Patient
        PatientDTO patientDTO = patientMapper.toDto(patient);
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isCreated());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate + 1);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getPatientIdentifier()).isEqualTo(DEFAULT_PATIENT_IDENTIFIER);
        assertThat(testPatient.isPrivacyStatement()).isEqualTo(DEFAULT_PRIVACY_STATEMENT);
        assertThat(testPatient.isAnonymised()).isEqualTo(DEFAULT_ANONYMISED);
        assertThat(testPatient.isClassified()).isEqualTo(DEFAULT_CLASSIFIED);
    }

    @Test
    @Transactional
    public void createPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().size();

        // Create the Patient with an existing ID
        patient.setId(1L);
        PatientDTO patientDTO = patientMapper.toDto(patient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPatientMockMvc.perform(post("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPatients() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get all the patientList
        restPatientMockMvc.perform(get("/api/patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(patient.getId().intValue())))
            .andExpect(jsonPath("$.[*].patientIdentifier").value(hasItem(DEFAULT_PATIENT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].privacyStatement").value(hasItem(DEFAULT_PRIVACY_STATEMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].anonymised").value(hasItem(DEFAULT_ANONYMISED.booleanValue())))
            .andExpect(jsonPath("$.[*].classified").value(hasItem(DEFAULT_CLASSIFIED.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllPatientsWithEagerRelationshipsIsEnabled() throws Exception {
        when(patientServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPatientMockMvc.perform(get("/api/patients?eagerload=true"))
            .andExpect(status().isOk());

        verify(patientServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllPatientsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(patientServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPatientMockMvc.perform(get("/api/patients?eagerload=true"))
            .andExpect(status().isOk());

        verify(patientServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getPatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", patient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(patient.getId().intValue()))
            .andExpect(jsonPath("$.patientIdentifier").value(DEFAULT_PATIENT_IDENTIFIER))
            .andExpect(jsonPath("$.privacyStatement").value(DEFAULT_PRIVACY_STATEMENT.booleanValue()))
            .andExpect(jsonPath("$.anonymised").value(DEFAULT_ANONYMISED.booleanValue()))
            .andExpect(jsonPath("$.classified").value(DEFAULT_CLASSIFIED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPatient() throws Exception {
        // Get the patient
        restPatientMockMvc.perform(get("/api/patients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Update the patient
        Patient updatedPatient = patientRepository.findById(patient.getId()).get();
        // Disconnect from session so that the updates on updatedPatient are not directly saved in db
        em.detach(updatedPatient);
        updatedPatient
            .patientIdentifier(UPDATED_PATIENT_IDENTIFIER)
            .privacyStatement(UPDATED_PRIVACY_STATEMENT)
            .anonymised(UPDATED_ANONYMISED)
            .classified(UPDATED_CLASSIFIED);
        PatientDTO patientDTO = patientMapper.toDto(updatedPatient);

        restPatientMockMvc.perform(put("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isOk());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getPatientIdentifier()).isEqualTo(UPDATED_PATIENT_IDENTIFIER);
        assertThat(testPatient.isPrivacyStatement()).isEqualTo(UPDATED_PRIVACY_STATEMENT);
        assertThat(testPatient.isAnonymised()).isEqualTo(UPDATED_ANONYMISED);
        assertThat(testPatient.isClassified()).isEqualTo(UPDATED_CLASSIFIED);
    }

    @Test
    @Transactional
    public void updateNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().size();

        // Create the Patient
        PatientDTO patientDTO = patientMapper.toDto(patient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPatientMockMvc.perform(put("/api/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(patientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePatient() throws Exception {
        // Initialize the database
        patientRepository.saveAndFlush(patient);

        int databaseSizeBeforeDelete = patientRepository.findAll().size();

        // Delete the patient
        restPatientMockMvc.perform(delete("/api/patients/{id}", patient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Patient> patientList = patientRepository.findAll();
        assertThat(patientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
