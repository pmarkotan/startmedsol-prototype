package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.CodeSetLoad;
import hu.paninform.startmedsol.repository.CodeSetLoadRepository;

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

import hu.paninform.startmedsol.domain.enumeration.CodeSetType;
import hu.paninform.startmedsol.domain.enumeration.CodeSetStatus;
/**
 * Integration tests for the {@link CodeSetLoadResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CodeSetLoadResourceIT {

    private static final CodeSetType DEFAULT_TYPE = CodeSetType.BNO;
    private static final CodeSetType UPDATED_TYPE = CodeSetType.OENO;

    private static final String DEFAULT_LATEST_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_LATEST_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_LOG = "AAAAAAAAAA";
    private static final String UPDATED_LOG = "BBBBBBBBBB";

    private static final CodeSetStatus DEFAULT_STATUS = CodeSetStatus.SUCCESS;
    private static final CodeSetStatus UPDATED_STATUS = CodeSetStatus.ERROR;

    @Autowired
    private CodeSetLoadRepository codeSetLoadRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCodeSetLoadMockMvc;

    private CodeSetLoad codeSetLoad;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodeSetLoad createEntity(EntityManager em) {
        CodeSetLoad codeSetLoad = new CodeSetLoad()
            .type(DEFAULT_TYPE)
            .latestVersion(DEFAULT_LATEST_VERSION)
            .url(DEFAULT_URL)
            .log(DEFAULT_LOG)
            .status(DEFAULT_STATUS);
        return codeSetLoad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CodeSetLoad createUpdatedEntity(EntityManager em) {
        CodeSetLoad codeSetLoad = new CodeSetLoad()
            .type(UPDATED_TYPE)
            .latestVersion(UPDATED_LATEST_VERSION)
            .url(UPDATED_URL)
            .log(UPDATED_LOG)
            .status(UPDATED_STATUS);
        return codeSetLoad;
    }

    @BeforeEach
    public void initTest() {
        codeSetLoad = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodeSetLoad() throws Exception {
        int databaseSizeBeforeCreate = codeSetLoadRepository.findAll().size();
        // Create the CodeSetLoad
        restCodeSetLoadMockMvc.perform(post("/api/code-set-loads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeSetLoad)))
            .andExpect(status().isCreated());

        // Validate the CodeSetLoad in the database
        List<CodeSetLoad> codeSetLoadList = codeSetLoadRepository.findAll();
        assertThat(codeSetLoadList).hasSize(databaseSizeBeforeCreate + 1);
        CodeSetLoad testCodeSetLoad = codeSetLoadList.get(codeSetLoadList.size() - 1);
        assertThat(testCodeSetLoad.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCodeSetLoad.getLatestVersion()).isEqualTo(DEFAULT_LATEST_VERSION);
        assertThat(testCodeSetLoad.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testCodeSetLoad.getLog()).isEqualTo(DEFAULT_LOG);
        assertThat(testCodeSetLoad.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCodeSetLoadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codeSetLoadRepository.findAll().size();

        // Create the CodeSetLoad with an existing ID
        codeSetLoad.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodeSetLoadMockMvc.perform(post("/api/code-set-loads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeSetLoad)))
            .andExpect(status().isBadRequest());

        // Validate the CodeSetLoad in the database
        List<CodeSetLoad> codeSetLoadList = codeSetLoadRepository.findAll();
        assertThat(codeSetLoadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCodeSetLoads() throws Exception {
        // Initialize the database
        codeSetLoadRepository.saveAndFlush(codeSetLoad);

        // Get all the codeSetLoadList
        restCodeSetLoadMockMvc.perform(get("/api/code-set-loads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codeSetLoad.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].latestVersion").value(hasItem(DEFAULT_LATEST_VERSION)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].log").value(hasItem(DEFAULT_LOG)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCodeSetLoad() throws Exception {
        // Initialize the database
        codeSetLoadRepository.saveAndFlush(codeSetLoad);

        // Get the codeSetLoad
        restCodeSetLoadMockMvc.perform(get("/api/code-set-loads/{id}", codeSetLoad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(codeSetLoad.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.latestVersion").value(DEFAULT_LATEST_VERSION))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.log").value(DEFAULT_LOG))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCodeSetLoad() throws Exception {
        // Get the codeSetLoad
        restCodeSetLoadMockMvc.perform(get("/api/code-set-loads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodeSetLoad() throws Exception {
        // Initialize the database
        codeSetLoadRepository.saveAndFlush(codeSetLoad);

        int databaseSizeBeforeUpdate = codeSetLoadRepository.findAll().size();

        // Update the codeSetLoad
        CodeSetLoad updatedCodeSetLoad = codeSetLoadRepository.findById(codeSetLoad.getId()).get();
        // Disconnect from session so that the updates on updatedCodeSetLoad are not directly saved in db
        em.detach(updatedCodeSetLoad);
        updatedCodeSetLoad
            .type(UPDATED_TYPE)
            .latestVersion(UPDATED_LATEST_VERSION)
            .url(UPDATED_URL)
            .log(UPDATED_LOG)
            .status(UPDATED_STATUS);

        restCodeSetLoadMockMvc.perform(put("/api/code-set-loads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCodeSetLoad)))
            .andExpect(status().isOk());

        // Validate the CodeSetLoad in the database
        List<CodeSetLoad> codeSetLoadList = codeSetLoadRepository.findAll();
        assertThat(codeSetLoadList).hasSize(databaseSizeBeforeUpdate);
        CodeSetLoad testCodeSetLoad = codeSetLoadList.get(codeSetLoadList.size() - 1);
        assertThat(testCodeSetLoad.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCodeSetLoad.getLatestVersion()).isEqualTo(UPDATED_LATEST_VERSION);
        assertThat(testCodeSetLoad.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testCodeSetLoad.getLog()).isEqualTo(UPDATED_LOG);
        assertThat(testCodeSetLoad.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCodeSetLoad() throws Exception {
        int databaseSizeBeforeUpdate = codeSetLoadRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCodeSetLoadMockMvc.perform(put("/api/code-set-loads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(codeSetLoad)))
            .andExpect(status().isBadRequest());

        // Validate the CodeSetLoad in the database
        List<CodeSetLoad> codeSetLoadList = codeSetLoadRepository.findAll();
        assertThat(codeSetLoadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCodeSetLoad() throws Exception {
        // Initialize the database
        codeSetLoadRepository.saveAndFlush(codeSetLoad);

        int databaseSizeBeforeDelete = codeSetLoadRepository.findAll().size();

        // Delete the codeSetLoad
        restCodeSetLoadMockMvc.perform(delete("/api/code-set-loads/{id}", codeSetLoad.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CodeSetLoad> codeSetLoadList = codeSetLoadRepository.findAll();
        assertThat(codeSetLoadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
