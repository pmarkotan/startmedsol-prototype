package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphAidISOBook;
import hu.paninform.startmedsol.repository.PphAidISOBookRepository;

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
 * Integration tests for the {@link PphAidISOBookResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphAidISOBookResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphAidISOBookRepository pphAidISOBookRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphAidISOBookMockMvc;

    private PphAidISOBook pphAidISOBook;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphAidISOBook createEntity(EntityManager em) {
        PphAidISOBook pphAidISOBook = new PphAidISOBook()
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphAidISOBook;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphAidISOBook createUpdatedEntity(EntityManager em) {
        PphAidISOBook pphAidISOBook = new PphAidISOBook()
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphAidISOBook;
    }

    @BeforeEach
    public void initTest() {
        pphAidISOBook = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphAidISOBook() throws Exception {
        int databaseSizeBeforeCreate = pphAidISOBookRepository.findAll().size();
        // Create the PphAidISOBook
        restPphAidISOBookMockMvc.perform(post("/api/pph-aid-iso-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAidISOBook)))
            .andExpect(status().isCreated());

        // Validate the PphAidISOBook in the database
        List<PphAidISOBook> pphAidISOBookList = pphAidISOBookRepository.findAll();
        assertThat(pphAidISOBookList).hasSize(databaseSizeBeforeCreate + 1);
        PphAidISOBook testPphAidISOBook = pphAidISOBookList.get(pphAidISOBookList.size() - 1);
        assertThat(testPphAidISOBook.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPphAidISOBook.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPphAidISOBook.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphAidISOBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphAidISOBookRepository.findAll().size();

        // Create the PphAidISOBook with an existing ID
        pphAidISOBook.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphAidISOBookMockMvc.perform(post("/api/pph-aid-iso-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAidISOBook)))
            .andExpect(status().isBadRequest());

        // Validate the PphAidISOBook in the database
        List<PphAidISOBook> pphAidISOBookList = pphAidISOBookRepository.findAll();
        assertThat(pphAidISOBookList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphAidISOBookRepository.findAll().size();
        // set the field null
        pphAidISOBook.setActivePuphaData(null);

        // Create the PphAidISOBook, which fails.


        restPphAidISOBookMockMvc.perform(post("/api/pph-aid-iso-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAidISOBook)))
            .andExpect(status().isBadRequest());

        List<PphAidISOBook> pphAidISOBookList = pphAidISOBookRepository.findAll();
        assertThat(pphAidISOBookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphAidISOBooks() throws Exception {
        // Initialize the database
        pphAidISOBookRepository.saveAndFlush(pphAidISOBook);

        // Get all the pphAidISOBookList
        restPphAidISOBookMockMvc.perform(get("/api/pph-aid-iso-books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphAidISOBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPphAidISOBook() throws Exception {
        // Initialize the database
        pphAidISOBookRepository.saveAndFlush(pphAidISOBook);

        // Get the pphAidISOBook
        restPphAidISOBookMockMvc.perform(get("/api/pph-aid-iso-books/{id}", pphAidISOBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphAidISOBook.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphAidISOBook() throws Exception {
        // Get the pphAidISOBook
        restPphAidISOBookMockMvc.perform(get("/api/pph-aid-iso-books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphAidISOBook() throws Exception {
        // Initialize the database
        pphAidISOBookRepository.saveAndFlush(pphAidISOBook);

        int databaseSizeBeforeUpdate = pphAidISOBookRepository.findAll().size();

        // Update the pphAidISOBook
        PphAidISOBook updatedPphAidISOBook = pphAidISOBookRepository.findById(pphAidISOBook.getId()).get();
        // Disconnect from session so that the updates on updatedPphAidISOBook are not directly saved in db
        em.detach(updatedPphAidISOBook);
        updatedPphAidISOBook
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);

        restPphAidISOBookMockMvc.perform(put("/api/pph-aid-iso-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPphAidISOBook)))
            .andExpect(status().isOk());

        // Validate the PphAidISOBook in the database
        List<PphAidISOBook> pphAidISOBookList = pphAidISOBookRepository.findAll();
        assertThat(pphAidISOBookList).hasSize(databaseSizeBeforeUpdate);
        PphAidISOBook testPphAidISOBook = pphAidISOBookList.get(pphAidISOBookList.size() - 1);
        assertThat(testPphAidISOBook.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPphAidISOBook.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPphAidISOBook.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphAidISOBook() throws Exception {
        int databaseSizeBeforeUpdate = pphAidISOBookRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphAidISOBookMockMvc.perform(put("/api/pph-aid-iso-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphAidISOBook)))
            .andExpect(status().isBadRequest());

        // Validate the PphAidISOBook in the database
        List<PphAidISOBook> pphAidISOBookList = pphAidISOBookRepository.findAll();
        assertThat(pphAidISOBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphAidISOBook() throws Exception {
        // Initialize the database
        pphAidISOBookRepository.saveAndFlush(pphAidISOBook);

        int databaseSizeBeforeDelete = pphAidISOBookRepository.findAll().size();

        // Delete the pphAidISOBook
        restPphAidISOBookMockMvc.perform(delete("/api/pph-aid-iso-books/{id}", pphAidISOBook.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphAidISOBook> pphAidISOBookList = pphAidISOBookRepository.findAll();
        assertThat(pphAidISOBookList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
