package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.CaseTextDocumentation;
import hu.paninform.startmedsol.repository.CaseTextDocumentationRepository;
import hu.paninform.startmedsol.service.CaseTextDocumentationService;
import hu.paninform.startmedsol.service.dto.CaseTextDocumentationDTO;
import hu.paninform.startmedsol.service.mapper.CaseTextDocumentationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.paninform.startmedsol.domain.enumeration.PatientDocumentationType;
/**
 * Integration tests for the {@link CaseTextDocumentationResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CaseTextDocumentationResourceIT {

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final PatientDocumentationType DEFAULT_TYPE = PatientDocumentationType.ACTUAL_COMPLAINTS;
    private static final PatientDocumentationType UPDATED_TYPE = PatientDocumentationType.ANAMNESIS;

    @Autowired
    private CaseTextDocumentationRepository caseTextDocumentationRepository;

    @Autowired
    private CaseTextDocumentationMapper caseTextDocumentationMapper;

    @Autowired
    private CaseTextDocumentationService caseTextDocumentationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCaseTextDocumentationMockMvc;

    private CaseTextDocumentation caseTextDocumentation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseTextDocumentation createEntity(EntityManager em) {
        CaseTextDocumentation caseTextDocumentation = new CaseTextDocumentation()
            .text(DEFAULT_TEXT)
            .type(DEFAULT_TYPE);
        return caseTextDocumentation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseTextDocumentation createUpdatedEntity(EntityManager em) {
        CaseTextDocumentation caseTextDocumentation = new CaseTextDocumentation()
            .text(UPDATED_TEXT)
            .type(UPDATED_TYPE);
        return caseTextDocumentation;
    }

    @BeforeEach
    public void initTest() {
        caseTextDocumentation = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseTextDocumentation() throws Exception {
        int databaseSizeBeforeCreate = caseTextDocumentationRepository.findAll().size();
        // Create the CaseTextDocumentation
        CaseTextDocumentationDTO caseTextDocumentationDTO = caseTextDocumentationMapper.toDto(caseTextDocumentation);
        restCaseTextDocumentationMockMvc.perform(post("/api/case-text-documentations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseTextDocumentationDTO)))
            .andExpect(status().isCreated());

        // Validate the CaseTextDocumentation in the database
        List<CaseTextDocumentation> caseTextDocumentationList = caseTextDocumentationRepository.findAll();
        assertThat(caseTextDocumentationList).hasSize(databaseSizeBeforeCreate + 1);
        CaseTextDocumentation testCaseTextDocumentation = caseTextDocumentationList.get(caseTextDocumentationList.size() - 1);
        assertThat(testCaseTextDocumentation.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testCaseTextDocumentation.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createCaseTextDocumentationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseTextDocumentationRepository.findAll().size();

        // Create the CaseTextDocumentation with an existing ID
        caseTextDocumentation.setId(1L);
        CaseTextDocumentationDTO caseTextDocumentationDTO = caseTextDocumentationMapper.toDto(caseTextDocumentation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseTextDocumentationMockMvc.perform(post("/api/case-text-documentations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseTextDocumentationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseTextDocumentation in the database
        List<CaseTextDocumentation> caseTextDocumentationList = caseTextDocumentationRepository.findAll();
        assertThat(caseTextDocumentationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = caseTextDocumentationRepository.findAll().size();
        // set the field null
        caseTextDocumentation.setType(null);

        // Create the CaseTextDocumentation, which fails.
        CaseTextDocumentationDTO caseTextDocumentationDTO = caseTextDocumentationMapper.toDto(caseTextDocumentation);


        restCaseTextDocumentationMockMvc.perform(post("/api/case-text-documentations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseTextDocumentationDTO)))
            .andExpect(status().isBadRequest());

        List<CaseTextDocumentation> caseTextDocumentationList = caseTextDocumentationRepository.findAll();
        assertThat(caseTextDocumentationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCaseTextDocumentations() throws Exception {
        // Initialize the database
        caseTextDocumentationRepository.saveAndFlush(caseTextDocumentation);

        // Get all the caseTextDocumentationList
        restCaseTextDocumentationMockMvc.perform(get("/api/case-text-documentations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseTextDocumentation.getId().intValue())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getCaseTextDocumentation() throws Exception {
        // Initialize the database
        caseTextDocumentationRepository.saveAndFlush(caseTextDocumentation);

        // Get the caseTextDocumentation
        restCaseTextDocumentationMockMvc.perform(get("/api/case-text-documentations/{id}", caseTextDocumentation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caseTextDocumentation.getId().intValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCaseTextDocumentation() throws Exception {
        // Get the caseTextDocumentation
        restCaseTextDocumentationMockMvc.perform(get("/api/case-text-documentations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseTextDocumentation() throws Exception {
        // Initialize the database
        caseTextDocumentationRepository.saveAndFlush(caseTextDocumentation);

        int databaseSizeBeforeUpdate = caseTextDocumentationRepository.findAll().size();

        // Update the caseTextDocumentation
        CaseTextDocumentation updatedCaseTextDocumentation = caseTextDocumentationRepository.findById(caseTextDocumentation.getId()).get();
        // Disconnect from session so that the updates on updatedCaseTextDocumentation are not directly saved in db
        em.detach(updatedCaseTextDocumentation);
        updatedCaseTextDocumentation
            .text(UPDATED_TEXT)
            .type(UPDATED_TYPE);
        CaseTextDocumentationDTO caseTextDocumentationDTO = caseTextDocumentationMapper.toDto(updatedCaseTextDocumentation);

        restCaseTextDocumentationMockMvc.perform(put("/api/case-text-documentations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseTextDocumentationDTO)))
            .andExpect(status().isOk());

        // Validate the CaseTextDocumentation in the database
        List<CaseTextDocumentation> caseTextDocumentationList = caseTextDocumentationRepository.findAll();
        assertThat(caseTextDocumentationList).hasSize(databaseSizeBeforeUpdate);
        CaseTextDocumentation testCaseTextDocumentation = caseTextDocumentationList.get(caseTextDocumentationList.size() - 1);
        assertThat(testCaseTextDocumentation.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testCaseTextDocumentation.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseTextDocumentation() throws Exception {
        int databaseSizeBeforeUpdate = caseTextDocumentationRepository.findAll().size();

        // Create the CaseTextDocumentation
        CaseTextDocumentationDTO caseTextDocumentationDTO = caseTextDocumentationMapper.toDto(caseTextDocumentation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseTextDocumentationMockMvc.perform(put("/api/case-text-documentations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseTextDocumentationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CaseTextDocumentation in the database
        List<CaseTextDocumentation> caseTextDocumentationList = caseTextDocumentationRepository.findAll();
        assertThat(caseTextDocumentationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseTextDocumentation() throws Exception {
        // Initialize the database
        caseTextDocumentationRepository.saveAndFlush(caseTextDocumentation);

        int databaseSizeBeforeDelete = caseTextDocumentationRepository.findAll().size();

        // Delete the caseTextDocumentation
        restCaseTextDocumentationMockMvc.perform(delete("/api/case-text-documentations/{id}", caseTextDocumentation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CaseTextDocumentation> caseTextDocumentationList = caseTextDocumentationRepository.findAll();
        assertThat(caseTextDocumentationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
