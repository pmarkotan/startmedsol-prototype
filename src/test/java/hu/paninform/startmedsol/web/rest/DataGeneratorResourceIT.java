package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.DataGenerator;
import hu.paninform.startmedsol.repository.DataGeneratorRepository;
import hu.paninform.startmedsol.service.DataGeneratorService;
import hu.paninform.startmedsol.service.dto.DataGeneratorDTO;
import hu.paninform.startmedsol.service.mapper.DataGeneratorMapper;

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
 * Integration tests for the {@link DataGeneratorResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DataGeneratorResourceIT {

    private static final Integer DEFAULT_PROVIDER = 1;
    private static final Integer UPDATED_PROVIDER = 2;

    private static final Integer DEFAULT_PRAXIS = 1;
    private static final Integer UPDATED_PRAXIS = 2;

    private static final Integer DEFAULT_PATIENT = 1;
    private static final Integer UPDATED_PATIENT = 2;

    private static final Integer DEFAULT_MEDICAL_CASE = 1;
    private static final Integer UPDATED_MEDICAL_CASE = 2;

    @Autowired
    private DataGeneratorRepository dataGeneratorRepository;

    @Autowired
    private DataGeneratorMapper dataGeneratorMapper;

    @Autowired
    private DataGeneratorService dataGeneratorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDataGeneratorMockMvc;

    private DataGenerator dataGenerator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataGenerator createEntity(EntityManager em) {
        DataGenerator dataGenerator = new DataGenerator()
            .provider(DEFAULT_PROVIDER)
            .praxis(DEFAULT_PRAXIS)
            .patient(DEFAULT_PATIENT)
            .medicalCase(DEFAULT_MEDICAL_CASE);
        return dataGenerator;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataGenerator createUpdatedEntity(EntityManager em) {
        DataGenerator dataGenerator = new DataGenerator()
            .provider(UPDATED_PROVIDER)
            .praxis(UPDATED_PRAXIS)
            .patient(UPDATED_PATIENT)
            .medicalCase(UPDATED_MEDICAL_CASE);
        return dataGenerator;
    }

    @BeforeEach
    public void initTest() {
        dataGenerator = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataGenerator() throws Exception {
        int databaseSizeBeforeCreate = dataGeneratorRepository.findAll().size();
        // Create the DataGenerator
        DataGeneratorDTO dataGeneratorDTO = dataGeneratorMapper.toDto(dataGenerator);
        restDataGeneratorMockMvc.perform(post("/api/data-generators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataGeneratorDTO)))
            .andExpect(status().isCreated());

        // Validate the DataGenerator in the database
        List<DataGenerator> dataGeneratorList = dataGeneratorRepository.findAll();
        assertThat(dataGeneratorList).hasSize(databaseSizeBeforeCreate + 1);
        DataGenerator testDataGenerator = dataGeneratorList.get(dataGeneratorList.size() - 1);
        assertThat(testDataGenerator.getProvider()).isEqualTo(DEFAULT_PROVIDER);
        assertThat(testDataGenerator.getPraxis()).isEqualTo(DEFAULT_PRAXIS);
        assertThat(testDataGenerator.getPatient()).isEqualTo(DEFAULT_PATIENT);
        assertThat(testDataGenerator.getMedicalCase()).isEqualTo(DEFAULT_MEDICAL_CASE);
    }

    @Test
    @Transactional
    public void createDataGeneratorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataGeneratorRepository.findAll().size();

        // Create the DataGenerator with an existing ID
        dataGenerator.setId(1L);
        DataGeneratorDTO dataGeneratorDTO = dataGeneratorMapper.toDto(dataGenerator);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataGeneratorMockMvc.perform(post("/api/data-generators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataGeneratorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataGenerator in the database
        List<DataGenerator> dataGeneratorList = dataGeneratorRepository.findAll();
        assertThat(dataGeneratorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataGenerators() throws Exception {
        // Initialize the database
        dataGeneratorRepository.saveAndFlush(dataGenerator);

        // Get all the dataGeneratorList
        restDataGeneratorMockMvc.perform(get("/api/data-generators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataGenerator.getId().intValue())))
            .andExpect(jsonPath("$.[*].provider").value(hasItem(DEFAULT_PROVIDER)))
            .andExpect(jsonPath("$.[*].praxis").value(hasItem(DEFAULT_PRAXIS)))
            .andExpect(jsonPath("$.[*].patient").value(hasItem(DEFAULT_PATIENT)))
            .andExpect(jsonPath("$.[*].medicalCase").value(hasItem(DEFAULT_MEDICAL_CASE)));
    }
    
    @Test
    @Transactional
    public void getDataGenerator() throws Exception {
        // Initialize the database
        dataGeneratorRepository.saveAndFlush(dataGenerator);

        // Get the dataGenerator
        restDataGeneratorMockMvc.perform(get("/api/data-generators/{id}", dataGenerator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataGenerator.getId().intValue()))
            .andExpect(jsonPath("$.provider").value(DEFAULT_PROVIDER))
            .andExpect(jsonPath("$.praxis").value(DEFAULT_PRAXIS))
            .andExpect(jsonPath("$.patient").value(DEFAULT_PATIENT))
            .andExpect(jsonPath("$.medicalCase").value(DEFAULT_MEDICAL_CASE));
    }
    @Test
    @Transactional
    public void getNonExistingDataGenerator() throws Exception {
        // Get the dataGenerator
        restDataGeneratorMockMvc.perform(get("/api/data-generators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataGenerator() throws Exception {
        // Initialize the database
        dataGeneratorRepository.saveAndFlush(dataGenerator);

        int databaseSizeBeforeUpdate = dataGeneratorRepository.findAll().size();

        // Update the dataGenerator
        DataGenerator updatedDataGenerator = dataGeneratorRepository.findById(dataGenerator.getId()).get();
        // Disconnect from session so that the updates on updatedDataGenerator are not directly saved in db
        em.detach(updatedDataGenerator);
        updatedDataGenerator
            .provider(UPDATED_PROVIDER)
            .praxis(UPDATED_PRAXIS)
            .patient(UPDATED_PATIENT)
            .medicalCase(UPDATED_MEDICAL_CASE);
        DataGeneratorDTO dataGeneratorDTO = dataGeneratorMapper.toDto(updatedDataGenerator);

        restDataGeneratorMockMvc.perform(put("/api/data-generators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataGeneratorDTO)))
            .andExpect(status().isOk());

        // Validate the DataGenerator in the database
        List<DataGenerator> dataGeneratorList = dataGeneratorRepository.findAll();
        assertThat(dataGeneratorList).hasSize(databaseSizeBeforeUpdate);
        DataGenerator testDataGenerator = dataGeneratorList.get(dataGeneratorList.size() - 1);
        assertThat(testDataGenerator.getProvider()).isEqualTo(UPDATED_PROVIDER);
        assertThat(testDataGenerator.getPraxis()).isEqualTo(UPDATED_PRAXIS);
        assertThat(testDataGenerator.getPatient()).isEqualTo(UPDATED_PATIENT);
        assertThat(testDataGenerator.getMedicalCase()).isEqualTo(UPDATED_MEDICAL_CASE);
    }

    @Test
    @Transactional
    public void updateNonExistingDataGenerator() throws Exception {
        int databaseSizeBeforeUpdate = dataGeneratorRepository.findAll().size();

        // Create the DataGenerator
        DataGeneratorDTO dataGeneratorDTO = dataGeneratorMapper.toDto(dataGenerator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataGeneratorMockMvc.perform(put("/api/data-generators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataGeneratorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataGenerator in the database
        List<DataGenerator> dataGeneratorList = dataGeneratorRepository.findAll();
        assertThat(dataGeneratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataGenerator() throws Exception {
        // Initialize the database
        dataGeneratorRepository.saveAndFlush(dataGenerator);

        int databaseSizeBeforeDelete = dataGeneratorRepository.findAll().size();

        // Delete the dataGenerator
        restDataGeneratorMockMvc.perform(delete("/api/data-generators/{id}", dataGenerator.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataGenerator> dataGeneratorList = dataGeneratorRepository.findAll();
        assertThat(dataGeneratorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
