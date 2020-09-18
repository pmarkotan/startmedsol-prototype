package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.Validity;
import hu.paninform.startmedsol.repository.ValidityRepository;
import hu.paninform.startmedsol.service.ValidityService;
import hu.paninform.startmedsol.service.dto.ValidityDTO;
import hu.paninform.startmedsol.service.mapper.ValidityMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ValidityResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ValidityResourceIT {

    private static final LocalDate DEFAULT_VALID_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VALID_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_TO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ValidityRepository validityRepository;

    @Autowired
    private ValidityMapper validityMapper;

    @Autowired
    private ValidityService validityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restValidityMockMvc;

    private Validity validity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Validity createEntity(EntityManager em) {
        Validity validity = new Validity()
            .validFrom(DEFAULT_VALID_FROM)
            .validTo(DEFAULT_VALID_TO);
        return validity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Validity createUpdatedEntity(EntityManager em) {
        Validity validity = new Validity()
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO);
        return validity;
    }

    @BeforeEach
    public void initTest() {
        validity = createEntity(em);
    }

    @Test
    @Transactional
    public void createValidity() throws Exception {
        int databaseSizeBeforeCreate = validityRepository.findAll().size();
        // Create the Validity
        ValidityDTO validityDTO = validityMapper.toDto(validity);
        restValidityMockMvc.perform(post("/api/validities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(validityDTO)))
            .andExpect(status().isCreated());

        // Validate the Validity in the database
        List<Validity> validityList = validityRepository.findAll();
        assertThat(validityList).hasSize(databaseSizeBeforeCreate + 1);
        Validity testValidity = validityList.get(validityList.size() - 1);
        assertThat(testValidity.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
        assertThat(testValidity.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
    }

    @Test
    @Transactional
    public void createValidityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = validityRepository.findAll().size();

        // Create the Validity with an existing ID
        validity.setId(1L);
        ValidityDTO validityDTO = validityMapper.toDto(validity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValidityMockMvc.perform(post("/api/validities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(validityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Validity in the database
        List<Validity> validityList = validityRepository.findAll();
        assertThat(validityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllValidities() throws Exception {
        // Initialize the database
        validityRepository.saveAndFlush(validity);

        // Get all the validityList
        restValidityMockMvc.perform(get("/api/validities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(validity.getId().intValue())))
            .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM.toString())))
            .andExpect(jsonPath("$.[*].validTo").value(hasItem(DEFAULT_VALID_TO.toString())));
    }
    
    @Test
    @Transactional
    public void getValidity() throws Exception {
        // Initialize the database
        validityRepository.saveAndFlush(validity);

        // Get the validity
        restValidityMockMvc.perform(get("/api/validities/{id}", validity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(validity.getId().intValue()))
            .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM.toString()))
            .andExpect(jsonPath("$.validTo").value(DEFAULT_VALID_TO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingValidity() throws Exception {
        // Get the validity
        restValidityMockMvc.perform(get("/api/validities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValidity() throws Exception {
        // Initialize the database
        validityRepository.saveAndFlush(validity);

        int databaseSizeBeforeUpdate = validityRepository.findAll().size();

        // Update the validity
        Validity updatedValidity = validityRepository.findById(validity.getId()).get();
        // Disconnect from session so that the updates on updatedValidity are not directly saved in db
        em.detach(updatedValidity);
        updatedValidity
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO);
        ValidityDTO validityDTO = validityMapper.toDto(updatedValidity);

        restValidityMockMvc.perform(put("/api/validities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(validityDTO)))
            .andExpect(status().isOk());

        // Validate the Validity in the database
        List<Validity> validityList = validityRepository.findAll();
        assertThat(validityList).hasSize(databaseSizeBeforeUpdate);
        Validity testValidity = validityList.get(validityList.size() - 1);
        assertThat(testValidity.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testValidity.getValidTo()).isEqualTo(UPDATED_VALID_TO);
    }

    @Test
    @Transactional
    public void updateNonExistingValidity() throws Exception {
        int databaseSizeBeforeUpdate = validityRepository.findAll().size();

        // Create the Validity
        ValidityDTO validityDTO = validityMapper.toDto(validity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValidityMockMvc.perform(put("/api/validities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(validityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Validity in the database
        List<Validity> validityList = validityRepository.findAll();
        assertThat(validityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteValidity() throws Exception {
        // Initialize the database
        validityRepository.saveAndFlush(validity);

        int databaseSizeBeforeDelete = validityRepository.findAll().size();

        // Delete the validity
        restValidityMockMvc.perform(delete("/api/validities/{id}", validity.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Validity> validityList = validityRepository.findAll();
        assertThat(validityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
