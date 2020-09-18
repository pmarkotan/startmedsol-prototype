package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphQualificationDoctorAll;
import hu.paninform.startmedsol.repository.PphQualificationDoctorAllRepository;

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
 * Integration tests for the {@link PphQualificationDoctorAllResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphQualificationDoctorAllResourceIT {

    private static final String DEFAULT_REGISTRATION_NO = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_NO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphQualificationDoctorAllRepository pphQualificationDoctorAllRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphQualificationDoctorAllMockMvc;

    private PphQualificationDoctorAll pphQualificationDoctorAll;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphQualificationDoctorAll createEntity(EntityManager em) {
        PphQualificationDoctorAll pphQualificationDoctorAll = new PphQualificationDoctorAll()
            .registrationNo(DEFAULT_REGISTRATION_NO)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphQualificationDoctorAll;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphQualificationDoctorAll createUpdatedEntity(EntityManager em) {
        PphQualificationDoctorAll pphQualificationDoctorAll = new PphQualificationDoctorAll()
            .registrationNo(UPDATED_REGISTRATION_NO)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphQualificationDoctorAll;
    }

    @BeforeEach
    public void initTest() {
        pphQualificationDoctorAll = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphQualificationDoctorAll() throws Exception {
        int databaseSizeBeforeCreate = pphQualificationDoctorAllRepository.findAll().size();
        // Create the PphQualificationDoctorAll
        restPphQualificationDoctorAllMockMvc.perform(post("/api/pph-qualification-doctor-alls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificationDoctorAll)))
            .andExpect(status().isCreated());

        // Validate the PphQualificationDoctorAll in the database
        List<PphQualificationDoctorAll> pphQualificationDoctorAllList = pphQualificationDoctorAllRepository.findAll();
        assertThat(pphQualificationDoctorAllList).hasSize(databaseSizeBeforeCreate + 1);
        PphQualificationDoctorAll testPphQualificationDoctorAll = pphQualificationDoctorAllList.get(pphQualificationDoctorAllList.size() - 1);
        assertThat(testPphQualificationDoctorAll.getRegistrationNo()).isEqualTo(DEFAULT_REGISTRATION_NO);
        assertThat(testPphQualificationDoctorAll.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphQualificationDoctorAllWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphQualificationDoctorAllRepository.findAll().size();

        // Create the PphQualificationDoctorAll with an existing ID
        pphQualificationDoctorAll.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphQualificationDoctorAllMockMvc.perform(post("/api/pph-qualification-doctor-alls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificationDoctorAll)))
            .andExpect(status().isBadRequest());

        // Validate the PphQualificationDoctorAll in the database
        List<PphQualificationDoctorAll> pphQualificationDoctorAllList = pphQualificationDoctorAllRepository.findAll();
        assertThat(pphQualificationDoctorAllList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphQualificationDoctorAllRepository.findAll().size();
        // set the field null
        pphQualificationDoctorAll.setActivePuphaData(null);

        // Create the PphQualificationDoctorAll, which fails.


        restPphQualificationDoctorAllMockMvc.perform(post("/api/pph-qualification-doctor-alls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificationDoctorAll)))
            .andExpect(status().isBadRequest());

        List<PphQualificationDoctorAll> pphQualificationDoctorAllList = pphQualificationDoctorAllRepository.findAll();
        assertThat(pphQualificationDoctorAllList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphQualificationDoctorAlls() throws Exception {
        // Initialize the database
        pphQualificationDoctorAllRepository.saveAndFlush(pphQualificationDoctorAll);

        // Get all the pphQualificationDoctorAllList
        restPphQualificationDoctorAllMockMvc.perform(get("/api/pph-qualification-doctor-alls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphQualificationDoctorAll.getId().intValue())))
            .andExpect(jsonPath("$.[*].registrationNo").value(hasItem(DEFAULT_REGISTRATION_NO)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphQualificationDoctorAll() throws Exception {
        // Initialize the database
        pphQualificationDoctorAllRepository.saveAndFlush(pphQualificationDoctorAll);

        // Get the pphQualificationDoctorAll
        restPphQualificationDoctorAllMockMvc.perform(get("/api/pph-qualification-doctor-alls/{id}", pphQualificationDoctorAll.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphQualificationDoctorAll.getId().intValue()))
            .andExpect(jsonPath("$.registrationNo").value(DEFAULT_REGISTRATION_NO))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphQualificationDoctorAll() throws Exception {
        // Get the pphQualificationDoctorAll
        restPphQualificationDoctorAllMockMvc.perform(get("/api/pph-qualification-doctor-alls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphQualificationDoctorAll() throws Exception {
        // Initialize the database
        pphQualificationDoctorAllRepository.saveAndFlush(pphQualificationDoctorAll);

        int databaseSizeBeforeUpdate = pphQualificationDoctorAllRepository.findAll().size();

        // Update the pphQualificationDoctorAll
        PphQualificationDoctorAll updatedPphQualificationDoctorAll = pphQualificationDoctorAllRepository.findById(pphQualificationDoctorAll.getId()).get();
        // Disconnect from session so that the updates on updatedPphQualificationDoctorAll are not directly saved in db
        em.detach(updatedPphQualificationDoctorAll);
        updatedPphQualificationDoctorAll
            .registrationNo(UPDATED_REGISTRATION_NO)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphQualificationDoctorAllMockMvc.perform(put("/api/pph-qualification-doctor-alls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphQualificationDoctorAll)))
            .andExpect(status().isOk());

        // Validate the PphQualificationDoctorAll in the database
        List<PphQualificationDoctorAll> pphQualificationDoctorAllList = pphQualificationDoctorAllRepository.findAll();
        assertThat(pphQualificationDoctorAllList).hasSize(databaseSizeBeforeUpdate);
        PphQualificationDoctorAll testPphQualificationDoctorAll = pphQualificationDoctorAllList.get(pphQualificationDoctorAllList.size() - 1);
        assertThat(testPphQualificationDoctorAll.getRegistrationNo()).isEqualTo(UPDATED_REGISTRATION_NO);
        assertThat(testPphQualificationDoctorAll.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphQualificationDoctorAll() throws Exception {
        int databaseSizeBeforeUpdate = pphQualificationDoctorAllRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphQualificationDoctorAllMockMvc.perform(put("/api/pph-qualification-doctor-alls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphQualificationDoctorAll)))
            .andExpect(status().isBadRequest());

        // Validate the PphQualificationDoctorAll in the database
        List<PphQualificationDoctorAll> pphQualificationDoctorAllList = pphQualificationDoctorAllRepository.findAll();
        assertThat(pphQualificationDoctorAllList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphQualificationDoctorAll() throws Exception {
        // Initialize the database
        pphQualificationDoctorAllRepository.saveAndFlush(pphQualificationDoctorAll);

        int databaseSizeBeforeDelete = pphQualificationDoctorAllRepository.findAll().size();

        // Delete the pphQualificationDoctorAll
        restPphQualificationDoctorAllMockMvc.perform(delete("/api/pph-qualification-doctor-alls/{id}", pphQualificationDoctorAll.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphQualificationDoctorAll> pphQualificationDoctorAllList = pphQualificationDoctorAllRepository.findAll();
        assertThat(pphQualificationDoctorAllList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
