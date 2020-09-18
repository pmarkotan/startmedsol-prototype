package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.DictionaryTranslation;
import hu.paninform.startmedsol.repository.DictionaryTranslationRepository;
import hu.paninform.startmedsol.service.DictionaryTranslationService;
import hu.paninform.startmedsol.service.dto.DictionaryTranslationDTO;
import hu.paninform.startmedsol.service.mapper.DictionaryTranslationMapper;

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

import hu.paninform.startmedsol.domain.enumeration.Language;
/**
 * Integration tests for the {@link DictionaryTranslationResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DictionaryTranslationResourceIT {

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.HU;
    private static final Language UPDATED_LANGUAGE = Language.EN;

    @Autowired
    private DictionaryTranslationRepository dictionaryTranslationRepository;

    @Autowired
    private DictionaryTranslationMapper dictionaryTranslationMapper;

    @Autowired
    private DictionaryTranslationService dictionaryTranslationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDictionaryTranslationMockMvc;

    private DictionaryTranslation dictionaryTranslation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DictionaryTranslation createEntity(EntityManager em) {
        DictionaryTranslation dictionaryTranslation = new DictionaryTranslation()
            .label(DEFAULT_LABEL)
            .language(DEFAULT_LANGUAGE);
        return dictionaryTranslation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DictionaryTranslation createUpdatedEntity(EntityManager em) {
        DictionaryTranslation dictionaryTranslation = new DictionaryTranslation()
            .label(UPDATED_LABEL)
            .language(UPDATED_LANGUAGE);
        return dictionaryTranslation;
    }

    @BeforeEach
    public void initTest() {
        dictionaryTranslation = createEntity(em);
    }

    @Test
    @Transactional
    public void createDictionaryTranslation() throws Exception {
        int databaseSizeBeforeCreate = dictionaryTranslationRepository.findAll().size();
        // Create the DictionaryTranslation
        DictionaryTranslationDTO dictionaryTranslationDTO = dictionaryTranslationMapper.toDto(dictionaryTranslation);
        restDictionaryTranslationMockMvc.perform(post("/api/dictionary-translations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryTranslationDTO)))
            .andExpect(status().isCreated());

        // Validate the DictionaryTranslation in the database
        List<DictionaryTranslation> dictionaryTranslationList = dictionaryTranslationRepository.findAll();
        assertThat(dictionaryTranslationList).hasSize(databaseSizeBeforeCreate + 1);
        DictionaryTranslation testDictionaryTranslation = dictionaryTranslationList.get(dictionaryTranslationList.size() - 1);
        assertThat(testDictionaryTranslation.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testDictionaryTranslation.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
    }

    @Test
    @Transactional
    public void createDictionaryTranslationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dictionaryTranslationRepository.findAll().size();

        // Create the DictionaryTranslation with an existing ID
        dictionaryTranslation.setId(1L);
        DictionaryTranslationDTO dictionaryTranslationDTO = dictionaryTranslationMapper.toDto(dictionaryTranslation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDictionaryTranslationMockMvc.perform(post("/api/dictionary-translations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryTranslationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DictionaryTranslation in the database
        List<DictionaryTranslation> dictionaryTranslationList = dictionaryTranslationRepository.findAll();
        assertThat(dictionaryTranslationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLabelIsRequired() throws Exception {
        int databaseSizeBeforeTest = dictionaryTranslationRepository.findAll().size();
        // set the field null
        dictionaryTranslation.setLabel(null);

        // Create the DictionaryTranslation, which fails.
        DictionaryTranslationDTO dictionaryTranslationDTO = dictionaryTranslationMapper.toDto(dictionaryTranslation);


        restDictionaryTranslationMockMvc.perform(post("/api/dictionary-translations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryTranslationDTO)))
            .andExpect(status().isBadRequest());

        List<DictionaryTranslation> dictionaryTranslationList = dictionaryTranslationRepository.findAll();
        assertThat(dictionaryTranslationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLanguageIsRequired() throws Exception {
        int databaseSizeBeforeTest = dictionaryTranslationRepository.findAll().size();
        // set the field null
        dictionaryTranslation.setLanguage(null);

        // Create the DictionaryTranslation, which fails.
        DictionaryTranslationDTO dictionaryTranslationDTO = dictionaryTranslationMapper.toDto(dictionaryTranslation);


        restDictionaryTranslationMockMvc.perform(post("/api/dictionary-translations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryTranslationDTO)))
            .andExpect(status().isBadRequest());

        List<DictionaryTranslation> dictionaryTranslationList = dictionaryTranslationRepository.findAll();
        assertThat(dictionaryTranslationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDictionaryTranslations() throws Exception {
        // Initialize the database
        dictionaryTranslationRepository.saveAndFlush(dictionaryTranslation);

        // Get all the dictionaryTranslationList
        restDictionaryTranslationMockMvc.perform(get("/api/dictionary-translations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dictionaryTranslation.getId().intValue())))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getDictionaryTranslation() throws Exception {
        // Initialize the database
        dictionaryTranslationRepository.saveAndFlush(dictionaryTranslation);

        // Get the dictionaryTranslation
        restDictionaryTranslationMockMvc.perform(get("/api/dictionary-translations/{id}", dictionaryTranslation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dictionaryTranslation.getId().intValue()))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDictionaryTranslation() throws Exception {
        // Get the dictionaryTranslation
        restDictionaryTranslationMockMvc.perform(get("/api/dictionary-translations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDictionaryTranslation() throws Exception {
        // Initialize the database
        dictionaryTranslationRepository.saveAndFlush(dictionaryTranslation);

        int databaseSizeBeforeUpdate = dictionaryTranslationRepository.findAll().size();

        // Update the dictionaryTranslation
        DictionaryTranslation updatedDictionaryTranslation = dictionaryTranslationRepository.findById(dictionaryTranslation.getId()).get();
        // Disconnect from session so that the updates on updatedDictionaryTranslation are not directly saved in db
        em.detach(updatedDictionaryTranslation);
        updatedDictionaryTranslation
            .label(UPDATED_LABEL)
            .language(UPDATED_LANGUAGE);
        DictionaryTranslationDTO dictionaryTranslationDTO = dictionaryTranslationMapper.toDto(updatedDictionaryTranslation);

        restDictionaryTranslationMockMvc.perform(put("/api/dictionary-translations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryTranslationDTO)))
            .andExpect(status().isOk());

        // Validate the DictionaryTranslation in the database
        List<DictionaryTranslation> dictionaryTranslationList = dictionaryTranslationRepository.findAll();
        assertThat(dictionaryTranslationList).hasSize(databaseSizeBeforeUpdate);
        DictionaryTranslation testDictionaryTranslation = dictionaryTranslationList.get(dictionaryTranslationList.size() - 1);
        assertThat(testDictionaryTranslation.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testDictionaryTranslation.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingDictionaryTranslation() throws Exception {
        int databaseSizeBeforeUpdate = dictionaryTranslationRepository.findAll().size();

        // Create the DictionaryTranslation
        DictionaryTranslationDTO dictionaryTranslationDTO = dictionaryTranslationMapper.toDto(dictionaryTranslation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDictionaryTranslationMockMvc.perform(put("/api/dictionary-translations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dictionaryTranslationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DictionaryTranslation in the database
        List<DictionaryTranslation> dictionaryTranslationList = dictionaryTranslationRepository.findAll();
        assertThat(dictionaryTranslationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDictionaryTranslation() throws Exception {
        // Initialize the database
        dictionaryTranslationRepository.saveAndFlush(dictionaryTranslation);

        int databaseSizeBeforeDelete = dictionaryTranslationRepository.findAll().size();

        // Delete the dictionaryTranslation
        restDictionaryTranslationMockMvc.perform(delete("/api/dictionary-translations/{id}", dictionaryTranslation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DictionaryTranslation> dictionaryTranslationList = dictionaryTranslationRepository.findAll();
        assertThat(dictionaryTranslationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
