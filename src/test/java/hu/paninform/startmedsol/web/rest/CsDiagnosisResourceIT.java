package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.CsDiagnosis;
import hu.paninform.startmedsol.domain.Validity;
import hu.paninform.startmedsol.repository.CsDiagnosisRepository;
import hu.paninform.startmedsol.service.CsDiagnosisService;
import hu.paninform.startmedsol.service.dto.CsDiagnosisDTO;
import hu.paninform.startmedsol.service.mapper.CsDiagnosisMapper;
import hu.paninform.startmedsol.service.dto.CsDiagnosisCriteria;
import hu.paninform.startmedsol.service.CsDiagnosisQueryService;

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

import hu.paninform.startmedsol.domain.enumeration.DgSex;
/**
 * Integration tests for the {@link CsDiagnosisResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CsDiagnosisResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REPORT = false;
    private static final Boolean UPDATED_REPORT = true;

    private static final String DEFAULT_DESCR = "AAAAAAAAAA";
    private static final String UPDATED_DESCR = "BBBBBBBBBB";

    private static final DgSex DEFAULT_SEX = DgSex.BOTH;
    private static final DgSex UPDATED_SEX = DgSex.MALE;

    private static final Integer DEFAULT_AGE_MIN = 0;
    private static final Integer UPDATED_AGE_MIN = 1;
    private static final Integer SMALLER_AGE_MIN = 0 - 1;

    private static final Integer DEFAULT_AGE_MAX = 0;
    private static final Integer UPDATED_AGE_MAX = 1;
    private static final Integer SMALLER_AGE_MAX = 0 - 1;

    @Autowired
    private CsDiagnosisRepository csDiagnosisRepository;

    @Autowired
    private CsDiagnosisMapper csDiagnosisMapper;

    @Autowired
    private CsDiagnosisService csDiagnosisService;

    @Autowired
    private CsDiagnosisQueryService csDiagnosisQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCsDiagnosisMockMvc;

    private CsDiagnosis csDiagnosis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsDiagnosis createEntity(EntityManager em) {
        CsDiagnosis csDiagnosis = new CsDiagnosis()
            .code(DEFAULT_CODE)
            .report(DEFAULT_REPORT)
            .descr(DEFAULT_DESCR)
            .sex(DEFAULT_SEX)
            .ageMin(DEFAULT_AGE_MIN)
            .ageMax(DEFAULT_AGE_MAX);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csDiagnosis.setValidity(validity);
        return csDiagnosis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CsDiagnosis createUpdatedEntity(EntityManager em) {
        CsDiagnosis csDiagnosis = new CsDiagnosis()
            .code(UPDATED_CODE)
            .report(UPDATED_REPORT)
            .descr(UPDATED_DESCR)
            .sex(UPDATED_SEX)
            .ageMin(UPDATED_AGE_MIN)
            .ageMax(UPDATED_AGE_MAX);
        // Add required entity
        Validity validity;
        if (TestUtil.findAll(em, Validity.class).isEmpty()) {
            validity = ValidityResourceIT.createUpdatedEntity(em);
            em.persist(validity);
            em.flush();
        } else {
            validity = TestUtil.findAll(em, Validity.class).get(0);
        }
        csDiagnosis.setValidity(validity);
        return csDiagnosis;
    }

    @BeforeEach
    public void initTest() {
        csDiagnosis = createEntity(em);
    }

    @Test
    @Transactional
    public void createCsDiagnosis() throws Exception {
        int databaseSizeBeforeCreate = csDiagnosisRepository.findAll().size();
        // Create the CsDiagnosis
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(csDiagnosis);
        restCsDiagnosisMockMvc.perform(post("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isCreated());

        // Validate the CsDiagnosis in the database
        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeCreate + 1);
        CsDiagnosis testCsDiagnosis = csDiagnosisList.get(csDiagnosisList.size() - 1);
        assertThat(testCsDiagnosis.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCsDiagnosis.isReport()).isEqualTo(DEFAULT_REPORT);
        assertThat(testCsDiagnosis.getDescr()).isEqualTo(DEFAULT_DESCR);
        assertThat(testCsDiagnosis.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testCsDiagnosis.getAgeMin()).isEqualTo(DEFAULT_AGE_MIN);
        assertThat(testCsDiagnosis.getAgeMax()).isEqualTo(DEFAULT_AGE_MAX);
    }

    @Test
    @Transactional
    public void createCsDiagnosisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = csDiagnosisRepository.findAll().size();

        // Create the CsDiagnosis with an existing ID
        csDiagnosis.setId(1L);
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(csDiagnosis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCsDiagnosisMockMvc.perform(post("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsDiagnosis in the database
        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = csDiagnosisRepository.findAll().size();
        // set the field null
        csDiagnosis.setCode(null);

        // Create the CsDiagnosis, which fails.
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(csDiagnosis);


        restCsDiagnosisMockMvc.perform(post("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReportIsRequired() throws Exception {
        int databaseSizeBeforeTest = csDiagnosisRepository.findAll().size();
        // set the field null
        csDiagnosis.setReport(null);

        // Create the CsDiagnosis, which fails.
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(csDiagnosis);


        restCsDiagnosisMockMvc.perform(post("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescrIsRequired() throws Exception {
        int databaseSizeBeforeTest = csDiagnosisRepository.findAll().size();
        // set the field null
        csDiagnosis.setDescr(null);

        // Create the CsDiagnosis, which fails.
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(csDiagnosis);


        restCsDiagnosisMockMvc.perform(post("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = csDiagnosisRepository.findAll().size();
        // set the field null
        csDiagnosis.setSex(null);

        // Create the CsDiagnosis, which fails.
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(csDiagnosis);


        restCsDiagnosisMockMvc.perform(post("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCsDiagnoses() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList
        restCsDiagnosisMockMvc.perform(get("/api/cs-diagnoses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csDiagnosis.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].report").value(hasItem(DEFAULT_REPORT.booleanValue())))
            .andExpect(jsonPath("$.[*].descr").value(hasItem(DEFAULT_DESCR)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].ageMin").value(hasItem(DEFAULT_AGE_MIN)))
            .andExpect(jsonPath("$.[*].ageMax").value(hasItem(DEFAULT_AGE_MAX)));
    }
    
    @Test
    @Transactional
    public void getCsDiagnosis() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get the csDiagnosis
        restCsDiagnosisMockMvc.perform(get("/api/cs-diagnoses/{id}", csDiagnosis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(csDiagnosis.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.report").value(DEFAULT_REPORT.booleanValue()))
            .andExpect(jsonPath("$.descr").value(DEFAULT_DESCR))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.ageMin").value(DEFAULT_AGE_MIN))
            .andExpect(jsonPath("$.ageMax").value(DEFAULT_AGE_MAX));
    }


    @Test
    @Transactional
    public void getCsDiagnosesByIdFiltering() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        Long id = csDiagnosis.getId();

        defaultCsDiagnosisShouldBeFound("id.equals=" + id);
        defaultCsDiagnosisShouldNotBeFound("id.notEquals=" + id);

        defaultCsDiagnosisShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCsDiagnosisShouldNotBeFound("id.greaterThan=" + id);

        defaultCsDiagnosisShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCsDiagnosisShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCsDiagnosesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where code equals to DEFAULT_CODE
        defaultCsDiagnosisShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the csDiagnosisList where code equals to UPDATED_CODE
        defaultCsDiagnosisShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where code not equals to DEFAULT_CODE
        defaultCsDiagnosisShouldNotBeFound("code.notEquals=" + DEFAULT_CODE);

        // Get all the csDiagnosisList where code not equals to UPDATED_CODE
        defaultCsDiagnosisShouldBeFound("code.notEquals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where code in DEFAULT_CODE or UPDATED_CODE
        defaultCsDiagnosisShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the csDiagnosisList where code equals to UPDATED_CODE
        defaultCsDiagnosisShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where code is not null
        defaultCsDiagnosisShouldBeFound("code.specified=true");

        // Get all the csDiagnosisList where code is null
        defaultCsDiagnosisShouldNotBeFound("code.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsDiagnosesByCodeContainsSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where code contains DEFAULT_CODE
        defaultCsDiagnosisShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the csDiagnosisList where code contains UPDATED_CODE
        defaultCsDiagnosisShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where code does not contain DEFAULT_CODE
        defaultCsDiagnosisShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the csDiagnosisList where code does not contain UPDATED_CODE
        defaultCsDiagnosisShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }


    @Test
    @Transactional
    public void getAllCsDiagnosesByReportIsEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where report equals to DEFAULT_REPORT
        defaultCsDiagnosisShouldBeFound("report.equals=" + DEFAULT_REPORT);

        // Get all the csDiagnosisList where report equals to UPDATED_REPORT
        defaultCsDiagnosisShouldNotBeFound("report.equals=" + UPDATED_REPORT);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByReportIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where report not equals to DEFAULT_REPORT
        defaultCsDiagnosisShouldNotBeFound("report.notEquals=" + DEFAULT_REPORT);

        // Get all the csDiagnosisList where report not equals to UPDATED_REPORT
        defaultCsDiagnosisShouldBeFound("report.notEquals=" + UPDATED_REPORT);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByReportIsInShouldWork() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where report in DEFAULT_REPORT or UPDATED_REPORT
        defaultCsDiagnosisShouldBeFound("report.in=" + DEFAULT_REPORT + "," + UPDATED_REPORT);

        // Get all the csDiagnosisList where report equals to UPDATED_REPORT
        defaultCsDiagnosisShouldNotBeFound("report.in=" + UPDATED_REPORT);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByReportIsNullOrNotNull() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where report is not null
        defaultCsDiagnosisShouldBeFound("report.specified=true");

        // Get all the csDiagnosisList where report is null
        defaultCsDiagnosisShouldNotBeFound("report.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByDescrIsEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where descr equals to DEFAULT_DESCR
        defaultCsDiagnosisShouldBeFound("descr.equals=" + DEFAULT_DESCR);

        // Get all the csDiagnosisList where descr equals to UPDATED_DESCR
        defaultCsDiagnosisShouldNotBeFound("descr.equals=" + UPDATED_DESCR);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByDescrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where descr not equals to DEFAULT_DESCR
        defaultCsDiagnosisShouldNotBeFound("descr.notEquals=" + DEFAULT_DESCR);

        // Get all the csDiagnosisList where descr not equals to UPDATED_DESCR
        defaultCsDiagnosisShouldBeFound("descr.notEquals=" + UPDATED_DESCR);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByDescrIsInShouldWork() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where descr in DEFAULT_DESCR or UPDATED_DESCR
        defaultCsDiagnosisShouldBeFound("descr.in=" + DEFAULT_DESCR + "," + UPDATED_DESCR);

        // Get all the csDiagnosisList where descr equals to UPDATED_DESCR
        defaultCsDiagnosisShouldNotBeFound("descr.in=" + UPDATED_DESCR);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByDescrIsNullOrNotNull() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where descr is not null
        defaultCsDiagnosisShouldBeFound("descr.specified=true");

        // Get all the csDiagnosisList where descr is null
        defaultCsDiagnosisShouldNotBeFound("descr.specified=false");
    }
                @Test
    @Transactional
    public void getAllCsDiagnosesByDescrContainsSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where descr contains DEFAULT_DESCR
        defaultCsDiagnosisShouldBeFound("descr.contains=" + DEFAULT_DESCR);

        // Get all the csDiagnosisList where descr contains UPDATED_DESCR
        defaultCsDiagnosisShouldNotBeFound("descr.contains=" + UPDATED_DESCR);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByDescrNotContainsSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where descr does not contain DEFAULT_DESCR
        defaultCsDiagnosisShouldNotBeFound("descr.doesNotContain=" + DEFAULT_DESCR);

        // Get all the csDiagnosisList where descr does not contain UPDATED_DESCR
        defaultCsDiagnosisShouldBeFound("descr.doesNotContain=" + UPDATED_DESCR);
    }


    @Test
    @Transactional
    public void getAllCsDiagnosesBySexIsEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where sex equals to DEFAULT_SEX
        defaultCsDiagnosisShouldBeFound("sex.equals=" + DEFAULT_SEX);

        // Get all the csDiagnosisList where sex equals to UPDATED_SEX
        defaultCsDiagnosisShouldNotBeFound("sex.equals=" + UPDATED_SEX);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesBySexIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where sex not equals to DEFAULT_SEX
        defaultCsDiagnosisShouldNotBeFound("sex.notEquals=" + DEFAULT_SEX);

        // Get all the csDiagnosisList where sex not equals to UPDATED_SEX
        defaultCsDiagnosisShouldBeFound("sex.notEquals=" + UPDATED_SEX);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesBySexIsInShouldWork() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where sex in DEFAULT_SEX or UPDATED_SEX
        defaultCsDiagnosisShouldBeFound("sex.in=" + DEFAULT_SEX + "," + UPDATED_SEX);

        // Get all the csDiagnosisList where sex equals to UPDATED_SEX
        defaultCsDiagnosisShouldNotBeFound("sex.in=" + UPDATED_SEX);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesBySexIsNullOrNotNull() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where sex is not null
        defaultCsDiagnosisShouldBeFound("sex.specified=true");

        // Get all the csDiagnosisList where sex is null
        defaultCsDiagnosisShouldNotBeFound("sex.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin equals to DEFAULT_AGE_MIN
        defaultCsDiagnosisShouldBeFound("ageMin.equals=" + DEFAULT_AGE_MIN);

        // Get all the csDiagnosisList where ageMin equals to UPDATED_AGE_MIN
        defaultCsDiagnosisShouldNotBeFound("ageMin.equals=" + UPDATED_AGE_MIN);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin not equals to DEFAULT_AGE_MIN
        defaultCsDiagnosisShouldNotBeFound("ageMin.notEquals=" + DEFAULT_AGE_MIN);

        // Get all the csDiagnosisList where ageMin not equals to UPDATED_AGE_MIN
        defaultCsDiagnosisShouldBeFound("ageMin.notEquals=" + UPDATED_AGE_MIN);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsInShouldWork() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin in DEFAULT_AGE_MIN or UPDATED_AGE_MIN
        defaultCsDiagnosisShouldBeFound("ageMin.in=" + DEFAULT_AGE_MIN + "," + UPDATED_AGE_MIN);

        // Get all the csDiagnosisList where ageMin equals to UPDATED_AGE_MIN
        defaultCsDiagnosisShouldNotBeFound("ageMin.in=" + UPDATED_AGE_MIN);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsNullOrNotNull() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin is not null
        defaultCsDiagnosisShouldBeFound("ageMin.specified=true");

        // Get all the csDiagnosisList where ageMin is null
        defaultCsDiagnosisShouldNotBeFound("ageMin.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin is greater than or equal to DEFAULT_AGE_MIN
        defaultCsDiagnosisShouldBeFound("ageMin.greaterThanOrEqual=" + DEFAULT_AGE_MIN);

        // Get all the csDiagnosisList where ageMin is greater than or equal to (DEFAULT_AGE_MIN + 1)
        defaultCsDiagnosisShouldNotBeFound("ageMin.greaterThanOrEqual=" + (DEFAULT_AGE_MIN + 1));
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin is less than or equal to DEFAULT_AGE_MIN
        defaultCsDiagnosisShouldBeFound("ageMin.lessThanOrEqual=" + DEFAULT_AGE_MIN);

        // Get all the csDiagnosisList where ageMin is less than or equal to SMALLER_AGE_MIN
        defaultCsDiagnosisShouldNotBeFound("ageMin.lessThanOrEqual=" + SMALLER_AGE_MIN);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsLessThanSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin is less than DEFAULT_AGE_MIN
        defaultCsDiagnosisShouldNotBeFound("ageMin.lessThan=" + DEFAULT_AGE_MIN);

        // Get all the csDiagnosisList where ageMin is less than (DEFAULT_AGE_MIN + 1)
        defaultCsDiagnosisShouldBeFound("ageMin.lessThan=" + (DEFAULT_AGE_MIN + 1));
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMinIsGreaterThanSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMin is greater than DEFAULT_AGE_MIN
        defaultCsDiagnosisShouldNotBeFound("ageMin.greaterThan=" + DEFAULT_AGE_MIN);

        // Get all the csDiagnosisList where ageMin is greater than SMALLER_AGE_MIN
        defaultCsDiagnosisShouldBeFound("ageMin.greaterThan=" + SMALLER_AGE_MIN);
    }


    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax equals to DEFAULT_AGE_MAX
        defaultCsDiagnosisShouldBeFound("ageMax.equals=" + DEFAULT_AGE_MAX);

        // Get all the csDiagnosisList where ageMax equals to UPDATED_AGE_MAX
        defaultCsDiagnosisShouldNotBeFound("ageMax.equals=" + UPDATED_AGE_MAX);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsNotEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax not equals to DEFAULT_AGE_MAX
        defaultCsDiagnosisShouldNotBeFound("ageMax.notEquals=" + DEFAULT_AGE_MAX);

        // Get all the csDiagnosisList where ageMax not equals to UPDATED_AGE_MAX
        defaultCsDiagnosisShouldBeFound("ageMax.notEquals=" + UPDATED_AGE_MAX);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsInShouldWork() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax in DEFAULT_AGE_MAX or UPDATED_AGE_MAX
        defaultCsDiagnosisShouldBeFound("ageMax.in=" + DEFAULT_AGE_MAX + "," + UPDATED_AGE_MAX);

        // Get all the csDiagnosisList where ageMax equals to UPDATED_AGE_MAX
        defaultCsDiagnosisShouldNotBeFound("ageMax.in=" + UPDATED_AGE_MAX);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax is not null
        defaultCsDiagnosisShouldBeFound("ageMax.specified=true");

        // Get all the csDiagnosisList where ageMax is null
        defaultCsDiagnosisShouldNotBeFound("ageMax.specified=false");
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax is greater than or equal to DEFAULT_AGE_MAX
        defaultCsDiagnosisShouldBeFound("ageMax.greaterThanOrEqual=" + DEFAULT_AGE_MAX);

        // Get all the csDiagnosisList where ageMax is greater than or equal to (DEFAULT_AGE_MAX + 1)
        defaultCsDiagnosisShouldNotBeFound("ageMax.greaterThanOrEqual=" + (DEFAULT_AGE_MAX + 1));
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax is less than or equal to DEFAULT_AGE_MAX
        defaultCsDiagnosisShouldBeFound("ageMax.lessThanOrEqual=" + DEFAULT_AGE_MAX);

        // Get all the csDiagnosisList where ageMax is less than or equal to SMALLER_AGE_MAX
        defaultCsDiagnosisShouldNotBeFound("ageMax.lessThanOrEqual=" + SMALLER_AGE_MAX);
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsLessThanSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax is less than DEFAULT_AGE_MAX
        defaultCsDiagnosisShouldNotBeFound("ageMax.lessThan=" + DEFAULT_AGE_MAX);

        // Get all the csDiagnosisList where ageMax is less than (DEFAULT_AGE_MAX + 1)
        defaultCsDiagnosisShouldBeFound("ageMax.lessThan=" + (DEFAULT_AGE_MAX + 1));
    }

    @Test
    @Transactional
    public void getAllCsDiagnosesByAgeMaxIsGreaterThanSomething() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        // Get all the csDiagnosisList where ageMax is greater than DEFAULT_AGE_MAX
        defaultCsDiagnosisShouldNotBeFound("ageMax.greaterThan=" + DEFAULT_AGE_MAX);

        // Get all the csDiagnosisList where ageMax is greater than SMALLER_AGE_MAX
        defaultCsDiagnosisShouldBeFound("ageMax.greaterThan=" + SMALLER_AGE_MAX);
    }


    @Test
    @Transactional
    public void getAllCsDiagnosesByValidityIsEqualToSomething() throws Exception {
        // Get already existing entity
        Validity validity = csDiagnosis.getValidity();
        csDiagnosisRepository.saveAndFlush(csDiagnosis);
        Long validityId = validity.getId();

        // Get all the csDiagnosisList where validity equals to validityId
        defaultCsDiagnosisShouldBeFound("validityId.equals=" + validityId);

        // Get all the csDiagnosisList where validity equals to validityId + 1
        defaultCsDiagnosisShouldNotBeFound("validityId.equals=" + (validityId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCsDiagnosisShouldBeFound(String filter) throws Exception {
        restCsDiagnosisMockMvc.perform(get("/api/cs-diagnoses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(csDiagnosis.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].report").value(hasItem(DEFAULT_REPORT.booleanValue())))
            .andExpect(jsonPath("$.[*].descr").value(hasItem(DEFAULT_DESCR)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].ageMin").value(hasItem(DEFAULT_AGE_MIN)))
            .andExpect(jsonPath("$.[*].ageMax").value(hasItem(DEFAULT_AGE_MAX)));

        // Check, that the count call also returns 1
        restCsDiagnosisMockMvc.perform(get("/api/cs-diagnoses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCsDiagnosisShouldNotBeFound(String filter) throws Exception {
        restCsDiagnosisMockMvc.perform(get("/api/cs-diagnoses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCsDiagnosisMockMvc.perform(get("/api/cs-diagnoses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCsDiagnosis() throws Exception {
        // Get the csDiagnosis
        restCsDiagnosisMockMvc.perform(get("/api/cs-diagnoses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCsDiagnosis() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        int databaseSizeBeforeUpdate = csDiagnosisRepository.findAll().size();

        // Update the csDiagnosis
        CsDiagnosis updatedCsDiagnosis = csDiagnosisRepository.findById(csDiagnosis.getId()).get();
        // Disconnect from session so that the updates on updatedCsDiagnosis are not directly saved in db
        em.detach(updatedCsDiagnosis);
        updatedCsDiagnosis
            .code(UPDATED_CODE)
            .report(UPDATED_REPORT)
            .descr(UPDATED_DESCR)
            .sex(UPDATED_SEX)
            .ageMin(UPDATED_AGE_MIN)
            .ageMax(UPDATED_AGE_MAX);
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(updatedCsDiagnosis);

        restCsDiagnosisMockMvc.perform(put("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isOk());

        // Validate the CsDiagnosis in the database
        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeUpdate);
        CsDiagnosis testCsDiagnosis = csDiagnosisList.get(csDiagnosisList.size() - 1);
        assertThat(testCsDiagnosis.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCsDiagnosis.isReport()).isEqualTo(UPDATED_REPORT);
        assertThat(testCsDiagnosis.getDescr()).isEqualTo(UPDATED_DESCR);
        assertThat(testCsDiagnosis.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testCsDiagnosis.getAgeMin()).isEqualTo(UPDATED_AGE_MIN);
        assertThat(testCsDiagnosis.getAgeMax()).isEqualTo(UPDATED_AGE_MAX);
    }

    @Test
    @Transactional
    public void updateNonExistingCsDiagnosis() throws Exception {
        int databaseSizeBeforeUpdate = csDiagnosisRepository.findAll().size();

        // Create the CsDiagnosis
        CsDiagnosisDTO csDiagnosisDTO = csDiagnosisMapper.toDto(csDiagnosis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCsDiagnosisMockMvc.perform(put("/api/cs-diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(csDiagnosisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CsDiagnosis in the database
        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCsDiagnosis() throws Exception {
        // Initialize the database
        csDiagnosisRepository.saveAndFlush(csDiagnosis);

        int databaseSizeBeforeDelete = csDiagnosisRepository.findAll().size();

        // Delete the csDiagnosis
        restCsDiagnosisMockMvc.perform(delete("/api/cs-diagnoses/{id}", csDiagnosis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CsDiagnosis> csDiagnosisList = csDiagnosisRepository.findAll();
        assertThat(csDiagnosisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
