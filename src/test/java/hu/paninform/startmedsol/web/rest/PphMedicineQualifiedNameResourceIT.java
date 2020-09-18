package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphMedicineQualifiedName;
import hu.paninform.startmedsol.domain.PphMedicine;
import hu.paninform.startmedsol.repository.PphMedicineQualifiedNameRepository;
import hu.paninform.startmedsol.service.PphMedicineQualifiedNameService;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameDTO;
import hu.paninform.startmedsol.service.mapper.PphMedicineQualifiedNameMapper;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameCriteria;
import hu.paninform.startmedsol.service.PphMedicineQualifiedNameQueryService;

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
 * Integration tests for the {@link PphMedicineQualifiedNameResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphMedicineQualifiedNameResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_SUBSTANCE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_SUBSTANCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphMedicineQualifiedNameRepository pphMedicineQualifiedNameRepository;

    @Autowired
    private PphMedicineQualifiedNameMapper pphMedicineQualifiedNameMapper;

    @Autowired
    private PphMedicineQualifiedNameService pphMedicineQualifiedNameService;

    @Autowired
    private PphMedicineQualifiedNameQueryService pphMedicineQualifiedNameQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphMedicineQualifiedNameMockMvc;

    private PphMedicineQualifiedName pphMedicineQualifiedName;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMedicineQualifiedName createEntity(EntityManager em) {
        PphMedicineQualifiedName pphMedicineQualifiedName = new PphMedicineQualifiedName()
            .name(DEFAULT_NAME)
            .activeSubstance(DEFAULT_ACTIVE_SUBSTANCE)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphMedicineQualifiedName;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMedicineQualifiedName createUpdatedEntity(EntityManager em) {
        PphMedicineQualifiedName pphMedicineQualifiedName = new PphMedicineQualifiedName()
            .name(UPDATED_NAME)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphMedicineQualifiedName;
    }

    @BeforeEach
    public void initTest() {
        pphMedicineQualifiedName = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphMedicineQualifiedName() throws Exception {
        int databaseSizeBeforeCreate = pphMedicineQualifiedNameRepository.findAll().size();
        // Create the PphMedicineQualifiedName
        PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO = pphMedicineQualifiedNameMapper.toDto(pphMedicineQualifiedName);
        restPphMedicineQualifiedNameMockMvc.perform(post("/api/pph-medicine-qualified-names")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineQualifiedNameDTO)))
            .andExpect(status().isCreated());

        // Validate the PphMedicineQualifiedName in the database
        List<PphMedicineQualifiedName> pphMedicineQualifiedNameList = pphMedicineQualifiedNameRepository.findAll();
        assertThat(pphMedicineQualifiedNameList).hasSize(databaseSizeBeforeCreate + 1);
        PphMedicineQualifiedName testPphMedicineQualifiedName = pphMedicineQualifiedNameList.get(pphMedicineQualifiedNameList.size() - 1);
        assertThat(testPphMedicineQualifiedName.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPphMedicineQualifiedName.getActiveSubstance()).isEqualTo(DEFAULT_ACTIVE_SUBSTANCE);
        assertThat(testPphMedicineQualifiedName.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphMedicineQualifiedNameWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphMedicineQualifiedNameRepository.findAll().size();

        // Create the PphMedicineQualifiedName with an existing ID
        pphMedicineQualifiedName.setId(1L);
        PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO = pphMedicineQualifiedNameMapper.toDto(pphMedicineQualifiedName);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphMedicineQualifiedNameMockMvc.perform(post("/api/pph-medicine-qualified-names")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineQualifiedNameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PphMedicineQualifiedName in the database
        List<PphMedicineQualifiedName> pphMedicineQualifiedNameList = pphMedicineQualifiedNameRepository.findAll();
        assertThat(pphMedicineQualifiedNameList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphMedicineQualifiedNameRepository.findAll().size();
        // set the field null
        pphMedicineQualifiedName.setActivePuphaData(null);

        // Create the PphMedicineQualifiedName, which fails.
        PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO = pphMedicineQualifiedNameMapper.toDto(pphMedicineQualifiedName);


        restPphMedicineQualifiedNameMockMvc.perform(post("/api/pph-medicine-qualified-names")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineQualifiedNameDTO)))
            .andExpect(status().isBadRequest());

        List<PphMedicineQualifiedName> pphMedicineQualifiedNameList = pphMedicineQualifiedNameRepository.findAll();
        assertThat(pphMedicineQualifiedNameList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNames() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList
        restPphMedicineQualifiedNameMockMvc.perform(get("/api/pph-medicine-qualified-names?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphMedicineQualifiedName.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].activeSubstance").value(hasItem(DEFAULT_ACTIVE_SUBSTANCE)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphMedicineQualifiedName() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get the pphMedicineQualifiedName
        restPphMedicineQualifiedNameMockMvc.perform(get("/api/pph-medicine-qualified-names/{id}", pphMedicineQualifiedName.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphMedicineQualifiedName.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.activeSubstance").value(DEFAULT_ACTIVE_SUBSTANCE))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }


    @Test
    @Transactional
    public void getPphMedicineQualifiedNamesByIdFiltering() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        Long id = pphMedicineQualifiedName.getId();

        defaultPphMedicineQualifiedNameShouldBeFound("id.equals=" + id);
        defaultPphMedicineQualifiedNameShouldNotBeFound("id.notEquals=" + id);

        defaultPphMedicineQualifiedNameShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPphMedicineQualifiedNameShouldNotBeFound("id.greaterThan=" + id);

        defaultPphMedicineQualifiedNameShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPphMedicineQualifiedNameShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where name equals to DEFAULT_NAME
        defaultPphMedicineQualifiedNameShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the pphMedicineQualifiedNameList where name equals to UPDATED_NAME
        defaultPphMedicineQualifiedNameShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where name not equals to DEFAULT_NAME
        defaultPphMedicineQualifiedNameShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the pphMedicineQualifiedNameList where name not equals to UPDATED_NAME
        defaultPphMedicineQualifiedNameShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPphMedicineQualifiedNameShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the pphMedicineQualifiedNameList where name equals to UPDATED_NAME
        defaultPphMedicineQualifiedNameShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where name is not null
        defaultPphMedicineQualifiedNameShouldBeFound("name.specified=true");

        // Get all the pphMedicineQualifiedNameList where name is null
        defaultPphMedicineQualifiedNameShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByNameContainsSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where name contains DEFAULT_NAME
        defaultPphMedicineQualifiedNameShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the pphMedicineQualifiedNameList where name contains UPDATED_NAME
        defaultPphMedicineQualifiedNameShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where name does not contain DEFAULT_NAME
        defaultPphMedicineQualifiedNameShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the pphMedicineQualifiedNameList where name does not contain UPDATED_NAME
        defaultPphMedicineQualifiedNameShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActiveSubstanceIsEqualToSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activeSubstance equals to DEFAULT_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldBeFound("activeSubstance.equals=" + DEFAULT_ACTIVE_SUBSTANCE);

        // Get all the pphMedicineQualifiedNameList where activeSubstance equals to UPDATED_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldNotBeFound("activeSubstance.equals=" + UPDATED_ACTIVE_SUBSTANCE);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActiveSubstanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activeSubstance not equals to DEFAULT_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldNotBeFound("activeSubstance.notEquals=" + DEFAULT_ACTIVE_SUBSTANCE);

        // Get all the pphMedicineQualifiedNameList where activeSubstance not equals to UPDATED_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldBeFound("activeSubstance.notEquals=" + UPDATED_ACTIVE_SUBSTANCE);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActiveSubstanceIsInShouldWork() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activeSubstance in DEFAULT_ACTIVE_SUBSTANCE or UPDATED_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldBeFound("activeSubstance.in=" + DEFAULT_ACTIVE_SUBSTANCE + "," + UPDATED_ACTIVE_SUBSTANCE);

        // Get all the pphMedicineQualifiedNameList where activeSubstance equals to UPDATED_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldNotBeFound("activeSubstance.in=" + UPDATED_ACTIVE_SUBSTANCE);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActiveSubstanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activeSubstance is not null
        defaultPphMedicineQualifiedNameShouldBeFound("activeSubstance.specified=true");

        // Get all the pphMedicineQualifiedNameList where activeSubstance is null
        defaultPphMedicineQualifiedNameShouldNotBeFound("activeSubstance.specified=false");
    }
                @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActiveSubstanceContainsSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activeSubstance contains DEFAULT_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldBeFound("activeSubstance.contains=" + DEFAULT_ACTIVE_SUBSTANCE);

        // Get all the pphMedicineQualifiedNameList where activeSubstance contains UPDATED_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldNotBeFound("activeSubstance.contains=" + UPDATED_ACTIVE_SUBSTANCE);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActiveSubstanceNotContainsSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activeSubstance does not contain DEFAULT_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldNotBeFound("activeSubstance.doesNotContain=" + DEFAULT_ACTIVE_SUBSTANCE);

        // Get all the pphMedicineQualifiedNameList where activeSubstance does not contain UPDATED_ACTIVE_SUBSTANCE
        defaultPphMedicineQualifiedNameShouldBeFound("activeSubstance.doesNotContain=" + UPDATED_ACTIVE_SUBSTANCE);
    }


    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActivePuphaDataIsEqualToSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activePuphaData equals to DEFAULT_ACTIVE_PUPHA_DATA
        defaultPphMedicineQualifiedNameShouldBeFound("activePuphaData.equals=" + DEFAULT_ACTIVE_PUPHA_DATA);

        // Get all the pphMedicineQualifiedNameList where activePuphaData equals to UPDATED_ACTIVE_PUPHA_DATA
        defaultPphMedicineQualifiedNameShouldNotBeFound("activePuphaData.equals=" + UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActivePuphaDataIsNotEqualToSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activePuphaData not equals to DEFAULT_ACTIVE_PUPHA_DATA
        defaultPphMedicineQualifiedNameShouldNotBeFound("activePuphaData.notEquals=" + DEFAULT_ACTIVE_PUPHA_DATA);

        // Get all the pphMedicineQualifiedNameList where activePuphaData not equals to UPDATED_ACTIVE_PUPHA_DATA
        defaultPphMedicineQualifiedNameShouldBeFound("activePuphaData.notEquals=" + UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActivePuphaDataIsInShouldWork() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activePuphaData in DEFAULT_ACTIVE_PUPHA_DATA or UPDATED_ACTIVE_PUPHA_DATA
        defaultPphMedicineQualifiedNameShouldBeFound("activePuphaData.in=" + DEFAULT_ACTIVE_PUPHA_DATA + "," + UPDATED_ACTIVE_PUPHA_DATA);

        // Get all the pphMedicineQualifiedNameList where activePuphaData equals to UPDATED_ACTIVE_PUPHA_DATA
        defaultPphMedicineQualifiedNameShouldNotBeFound("activePuphaData.in=" + UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByActivePuphaDataIsNullOrNotNull() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        // Get all the pphMedicineQualifiedNameList where activePuphaData is not null
        defaultPphMedicineQualifiedNameShouldBeFound("activePuphaData.specified=true");

        // Get all the pphMedicineQualifiedNameList where activePuphaData is null
        defaultPphMedicineQualifiedNameShouldNotBeFound("activePuphaData.specified=false");
    }

    @Test
    @Transactional
    public void getAllPphMedicineQualifiedNamesByMedicineIsEqualToSomething() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);
        PphMedicine medicine = PphMedicineResourceIT.createEntity(em);
        em.persist(medicine);
        em.flush();
        pphMedicineQualifiedName.addMedicine(medicine);
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);
        Long medicineId = medicine.getId();

        // Get all the pphMedicineQualifiedNameList where medicine equals to medicineId
        defaultPphMedicineQualifiedNameShouldBeFound("medicineId.equals=" + medicineId);

        // Get all the pphMedicineQualifiedNameList where medicine equals to medicineId + 1
        defaultPphMedicineQualifiedNameShouldNotBeFound("medicineId.equals=" + (medicineId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPphMedicineQualifiedNameShouldBeFound(String filter) throws Exception {
        restPphMedicineQualifiedNameMockMvc.perform(get("/api/pph-medicine-qualified-names?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphMedicineQualifiedName.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].activeSubstance").value(hasItem(DEFAULT_ACTIVE_SUBSTANCE)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));

        // Check, that the count call also returns 1
        restPphMedicineQualifiedNameMockMvc.perform(get("/api/pph-medicine-qualified-names/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPphMedicineQualifiedNameShouldNotBeFound(String filter) throws Exception {
        restPphMedicineQualifiedNameMockMvc.perform(get("/api/pph-medicine-qualified-names?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPphMedicineQualifiedNameMockMvc.perform(get("/api/pph-medicine-qualified-names/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPphMedicineQualifiedName() throws Exception {
        // Get the pphMedicineQualifiedName
        restPphMedicineQualifiedNameMockMvc.perform(get("/api/pph-medicine-qualified-names/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphMedicineQualifiedName() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        int databaseSizeBeforeUpdate = pphMedicineQualifiedNameRepository.findAll().size();

        // Update the pphMedicineQualifiedName
        PphMedicineQualifiedName updatedPphMedicineQualifiedName = pphMedicineQualifiedNameRepository.findById(pphMedicineQualifiedName.getId()).get();
        // Disconnect from session so that the updates on updatedPphMedicineQualifiedName are not directly saved in db
        em.detach(updatedPphMedicineQualifiedName);
        updatedPphMedicineQualifiedName
            .name(UPDATED_NAME)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO = pphMedicineQualifiedNameMapper.toDto(updatedPphMedicineQualifiedName);

        restPphMedicineQualifiedNameMockMvc.perform(put("/api/pph-medicine-qualified-names")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineQualifiedNameDTO)))
            .andExpect(status().isOk());

        // Validate the PphMedicineQualifiedName in the database
        List<PphMedicineQualifiedName> pphMedicineQualifiedNameList = pphMedicineQualifiedNameRepository.findAll();
        assertThat(pphMedicineQualifiedNameList).hasSize(databaseSizeBeforeUpdate);
        PphMedicineQualifiedName testPphMedicineQualifiedName = pphMedicineQualifiedNameList.get(pphMedicineQualifiedNameList.size() - 1);
        assertThat(testPphMedicineQualifiedName.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPphMedicineQualifiedName.getActiveSubstance()).isEqualTo(UPDATED_ACTIVE_SUBSTANCE);
        assertThat(testPphMedicineQualifiedName.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphMedicineQualifiedName() throws Exception {
        int databaseSizeBeforeUpdate = pphMedicineQualifiedNameRepository.findAll().size();

        // Create the PphMedicineQualifiedName
        PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO = pphMedicineQualifiedNameMapper.toDto(pphMedicineQualifiedName);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphMedicineQualifiedNameMockMvc.perform(put("/api/pph-medicine-qualified-names")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineQualifiedNameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PphMedicineQualifiedName in the database
        List<PphMedicineQualifiedName> pphMedicineQualifiedNameList = pphMedicineQualifiedNameRepository.findAll();
        assertThat(pphMedicineQualifiedNameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphMedicineQualifiedName() throws Exception {
        // Initialize the database
        pphMedicineQualifiedNameRepository.saveAndFlush(pphMedicineQualifiedName);

        int databaseSizeBeforeDelete = pphMedicineQualifiedNameRepository.findAll().size();

        // Delete the pphMedicineQualifiedName
        restPphMedicineQualifiedNameMockMvc.perform(delete("/api/pph-medicine-qualified-names/{id}", pphMedicineQualifiedName.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphMedicineQualifiedName> pphMedicineQualifiedNameList = pphMedicineQualifiedNameRepository.findAll();
        assertThat(pphMedicineQualifiedNameList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
