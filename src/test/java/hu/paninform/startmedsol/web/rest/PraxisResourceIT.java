package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.Praxis;
import hu.paninform.startmedsol.repository.PraxisRepository;
import hu.paninform.startmedsol.service.PraxisService;
import hu.paninform.startmedsol.service.dto.PraxisDTO;
import hu.paninform.startmedsol.service.mapper.PraxisMapper;

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

import hu.paninform.startmedsol.domain.enumeration.PraxisStatus;
/**
 * Integration tests for the {@link PraxisResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PraxisResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT_ID = "AAAAAAAAA";
    private static final String UPDATED_DEPARTMENT_ID = "BBBBBBBBB";

    private static final String DEFAULT_PROFESSION_CODE = "AAAA";
    private static final String UPDATED_PROFESSION_CODE = "BBBB";

    private static final String DEFAULT_PROFESSION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION_NAME = "BBBBBBBBBB";

    private static final PraxisStatus DEFAULT_STATUS = PraxisStatus.ACTIVE;
    private static final PraxisStatus UPDATED_STATUS = PraxisStatus.PASSIVE;

    private static final String DEFAULT_APPOINTMENT_PHONE = "+135936";
    private static final String UPDATED_APPOINTMENT_PHONE = "+302225";

    private static final Integer DEFAULT_TREATMENT_LOGBOOK_NUMBER = 1;
    private static final Integer UPDATED_TREATMENT_LOGBOOK_NUMBER = 2;

    @Autowired
    private PraxisRepository praxisRepository;

    @Autowired
    private PraxisMapper praxisMapper;

    @Autowired
    private PraxisService praxisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPraxisMockMvc;

    private Praxis praxis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Praxis createEntity(EntityManager em) {
        Praxis praxis = new Praxis()
            .name(DEFAULT_NAME)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .professionCode(DEFAULT_PROFESSION_CODE)
            .professionName(DEFAULT_PROFESSION_NAME)
            .status(DEFAULT_STATUS)
            .appointmentPhone(DEFAULT_APPOINTMENT_PHONE)
            .treatmentLogbookNumber(DEFAULT_TREATMENT_LOGBOOK_NUMBER);
        return praxis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Praxis createUpdatedEntity(EntityManager em) {
        Praxis praxis = new Praxis()
            .name(UPDATED_NAME)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .professionCode(UPDATED_PROFESSION_CODE)
            .professionName(UPDATED_PROFESSION_NAME)
            .status(UPDATED_STATUS)
            .appointmentPhone(UPDATED_APPOINTMENT_PHONE)
            .treatmentLogbookNumber(UPDATED_TREATMENT_LOGBOOK_NUMBER);
        return praxis;
    }

    @BeforeEach
    public void initTest() {
        praxis = createEntity(em);
    }

    @Test
    @Transactional
    public void createPraxis() throws Exception {
        int databaseSizeBeforeCreate = praxisRepository.findAll().size();
        // Create the Praxis
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);
        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isCreated());

        // Validate the Praxis in the database
        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeCreate + 1);
        Praxis testPraxis = praxisList.get(praxisList.size() - 1);
        assertThat(testPraxis.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPraxis.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testPraxis.getProfessionCode()).isEqualTo(DEFAULT_PROFESSION_CODE);
        assertThat(testPraxis.getProfessionName()).isEqualTo(DEFAULT_PROFESSION_NAME);
        assertThat(testPraxis.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPraxis.getAppointmentPhone()).isEqualTo(DEFAULT_APPOINTMENT_PHONE);
        assertThat(testPraxis.getTreatmentLogbookNumber()).isEqualTo(DEFAULT_TREATMENT_LOGBOOK_NUMBER);
    }

    @Test
    @Transactional
    public void createPraxisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = praxisRepository.findAll().size();

        // Create the Praxis with an existing ID
        praxis.setId(1L);
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Praxis in the database
        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = praxisRepository.findAll().size();
        // set the field null
        praxis.setName(null);

        // Create the Praxis, which fails.
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);


        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDepartmentIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = praxisRepository.findAll().size();
        // set the field null
        praxis.setDepartmentId(null);

        // Create the Praxis, which fails.
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);


        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProfessionCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = praxisRepository.findAll().size();
        // set the field null
        praxis.setProfessionCode(null);

        // Create the Praxis, which fails.
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);


        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProfessionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = praxisRepository.findAll().size();
        // set the field null
        praxis.setProfessionName(null);

        // Create the Praxis, which fails.
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);


        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = praxisRepository.findAll().size();
        // set the field null
        praxis.setStatus(null);

        // Create the Praxis, which fails.
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);


        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAppointmentPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = praxisRepository.findAll().size();
        // set the field null
        praxis.setAppointmentPhone(null);

        // Create the Praxis, which fails.
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);


        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTreatmentLogbookNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = praxisRepository.findAll().size();
        // set the field null
        praxis.setTreatmentLogbookNumber(null);

        // Create the Praxis, which fails.
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);


        restPraxisMockMvc.perform(post("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPraxes() throws Exception {
        // Initialize the database
        praxisRepository.saveAndFlush(praxis);

        // Get all the praxisList
        restPraxisMockMvc.perform(get("/api/praxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(praxis.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].professionCode").value(hasItem(DEFAULT_PROFESSION_CODE)))
            .andExpect(jsonPath("$.[*].professionName").value(hasItem(DEFAULT_PROFESSION_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].appointmentPhone").value(hasItem(DEFAULT_APPOINTMENT_PHONE)))
            .andExpect(jsonPath("$.[*].treatmentLogbookNumber").value(hasItem(DEFAULT_TREATMENT_LOGBOOK_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getPraxis() throws Exception {
        // Initialize the database
        praxisRepository.saveAndFlush(praxis);

        // Get the praxis
        restPraxisMockMvc.perform(get("/api/praxes/{id}", praxis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(praxis.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.professionCode").value(DEFAULT_PROFESSION_CODE))
            .andExpect(jsonPath("$.professionName").value(DEFAULT_PROFESSION_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.appointmentPhone").value(DEFAULT_APPOINTMENT_PHONE))
            .andExpect(jsonPath("$.treatmentLogbookNumber").value(DEFAULT_TREATMENT_LOGBOOK_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingPraxis() throws Exception {
        // Get the praxis
        restPraxisMockMvc.perform(get("/api/praxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePraxis() throws Exception {
        // Initialize the database
        praxisRepository.saveAndFlush(praxis);

        int databaseSizeBeforeUpdate = praxisRepository.findAll().size();

        // Update the praxis
        Praxis updatedPraxis = praxisRepository.findById(praxis.getId()).get();
        // Disconnect from session so that the updates on updatedPraxis are not directly saved in db
        em.detach(updatedPraxis);
        updatedPraxis
            .name(UPDATED_NAME)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .professionCode(UPDATED_PROFESSION_CODE)
            .professionName(UPDATED_PROFESSION_NAME)
            .status(UPDATED_STATUS)
            .appointmentPhone(UPDATED_APPOINTMENT_PHONE)
            .treatmentLogbookNumber(UPDATED_TREATMENT_LOGBOOK_NUMBER);
        PraxisDTO praxisDTO = praxisMapper.toDto(updatedPraxis);

        restPraxisMockMvc.perform(put("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isOk());

        // Validate the Praxis in the database
        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeUpdate);
        Praxis testPraxis = praxisList.get(praxisList.size() - 1);
        assertThat(testPraxis.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPraxis.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testPraxis.getProfessionCode()).isEqualTo(UPDATED_PROFESSION_CODE);
        assertThat(testPraxis.getProfessionName()).isEqualTo(UPDATED_PROFESSION_NAME);
        assertThat(testPraxis.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPraxis.getAppointmentPhone()).isEqualTo(UPDATED_APPOINTMENT_PHONE);
        assertThat(testPraxis.getTreatmentLogbookNumber()).isEqualTo(UPDATED_TREATMENT_LOGBOOK_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingPraxis() throws Exception {
        int databaseSizeBeforeUpdate = praxisRepository.findAll().size();

        // Create the Praxis
        PraxisDTO praxisDTO = praxisMapper.toDto(praxis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPraxisMockMvc.perform(put("/api/praxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praxisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Praxis in the database
        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePraxis() throws Exception {
        // Initialize the database
        praxisRepository.saveAndFlush(praxis);

        int databaseSizeBeforeDelete = praxisRepository.findAll().size();

        // Delete the praxis
        restPraxisMockMvc.perform(delete("/api/praxes/{id}", praxis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Praxis> praxisList = praxisRepository.findAll();
        assertThat(praxisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
