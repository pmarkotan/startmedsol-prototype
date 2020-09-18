package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.DynamicForm;
import hu.paninform.startmedsol.domain.Provider;
import hu.paninform.startmedsol.repository.DynamicFormRepository;
import hu.paninform.startmedsol.service.DynamicFormService;
import hu.paninform.startmedsol.service.dto.DynamicFormDTO;
import hu.paninform.startmedsol.service.mapper.DynamicFormMapper;

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

/**
 * Integration tests for the {@link DynamicFormResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DynamicFormResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION_CODE = "AAAA";
    private static final String UPDATED_PROFESSION_CODE = "BBBB";

    private static final String DEFAULT_PROFESSION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FORM_TEMPLATE = "AAAAAAAAAA";
    private static final String UPDATED_FORM_TEMPLATE = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT_TEMPLATE = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TEMPLATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEPARATELY_PRINT = false;
    private static final Boolean UPDATED_SEPARATELY_PRINT = true;

    @Autowired
    private DynamicFormRepository dynamicFormRepository;

    @Autowired
    private DynamicFormMapper dynamicFormMapper;

    @Autowired
    private DynamicFormService dynamicFormService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDynamicFormMockMvc;

    private DynamicForm dynamicForm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DynamicForm createEntity(EntityManager em) {
        DynamicForm dynamicForm = new DynamicForm()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .professionCode(DEFAULT_PROFESSION_CODE)
            .professionName(DEFAULT_PROFESSION_NAME)
            .formTemplate(DEFAULT_FORM_TEMPLATE)
            .reportTemplate(DEFAULT_REPORT_TEMPLATE)
            .separatelyPrint(DEFAULT_SEPARATELY_PRINT);
        // Add required entity
        Provider provider;
        if (TestUtil.findAll(em, Provider.class).isEmpty()) {
            provider = ProviderResourceIT.createEntity(em);
            em.persist(provider);
            em.flush();
        } else {
            provider = TestUtil.findAll(em, Provider.class).get(0);
        }
        dynamicForm.setProvider(provider);
        return dynamicForm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DynamicForm createUpdatedEntity(EntityManager em) {
        DynamicForm dynamicForm = new DynamicForm()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .professionCode(UPDATED_PROFESSION_CODE)
            .professionName(UPDATED_PROFESSION_NAME)
            .formTemplate(UPDATED_FORM_TEMPLATE)
            .reportTemplate(UPDATED_REPORT_TEMPLATE)
            .separatelyPrint(UPDATED_SEPARATELY_PRINT);
        // Add required entity
        Provider provider;
        if (TestUtil.findAll(em, Provider.class).isEmpty()) {
            provider = ProviderResourceIT.createUpdatedEntity(em);
            em.persist(provider);
            em.flush();
        } else {
            provider = TestUtil.findAll(em, Provider.class).get(0);
        }
        dynamicForm.setProvider(provider);
        return dynamicForm;
    }

    @BeforeEach
    public void initTest() {
        dynamicForm = createEntity(em);
    }

    @Test
    @Transactional
    public void createDynamicForm() throws Exception {
        int databaseSizeBeforeCreate = dynamicFormRepository.findAll().size();
        // Create the DynamicForm
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(dynamicForm);
        restDynamicFormMockMvc.perform(post("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isCreated());

        // Validate the DynamicForm in the database
        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeCreate + 1);
        DynamicForm testDynamicForm = dynamicFormList.get(dynamicFormList.size() - 1);
        assertThat(testDynamicForm.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDynamicForm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDynamicForm.getProfessionCode()).isEqualTo(DEFAULT_PROFESSION_CODE);
        assertThat(testDynamicForm.getProfessionName()).isEqualTo(DEFAULT_PROFESSION_NAME);
        assertThat(testDynamicForm.getFormTemplate()).isEqualTo(DEFAULT_FORM_TEMPLATE);
        assertThat(testDynamicForm.getReportTemplate()).isEqualTo(DEFAULT_REPORT_TEMPLATE);
        assertThat(testDynamicForm.isSeparatelyPrint()).isEqualTo(DEFAULT_SEPARATELY_PRINT);
    }

    @Test
    @Transactional
    public void createDynamicFormWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dynamicFormRepository.findAll().size();

        // Create the DynamicForm with an existing ID
        dynamicForm.setId(1L);
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(dynamicForm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDynamicFormMockMvc.perform(post("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DynamicForm in the database
        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dynamicFormRepository.findAll().size();
        // set the field null
        dynamicForm.setCode(null);

        // Create the DynamicForm, which fails.
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(dynamicForm);


        restDynamicFormMockMvc.perform(post("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isBadRequest());

        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dynamicFormRepository.findAll().size();
        // set the field null
        dynamicForm.setName(null);

        // Create the DynamicForm, which fails.
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(dynamicForm);


        restDynamicFormMockMvc.perform(post("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isBadRequest());

        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProfessionCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dynamicFormRepository.findAll().size();
        // set the field null
        dynamicForm.setProfessionCode(null);

        // Create the DynamicForm, which fails.
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(dynamicForm);


        restDynamicFormMockMvc.perform(post("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isBadRequest());

        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProfessionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dynamicFormRepository.findAll().size();
        // set the field null
        dynamicForm.setProfessionName(null);

        // Create the DynamicForm, which fails.
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(dynamicForm);


        restDynamicFormMockMvc.perform(post("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isBadRequest());

        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDynamicForms() throws Exception {
        // Initialize the database
        dynamicFormRepository.saveAndFlush(dynamicForm);

        // Get all the dynamicFormList
        restDynamicFormMockMvc.perform(get("/api/dynamic-forms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dynamicForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].professionCode").value(hasItem(DEFAULT_PROFESSION_CODE)))
            .andExpect(jsonPath("$.[*].professionName").value(hasItem(DEFAULT_PROFESSION_NAME)))
            .andExpect(jsonPath("$.[*].formTemplate").value(hasItem(DEFAULT_FORM_TEMPLATE.toString())))
            .andExpect(jsonPath("$.[*].reportTemplate").value(hasItem(DEFAULT_REPORT_TEMPLATE.toString())))
            .andExpect(jsonPath("$.[*].separatelyPrint").value(hasItem(DEFAULT_SEPARATELY_PRINT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDynamicForm() throws Exception {
        // Initialize the database
        dynamicFormRepository.saveAndFlush(dynamicForm);

        // Get the dynamicForm
        restDynamicFormMockMvc.perform(get("/api/dynamic-forms/{id}", dynamicForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dynamicForm.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.professionCode").value(DEFAULT_PROFESSION_CODE))
            .andExpect(jsonPath("$.professionName").value(DEFAULT_PROFESSION_NAME))
            .andExpect(jsonPath("$.formTemplate").value(DEFAULT_FORM_TEMPLATE.toString()))
            .andExpect(jsonPath("$.reportTemplate").value(DEFAULT_REPORT_TEMPLATE.toString()))
            .andExpect(jsonPath("$.separatelyPrint").value(DEFAULT_SEPARATELY_PRINT.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDynamicForm() throws Exception {
        // Get the dynamicForm
        restDynamicFormMockMvc.perform(get("/api/dynamic-forms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDynamicForm() throws Exception {
        // Initialize the database
        dynamicFormRepository.saveAndFlush(dynamicForm);

        int databaseSizeBeforeUpdate = dynamicFormRepository.findAll().size();

        // Update the dynamicForm
        DynamicForm updatedDynamicForm = dynamicFormRepository.findById(dynamicForm.getId()).get();
        // Disconnect from session so that the updates on updatedDynamicForm are not directly saved in db
        em.detach(updatedDynamicForm);
        updatedDynamicForm
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .professionCode(UPDATED_PROFESSION_CODE)
            .professionName(UPDATED_PROFESSION_NAME)
            .formTemplate(UPDATED_FORM_TEMPLATE)
            .reportTemplate(UPDATED_REPORT_TEMPLATE)
            .separatelyPrint(UPDATED_SEPARATELY_PRINT);
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(updatedDynamicForm);

        restDynamicFormMockMvc.perform(put("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isOk());

        // Validate the DynamicForm in the database
        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeUpdate);
        DynamicForm testDynamicForm = dynamicFormList.get(dynamicFormList.size() - 1);
        assertThat(testDynamicForm.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDynamicForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDynamicForm.getProfessionCode()).isEqualTo(UPDATED_PROFESSION_CODE);
        assertThat(testDynamicForm.getProfessionName()).isEqualTo(UPDATED_PROFESSION_NAME);
        assertThat(testDynamicForm.getFormTemplate()).isEqualTo(UPDATED_FORM_TEMPLATE);
        assertThat(testDynamicForm.getReportTemplate()).isEqualTo(UPDATED_REPORT_TEMPLATE);
        assertThat(testDynamicForm.isSeparatelyPrint()).isEqualTo(UPDATED_SEPARATELY_PRINT);
    }

    @Test
    @Transactional
    public void updateNonExistingDynamicForm() throws Exception {
        int databaseSizeBeforeUpdate = dynamicFormRepository.findAll().size();

        // Create the DynamicForm
        DynamicFormDTO dynamicFormDTO = dynamicFormMapper.toDto(dynamicForm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDynamicFormMockMvc.perform(put("/api/dynamic-forms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dynamicFormDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DynamicForm in the database
        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDynamicForm() throws Exception {
        // Initialize the database
        dynamicFormRepository.saveAndFlush(dynamicForm);

        int databaseSizeBeforeDelete = dynamicFormRepository.findAll().size();

        // Delete the dynamicForm
        restDynamicFormMockMvc.perform(delete("/api/dynamic-forms/{id}", dynamicForm.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DynamicForm> dynamicFormList = dynamicFormRepository.findAll();
        assertThat(dynamicFormList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
