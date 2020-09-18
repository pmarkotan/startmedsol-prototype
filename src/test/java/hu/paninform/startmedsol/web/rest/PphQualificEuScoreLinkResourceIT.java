package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphQualificEuScoreLink;
import hu.paninform.startmedsol.repository.PphQualificEuScoreLinkRepository;

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

import hu.paninform.startmedsol.domain.enumeration.InstitutionCategory;
import hu.paninform.startmedsol.domain.enumeration.PrescriptionRight;
/**
 * Integration tests for the {@link PphQualificEuScoreLinkResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphQualificEuScoreLinkResourceIT {

    private static final Integer DEFAULT_TIME_LIMIT = 1;
    private static final Integer UPDATED_TIME_LIMIT = 2;

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    private static final InstitutionCategory DEFAULT_INSTITUTION_CATEGORY = InstitutionCategory.WITHOUT_LIMITATION;
    private static final InstitutionCategory UPDATED_INSTITUTION_CATEGORY = InstitutionCategory.INPATIENT_MEDICAL_UNIT;

    private static final PrescriptionRight DEFAULT_PRESCRIPTION_RIGHT = PrescriptionRight.PRESCRIBE;
    private static final PrescriptionRight UPDATED_PRESCRIPTION_RIGHT = PrescriptionRight.PROPOSE;

    @Autowired
    private PphQualificEuScoreLinkRepository pphQualificEuScoreLinkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphQualificEuScoreLinkMockMvc;

    private PphQualificEuScoreLink pphQualificEuScoreLink;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphQualificEuScoreLink createEntity(EntityManager em) {
        PphQualificEuScoreLink pphQualificEuScoreLink = new PphQualificEuScoreLink()
            .timeLimit(DEFAULT_TIME_LIMIT)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA)
            .institutionCategory(DEFAULT_INSTITUTION_CATEGORY)
            .prescriptionRight(DEFAULT_PRESCRIPTION_RIGHT);
        return pphQualificEuScoreLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphQualificEuScoreLink createUpdatedEntity(EntityManager em) {
        PphQualificEuScoreLink pphQualificEuScoreLink = new PphQualificEuScoreLink()
            .timeLimit(UPDATED_TIME_LIMIT)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA)
            .institutionCategory(UPDATED_INSTITUTION_CATEGORY)
            .prescriptionRight(UPDATED_PRESCRIPTION_RIGHT);
        return pphQualificEuScoreLink;
    }

    @BeforeEach
    public void initTest() {
        pphQualificEuScoreLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphQualificEuScoreLink() throws Exception {
        int databaseSizeBeforeCreate = pphQualificEuScoreLinkRepository.findAll().size();
        // Create the PphQualificEuScoreLink
        restPphQualificEuScoreLinkMockMvc.perform(post("/api/pph-qualific-eu-score-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificEuScoreLink)))
            .andExpect(status().isCreated());

        // Validate the PphQualificEuScoreLink in the database
        List<PphQualificEuScoreLink> pphQualificEuScoreLinkList = pphQualificEuScoreLinkRepository.findAll();
        assertThat(pphQualificEuScoreLinkList).hasSize(databaseSizeBeforeCreate + 1);
        PphQualificEuScoreLink testPphQualificEuScoreLink = pphQualificEuScoreLinkList.get(pphQualificEuScoreLinkList.size() - 1);
        assertThat(testPphQualificEuScoreLink.getTimeLimit()).isEqualTo(DEFAULT_TIME_LIMIT);
        assertThat(testPphQualificEuScoreLink.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
        assertThat(testPphQualificEuScoreLink.getInstitutionCategory()).isEqualTo(DEFAULT_INSTITUTION_CATEGORY);
        assertThat(testPphQualificEuScoreLink.getPrescriptionRight()).isEqualTo(DEFAULT_PRESCRIPTION_RIGHT);
    }

    @Test
    @Transactional
    public void createPphQualificEuScoreLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphQualificEuScoreLinkRepository.findAll().size();

        // Create the PphQualificEuScoreLink with an existing ID
        pphQualificEuScoreLink.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphQualificEuScoreLinkMockMvc.perform(post("/api/pph-qualific-eu-score-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificEuScoreLink)))
            .andExpect(status().isBadRequest());

        // Validate the PphQualificEuScoreLink in the database
        List<PphQualificEuScoreLink> pphQualificEuScoreLinkList = pphQualificEuScoreLinkRepository.findAll();
        assertThat(pphQualificEuScoreLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphQualificEuScoreLinkRepository.findAll().size();
        // set the field null
        pphQualificEuScoreLink.setActivePuphaData(null);

        // Create the PphQualificEuScoreLink, which fails.


        restPphQualificEuScoreLinkMockMvc.perform(post("/api/pph-qualific-eu-score-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificEuScoreLink)))
            .andExpect(status().isBadRequest());

        List<PphQualificEuScoreLink> pphQualificEuScoreLinkList = pphQualificEuScoreLinkRepository.findAll();
        assertThat(pphQualificEuScoreLinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphQualificEuScoreLinks() throws Exception {
        // Initialize the database
        pphQualificEuScoreLinkRepository.saveAndFlush(pphQualificEuScoreLink);

        // Get all the pphQualificEuScoreLinkList
        restPphQualificEuScoreLinkMockMvc.perform(get("/api/pph-qualific-eu-score-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphQualificEuScoreLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeLimit").value(hasItem(DEFAULT_TIME_LIMIT)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())))
            .andExpect(jsonPath("$.[*].institutionCategory").value(hasItem(DEFAULT_INSTITUTION_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].prescriptionRight").value(hasItem(DEFAULT_PRESCRIPTION_RIGHT.toString())));
    }
    
    @Test
    @Transactional
    public void getPphQualificEuScoreLink() throws Exception {
        // Initialize the database
        pphQualificEuScoreLinkRepository.saveAndFlush(pphQualificEuScoreLink);

        // Get the pphQualificEuScoreLink
        restPphQualificEuScoreLinkMockMvc.perform(get("/api/pph-qualific-eu-score-links/{id}", pphQualificEuScoreLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphQualificEuScoreLink.getId().intValue()))
            .andExpect(jsonPath("$.timeLimit").value(DEFAULT_TIME_LIMIT))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()))
            .andExpect(jsonPath("$.institutionCategory").value(DEFAULT_INSTITUTION_CATEGORY.toString()))
            .andExpect(jsonPath("$.prescriptionRight").value(DEFAULT_PRESCRIPTION_RIGHT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPphQualificEuScoreLink() throws Exception {
        // Get the pphQualificEuScoreLink
        restPphQualificEuScoreLinkMockMvc.perform(get("/api/pph-qualific-eu-score-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphQualificEuScoreLink() throws Exception {
        // Initialize the database
        pphQualificEuScoreLinkRepository.saveAndFlush(pphQualificEuScoreLink);

        int databaseSizeBeforeUpdate = pphQualificEuScoreLinkRepository.findAll().size();

        // Update the pphQualificEuScoreLink
        PphQualificEuScoreLink updatedPphQualificEuScoreLink = pphQualificEuScoreLinkRepository.findById(pphQualificEuScoreLink.getId()).get();
        // Disconnect from session so that the updates on updatedPphQualificEuScoreLink are not directly saved in db
        em.detach(updatedPphQualificEuScoreLink);
        updatedPphQualificEuScoreLink
            .timeLimit(UPDATED_TIME_LIMIT)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA)
            .institutionCategory(UPDATED_INSTITUTION_CATEGORY)
            .prescriptionRight(UPDATED_PRESCRIPTION_RIGHT);

        restPphQualificEuScoreLinkMockMvc.perform(put("/api/pph-qualific-eu-score-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphQualificEuScoreLink)))
            .andExpect(status().isOk());

        // Validate the PphQualificEuScoreLink in the database
        List<PphQualificEuScoreLink> pphQualificEuScoreLinkList = pphQualificEuScoreLinkRepository.findAll();
        assertThat(pphQualificEuScoreLinkList).hasSize(databaseSizeBeforeUpdate);
        PphQualificEuScoreLink testPphQualificEuScoreLink = pphQualificEuScoreLinkList.get(pphQualificEuScoreLinkList.size() - 1);
        assertThat(testPphQualificEuScoreLink.getTimeLimit()).isEqualTo(UPDATED_TIME_LIMIT);
        assertThat(testPphQualificEuScoreLink.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
        assertThat(testPphQualificEuScoreLink.getInstitutionCategory()).isEqualTo(UPDATED_INSTITUTION_CATEGORY);
        assertThat(testPphQualificEuScoreLink.getPrescriptionRight()).isEqualTo(UPDATED_PRESCRIPTION_RIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingPphQualificEuScoreLink() throws Exception {
        int databaseSizeBeforeUpdate = pphQualificEuScoreLinkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphQualificEuScoreLinkMockMvc.perform(put("/api/pph-qualific-eu-score-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificEuScoreLink)))
            .andExpect(status().isBadRequest());

        // Validate the PphQualificEuScoreLink in the database
        List<PphQualificEuScoreLink> pphQualificEuScoreLinkList = pphQualificEuScoreLinkRepository.findAll();
        assertThat(pphQualificEuScoreLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphQualificEuScoreLink() throws Exception {
        // Initialize the database
        pphQualificEuScoreLinkRepository.saveAndFlush(pphQualificEuScoreLink);

        int databaseSizeBeforeDelete = pphQualificEuScoreLinkRepository.findAll().size();

        // Delete the pphQualificEuScoreLink
        restPphQualificEuScoreLinkMockMvc.perform(delete("/api/pph-qualific-eu-score-links/{id}", pphQualificEuScoreLink.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphQualificEuScoreLink> pphQualificEuScoreLinkList = pphQualificEuScoreLinkRepository.findAll();
        assertThat(pphQualificEuScoreLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
