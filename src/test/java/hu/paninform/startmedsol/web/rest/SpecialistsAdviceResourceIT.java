package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.SpecialistsAdvice;
import hu.paninform.startmedsol.domain.MedicalCase;
import hu.paninform.startmedsol.domain.CsDiagnosis;
import hu.paninform.startmedsol.repository.SpecialistsAdviceRepository;
import hu.paninform.startmedsol.service.SpecialistsAdviceService;
import hu.paninform.startmedsol.service.dto.SpecialistsAdviceDTO;
import hu.paninform.startmedsol.service.mapper.SpecialistsAdviceMapper;

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
 * Integration tests for the {@link SpecialistsAdviceResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SpecialistsAdviceResourceIT {

    private static final String DEFAULT_PERIOD_OF_TIME = "AAAAAAAAAA";
    private static final String UPDATED_PERIOD_OF_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RAISED_INDICATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RAISED_INDICATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RAISED_SUBSIDY_PERCENT = "AAAAAAAAAA";
    private static final String UPDATED_RAISED_SUBSIDY_PERCENT = "BBBBBBBBBB";

    private static final String DEFAULT_EMPHASIZED_INDICATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EMPHASIZED_INDICATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_SUBSTANCE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_SUBSTANCE = "BBBBBBBBBB";

    private static final String DEFAULT_EFFICACY = "AAAAAAAAAA";
    private static final String UPDATED_EFFICACY = "BBBBBBBBBB";

    private static final String DEFAULT_DOSAGE_MOD = "AAAAAAAAAA";
    private static final String UPDATED_DOSAGE_MOD = "BBBBBBBBBB";

    private static final String DEFAULT_DOSAGE = "AAAAAAAAAA";
    private static final String UPDATED_DOSAGE = "BBBBBBBBBB";

    @Autowired
    private SpecialistsAdviceRepository specialistsAdviceRepository;

    @Autowired
    private SpecialistsAdviceMapper specialistsAdviceMapper;

    @Autowired
    private SpecialistsAdviceService specialistsAdviceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpecialistsAdviceMockMvc;

    private SpecialistsAdvice specialistsAdvice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SpecialistsAdvice createEntity(EntityManager em) {
        SpecialistsAdvice specialistsAdvice = new SpecialistsAdvice()
            .periodOfTime(DEFAULT_PERIOD_OF_TIME)
            .raisedIndicationCode(DEFAULT_RAISED_INDICATION_CODE)
            .raisedSubsidyPercent(DEFAULT_RAISED_SUBSIDY_PERCENT)
            .emphasizedIndicationCode(DEFAULT_EMPHASIZED_INDICATION_CODE)
            .activeSubstance(DEFAULT_ACTIVE_SUBSTANCE)
            .efficacy(DEFAULT_EFFICACY)
            .dosageMod(DEFAULT_DOSAGE_MOD)
            .dosage(DEFAULT_DOSAGE);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        specialistsAdvice.setMedicalCase(medicalCase);
        // Add required entity
        CsDiagnosis csDiagnosis;
        if (TestUtil.findAll(em, CsDiagnosis.class).isEmpty()) {
            csDiagnosis = CsDiagnosisResourceIT.createEntity(em);
            em.persist(csDiagnosis);
            em.flush();
        } else {
            csDiagnosis = TestUtil.findAll(em, CsDiagnosis.class).get(0);
        }
        specialistsAdvice.setDiagnosis(csDiagnosis);
        return specialistsAdvice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SpecialistsAdvice createUpdatedEntity(EntityManager em) {
        SpecialistsAdvice specialistsAdvice = new SpecialistsAdvice()
            .periodOfTime(UPDATED_PERIOD_OF_TIME)
            .raisedIndicationCode(UPDATED_RAISED_INDICATION_CODE)
            .raisedSubsidyPercent(UPDATED_RAISED_SUBSIDY_PERCENT)
            .emphasizedIndicationCode(UPDATED_EMPHASIZED_INDICATION_CODE)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .efficacy(UPDATED_EFFICACY)
            .dosageMod(UPDATED_DOSAGE_MOD)
            .dosage(UPDATED_DOSAGE);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createUpdatedEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        specialistsAdvice.setMedicalCase(medicalCase);
        // Add required entity
        CsDiagnosis csDiagnosis;
        if (TestUtil.findAll(em, CsDiagnosis.class).isEmpty()) {
            csDiagnosis = CsDiagnosisResourceIT.createUpdatedEntity(em);
            em.persist(csDiagnosis);
            em.flush();
        } else {
            csDiagnosis = TestUtil.findAll(em, CsDiagnosis.class).get(0);
        }
        specialistsAdvice.setDiagnosis(csDiagnosis);
        return specialistsAdvice;
    }

    @BeforeEach
    public void initTest() {
        specialistsAdvice = createEntity(em);
    }

    @Test
    @Transactional
    public void createSpecialistsAdvice() throws Exception {
        int databaseSizeBeforeCreate = specialistsAdviceRepository.findAll().size();
        // Create the SpecialistsAdvice
        SpecialistsAdviceDTO specialistsAdviceDTO = specialistsAdviceMapper.toDto(specialistsAdvice);
        restSpecialistsAdviceMockMvc.perform(post("/api/specialists-advices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistsAdviceDTO)))
            .andExpect(status().isCreated());

        // Validate the SpecialistsAdvice in the database
        List<SpecialistsAdvice> specialistsAdviceList = specialistsAdviceRepository.findAll();
        assertThat(specialistsAdviceList).hasSize(databaseSizeBeforeCreate + 1);
        SpecialistsAdvice testSpecialistsAdvice = specialistsAdviceList.get(specialistsAdviceList.size() - 1);
        assertThat(testSpecialistsAdvice.getPeriodOfTime()).isEqualTo(DEFAULT_PERIOD_OF_TIME);
        assertThat(testSpecialistsAdvice.getRaisedIndicationCode()).isEqualTo(DEFAULT_RAISED_INDICATION_CODE);
        assertThat(testSpecialistsAdvice.getRaisedSubsidyPercent()).isEqualTo(DEFAULT_RAISED_SUBSIDY_PERCENT);
        assertThat(testSpecialistsAdvice.getEmphasizedIndicationCode()).isEqualTo(DEFAULT_EMPHASIZED_INDICATION_CODE);
        assertThat(testSpecialistsAdvice.getActiveSubstance()).isEqualTo(DEFAULT_ACTIVE_SUBSTANCE);
        assertThat(testSpecialistsAdvice.getEfficacy()).isEqualTo(DEFAULT_EFFICACY);
        assertThat(testSpecialistsAdvice.getDosageMod()).isEqualTo(DEFAULT_DOSAGE_MOD);
        assertThat(testSpecialistsAdvice.getDosage()).isEqualTo(DEFAULT_DOSAGE);
    }

    @Test
    @Transactional
    public void createSpecialistsAdviceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = specialistsAdviceRepository.findAll().size();

        // Create the SpecialistsAdvice with an existing ID
        specialistsAdvice.setId(1L);
        SpecialistsAdviceDTO specialistsAdviceDTO = specialistsAdviceMapper.toDto(specialistsAdvice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpecialistsAdviceMockMvc.perform(post("/api/specialists-advices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistsAdviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SpecialistsAdvice in the database
        List<SpecialistsAdvice> specialistsAdviceList = specialistsAdviceRepository.findAll();
        assertThat(specialistsAdviceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSpecialistsAdvices() throws Exception {
        // Initialize the database
        specialistsAdviceRepository.saveAndFlush(specialistsAdvice);

        // Get all the specialistsAdviceList
        restSpecialistsAdviceMockMvc.perform(get("/api/specialists-advices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(specialistsAdvice.getId().intValue())))
            .andExpect(jsonPath("$.[*].periodOfTime").value(hasItem(DEFAULT_PERIOD_OF_TIME)))
            .andExpect(jsonPath("$.[*].raisedIndicationCode").value(hasItem(DEFAULT_RAISED_INDICATION_CODE)))
            .andExpect(jsonPath("$.[*].raisedSubsidyPercent").value(hasItem(DEFAULT_RAISED_SUBSIDY_PERCENT)))
            .andExpect(jsonPath("$.[*].emphasizedIndicationCode").value(hasItem(DEFAULT_EMPHASIZED_INDICATION_CODE)))
            .andExpect(jsonPath("$.[*].activeSubstance").value(hasItem(DEFAULT_ACTIVE_SUBSTANCE)))
            .andExpect(jsonPath("$.[*].efficacy").value(hasItem(DEFAULT_EFFICACY)))
            .andExpect(jsonPath("$.[*].dosageMod").value(hasItem(DEFAULT_DOSAGE_MOD)))
            .andExpect(jsonPath("$.[*].dosage").value(hasItem(DEFAULT_DOSAGE)));
    }
    
    @Test
    @Transactional
    public void getSpecialistsAdvice() throws Exception {
        // Initialize the database
        specialistsAdviceRepository.saveAndFlush(specialistsAdvice);

        // Get the specialistsAdvice
        restSpecialistsAdviceMockMvc.perform(get("/api/specialists-advices/{id}", specialistsAdvice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(specialistsAdvice.getId().intValue()))
            .andExpect(jsonPath("$.periodOfTime").value(DEFAULT_PERIOD_OF_TIME))
            .andExpect(jsonPath("$.raisedIndicationCode").value(DEFAULT_RAISED_INDICATION_CODE))
            .andExpect(jsonPath("$.raisedSubsidyPercent").value(DEFAULT_RAISED_SUBSIDY_PERCENT))
            .andExpect(jsonPath("$.emphasizedIndicationCode").value(DEFAULT_EMPHASIZED_INDICATION_CODE))
            .andExpect(jsonPath("$.activeSubstance").value(DEFAULT_ACTIVE_SUBSTANCE))
            .andExpect(jsonPath("$.efficacy").value(DEFAULT_EFFICACY))
            .andExpect(jsonPath("$.dosageMod").value(DEFAULT_DOSAGE_MOD))
            .andExpect(jsonPath("$.dosage").value(DEFAULT_DOSAGE));
    }
    @Test
    @Transactional
    public void getNonExistingSpecialistsAdvice() throws Exception {
        // Get the specialistsAdvice
        restSpecialistsAdviceMockMvc.perform(get("/api/specialists-advices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSpecialistsAdvice() throws Exception {
        // Initialize the database
        specialistsAdviceRepository.saveAndFlush(specialistsAdvice);

        int databaseSizeBeforeUpdate = specialistsAdviceRepository.findAll().size();

        // Update the specialistsAdvice
        SpecialistsAdvice updatedSpecialistsAdvice = specialistsAdviceRepository.findById(specialistsAdvice.getId()).get();
        // Disconnect from session so that the updates on updatedSpecialistsAdvice are not directly saved in db
        em.detach(updatedSpecialistsAdvice);
        updatedSpecialistsAdvice
            .periodOfTime(UPDATED_PERIOD_OF_TIME)
            .raisedIndicationCode(UPDATED_RAISED_INDICATION_CODE)
            .raisedSubsidyPercent(UPDATED_RAISED_SUBSIDY_PERCENT)
            .emphasizedIndicationCode(UPDATED_EMPHASIZED_INDICATION_CODE)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .efficacy(UPDATED_EFFICACY)
            .dosageMod(UPDATED_DOSAGE_MOD)
            .dosage(UPDATED_DOSAGE);
        SpecialistsAdviceDTO specialistsAdviceDTO = specialistsAdviceMapper.toDto(updatedSpecialistsAdvice);

        restSpecialistsAdviceMockMvc.perform(put("/api/specialists-advices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistsAdviceDTO)))
            .andExpect(status().isOk());

        // Validate the SpecialistsAdvice in the database
        List<SpecialistsAdvice> specialistsAdviceList = specialistsAdviceRepository.findAll();
        assertThat(specialistsAdviceList).hasSize(databaseSizeBeforeUpdate);
        SpecialistsAdvice testSpecialistsAdvice = specialistsAdviceList.get(specialistsAdviceList.size() - 1);
        assertThat(testSpecialistsAdvice.getPeriodOfTime()).isEqualTo(UPDATED_PERIOD_OF_TIME);
        assertThat(testSpecialistsAdvice.getRaisedIndicationCode()).isEqualTo(UPDATED_RAISED_INDICATION_CODE);
        assertThat(testSpecialistsAdvice.getRaisedSubsidyPercent()).isEqualTo(UPDATED_RAISED_SUBSIDY_PERCENT);
        assertThat(testSpecialistsAdvice.getEmphasizedIndicationCode()).isEqualTo(UPDATED_EMPHASIZED_INDICATION_CODE);
        assertThat(testSpecialistsAdvice.getActiveSubstance()).isEqualTo(UPDATED_ACTIVE_SUBSTANCE);
        assertThat(testSpecialistsAdvice.getEfficacy()).isEqualTo(UPDATED_EFFICACY);
        assertThat(testSpecialistsAdvice.getDosageMod()).isEqualTo(UPDATED_DOSAGE_MOD);
        assertThat(testSpecialistsAdvice.getDosage()).isEqualTo(UPDATED_DOSAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingSpecialistsAdvice() throws Exception {
        int databaseSizeBeforeUpdate = specialistsAdviceRepository.findAll().size();

        // Create the SpecialistsAdvice
        SpecialistsAdviceDTO specialistsAdviceDTO = specialistsAdviceMapper.toDto(specialistsAdvice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpecialistsAdviceMockMvc.perform(put("/api/specialists-advices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(specialistsAdviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SpecialistsAdvice in the database
        List<SpecialistsAdvice> specialistsAdviceList = specialistsAdviceRepository.findAll();
        assertThat(specialistsAdviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSpecialistsAdvice() throws Exception {
        // Initialize the database
        specialistsAdviceRepository.saveAndFlush(specialistsAdvice);

        int databaseSizeBeforeDelete = specialistsAdviceRepository.findAll().size();

        // Delete the specialistsAdvice
        restSpecialistsAdviceMockMvc.perform(delete("/api/specialists-advices/{id}", specialistsAdvice.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SpecialistsAdvice> specialistsAdviceList = specialistsAdviceRepository.findAll();
        assertThat(specialistsAdviceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
