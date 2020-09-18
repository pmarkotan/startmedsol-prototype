package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphMotivationGroup;
import hu.paninform.startmedsol.repository.PphMotivationGroupRepository;

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
 * Integration tests for the {@link PphMotivationGroupResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphMotivationGroupResourceIT {

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final Double DEFAULT_TARGET_VALUE = 1D;
    private static final Double UPDATED_TARGET_VALUE = 2D;

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphMotivationGroupRepository pphMotivationGroupRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphMotivationGroupMockMvc;

    private PphMotivationGroup pphMotivationGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMotivationGroup createEntity(EntityManager em) {
        PphMotivationGroup pphMotivationGroup = new PphMotivationGroup()
            .code(DEFAULT_CODE)
            .targetValue(DEFAULT_TARGET_VALUE)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphMotivationGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMotivationGroup createUpdatedEntity(EntityManager em) {
        PphMotivationGroup pphMotivationGroup = new PphMotivationGroup()
            .code(UPDATED_CODE)
            .targetValue(UPDATED_TARGET_VALUE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphMotivationGroup;
    }

    @BeforeEach
    public void initTest() {
        pphMotivationGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphMotivationGroup() throws Exception {
        int databaseSizeBeforeCreate = pphMotivationGroupRepository.findAll().size();
        // Create the PphMotivationGroup
        restPphMotivationGroupMockMvc.perform(post("/api/pph-motivation-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMotivationGroup)))
            .andExpect(status().isCreated());

        // Validate the PphMotivationGroup in the database
        List<PphMotivationGroup> pphMotivationGroupList = pphMotivationGroupRepository.findAll();
        assertThat(pphMotivationGroupList).hasSize(databaseSizeBeforeCreate + 1);
        PphMotivationGroup testPphMotivationGroup = pphMotivationGroupList.get(pphMotivationGroupList.size() - 1);
        assertThat(testPphMotivationGroup.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPphMotivationGroup.getTargetValue()).isEqualTo(DEFAULT_TARGET_VALUE);
        assertThat(testPphMotivationGroup.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphMotivationGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphMotivationGroupRepository.findAll().size();

        // Create the PphMotivationGroup with an existing ID
        pphMotivationGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphMotivationGroupMockMvc.perform(post("/api/pph-motivation-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMotivationGroup)))
            .andExpect(status().isBadRequest());

        // Validate the PphMotivationGroup in the database
        List<PphMotivationGroup> pphMotivationGroupList = pphMotivationGroupRepository.findAll();
        assertThat(pphMotivationGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphMotivationGroupRepository.findAll().size();
        // set the field null
        pphMotivationGroup.setActivePuphaData(null);

        // Create the PphMotivationGroup, which fails.


        restPphMotivationGroupMockMvc.perform(post("/api/pph-motivation-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMotivationGroup)))
            .andExpect(status().isBadRequest());

        List<PphMotivationGroup> pphMotivationGroupList = pphMotivationGroupRepository.findAll();
        assertThat(pphMotivationGroupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphMotivationGroups() throws Exception {
        // Initialize the database
        pphMotivationGroupRepository.saveAndFlush(pphMotivationGroup);

        // Get all the pphMotivationGroupList
        restPphMotivationGroupMockMvc.perform(get("/api/pph-motivation-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphMotivationGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].targetValue").value(hasItem(DEFAULT_TARGET_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphMotivationGroup() throws Exception {
        // Initialize the database
        pphMotivationGroupRepository.saveAndFlush(pphMotivationGroup);

        // Get the pphMotivationGroup
        restPphMotivationGroupMockMvc.perform(get("/api/pph-motivation-groups/{id}", pphMotivationGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphMotivationGroup.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.targetValue").value(DEFAULT_TARGET_VALUE.doubleValue()))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphMotivationGroup() throws Exception {
        // Get the pphMotivationGroup
        restPphMotivationGroupMockMvc.perform(get("/api/pph-motivation-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphMotivationGroup() throws Exception {
        // Initialize the database
        pphMotivationGroupRepository.saveAndFlush(pphMotivationGroup);

        int databaseSizeBeforeUpdate = pphMotivationGroupRepository.findAll().size();

        // Update the pphMotivationGroup
        PphMotivationGroup updatedPphMotivationGroup = pphMotivationGroupRepository.findById(pphMotivationGroup.getId()).get();
        // Disconnect from session so that the updates on updatedPphMotivationGroup are not directly saved in db
        em.detach(updatedPphMotivationGroup);
        updatedPphMotivationGroup
            .code(UPDATED_CODE)
            .targetValue(UPDATED_TARGET_VALUE)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphMotivationGroupMockMvc.perform(put("/api/pph-motivation-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphMotivationGroup)))
            .andExpect(status().isOk());

        // Validate the PphMotivationGroup in the database
        List<PphMotivationGroup> pphMotivationGroupList = pphMotivationGroupRepository.findAll();
        assertThat(pphMotivationGroupList).hasSize(databaseSizeBeforeUpdate);
        PphMotivationGroup testPphMotivationGroup = pphMotivationGroupList.get(pphMotivationGroupList.size() - 1);
        assertThat(testPphMotivationGroup.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPphMotivationGroup.getTargetValue()).isEqualTo(UPDATED_TARGET_VALUE);
        assertThat(testPphMotivationGroup.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphMotivationGroup() throws Exception {
        int databaseSizeBeforeUpdate = pphMotivationGroupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphMotivationGroupMockMvc.perform(put("/api/pph-motivation-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMotivationGroup)))
            .andExpect(status().isBadRequest());

        // Validate the PphMotivationGroup in the database
        List<PphMotivationGroup> pphMotivationGroupList = pphMotivationGroupRepository.findAll();
        assertThat(pphMotivationGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphMotivationGroup() throws Exception {
        // Initialize the database
        pphMotivationGroupRepository.saveAndFlush(pphMotivationGroup);

        int databaseSizeBeforeDelete = pphMotivationGroupRepository.findAll().size();

        // Delete the pphMotivationGroup
        restPphMotivationGroupMockMvc.perform(delete("/api/pph-motivation-groups/{id}", pphMotivationGroup.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphMotivationGroup> pphMotivationGroupList = pphMotivationGroupRepository.findAll();
        assertThat(pphMotivationGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
