package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.ContactPerson;
import hu.paninform.startmedsol.repository.ContactPersonRepository;
import hu.paninform.startmedsol.service.ContactPersonService;
import hu.paninform.startmedsol.service.dto.ContactPersonDTO;
import hu.paninform.startmedsol.service.mapper.ContactPersonMapper;

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
 * Integration tests for the {@link ContactPersonResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ContactPersonResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Autowired
    private ContactPersonMapper contactPersonMapper;

    @Autowired
    private ContactPersonService contactPersonService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContactPersonMockMvc;

    private ContactPerson contactPerson;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactPerson createEntity(EntityManager em) {
        ContactPerson contactPerson = new ContactPerson()
            .name(DEFAULT_NAME)
            .phone(DEFAULT_PHONE)
            .email(DEFAULT_EMAIL);
        return contactPerson;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactPerson createUpdatedEntity(EntityManager em) {
        ContactPerson contactPerson = new ContactPerson()
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL);
        return contactPerson;
    }

    @BeforeEach
    public void initTest() {
        contactPerson = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactPerson() throws Exception {
        int databaseSizeBeforeCreate = contactPersonRepository.findAll().size();
        // Create the ContactPerson
        ContactPersonDTO contactPersonDTO = contactPersonMapper.toDto(contactPerson);
        restContactPersonMockMvc.perform(post("/api/contact-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactPersonDTO)))
            .andExpect(status().isCreated());

        // Validate the ContactPerson in the database
        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeCreate + 1);
        ContactPerson testContactPerson = contactPersonList.get(contactPersonList.size() - 1);
        assertThat(testContactPerson.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testContactPerson.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testContactPerson.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createContactPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactPersonRepository.findAll().size();

        // Create the ContactPerson with an existing ID
        contactPerson.setId(1L);
        ContactPersonDTO contactPersonDTO = contactPersonMapper.toDto(contactPerson);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactPersonMockMvc.perform(post("/api/contact-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactPersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactPerson in the database
        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactPersonRepository.findAll().size();
        // set the field null
        contactPerson.setName(null);

        // Create the ContactPerson, which fails.
        ContactPersonDTO contactPersonDTO = contactPersonMapper.toDto(contactPerson);


        restContactPersonMockMvc.perform(post("/api/contact-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactPersonDTO)))
            .andExpect(status().isBadRequest());

        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactPersonRepository.findAll().size();
        // set the field null
        contactPerson.setPhone(null);

        // Create the ContactPerson, which fails.
        ContactPersonDTO contactPersonDTO = contactPersonMapper.toDto(contactPerson);


        restContactPersonMockMvc.perform(post("/api/contact-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactPersonDTO)))
            .andExpect(status().isBadRequest());

        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactPersonRepository.findAll().size();
        // set the field null
        contactPerson.setEmail(null);

        // Create the ContactPerson, which fails.
        ContactPersonDTO contactPersonDTO = contactPersonMapper.toDto(contactPerson);


        restContactPersonMockMvc.perform(post("/api/contact-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactPersonDTO)))
            .andExpect(status().isBadRequest());

        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContactPeople() throws Exception {
        // Initialize the database
        contactPersonRepository.saveAndFlush(contactPerson);

        // Get all the contactPersonList
        restContactPersonMockMvc.perform(get("/api/contact-people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactPerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getContactPerson() throws Exception {
        // Initialize the database
        contactPersonRepository.saveAndFlush(contactPerson);

        // Get the contactPerson
        restContactPersonMockMvc.perform(get("/api/contact-people/{id}", contactPerson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contactPerson.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingContactPerson() throws Exception {
        // Get the contactPerson
        restContactPersonMockMvc.perform(get("/api/contact-people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactPerson() throws Exception {
        // Initialize the database
        contactPersonRepository.saveAndFlush(contactPerson);

        int databaseSizeBeforeUpdate = contactPersonRepository.findAll().size();

        // Update the contactPerson
        ContactPerson updatedContactPerson = contactPersonRepository.findById(contactPerson.getId()).get();
        // Disconnect from session so that the updates on updatedContactPerson are not directly saved in db
        em.detach(updatedContactPerson);
        updatedContactPerson
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .email(UPDATED_EMAIL);
        ContactPersonDTO contactPersonDTO = contactPersonMapper.toDto(updatedContactPerson);

        restContactPersonMockMvc.perform(put("/api/contact-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactPersonDTO)))
            .andExpect(status().isOk());

        // Validate the ContactPerson in the database
        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeUpdate);
        ContactPerson testContactPerson = contactPersonList.get(contactPersonList.size() - 1);
        assertThat(testContactPerson.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testContactPerson.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testContactPerson.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingContactPerson() throws Exception {
        int databaseSizeBeforeUpdate = contactPersonRepository.findAll().size();

        // Create the ContactPerson
        ContactPersonDTO contactPersonDTO = contactPersonMapper.toDto(contactPerson);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactPersonMockMvc.perform(put("/api/contact-people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactPersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactPerson in the database
        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContactPerson() throws Exception {
        // Initialize the database
        contactPersonRepository.saveAndFlush(contactPerson);

        int databaseSizeBeforeDelete = contactPersonRepository.findAll().size();

        // Delete the contactPerson
        restContactPersonMockMvc.perform(delete("/api/contact-people/{id}", contactPerson.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactPerson> contactPersonList = contactPersonRepository.findAll();
        assertThat(contactPersonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
