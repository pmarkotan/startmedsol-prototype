package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.Prescription;
import hu.paninform.startmedsol.domain.CsDiagnosis;
import hu.paninform.startmedsol.domain.Employee;
import hu.paninform.startmedsol.domain.PphMedicine;
import hu.paninform.startmedsol.domain.MedicalCase;
import hu.paninform.startmedsol.domain.PphPuphaVersion;
import hu.paninform.startmedsol.repository.PrescriptionRepository;
import hu.paninform.startmedsol.service.PrescriptionService;
import hu.paninform.startmedsol.service.dto.PrescriptionDTO;
import hu.paninform.startmedsol.service.mapper.PrescriptionMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.paninform.startmedsol.domain.enumeration.DoctorQualificationValidatorRuleType;
import hu.paninform.startmedsol.domain.enumeration.PrescriptionSubsidyCategory;
import hu.paninform.startmedsol.domain.enumeration.PrescriptionStatus;
import hu.paninform.startmedsol.domain.enumeration.EesztSendingStatus;
import hu.paninform.startmedsol.domain.enumeration.MedicalProductType;
import hu.paninform.startmedsol.domain.enumeration.Frequency;
/**
 * Integration tests for the {@link PrescriptionResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrescriptionResourceIT {

    private static final Boolean DEFAULT_OPENED_PACKAGE = false;
    private static final Boolean UPDATED_OPENED_PACKAGE = true;

    private static final Boolean DEFAULT_MEDICAL_AID_TEACH_PRESCRIBING = false;
    private static final Boolean UPDATED_MEDICAL_AID_TEACH_PRESCRIBING = true;

    private static final Boolean DEFAULT_FOR_H_2_C_CERTIFICATE = false;
    private static final Boolean UPDATED_FOR_H_2_C_CERTIFICATE = true;

    private static final Instant DEFAULT_INSCRIPTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSCRIPTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CAUSE_OF_NOT_REPLACEABILITY = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_OF_NOT_REPLACEABILITY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DOSE_MSM_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_DOSE_MSM_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_EESZT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_EESZT_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_INTEGRATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_INTEGRATION_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_EQUIPPED_WITH = 1;
    private static final Integer UPDATED_EQUIPPED_WITH = 2;

    private static final DoctorQualificationValidatorRuleType DEFAULT_ACCEPTED_QUALIFICATION_RULE = DoctorQualificationValidatorRuleType.WITHOUT_QUALIFICATION_RULE;
    private static final DoctorQualificationValidatorRuleType UPDATED_ACCEPTED_QUALIFICATION_RULE = DoctorQualificationValidatorRuleType.WITHOUT_QUALIFICATION_RULE;

    private static final PrescriptionSubsidyCategory DEFAULT_SUBSIDY_CATEGORY = PrescriptionSubsidyCategory.NORMATIVE;
    private static final PrescriptionSubsidyCategory UPDATED_SUBSIDY_CATEGORY = PrescriptionSubsidyCategory.EU_RAISED;

    private static final PrescriptionStatus DEFAULT_STATUS = PrescriptionStatus.PREPARED;
    private static final PrescriptionStatus UPDATED_STATUS = PrescriptionStatus.PRINTED;

    private static final EesztSendingStatus DEFAULT_EESZT_SENDING_STATUS = EesztSendingStatus.SUCCESSFUL;
    private static final EesztSendingStatus UPDATED_EESZT_SENDING_STATUS = EesztSendingStatus.UNSUCCESSFUL;

    private static final MedicalProductType DEFAULT_MEDICAL_PRODUCT_TYPE = MedicalProductType.MEDICINE;
    private static final MedicalProductType UPDATED_MEDICAL_PRODUCT_TYPE = MedicalProductType.MAGISTRAL_PREPARATION;

    private static final String DEFAULT_PREPARATION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PREPARATION_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVALIDATION_REASON = "AAAAAAAAAA";
    private static final String UPDATED_INVALIDATION_REASON = "BBBBBBBBBB";

    private static final Frequency DEFAULT_FREQUENCY = Frequency.DAILY;
    private static final Frequency UPDATED_FREQUENCY = Frequency.WEEKLY;

    private static final Double DEFAULT_FREQUENCY_MULTIPLIER = 1D;
    private static final Double UPDATED_FREQUENCY_MULTIPLIER = 2D;

    private static final Double DEFAULT_QUANTITY_MULTIPLIER = 1D;
    private static final Double UPDATED_QUANTITY_MULTIPLIER = 2D;

    private static final Double DEFAULT_MORNING = 1D;
    private static final Double UPDATED_MORNING = 2D;

    private static final Double DEFAULT_NOON = 1D;
    private static final Double UPDATED_NOON = 2D;

    private static final Double DEFAULT_EVENING = 1D;
    private static final Double UPDATED_EVENING = 2D;

    private static final Double DEFAULT_BEFORE_SLEEP = 1D;
    private static final Double UPDATED_BEFORE_SLEEP = 2D;

    private static final String DEFAULT_DOSAGE_PATTERN_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_DOSAGE_PATTERN_TEXT = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_QUANTITY_CAUSE = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY_CAUSE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FOR_SEVERAL_MONTHS = false;
    private static final Boolean UPDATED_FOR_SEVERAL_MONTHS = true;

    private static final Integer DEFAULT_MONTHS_SUPPLIED_FOR = 1;
    private static final Integer UPDATED_MONTHS_SUPPLIED_FOR = 2;

    private static final Boolean DEFAULT_FOR_ONE_PRESCRIPTION = false;
    private static final Boolean UPDATED_FOR_ONE_PRESCRIPTION = true;

    private static final String DEFAULT_SPECIALIST_REG_NO = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALIST_REG_NO = "BBBBBBBBBB";

    private static final Instant DEFAULT_PROPOSAL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROPOSAL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrescriptionMockMvc;

    private Prescription prescription;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prescription createEntity(EntityManager em) {
        Prescription prescription = new Prescription()
            .openedPackage(DEFAULT_OPENED_PACKAGE)
            .medicalAidTeachPrescribing(DEFAULT_MEDICAL_AID_TEACH_PRESCRIBING)
            .forH2cCertificate(DEFAULT_FOR_H_2_C_CERTIFICATE)
            .inscriptionDate(DEFAULT_INSCRIPTION_DATE)
            .causeOfNotReplaceability(DEFAULT_CAUSE_OF_NOT_REPLACEABILITY)
            .description(DEFAULT_DESCRIPTION)
            .doseMsmUnit(DEFAULT_DOSE_MSM_UNIT)
            .eesztGroupId(DEFAULT_EESZT_GROUP_ID)
            .institution(DEFAULT_INSTITUTION)
            .instructions(DEFAULT_INSTRUCTIONS)
            .integrationId(DEFAULT_INTEGRATION_ID)
            .equippedWith(DEFAULT_EQUIPPED_WITH)
            .acceptedQualificationRule(DEFAULT_ACCEPTED_QUALIFICATION_RULE)
            .subsidyCategory(DEFAULT_SUBSIDY_CATEGORY)
            .status(DEFAULT_STATUS)
            .eesztSendingStatus(DEFAULT_EESZT_SENDING_STATUS)
            .medicalProductType(DEFAULT_MEDICAL_PRODUCT_TYPE)
            .preparationDescription(DEFAULT_PREPARATION_DESCRIPTION)
            .invalidationReason(DEFAULT_INVALIDATION_REASON)
            .frequency(DEFAULT_FREQUENCY)
            .frequencyMultiplier(DEFAULT_FREQUENCY_MULTIPLIER)
            .quantityMultiplier(DEFAULT_QUANTITY_MULTIPLIER)
            .morning(DEFAULT_MORNING)
            .noon(DEFAULT_NOON)
            .evening(DEFAULT_EVENING)
            .beforeSleep(DEFAULT_BEFORE_SLEEP)
            .dosagePatternText(DEFAULT_DOSAGE_PATTERN_TEXT)
            .quantity(DEFAULT_QUANTITY)
            .quantityCause(DEFAULT_QUANTITY_CAUSE)
            .forSeveralMonths(DEFAULT_FOR_SEVERAL_MONTHS)
            .monthsSuppliedFor(DEFAULT_MONTHS_SUPPLIED_FOR)
            .forOnePrescription(DEFAULT_FOR_ONE_PRESCRIPTION)
            .specialistRegNo(DEFAULT_SPECIALIST_REG_NO)
            .proposalDate(DEFAULT_PROPOSAL_DATE);
        // Add required entity
        CsDiagnosis csDiagnosis;
        if (TestUtil.findAll(em, CsDiagnosis.class).isEmpty()) {
            csDiagnosis = CsDiagnosisResourceIT.createEntity(em);
            em.persist(csDiagnosis);
            em.flush();
        } else {
            csDiagnosis = TestUtil.findAll(em, CsDiagnosis.class).get(0);
        }
        prescription.setDiagnosis(csDiagnosis);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        prescription.setInscriberDoctor(employee);
        // Add required entity
        prescription.setQualificationRuleAcceptor(employee);
        // Add required entity
        PphMedicine pphMedicine;
        if (TestUtil.findAll(em, PphMedicine.class).isEmpty()) {
            pphMedicine = PphMedicineResourceIT.createEntity(em);
            em.persist(pphMedicine);
            em.flush();
        } else {
            pphMedicine = TestUtil.findAll(em, PphMedicine.class).get(0);
        }
        prescription.setMedicine(pphMedicine);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        prescription.setMedicalCase(medicalCase);
        // Add required entity
        PphPuphaVersion pphPuphaVersion;
        if (TestUtil.findAll(em, PphPuphaVersion.class).isEmpty()) {
            pphPuphaVersion = PphPuphaVersionResourceIT.createEntity(em);
            em.persist(pphPuphaVersion);
            em.flush();
        } else {
            pphPuphaVersion = TestUtil.findAll(em, PphPuphaVersion.class).get(0);
        }
        prescription.setPuphaVersion(pphPuphaVersion);
        return prescription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prescription createUpdatedEntity(EntityManager em) {
        Prescription prescription = new Prescription()
            .openedPackage(UPDATED_OPENED_PACKAGE)
            .medicalAidTeachPrescribing(UPDATED_MEDICAL_AID_TEACH_PRESCRIBING)
            .forH2cCertificate(UPDATED_FOR_H_2_C_CERTIFICATE)
            .inscriptionDate(UPDATED_INSCRIPTION_DATE)
            .causeOfNotReplaceability(UPDATED_CAUSE_OF_NOT_REPLACEABILITY)
            .description(UPDATED_DESCRIPTION)
            .doseMsmUnit(UPDATED_DOSE_MSM_UNIT)
            .eesztGroupId(UPDATED_EESZT_GROUP_ID)
            .institution(UPDATED_INSTITUTION)
            .instructions(UPDATED_INSTRUCTIONS)
            .integrationId(UPDATED_INTEGRATION_ID)
            .equippedWith(UPDATED_EQUIPPED_WITH)
            .acceptedQualificationRule(UPDATED_ACCEPTED_QUALIFICATION_RULE)
            .subsidyCategory(UPDATED_SUBSIDY_CATEGORY)
            .status(UPDATED_STATUS)
            .eesztSendingStatus(UPDATED_EESZT_SENDING_STATUS)
            .medicalProductType(UPDATED_MEDICAL_PRODUCT_TYPE)
            .preparationDescription(UPDATED_PREPARATION_DESCRIPTION)
            .invalidationReason(UPDATED_INVALIDATION_REASON)
            .frequency(UPDATED_FREQUENCY)
            .frequencyMultiplier(UPDATED_FREQUENCY_MULTIPLIER)
            .quantityMultiplier(UPDATED_QUANTITY_MULTIPLIER)
            .morning(UPDATED_MORNING)
            .noon(UPDATED_NOON)
            .evening(UPDATED_EVENING)
            .beforeSleep(UPDATED_BEFORE_SLEEP)
            .dosagePatternText(UPDATED_DOSAGE_PATTERN_TEXT)
            .quantity(UPDATED_QUANTITY)
            .quantityCause(UPDATED_QUANTITY_CAUSE)
            .forSeveralMonths(UPDATED_FOR_SEVERAL_MONTHS)
            .monthsSuppliedFor(UPDATED_MONTHS_SUPPLIED_FOR)
            .forOnePrescription(UPDATED_FOR_ONE_PRESCRIPTION)
            .specialistRegNo(UPDATED_SPECIALIST_REG_NO)
            .proposalDate(UPDATED_PROPOSAL_DATE);
        // Add required entity
        CsDiagnosis csDiagnosis;
        if (TestUtil.findAll(em, CsDiagnosis.class).isEmpty()) {
            csDiagnosis = CsDiagnosisResourceIT.createUpdatedEntity(em);
            em.persist(csDiagnosis);
            em.flush();
        } else {
            csDiagnosis = TestUtil.findAll(em, CsDiagnosis.class).get(0);
        }
        prescription.setDiagnosis(csDiagnosis);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createUpdatedEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        prescription.setInscriberDoctor(employee);
        // Add required entity
        prescription.setQualificationRuleAcceptor(employee);
        // Add required entity
        PphMedicine pphMedicine;
        if (TestUtil.findAll(em, PphMedicine.class).isEmpty()) {
            pphMedicine = PphMedicineResourceIT.createUpdatedEntity(em);
            em.persist(pphMedicine);
            em.flush();
        } else {
            pphMedicine = TestUtil.findAll(em, PphMedicine.class).get(0);
        }
        prescription.setMedicine(pphMedicine);
        // Add required entity
        MedicalCase medicalCase;
        if (TestUtil.findAll(em, MedicalCase.class).isEmpty()) {
            medicalCase = MedicalCaseResourceIT.createUpdatedEntity(em);
            em.persist(medicalCase);
            em.flush();
        } else {
            medicalCase = TestUtil.findAll(em, MedicalCase.class).get(0);
        }
        prescription.setMedicalCase(medicalCase);
        // Add required entity
        PphPuphaVersion pphPuphaVersion;
        if (TestUtil.findAll(em, PphPuphaVersion.class).isEmpty()) {
            pphPuphaVersion = PphPuphaVersionResourceIT.createUpdatedEntity(em);
            em.persist(pphPuphaVersion);
            em.flush();
        } else {
            pphPuphaVersion = TestUtil.findAll(em, PphPuphaVersion.class).get(0);
        }
        prescription.setPuphaVersion(pphPuphaVersion);
        return prescription;
    }

    @BeforeEach
    public void initTest() {
        prescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrescription() throws Exception {
        int databaseSizeBeforeCreate = prescriptionRepository.findAll().size();
        // Create the Prescription
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);
        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        Prescription testPrescription = prescriptionList.get(prescriptionList.size() - 1);
        assertThat(testPrescription.isOpenedPackage()).isEqualTo(DEFAULT_OPENED_PACKAGE);
        assertThat(testPrescription.isMedicalAidTeachPrescribing()).isEqualTo(DEFAULT_MEDICAL_AID_TEACH_PRESCRIBING);
        assertThat(testPrescription.isForH2cCertificate()).isEqualTo(DEFAULT_FOR_H_2_C_CERTIFICATE);
        assertThat(testPrescription.getInscriptionDate()).isEqualTo(DEFAULT_INSCRIPTION_DATE);
        assertThat(testPrescription.getCauseOfNotReplaceability()).isEqualTo(DEFAULT_CAUSE_OF_NOT_REPLACEABILITY);
        assertThat(testPrescription.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPrescription.getDoseMsmUnit()).isEqualTo(DEFAULT_DOSE_MSM_UNIT);
        assertThat(testPrescription.getEesztGroupId()).isEqualTo(DEFAULT_EESZT_GROUP_ID);
        assertThat(testPrescription.getInstitution()).isEqualTo(DEFAULT_INSTITUTION);
        assertThat(testPrescription.getInstructions()).isEqualTo(DEFAULT_INSTRUCTIONS);
        assertThat(testPrescription.getIntegrationId()).isEqualTo(DEFAULT_INTEGRATION_ID);
        assertThat(testPrescription.getEquippedWith()).isEqualTo(DEFAULT_EQUIPPED_WITH);
        assertThat(testPrescription.getAcceptedQualificationRule()).isEqualTo(DEFAULT_ACCEPTED_QUALIFICATION_RULE);
        assertThat(testPrescription.getSubsidyCategory()).isEqualTo(DEFAULT_SUBSIDY_CATEGORY);
        assertThat(testPrescription.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPrescription.getEesztSendingStatus()).isEqualTo(DEFAULT_EESZT_SENDING_STATUS);
        assertThat(testPrescription.getMedicalProductType()).isEqualTo(DEFAULT_MEDICAL_PRODUCT_TYPE);
        assertThat(testPrescription.getPreparationDescription()).isEqualTo(DEFAULT_PREPARATION_DESCRIPTION);
        assertThat(testPrescription.getInvalidationReason()).isEqualTo(DEFAULT_INVALIDATION_REASON);
        assertThat(testPrescription.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testPrescription.getFrequencyMultiplier()).isEqualTo(DEFAULT_FREQUENCY_MULTIPLIER);
        assertThat(testPrescription.getQuantityMultiplier()).isEqualTo(DEFAULT_QUANTITY_MULTIPLIER);
        assertThat(testPrescription.getMorning()).isEqualTo(DEFAULT_MORNING);
        assertThat(testPrescription.getNoon()).isEqualTo(DEFAULT_NOON);
        assertThat(testPrescription.getEvening()).isEqualTo(DEFAULT_EVENING);
        assertThat(testPrescription.getBeforeSleep()).isEqualTo(DEFAULT_BEFORE_SLEEP);
        assertThat(testPrescription.getDosagePatternText()).isEqualTo(DEFAULT_DOSAGE_PATTERN_TEXT);
        assertThat(testPrescription.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testPrescription.getQuantityCause()).isEqualTo(DEFAULT_QUANTITY_CAUSE);
        assertThat(testPrescription.isForSeveralMonths()).isEqualTo(DEFAULT_FOR_SEVERAL_MONTHS);
        assertThat(testPrescription.getMonthsSuppliedFor()).isEqualTo(DEFAULT_MONTHS_SUPPLIED_FOR);
        assertThat(testPrescription.isForOnePrescription()).isEqualTo(DEFAULT_FOR_ONE_PRESCRIPTION);
        assertThat(testPrescription.getSpecialistRegNo()).isEqualTo(DEFAULT_SPECIALIST_REG_NO);
        assertThat(testPrescription.getProposalDate()).isEqualTo(DEFAULT_PROPOSAL_DATE);
    }

    @Test
    @Transactional
    public void createPrescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prescriptionRepository.findAll().size();

        // Create the Prescription with an existing ID
        prescription.setId(1L);
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkInscriptionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = prescriptionRepository.findAll().size();
        // set the field null
        prescription.setInscriptionDate(null);

        // Create the Prescription, which fails.
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);


        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAcceptedQualificationRuleIsRequired() throws Exception {
        int databaseSizeBeforeTest = prescriptionRepository.findAll().size();
        // set the field null
        prescription.setAcceptedQualificationRule(null);

        // Create the Prescription, which fails.
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);


        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = prescriptionRepository.findAll().size();
        // set the field null
        prescription.setStatus(null);

        // Create the Prescription, which fails.
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);


        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMedicalProductTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = prescriptionRepository.findAll().size();
        // set the field null
        prescription.setMedicalProductType(null);

        // Create the Prescription, which fails.
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);


        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrequencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = prescriptionRepository.findAll().size();
        // set the field null
        prescription.setFrequency(null);

        // Create the Prescription, which fails.
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);


        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkForSeveralMonthsIsRequired() throws Exception {
        int databaseSizeBeforeTest = prescriptionRepository.findAll().size();
        // set the field null
        prescription.setForSeveralMonths(null);

        // Create the Prescription, which fails.
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);


        restPrescriptionMockMvc.perform(post("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrescriptions() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList
        restPrescriptionMockMvc.perform(get("/api/prescriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].openedPackage").value(hasItem(DEFAULT_OPENED_PACKAGE.booleanValue())))
            .andExpect(jsonPath("$.[*].medicalAidTeachPrescribing").value(hasItem(DEFAULT_MEDICAL_AID_TEACH_PRESCRIBING.booleanValue())))
            .andExpect(jsonPath("$.[*].forH2cCertificate").value(hasItem(DEFAULT_FOR_H_2_C_CERTIFICATE.booleanValue())))
            .andExpect(jsonPath("$.[*].inscriptionDate").value(hasItem(DEFAULT_INSCRIPTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].causeOfNotReplaceability").value(hasItem(DEFAULT_CAUSE_OF_NOT_REPLACEABILITY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].doseMsmUnit").value(hasItem(DEFAULT_DOSE_MSM_UNIT)))
            .andExpect(jsonPath("$.[*].eesztGroupId").value(hasItem(DEFAULT_EESZT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].institution").value(hasItem(DEFAULT_INSTITUTION)))
            .andExpect(jsonPath("$.[*].instructions").value(hasItem(DEFAULT_INSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].integrationId").value(hasItem(DEFAULT_INTEGRATION_ID)))
            .andExpect(jsonPath("$.[*].equippedWith").value(hasItem(DEFAULT_EQUIPPED_WITH)))
            .andExpect(jsonPath("$.[*].acceptedQualificationRule").value(hasItem(DEFAULT_ACCEPTED_QUALIFICATION_RULE.toString())))
            .andExpect(jsonPath("$.[*].subsidyCategory").value(hasItem(DEFAULT_SUBSIDY_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].eesztSendingStatus").value(hasItem(DEFAULT_EESZT_SENDING_STATUS.toString())))
            .andExpect(jsonPath("$.[*].medicalProductType").value(hasItem(DEFAULT_MEDICAL_PRODUCT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].preparationDescription").value(hasItem(DEFAULT_PREPARATION_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].invalidationReason").value(hasItem(DEFAULT_INVALIDATION_REASON)))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY.toString())))
            .andExpect(jsonPath("$.[*].frequencyMultiplier").value(hasItem(DEFAULT_FREQUENCY_MULTIPLIER.doubleValue())))
            .andExpect(jsonPath("$.[*].quantityMultiplier").value(hasItem(DEFAULT_QUANTITY_MULTIPLIER.doubleValue())))
            .andExpect(jsonPath("$.[*].morning").value(hasItem(DEFAULT_MORNING.doubleValue())))
            .andExpect(jsonPath("$.[*].noon").value(hasItem(DEFAULT_NOON.doubleValue())))
            .andExpect(jsonPath("$.[*].evening").value(hasItem(DEFAULT_EVENING.doubleValue())))
            .andExpect(jsonPath("$.[*].beforeSleep").value(hasItem(DEFAULT_BEFORE_SLEEP.doubleValue())))
            .andExpect(jsonPath("$.[*].dosagePatternText").value(hasItem(DEFAULT_DOSAGE_PATTERN_TEXT)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].quantityCause").value(hasItem(DEFAULT_QUANTITY_CAUSE)))
            .andExpect(jsonPath("$.[*].forSeveralMonths").value(hasItem(DEFAULT_FOR_SEVERAL_MONTHS.booleanValue())))
            .andExpect(jsonPath("$.[*].monthsSuppliedFor").value(hasItem(DEFAULT_MONTHS_SUPPLIED_FOR)))
            .andExpect(jsonPath("$.[*].forOnePrescription").value(hasItem(DEFAULT_FOR_ONE_PRESCRIPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].specialistRegNo").value(hasItem(DEFAULT_SPECIALIST_REG_NO)))
            .andExpect(jsonPath("$.[*].proposalDate").value(hasItem(DEFAULT_PROPOSAL_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getPrescription() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get the prescription
        restPrescriptionMockMvc.perform(get("/api/prescriptions/{id}", prescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prescription.getId().intValue()))
            .andExpect(jsonPath("$.openedPackage").value(DEFAULT_OPENED_PACKAGE.booleanValue()))
            .andExpect(jsonPath("$.medicalAidTeachPrescribing").value(DEFAULT_MEDICAL_AID_TEACH_PRESCRIBING.booleanValue()))
            .andExpect(jsonPath("$.forH2cCertificate").value(DEFAULT_FOR_H_2_C_CERTIFICATE.booleanValue()))
            .andExpect(jsonPath("$.inscriptionDate").value(DEFAULT_INSCRIPTION_DATE.toString()))
            .andExpect(jsonPath("$.causeOfNotReplaceability").value(DEFAULT_CAUSE_OF_NOT_REPLACEABILITY))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.doseMsmUnit").value(DEFAULT_DOSE_MSM_UNIT))
            .andExpect(jsonPath("$.eesztGroupId").value(DEFAULT_EESZT_GROUP_ID))
            .andExpect(jsonPath("$.institution").value(DEFAULT_INSTITUTION))
            .andExpect(jsonPath("$.instructions").value(DEFAULT_INSTRUCTIONS))
            .andExpect(jsonPath("$.integrationId").value(DEFAULT_INTEGRATION_ID))
            .andExpect(jsonPath("$.equippedWith").value(DEFAULT_EQUIPPED_WITH))
            .andExpect(jsonPath("$.acceptedQualificationRule").value(DEFAULT_ACCEPTED_QUALIFICATION_RULE.toString()))
            .andExpect(jsonPath("$.subsidyCategory").value(DEFAULT_SUBSIDY_CATEGORY.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.eesztSendingStatus").value(DEFAULT_EESZT_SENDING_STATUS.toString()))
            .andExpect(jsonPath("$.medicalProductType").value(DEFAULT_MEDICAL_PRODUCT_TYPE.toString()))
            .andExpect(jsonPath("$.preparationDescription").value(DEFAULT_PREPARATION_DESCRIPTION))
            .andExpect(jsonPath("$.invalidationReason").value(DEFAULT_INVALIDATION_REASON))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY.toString()))
            .andExpect(jsonPath("$.frequencyMultiplier").value(DEFAULT_FREQUENCY_MULTIPLIER.doubleValue()))
            .andExpect(jsonPath("$.quantityMultiplier").value(DEFAULT_QUANTITY_MULTIPLIER.doubleValue()))
            .andExpect(jsonPath("$.morning").value(DEFAULT_MORNING.doubleValue()))
            .andExpect(jsonPath("$.noon").value(DEFAULT_NOON.doubleValue()))
            .andExpect(jsonPath("$.evening").value(DEFAULT_EVENING.doubleValue()))
            .andExpect(jsonPath("$.beforeSleep").value(DEFAULT_BEFORE_SLEEP.doubleValue()))
            .andExpect(jsonPath("$.dosagePatternText").value(DEFAULT_DOSAGE_PATTERN_TEXT))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.quantityCause").value(DEFAULT_QUANTITY_CAUSE))
            .andExpect(jsonPath("$.forSeveralMonths").value(DEFAULT_FOR_SEVERAL_MONTHS.booleanValue()))
            .andExpect(jsonPath("$.monthsSuppliedFor").value(DEFAULT_MONTHS_SUPPLIED_FOR))
            .andExpect(jsonPath("$.forOnePrescription").value(DEFAULT_FOR_ONE_PRESCRIPTION.booleanValue()))
            .andExpect(jsonPath("$.specialistRegNo").value(DEFAULT_SPECIALIST_REG_NO))
            .andExpect(jsonPath("$.proposalDate").value(DEFAULT_PROPOSAL_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPrescription() throws Exception {
        // Get the prescription
        restPrescriptionMockMvc.perform(get("/api/prescriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrescription() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        int databaseSizeBeforeUpdate = prescriptionRepository.findAll().size();

        // Update the prescription
        Prescription updatedPrescription = prescriptionRepository.findById(prescription.getId()).get();
        // Disconnect from session so that the updates on updatedPrescription are not directly saved in db
        em.detach(updatedPrescription);
        updatedPrescription
            .openedPackage(UPDATED_OPENED_PACKAGE)
            .medicalAidTeachPrescribing(UPDATED_MEDICAL_AID_TEACH_PRESCRIBING)
            .forH2cCertificate(UPDATED_FOR_H_2_C_CERTIFICATE)
            .inscriptionDate(UPDATED_INSCRIPTION_DATE)
            .causeOfNotReplaceability(UPDATED_CAUSE_OF_NOT_REPLACEABILITY)
            .description(UPDATED_DESCRIPTION)
            .doseMsmUnit(UPDATED_DOSE_MSM_UNIT)
            .eesztGroupId(UPDATED_EESZT_GROUP_ID)
            .institution(UPDATED_INSTITUTION)
            .instructions(UPDATED_INSTRUCTIONS)
            .integrationId(UPDATED_INTEGRATION_ID)
            .equippedWith(UPDATED_EQUIPPED_WITH)
            .acceptedQualificationRule(UPDATED_ACCEPTED_QUALIFICATION_RULE)
            .subsidyCategory(UPDATED_SUBSIDY_CATEGORY)
            .status(UPDATED_STATUS)
            .eesztSendingStatus(UPDATED_EESZT_SENDING_STATUS)
            .medicalProductType(UPDATED_MEDICAL_PRODUCT_TYPE)
            .preparationDescription(UPDATED_PREPARATION_DESCRIPTION)
            .invalidationReason(UPDATED_INVALIDATION_REASON)
            .frequency(UPDATED_FREQUENCY)
            .frequencyMultiplier(UPDATED_FREQUENCY_MULTIPLIER)
            .quantityMultiplier(UPDATED_QUANTITY_MULTIPLIER)
            .morning(UPDATED_MORNING)
            .noon(UPDATED_NOON)
            .evening(UPDATED_EVENING)
            .beforeSleep(UPDATED_BEFORE_SLEEP)
            .dosagePatternText(UPDATED_DOSAGE_PATTERN_TEXT)
            .quantity(UPDATED_QUANTITY)
            .quantityCause(UPDATED_QUANTITY_CAUSE)
            .forSeveralMonths(UPDATED_FOR_SEVERAL_MONTHS)
            .monthsSuppliedFor(UPDATED_MONTHS_SUPPLIED_FOR)
            .forOnePrescription(UPDATED_FOR_ONE_PRESCRIPTION)
            .specialistRegNo(UPDATED_SPECIALIST_REG_NO)
            .proposalDate(UPDATED_PROPOSAL_DATE);
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(updatedPrescription);

        restPrescriptionMockMvc.perform(put("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isOk());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeUpdate);
        Prescription testPrescription = prescriptionList.get(prescriptionList.size() - 1);
        assertThat(testPrescription.isOpenedPackage()).isEqualTo(UPDATED_OPENED_PACKAGE);
        assertThat(testPrescription.isMedicalAidTeachPrescribing()).isEqualTo(UPDATED_MEDICAL_AID_TEACH_PRESCRIBING);
        assertThat(testPrescription.isForH2cCertificate()).isEqualTo(UPDATED_FOR_H_2_C_CERTIFICATE);
        assertThat(testPrescription.getInscriptionDate()).isEqualTo(UPDATED_INSCRIPTION_DATE);
        assertThat(testPrescription.getCauseOfNotReplaceability()).isEqualTo(UPDATED_CAUSE_OF_NOT_REPLACEABILITY);
        assertThat(testPrescription.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPrescription.getDoseMsmUnit()).isEqualTo(UPDATED_DOSE_MSM_UNIT);
        assertThat(testPrescription.getEesztGroupId()).isEqualTo(UPDATED_EESZT_GROUP_ID);
        assertThat(testPrescription.getInstitution()).isEqualTo(UPDATED_INSTITUTION);
        assertThat(testPrescription.getInstructions()).isEqualTo(UPDATED_INSTRUCTIONS);
        assertThat(testPrescription.getIntegrationId()).isEqualTo(UPDATED_INTEGRATION_ID);
        assertThat(testPrescription.getEquippedWith()).isEqualTo(UPDATED_EQUIPPED_WITH);
        assertThat(testPrescription.getAcceptedQualificationRule()).isEqualTo(UPDATED_ACCEPTED_QUALIFICATION_RULE);
        assertThat(testPrescription.getSubsidyCategory()).isEqualTo(UPDATED_SUBSIDY_CATEGORY);
        assertThat(testPrescription.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPrescription.getEesztSendingStatus()).isEqualTo(UPDATED_EESZT_SENDING_STATUS);
        assertThat(testPrescription.getMedicalProductType()).isEqualTo(UPDATED_MEDICAL_PRODUCT_TYPE);
        assertThat(testPrescription.getPreparationDescription()).isEqualTo(UPDATED_PREPARATION_DESCRIPTION);
        assertThat(testPrescription.getInvalidationReason()).isEqualTo(UPDATED_INVALIDATION_REASON);
        assertThat(testPrescription.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testPrescription.getFrequencyMultiplier()).isEqualTo(UPDATED_FREQUENCY_MULTIPLIER);
        assertThat(testPrescription.getQuantityMultiplier()).isEqualTo(UPDATED_QUANTITY_MULTIPLIER);
        assertThat(testPrescription.getMorning()).isEqualTo(UPDATED_MORNING);
        assertThat(testPrescription.getNoon()).isEqualTo(UPDATED_NOON);
        assertThat(testPrescription.getEvening()).isEqualTo(UPDATED_EVENING);
        assertThat(testPrescription.getBeforeSleep()).isEqualTo(UPDATED_BEFORE_SLEEP);
        assertThat(testPrescription.getDosagePatternText()).isEqualTo(UPDATED_DOSAGE_PATTERN_TEXT);
        assertThat(testPrescription.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testPrescription.getQuantityCause()).isEqualTo(UPDATED_QUANTITY_CAUSE);
        assertThat(testPrescription.isForSeveralMonths()).isEqualTo(UPDATED_FOR_SEVERAL_MONTHS);
        assertThat(testPrescription.getMonthsSuppliedFor()).isEqualTo(UPDATED_MONTHS_SUPPLIED_FOR);
        assertThat(testPrescription.isForOnePrescription()).isEqualTo(UPDATED_FOR_ONE_PRESCRIPTION);
        assertThat(testPrescription.getSpecialistRegNo()).isEqualTo(UPDATED_SPECIALIST_REG_NO);
        assertThat(testPrescription.getProposalDate()).isEqualTo(UPDATED_PROPOSAL_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPrescription() throws Exception {
        int databaseSizeBeforeUpdate = prescriptionRepository.findAll().size();

        // Create the Prescription
        PrescriptionDTO prescriptionDTO = prescriptionMapper.toDto(prescription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrescriptionMockMvc.perform(put("/api/prescriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prescriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrescription() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        int databaseSizeBeforeDelete = prescriptionRepository.findAll().size();

        // Delete the prescription
        restPrescriptionMockMvc.perform(delete("/api/prescriptions/{id}", prescription.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
