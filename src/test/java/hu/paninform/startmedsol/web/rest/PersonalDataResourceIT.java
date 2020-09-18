package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PersonalData;
import hu.paninform.startmedsol.repository.PersonalDataRepository;
import hu.paninform.startmedsol.service.PersonalDataService;
import hu.paninform.startmedsol.service.dto.PersonalDataDTO;
import hu.paninform.startmedsol.service.mapper.PersonalDataMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.paninform.startmedsol.domain.enumeration.Sex;
/**
 * Integration tests for the {@link PersonalDataResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonalDataResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NAMES = "BBBBBBBBBB";

    private static final String DEFAULT_PREFERRED_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_PLACE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MOTHERS_MAIDEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHERS_MAIDEN_NAME = "BBBBBBBBBB";

    private static final Sex DEFAULT_SEX = Sex.MALE;
    private static final Sex UPDATED_SEX = Sex.FEMALE;

    private static final String DEFAULT_SEX_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_SEX_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_1 = "5n@A`FY3.6";
    private static final String UPDATED_EMAIL_1 = "-Y_y2M@`.{SI}";

    private static final String DEFAULT_EMAIL_2 = "ewN@Tdqe._*E_";
    private static final String UPDATED_EMAIL_2 = "bm6t@WcKTqV._,X8";

    private static final String DEFAULT_PHONE = "++417";
    private static final String UPDATED_PHONE = "++++++9";

    private static final String DEFAULT_ICE_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ICE_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICE_CONTACT_PHONE = "+++++286065";
    private static final String UPDATED_ICE_CONTACT_PHONE = "++++++389";

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
    private PersonalDataMapper personalDataMapper;

    @Autowired
    private PersonalDataService personalDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonalDataMockMvc;

    private PersonalData personalData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalData createEntity(EntityManager em) {
        PersonalData personalData = new PersonalData()
            .title(DEFAULT_TITLE)
            .familyName(DEFAULT_FAMILY_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .otherNames(DEFAULT_OTHER_NAMES)
            .preferredName(DEFAULT_PREFERRED_NAME)
            .birthName(DEFAULT_BIRTH_NAME)
            .birthPlace(DEFAULT_BIRTH_PLACE)
            .birthDate(DEFAULT_BIRTH_DATE)
            .mothersMaidenName(DEFAULT_MOTHERS_MAIDEN_NAME)
            .sex(DEFAULT_SEX)
            .sexOther(DEFAULT_SEX_OTHER)
            .nationality(DEFAULT_NATIONALITY)
            .email1(DEFAULT_EMAIL_1)
            .email2(DEFAULT_EMAIL_2)
            .phone(DEFAULT_PHONE)
            .iceContactName(DEFAULT_ICE_CONTACT_NAME)
            .iceContactPhone(DEFAULT_ICE_CONTACT_PHONE);
        return personalData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonalData createUpdatedEntity(EntityManager em) {
        PersonalData personalData = new PersonalData()
            .title(UPDATED_TITLE)
            .familyName(UPDATED_FAMILY_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .otherNames(UPDATED_OTHER_NAMES)
            .preferredName(UPDATED_PREFERRED_NAME)
            .birthName(UPDATED_BIRTH_NAME)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .birthDate(UPDATED_BIRTH_DATE)
            .mothersMaidenName(UPDATED_MOTHERS_MAIDEN_NAME)
            .sex(UPDATED_SEX)
            .sexOther(UPDATED_SEX_OTHER)
            .nationality(UPDATED_NATIONALITY)
            .email1(UPDATED_EMAIL_1)
            .email2(UPDATED_EMAIL_2)
            .phone(UPDATED_PHONE)
            .iceContactName(UPDATED_ICE_CONTACT_NAME)
            .iceContactPhone(UPDATED_ICE_CONTACT_PHONE);
        return personalData;
    }

    @BeforeEach
    public void initTest() {
        personalData = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonalData() throws Exception {
        int databaseSizeBeforeCreate = personalDataRepository.findAll().size();
        // Create the PersonalData
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);
        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeCreate + 1);
        PersonalData testPersonalData = personalDataList.get(personalDataList.size() - 1);
        assertThat(testPersonalData.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testPersonalData.getFamilyName()).isEqualTo(DEFAULT_FAMILY_NAME);
        assertThat(testPersonalData.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPersonalData.getOtherNames()).isEqualTo(DEFAULT_OTHER_NAMES);
        assertThat(testPersonalData.getPreferredName()).isEqualTo(DEFAULT_PREFERRED_NAME);
        assertThat(testPersonalData.getBirthName()).isEqualTo(DEFAULT_BIRTH_NAME);
        assertThat(testPersonalData.getBirthPlace()).isEqualTo(DEFAULT_BIRTH_PLACE);
        assertThat(testPersonalData.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testPersonalData.getMothersMaidenName()).isEqualTo(DEFAULT_MOTHERS_MAIDEN_NAME);
        assertThat(testPersonalData.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testPersonalData.getSexOther()).isEqualTo(DEFAULT_SEX_OTHER);
        assertThat(testPersonalData.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testPersonalData.getEmail1()).isEqualTo(DEFAULT_EMAIL_1);
        assertThat(testPersonalData.getEmail2()).isEqualTo(DEFAULT_EMAIL_2);
        assertThat(testPersonalData.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPersonalData.getIceContactName()).isEqualTo(DEFAULT_ICE_CONTACT_NAME);
        assertThat(testPersonalData.getIceContactPhone()).isEqualTo(DEFAULT_ICE_CONTACT_PHONE);
    }

    @Test
    @Transactional
    public void createPersonalDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personalDataRepository.findAll().size();

        // Create the PersonalData with an existing ID
        personalData.setId(1L);
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFamilyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setFamilyName(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setFirstName(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBirthNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setBirthName(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBirthPlaceIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setBirthPlace(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBirthDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setBirthDate(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setSex(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNationalityIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setNationality(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmail1IsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setEmail1(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = personalDataRepository.findAll().size();
        // set the field null
        personalData.setPhone(null);

        // Create the PersonalData, which fails.
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);


        restPersonalDataMockMvc.perform(post("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        // Get all the personalDataList
        restPersonalDataMockMvc.perform(get("/api/personal-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personalData.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].familyName").value(hasItem(DEFAULT_FAMILY_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].otherNames").value(hasItem(DEFAULT_OTHER_NAMES)))
            .andExpect(jsonPath("$.[*].preferredName").value(hasItem(DEFAULT_PREFERRED_NAME)))
            .andExpect(jsonPath("$.[*].birthName").value(hasItem(DEFAULT_BIRTH_NAME)))
            .andExpect(jsonPath("$.[*].birthPlace").value(hasItem(DEFAULT_BIRTH_PLACE)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].mothersMaidenName").value(hasItem(DEFAULT_MOTHERS_MAIDEN_NAME)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].sexOther").value(hasItem(DEFAULT_SEX_OTHER)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].email1").value(hasItem(DEFAULT_EMAIL_1)))
            .andExpect(jsonPath("$.[*].email2").value(hasItem(DEFAULT_EMAIL_2)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].iceContactName").value(hasItem(DEFAULT_ICE_CONTACT_NAME)))
            .andExpect(jsonPath("$.[*].iceContactPhone").value(hasItem(DEFAULT_ICE_CONTACT_PHONE)));
    }
    
    @Test
    @Transactional
    public void getPersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        // Get the personalData
        restPersonalDataMockMvc.perform(get("/api/personal-data/{id}", personalData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personalData.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.familyName").value(DEFAULT_FAMILY_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.otherNames").value(DEFAULT_OTHER_NAMES))
            .andExpect(jsonPath("$.preferredName").value(DEFAULT_PREFERRED_NAME))
            .andExpect(jsonPath("$.birthName").value(DEFAULT_BIRTH_NAME))
            .andExpect(jsonPath("$.birthPlace").value(DEFAULT_BIRTH_PLACE))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.mothersMaidenName").value(DEFAULT_MOTHERS_MAIDEN_NAME))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.sexOther").value(DEFAULT_SEX_OTHER))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.email1").value(DEFAULT_EMAIL_1))
            .andExpect(jsonPath("$.email2").value(DEFAULT_EMAIL_2))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.iceContactName").value(DEFAULT_ICE_CONTACT_NAME))
            .andExpect(jsonPath("$.iceContactPhone").value(DEFAULT_ICE_CONTACT_PHONE));
    }
    @Test
    @Transactional
    public void getNonExistingPersonalData() throws Exception {
        // Get the personalData
        restPersonalDataMockMvc.perform(get("/api/personal-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        int databaseSizeBeforeUpdate = personalDataRepository.findAll().size();

        // Update the personalData
        PersonalData updatedPersonalData = personalDataRepository.findById(personalData.getId()).get();
        // Disconnect from session so that the updates on updatedPersonalData are not directly saved in db
        em.detach(updatedPersonalData);
        updatedPersonalData
            .title(UPDATED_TITLE)
            .familyName(UPDATED_FAMILY_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .otherNames(UPDATED_OTHER_NAMES)
            .preferredName(UPDATED_PREFERRED_NAME)
            .birthName(UPDATED_BIRTH_NAME)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .birthDate(UPDATED_BIRTH_DATE)
            .mothersMaidenName(UPDATED_MOTHERS_MAIDEN_NAME)
            .sex(UPDATED_SEX)
            .sexOther(UPDATED_SEX_OTHER)
            .nationality(UPDATED_NATIONALITY)
            .email1(UPDATED_EMAIL_1)
            .email2(UPDATED_EMAIL_2)
            .phone(UPDATED_PHONE)
            .iceContactName(UPDATED_ICE_CONTACT_NAME)
            .iceContactPhone(UPDATED_ICE_CONTACT_PHONE);
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(updatedPersonalData);

        restPersonalDataMockMvc.perform(put("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isOk());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeUpdate);
        PersonalData testPersonalData = personalDataList.get(personalDataList.size() - 1);
        assertThat(testPersonalData.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testPersonalData.getFamilyName()).isEqualTo(UPDATED_FAMILY_NAME);
        assertThat(testPersonalData.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPersonalData.getOtherNames()).isEqualTo(UPDATED_OTHER_NAMES);
        assertThat(testPersonalData.getPreferredName()).isEqualTo(UPDATED_PREFERRED_NAME);
        assertThat(testPersonalData.getBirthName()).isEqualTo(UPDATED_BIRTH_NAME);
        assertThat(testPersonalData.getBirthPlace()).isEqualTo(UPDATED_BIRTH_PLACE);
        assertThat(testPersonalData.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testPersonalData.getMothersMaidenName()).isEqualTo(UPDATED_MOTHERS_MAIDEN_NAME);
        assertThat(testPersonalData.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testPersonalData.getSexOther()).isEqualTo(UPDATED_SEX_OTHER);
        assertThat(testPersonalData.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testPersonalData.getEmail1()).isEqualTo(UPDATED_EMAIL_1);
        assertThat(testPersonalData.getEmail2()).isEqualTo(UPDATED_EMAIL_2);
        assertThat(testPersonalData.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPersonalData.getIceContactName()).isEqualTo(UPDATED_ICE_CONTACT_NAME);
        assertThat(testPersonalData.getIceContactPhone()).isEqualTo(UPDATED_ICE_CONTACT_PHONE);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonalData() throws Exception {
        int databaseSizeBeforeUpdate = personalDataRepository.findAll().size();

        // Create the PersonalData
        PersonalDataDTO personalDataDTO = personalDataMapper.toDto(personalData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonalDataMockMvc.perform(put("/api/personal-data")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personalDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonalData in the database
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersonalData() throws Exception {
        // Initialize the database
        personalDataRepository.saveAndFlush(personalData);

        int databaseSizeBeforeDelete = personalDataRepository.findAll().size();

        // Delete the personalData
        restPersonalDataMockMvc.perform(delete("/api/personal-data/{id}", personalData.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonalData> personalDataList = personalDataRepository.findAll();
        assertThat(personalDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
