package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.MedicalCase;
import hu.paninform.startmedsol.repository.MedicalCaseRepository;
import hu.paninform.startmedsol.service.MedicalCaseService;
import hu.paninform.startmedsol.service.dto.MedicalCaseDTO;
import hu.paninform.startmedsol.service.mapper.MedicalCaseMapper;

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

import hu.paninform.startmedsol.domain.enumeration.MedicalCaseStatus;
import hu.paninform.startmedsol.domain.enumeration.AttendanceType;
/**
 * Integration tests for the {@link MedicalCaseResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MedicalCaseResourceIT {

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final Boolean DEFAULT_CONFIDENTAL = false;
    private static final Boolean UPDATED_CONFIDENTAL = true;

    private static final String DEFAULT_AMBULENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_AMBULENT_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_ADMISSION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADMISSION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CLOSE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final MedicalCaseStatus DEFAULT_STATUS = MedicalCaseStatus.ACTIVE;
    private static final MedicalCaseStatus UPDATED_STATUS = MedicalCaseStatus.CLOSED;

    private static final AttendanceType DEFAULT_ATTENDANCE_TYPE = AttendanceType.TYPE_1_FIRST_SPECIALIST_CARE;
    private static final AttendanceType UPDATED_ATTENDANCE_TYPE = AttendanceType.TYPE_2_RECALL;

    @Autowired
    private MedicalCaseRepository medicalCaseRepository;

    @Autowired
    private MedicalCaseMapper medicalCaseMapper;

    @Autowired
    private MedicalCaseService medicalCaseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalCaseMockMvc;

    private MedicalCase medicalCase;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCase createEntity(EntityManager em) {
        MedicalCase medicalCase = new MedicalCase()
            .deleted(DEFAULT_DELETED)
            .confidental(DEFAULT_CONFIDENTAL)
            .ambulentNumber(DEFAULT_AMBULENT_NUMBER)
            .admissionDate(DEFAULT_ADMISSION_DATE)
            .closeDate(DEFAULT_CLOSE_DATE)
            .status(DEFAULT_STATUS)
            .attendanceType(DEFAULT_ATTENDANCE_TYPE);
        return medicalCase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalCase createUpdatedEntity(EntityManager em) {
        MedicalCase medicalCase = new MedicalCase()
            .deleted(UPDATED_DELETED)
            .confidental(UPDATED_CONFIDENTAL)
            .ambulentNumber(UPDATED_AMBULENT_NUMBER)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .closeDate(UPDATED_CLOSE_DATE)
            .status(UPDATED_STATUS)
            .attendanceType(UPDATED_ATTENDANCE_TYPE);
        return medicalCase;
    }

    @BeforeEach
    public void initTest() {
        medicalCase = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalCase() throws Exception {
        int databaseSizeBeforeCreate = medicalCaseRepository.findAll().size();
        // Create the MedicalCase
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);
        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalCase testMedicalCase = medicalCaseList.get(medicalCaseList.size() - 1);
        assertThat(testMedicalCase.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testMedicalCase.isConfidental()).isEqualTo(DEFAULT_CONFIDENTAL);
        assertThat(testMedicalCase.getAmbulentNumber()).isEqualTo(DEFAULT_AMBULENT_NUMBER);
        assertThat(testMedicalCase.getAdmissionDate()).isEqualTo(DEFAULT_ADMISSION_DATE);
        assertThat(testMedicalCase.getCloseDate()).isEqualTo(DEFAULT_CLOSE_DATE);
        assertThat(testMedicalCase.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMedicalCase.getAttendanceType()).isEqualTo(DEFAULT_ATTENDANCE_TYPE);
    }

    @Test
    @Transactional
    public void createMedicalCaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalCaseRepository.findAll().size();

        // Create the MedicalCase with an existing ID
        medicalCase.setId(1L);
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalCaseRepository.findAll().size();
        // set the field null
        medicalCase.setDeleted(null);

        // Create the MedicalCase, which fails.
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);


        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConfidentalIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalCaseRepository.findAll().size();
        // set the field null
        medicalCase.setConfidental(null);

        // Create the MedicalCase, which fails.
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);


        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmbulentNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalCaseRepository.findAll().size();
        // set the field null
        medicalCase.setAmbulentNumber(null);

        // Create the MedicalCase, which fails.
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);


        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdmissionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalCaseRepository.findAll().size();
        // set the field null
        medicalCase.setAdmissionDate(null);

        // Create the MedicalCase, which fails.
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);


        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalCaseRepository.findAll().size();
        // set the field null
        medicalCase.setStatus(null);

        // Create the MedicalCase, which fails.
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);


        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAttendanceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = medicalCaseRepository.findAll().size();
        // set the field null
        medicalCase.setAttendanceType(null);

        // Create the MedicalCase, which fails.
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);


        restMedicalCaseMockMvc.perform(post("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMedicalCases() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        // Get all the medicalCaseList
        restMedicalCaseMockMvc.perform(get("/api/medical-cases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].confidental").value(hasItem(DEFAULT_CONFIDENTAL.booleanValue())))
            .andExpect(jsonPath("$.[*].ambulentNumber").value(hasItem(DEFAULT_AMBULENT_NUMBER)))
            .andExpect(jsonPath("$.[*].admissionDate").value(hasItem(DEFAULT_ADMISSION_DATE.toString())))
            .andExpect(jsonPath("$.[*].closeDate").value(hasItem(DEFAULT_CLOSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].attendanceType").value(hasItem(DEFAULT_ATTENDANCE_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getMedicalCase() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        // Get the medicalCase
        restMedicalCaseMockMvc.perform(get("/api/medical-cases/{id}", medicalCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalCase.getId().intValue()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.confidental").value(DEFAULT_CONFIDENTAL.booleanValue()))
            .andExpect(jsonPath("$.ambulentNumber").value(DEFAULT_AMBULENT_NUMBER))
            .andExpect(jsonPath("$.admissionDate").value(DEFAULT_ADMISSION_DATE.toString()))
            .andExpect(jsonPath("$.closeDate").value(DEFAULT_CLOSE_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.attendanceType").value(DEFAULT_ATTENDANCE_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalCase() throws Exception {
        // Get the medicalCase
        restMedicalCaseMockMvc.perform(get("/api/medical-cases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalCase() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        int databaseSizeBeforeUpdate = medicalCaseRepository.findAll().size();

        // Update the medicalCase
        MedicalCase updatedMedicalCase = medicalCaseRepository.findById(medicalCase.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalCase are not directly saved in db
        em.detach(updatedMedicalCase);
        updatedMedicalCase
            .deleted(UPDATED_DELETED)
            .confidental(UPDATED_CONFIDENTAL)
            .ambulentNumber(UPDATED_AMBULENT_NUMBER)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .closeDate(UPDATED_CLOSE_DATE)
            .status(UPDATED_STATUS)
            .attendanceType(UPDATED_ATTENDANCE_TYPE);
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(updatedMedicalCase);

        restMedicalCaseMockMvc.perform(put("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeUpdate);
        MedicalCase testMedicalCase = medicalCaseList.get(medicalCaseList.size() - 1);
        assertThat(testMedicalCase.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testMedicalCase.isConfidental()).isEqualTo(UPDATED_CONFIDENTAL);
        assertThat(testMedicalCase.getAmbulentNumber()).isEqualTo(UPDATED_AMBULENT_NUMBER);
        assertThat(testMedicalCase.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testMedicalCase.getCloseDate()).isEqualTo(UPDATED_CLOSE_DATE);
        assertThat(testMedicalCase.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMedicalCase.getAttendanceType()).isEqualTo(UPDATED_ATTENDANCE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalCase() throws Exception {
        int databaseSizeBeforeUpdate = medicalCaseRepository.findAll().size();

        // Create the MedicalCase
        MedicalCaseDTO medicalCaseDTO = medicalCaseMapper.toDto(medicalCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalCaseMockMvc.perform(put("/api/medical-cases")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalCase in the database
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalCase() throws Exception {
        // Initialize the database
        medicalCaseRepository.saveAndFlush(medicalCase);

        int databaseSizeBeforeDelete = medicalCaseRepository.findAll().size();

        // Delete the medicalCase
        restMedicalCaseMockMvc.perform(delete("/api/medical-cases/{id}", medicalCase.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalCase> medicalCaseList = medicalCaseRepository.findAll();
        assertThat(medicalCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
