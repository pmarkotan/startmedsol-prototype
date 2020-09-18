package hu.paninform.startmedsol.web.rest;

import hu.paninform.startmedsol.StartMedsolPrototypeApp;
import hu.paninform.startmedsol.domain.PphMedicine;
import hu.paninform.startmedsol.repository.PphMedicineRepository;
import hu.paninform.startmedsol.service.PphMedicineService;
import hu.paninform.startmedsol.service.dto.PphMedicineDTO;
import hu.paninform.startmedsol.service.mapper.PphMedicineMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.paninform.startmedsol.domain.enumeration.PrescriptionType;
import hu.paninform.startmedsol.domain.enumeration.MedicineType;
import hu.paninform.startmedsol.domain.enumeration.MedicineStatus;
/**
 * Integration tests for the {@link PphMedicineResource} REST controller.
 */
@SpringBootTest(classes = StartMedsolPrototypeApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PphMedicineResourceIT {

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    private static final String DEFAULT_MASTER_BOOK_ID = "AAAAAAAAAA";
    private static final String UPDATED_MASTER_BOOK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PACKAGE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EAN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EAN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ATC_CODE = "AAAAAAAA";
    private static final String UPDATED_ATC_CODE = "BBBBBBBB";

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE_SUBSTANCE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE_SUBSTANCE = "BBBBBBBBBB";

    private static final String DEFAULT_POTENCY = "AAAAAAAAAA";
    private static final String UPDATED_POTENCY = "BBBBBBBBBB";

    private static final Double DEFAULT_SUBSTANCE_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_SUBSTANCE_AMOUNT_TOTAL = 2D;

    private static final Integer DEFAULT_DOSE_IN_PACKAGE = 1;
    private static final Integer UPDATED_DOSE_IN_PACKAGE = 2;

    private static final Double DEFAULT_SUBSTANCE_AMOUNT = 1D;
    private static final Double UPDATED_SUBSTANCE_AMOUNT = 2D;

    private static final String DEFAULT_DOSE_MSM_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_DOSE_MSM_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSTANCE_MSM_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_SUBSTANCE_MSM_UNIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_PACKAGE_SIZE = 1;
    private static final Integer UPDATED_PACKAGE_SIZE = 2;

    private static final String DEFAULT_PACKAGE_MSM_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_PACKAGE_MSM_UNIT = "BBBBBBBBBB";

    private static final Double DEFAULT_DAILY_DOZE = 1D;
    private static final Double UPDATED_DAILY_DOZE = 2D;

    private static final String DEFAULT_DAILY_DOZE_MSM_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_DAILY_DOZE_MSM_UNIT = "BBBBBBBBBB";

    private static final Double DEFAULT_DD_MSM_UNIT_FACTOR = 1D;
    private static final Double UPDATED_DD_MSM_UNIT_FACTOR = 2D;

    private static final Integer DEFAULT_DAYS_OF_THERAPY = 1;
    private static final Integer UPDATED_DAYS_OF_THERAPY = 2;

    private static final Double DEFAULT_CONSUMER_PRICE = 1D;
    private static final Double UPDATED_CONSUMER_PRICE = 2D;

    private static final Double DEFAULT_GROSS_CONSUMER_PRICE = 1D;
    private static final Double UPDATED_GROSS_CONSUMER_PRICE = 2D;

    private static final String DEFAULT_EFFICACY = "AAAAAAAAAA";
    private static final String UPDATED_EFFICACY = "BBBBBBBBBB";

    private static final String DEFAULT_OLD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OLD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OEP_TTT = "AAAAAAAAA";
    private static final String UPDATED_OEP_TTT = "BBBBBBBBB";

    private static final Boolean DEFAULT_MASTER_BOOK_DELETED = false;
    private static final Boolean UPDATED_MASTER_BOOK_DELETED = true;

    private static final Instant DEFAULT_MBOOK_DELETED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MBOOK_DELETED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_AVAILABLE = false;
    private static final Boolean UPDATED_AVAILABLE = true;

    private static final Integer DEFAULT_MOTIVATION_STATUS = 1;
    private static final Integer UPDATED_MOTIVATION_STATUS = 2;

    private static final Integer DEFAULT_COST_EFFECTIVITY_CODE = 1;
    private static final Integer UPDATED_COST_EFFECTIVITY_CODE = 2;

    private static final Double DEFAULT_DAILY_THERAPY_COST = 1D;
    private static final Double UPDATED_DAILY_THERAPY_COST = 2D;

    private static final Integer DEFAULT_EQUIVALENCE_GROUP_ID = 1;
    private static final Integer UPDATED_EQUIVALENCE_GROUP_ID = 2;

    private static final String DEFAULT_OLD_SUBSIDY_TYPE = "AAAA";
    private static final String UPDATED_OLD_SUBSIDY_TYPE = "BBBB";

    private static final Integer DEFAULT_PREFERRED_PRICE_FLAG = 1;
    private static final Integer UPDATED_PREFERRED_PRICE_FLAG = 2;

    private static final String DEFAULT_PHARMACY_FLAG = "AA";
    private static final String UPDATED_PHARMACY_FLAG = "BB";

    private static final Boolean DEFAULT_CUSTOM_MADE = false;
    private static final Boolean UPDATED_CUSTOM_MADE = true;

    private static final String DEFAULT_MEDICAL_DEVICE_ISO = "AAAAAAAAAA";
    private static final String UPDATED_MEDICAL_DEVICE_ISO = "BBBBBBBBBB";

    private static final Double DEFAULT_PRODUCER_PRICE = 1D;
    private static final Double UPDATED_PRODUCER_PRICE = 2D;

    private static final Double DEFAULT_WHOLESALE_PRICE = 1D;
    private static final Double UPDATED_WHOLESALE_PRICE = 2D;

    private static final Double DEFAULT_VAT_RATE = 1D;
    private static final Double UPDATED_VAT_RATE = 2D;

    private static final Boolean DEFAULT_ARMY_PRESCRIPTION = false;
    private static final Boolean UPDATED_ARMY_PRESCRIPTION = true;

    private static final Boolean DEFAULT_PUBLIC_SUPPLY = false;
    private static final Boolean UPDATED_PUBLIC_SUPPLY = true;

    private static final Boolean DEFAULT_WORK_ACCIDENT_PRESCR = false;
    private static final Boolean UPDATED_WORK_ACCIDENT_PRESCR = true;

    private static final Boolean DEFAULT_EXTRA_SUBSIDY_PRESCR = false;
    private static final Boolean UPDATED_EXTRA_SUBSIDY_PRESCR = true;

    private static final Boolean DEFAULT_MEDICAL_SUBSIDY_PRESCR = false;
    private static final Boolean UPDATED_MEDICAL_SUBSIDY_PRESCR = true;

    private static final Double DEFAULT_SUBSTANCE_PRICE = 1D;
    private static final Double UPDATED_SUBSTANCE_PRICE = 2D;

    private static final Integer DEFAULT_INCLUDE_IN_HC_2 = 1;
    private static final Integer UPDATED_INCLUDE_IN_HC_2 = 2;

    private static final PrescriptionType DEFAULT_PRESCRIPTION_TYPE = PrescriptionType.WITHOUT_PRESCRIPTION;
    private static final PrescriptionType UPDATED_PRESCRIPTION_TYPE = PrescriptionType.ONLY_WITH_PRESCRIPTION;

    private static final MedicineType DEFAULT_MEDICINE_TYPE = MedicineType.MEDICINE;
    private static final MedicineType UPDATED_MEDICINE_TYPE = MedicineType.IMMUN;

    private static final MedicineStatus DEFAULT_MEDICINE_STATUS = MedicineStatus.NEW_PRODUCT;
    private static final MedicineStatus UPDATED_MEDICINE_STATUS = MedicineStatus.MODIFIED;

    private static final Boolean DEFAULT_NORMATIVE = false;
    private static final Boolean UPDATED_NORMATIVE = true;

    private static final Long DEFAULT_OGYI_ID = 1L;
    private static final Long UPDATED_OGYI_ID = 2L;

    private static final Integer DEFAULT_NORM_FIX_GROUP_ID = 1;
    private static final Integer UPDATED_NORM_FIX_GROUP_ID = 2;

    private static final Integer DEFAULT_EXTRA_SUBSIDY_FIX_GROUP_ID = 1;
    private static final Integer UPDATED_EXTRA_SUBSIDY_FIX_GROUP_ID = 2;

    private static final Integer DEFAULT_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID = 1;
    private static final Integer UPDATED_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID = 2;

    private static final String DEFAULT_DOSAGE_MOD = "AAAAAAAAAA";
    private static final String UPDATED_DOSAGE_MOD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE_PUPHA_DATA = false;
    private static final Boolean UPDATED_ACTIVE_PUPHA_DATA = true;

    @Autowired
    private PphMedicineRepository pphMedicineRepository;

    @Mock
    private PphMedicineRepository pphMedicineRepositoryMock;

    @Autowired
    private PphMedicineMapper pphMedicineMapper;

    @Mock
    private PphMedicineService pphMedicineServiceMock;

    @Autowired
    private PphMedicineService pphMedicineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPphMedicineMockMvc;

    private PphMedicine pphMedicine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMedicine createEntity(EntityManager em) {
        PphMedicine pphMedicine = new PphMedicine()
            .productId(DEFAULT_PRODUCT_ID)
            .masterBookId(DEFAULT_MASTER_BOOK_ID)
            .name(DEFAULT_NAME)
            .packageDescription(DEFAULT_PACKAGE_DESCRIPTION)
            .eanCode(DEFAULT_EAN_CODE)
            .atcCode(DEFAULT_ATC_CODE)
            .brand(DEFAULT_BRAND)
            .activeSubstance(DEFAULT_ACTIVE_SUBSTANCE)
            .potency(DEFAULT_POTENCY)
            .substanceAmountTotal(DEFAULT_SUBSTANCE_AMOUNT_TOTAL)
            .doseInPackage(DEFAULT_DOSE_IN_PACKAGE)
            .substanceAmount(DEFAULT_SUBSTANCE_AMOUNT)
            .doseMsmUnit(DEFAULT_DOSE_MSM_UNIT)
            .substanceMsmUnit(DEFAULT_SUBSTANCE_MSM_UNIT)
            .packageSize(DEFAULT_PACKAGE_SIZE)
            .packageMsmUnit(DEFAULT_PACKAGE_MSM_UNIT)
            .dailyDoze(DEFAULT_DAILY_DOZE)
            .dailyDozeMsmUnit(DEFAULT_DAILY_DOZE_MSM_UNIT)
            .ddMsmUnitFactor(DEFAULT_DD_MSM_UNIT_FACTOR)
            .daysOfTherapy(DEFAULT_DAYS_OF_THERAPY)
            .consumerPrice(DEFAULT_CONSUMER_PRICE)
            .grossConsumerPrice(DEFAULT_GROSS_CONSUMER_PRICE)
            .efficacy(DEFAULT_EFFICACY)
            .oldName(DEFAULT_OLD_NAME)
            .oepTtt(DEFAULT_OEP_TTT)
            .masterBookDeleted(DEFAULT_MASTER_BOOK_DELETED)
            .mbookDeletedDate(DEFAULT_MBOOK_DELETED_DATE)
            .available(DEFAULT_AVAILABLE)
            .motivationStatus(DEFAULT_MOTIVATION_STATUS)
            .costEffectivityCode(DEFAULT_COST_EFFECTIVITY_CODE)
            .dailyTherapyCost(DEFAULT_DAILY_THERAPY_COST)
            .equivalenceGroupId(DEFAULT_EQUIVALENCE_GROUP_ID)
            .oldSubsidyType(DEFAULT_OLD_SUBSIDY_TYPE)
            .preferredPriceFlag(DEFAULT_PREFERRED_PRICE_FLAG)
            .pharmacyFlag(DEFAULT_PHARMACY_FLAG)
            .customMade(DEFAULT_CUSTOM_MADE)
            .medicalDeviceIso(DEFAULT_MEDICAL_DEVICE_ISO)
            .producerPrice(DEFAULT_PRODUCER_PRICE)
            .wholesalePrice(DEFAULT_WHOLESALE_PRICE)
            .vatRate(DEFAULT_VAT_RATE)
            .armyPrescription(DEFAULT_ARMY_PRESCRIPTION)
            .publicSupply(DEFAULT_PUBLIC_SUPPLY)
            .workAccidentPrescr(DEFAULT_WORK_ACCIDENT_PRESCR)
            .extraSubsidyPrescr(DEFAULT_EXTRA_SUBSIDY_PRESCR)
            .medicalSubsidyPrescr(DEFAULT_MEDICAL_SUBSIDY_PRESCR)
            .substancePrice(DEFAULT_SUBSTANCE_PRICE)
            .includeInHc2(DEFAULT_INCLUDE_IN_HC_2)
            .prescriptionType(DEFAULT_PRESCRIPTION_TYPE)
            .medicineType(DEFAULT_MEDICINE_TYPE)
            .medicineStatus(DEFAULT_MEDICINE_STATUS)
            .normative(DEFAULT_NORMATIVE)
            .ogyiId(DEFAULT_OGYI_ID)
            .normFixGroupId(DEFAULT_NORM_FIX_GROUP_ID)
            .extraSubsidyFixGroupId(DEFAULT_EXTRA_SUBSIDY_FIX_GROUP_ID)
            .medicalSubsidyPrescriptionFixGroupId(DEFAULT_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID)
            .dosageMod(DEFAULT_DOSAGE_MOD)
            .activePuphaData(DEFAULT_ACTIVE_PUPHA_DATA);
        return pphMedicine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PphMedicine createUpdatedEntity(EntityManager em) {
        PphMedicine pphMedicine = new PphMedicine()
            .productId(UPDATED_PRODUCT_ID)
            .masterBookId(UPDATED_MASTER_BOOK_ID)
            .name(UPDATED_NAME)
            .packageDescription(UPDATED_PACKAGE_DESCRIPTION)
            .eanCode(UPDATED_EAN_CODE)
            .atcCode(UPDATED_ATC_CODE)
            .brand(UPDATED_BRAND)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .potency(UPDATED_POTENCY)
            .substanceAmountTotal(UPDATED_SUBSTANCE_AMOUNT_TOTAL)
            .doseInPackage(UPDATED_DOSE_IN_PACKAGE)
            .substanceAmount(UPDATED_SUBSTANCE_AMOUNT)
            .doseMsmUnit(UPDATED_DOSE_MSM_UNIT)
            .substanceMsmUnit(UPDATED_SUBSTANCE_MSM_UNIT)
            .packageSize(UPDATED_PACKAGE_SIZE)
            .packageMsmUnit(UPDATED_PACKAGE_MSM_UNIT)
            .dailyDoze(UPDATED_DAILY_DOZE)
            .dailyDozeMsmUnit(UPDATED_DAILY_DOZE_MSM_UNIT)
            .ddMsmUnitFactor(UPDATED_DD_MSM_UNIT_FACTOR)
            .daysOfTherapy(UPDATED_DAYS_OF_THERAPY)
            .consumerPrice(UPDATED_CONSUMER_PRICE)
            .grossConsumerPrice(UPDATED_GROSS_CONSUMER_PRICE)
            .efficacy(UPDATED_EFFICACY)
            .oldName(UPDATED_OLD_NAME)
            .oepTtt(UPDATED_OEP_TTT)
            .masterBookDeleted(UPDATED_MASTER_BOOK_DELETED)
            .mbookDeletedDate(UPDATED_MBOOK_DELETED_DATE)
            .available(UPDATED_AVAILABLE)
            .motivationStatus(UPDATED_MOTIVATION_STATUS)
            .costEffectivityCode(UPDATED_COST_EFFECTIVITY_CODE)
            .dailyTherapyCost(UPDATED_DAILY_THERAPY_COST)
            .equivalenceGroupId(UPDATED_EQUIVALENCE_GROUP_ID)
            .oldSubsidyType(UPDATED_OLD_SUBSIDY_TYPE)
            .preferredPriceFlag(UPDATED_PREFERRED_PRICE_FLAG)
            .pharmacyFlag(UPDATED_PHARMACY_FLAG)
            .customMade(UPDATED_CUSTOM_MADE)
            .medicalDeviceIso(UPDATED_MEDICAL_DEVICE_ISO)
            .producerPrice(UPDATED_PRODUCER_PRICE)
            .wholesalePrice(UPDATED_WHOLESALE_PRICE)
            .vatRate(UPDATED_VAT_RATE)
            .armyPrescription(UPDATED_ARMY_PRESCRIPTION)
            .publicSupply(UPDATED_PUBLIC_SUPPLY)
            .workAccidentPrescr(UPDATED_WORK_ACCIDENT_PRESCR)
            .extraSubsidyPrescr(UPDATED_EXTRA_SUBSIDY_PRESCR)
            .medicalSubsidyPrescr(UPDATED_MEDICAL_SUBSIDY_PRESCR)
            .substancePrice(UPDATED_SUBSTANCE_PRICE)
            .includeInHc2(UPDATED_INCLUDE_IN_HC_2)
            .prescriptionType(UPDATED_PRESCRIPTION_TYPE)
            .medicineType(UPDATED_MEDICINE_TYPE)
            .medicineStatus(UPDATED_MEDICINE_STATUS)
            .normative(UPDATED_NORMATIVE)
            .ogyiId(UPDATED_OGYI_ID)
            .normFixGroupId(UPDATED_NORM_FIX_GROUP_ID)
            .extraSubsidyFixGroupId(UPDATED_EXTRA_SUBSIDY_FIX_GROUP_ID)
            .medicalSubsidyPrescriptionFixGroupId(UPDATED_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID)
            .dosageMod(UPDATED_DOSAGE_MOD)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        return pphMedicine;
    }

    @BeforeEach
    public void initTest() {
        pphMedicine = createEntity(em);
    }

    @Test
    @Transactional
    public void createPphMedicine() throws Exception {
        int databaseSizeBeforeCreate = pphMedicineRepository.findAll().size();
        // Create the PphMedicine
        PphMedicineDTO pphMedicineDTO = pphMedicineMapper.toDto(pphMedicine);
        restPphMedicineMockMvc.perform(post("/api/pph-medicines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineDTO)))
            .andExpect(status().isCreated());

        // Validate the PphMedicine in the database
        List<PphMedicine> pphMedicineList = pphMedicineRepository.findAll();
        assertThat(pphMedicineList).hasSize(databaseSizeBeforeCreate + 1);
        PphMedicine testPphMedicine = pphMedicineList.get(pphMedicineList.size() - 1);
        assertThat(testPphMedicine.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testPphMedicine.getMasterBookId()).isEqualTo(DEFAULT_MASTER_BOOK_ID);
        assertThat(testPphMedicine.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPphMedicine.getPackageDescription()).isEqualTo(DEFAULT_PACKAGE_DESCRIPTION);
        assertThat(testPphMedicine.getEanCode()).isEqualTo(DEFAULT_EAN_CODE);
        assertThat(testPphMedicine.getAtcCode()).isEqualTo(DEFAULT_ATC_CODE);
        assertThat(testPphMedicine.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testPphMedicine.getActiveSubstance()).isEqualTo(DEFAULT_ACTIVE_SUBSTANCE);
        assertThat(testPphMedicine.getPotency()).isEqualTo(DEFAULT_POTENCY);
        assertThat(testPphMedicine.getSubstanceAmountTotal()).isEqualTo(DEFAULT_SUBSTANCE_AMOUNT_TOTAL);
        assertThat(testPphMedicine.getDoseInPackage()).isEqualTo(DEFAULT_DOSE_IN_PACKAGE);
        assertThat(testPphMedicine.getSubstanceAmount()).isEqualTo(DEFAULT_SUBSTANCE_AMOUNT);
        assertThat(testPphMedicine.getDoseMsmUnit()).isEqualTo(DEFAULT_DOSE_MSM_UNIT);
        assertThat(testPphMedicine.getSubstanceMsmUnit()).isEqualTo(DEFAULT_SUBSTANCE_MSM_UNIT);
        assertThat(testPphMedicine.getPackageSize()).isEqualTo(DEFAULT_PACKAGE_SIZE);
        assertThat(testPphMedicine.getPackageMsmUnit()).isEqualTo(DEFAULT_PACKAGE_MSM_UNIT);
        assertThat(testPphMedicine.getDailyDoze()).isEqualTo(DEFAULT_DAILY_DOZE);
        assertThat(testPphMedicine.getDailyDozeMsmUnit()).isEqualTo(DEFAULT_DAILY_DOZE_MSM_UNIT);
        assertThat(testPphMedicine.getDdMsmUnitFactor()).isEqualTo(DEFAULT_DD_MSM_UNIT_FACTOR);
        assertThat(testPphMedicine.getDaysOfTherapy()).isEqualTo(DEFAULT_DAYS_OF_THERAPY);
        assertThat(testPphMedicine.getConsumerPrice()).isEqualTo(DEFAULT_CONSUMER_PRICE);
        assertThat(testPphMedicine.getGrossConsumerPrice()).isEqualTo(DEFAULT_GROSS_CONSUMER_PRICE);
        assertThat(testPphMedicine.getEfficacy()).isEqualTo(DEFAULT_EFFICACY);
        assertThat(testPphMedicine.getOldName()).isEqualTo(DEFAULT_OLD_NAME);
        assertThat(testPphMedicine.getOepTtt()).isEqualTo(DEFAULT_OEP_TTT);
        assertThat(testPphMedicine.isMasterBookDeleted()).isEqualTo(DEFAULT_MASTER_BOOK_DELETED);
        assertThat(testPphMedicine.getMbookDeletedDate()).isEqualTo(DEFAULT_MBOOK_DELETED_DATE);
        assertThat(testPphMedicine.isAvailable()).isEqualTo(DEFAULT_AVAILABLE);
        assertThat(testPphMedicine.getMotivationStatus()).isEqualTo(DEFAULT_MOTIVATION_STATUS);
        assertThat(testPphMedicine.getCostEffectivityCode()).isEqualTo(DEFAULT_COST_EFFECTIVITY_CODE);
        assertThat(testPphMedicine.getDailyTherapyCost()).isEqualTo(DEFAULT_DAILY_THERAPY_COST);
        assertThat(testPphMedicine.getEquivalenceGroupId()).isEqualTo(DEFAULT_EQUIVALENCE_GROUP_ID);
        assertThat(testPphMedicine.getOldSubsidyType()).isEqualTo(DEFAULT_OLD_SUBSIDY_TYPE);
        assertThat(testPphMedicine.getPreferredPriceFlag()).isEqualTo(DEFAULT_PREFERRED_PRICE_FLAG);
        assertThat(testPphMedicine.getPharmacyFlag()).isEqualTo(DEFAULT_PHARMACY_FLAG);
        assertThat(testPphMedicine.isCustomMade()).isEqualTo(DEFAULT_CUSTOM_MADE);
        assertThat(testPphMedicine.getMedicalDeviceIso()).isEqualTo(DEFAULT_MEDICAL_DEVICE_ISO);
        assertThat(testPphMedicine.getProducerPrice()).isEqualTo(DEFAULT_PRODUCER_PRICE);
        assertThat(testPphMedicine.getWholesalePrice()).isEqualTo(DEFAULT_WHOLESALE_PRICE);
        assertThat(testPphMedicine.getVatRate()).isEqualTo(DEFAULT_VAT_RATE);
        assertThat(testPphMedicine.isArmyPrescription()).isEqualTo(DEFAULT_ARMY_PRESCRIPTION);
        assertThat(testPphMedicine.isPublicSupply()).isEqualTo(DEFAULT_PUBLIC_SUPPLY);
        assertThat(testPphMedicine.isWorkAccidentPrescr()).isEqualTo(DEFAULT_WORK_ACCIDENT_PRESCR);
        assertThat(testPphMedicine.isExtraSubsidyPrescr()).isEqualTo(DEFAULT_EXTRA_SUBSIDY_PRESCR);
        assertThat(testPphMedicine.isMedicalSubsidyPrescr()).isEqualTo(DEFAULT_MEDICAL_SUBSIDY_PRESCR);
        assertThat(testPphMedicine.getSubstancePrice()).isEqualTo(DEFAULT_SUBSTANCE_PRICE);
        assertThat(testPphMedicine.getIncludeInHc2()).isEqualTo(DEFAULT_INCLUDE_IN_HC_2);
        assertThat(testPphMedicine.getPrescriptionType()).isEqualTo(DEFAULT_PRESCRIPTION_TYPE);
        assertThat(testPphMedicine.getMedicineType()).isEqualTo(DEFAULT_MEDICINE_TYPE);
        assertThat(testPphMedicine.getMedicineStatus()).isEqualTo(DEFAULT_MEDICINE_STATUS);
        assertThat(testPphMedicine.isNormative()).isEqualTo(DEFAULT_NORMATIVE);
        assertThat(testPphMedicine.getOgyiId()).isEqualTo(DEFAULT_OGYI_ID);
        assertThat(testPphMedicine.getNormFixGroupId()).isEqualTo(DEFAULT_NORM_FIX_GROUP_ID);
        assertThat(testPphMedicine.getExtraSubsidyFixGroupId()).isEqualTo(DEFAULT_EXTRA_SUBSIDY_FIX_GROUP_ID);
        assertThat(testPphMedicine.getMedicalSubsidyPrescriptionFixGroupId()).isEqualTo(DEFAULT_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID);
        assertThat(testPphMedicine.getDosageMod()).isEqualTo(DEFAULT_DOSAGE_MOD);
        assertThat(testPphMedicine.isActivePuphaData()).isEqualTo(DEFAULT_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void createPphMedicineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pphMedicineRepository.findAll().size();

        // Create the PphMedicine with an existing ID
        pphMedicine.setId(1L);
        PphMedicineDTO pphMedicineDTO = pphMedicineMapper.toDto(pphMedicine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPphMedicineMockMvc.perform(post("/api/pph-medicines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PphMedicine in the database
        List<PphMedicine> pphMedicineList = pphMedicineRepository.findAll();
        assertThat(pphMedicineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkActivePuphaDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = pphMedicineRepository.findAll().size();
        // set the field null
        pphMedicine.setActivePuphaData(null);

        // Create the PphMedicine, which fails.
        PphMedicineDTO pphMedicineDTO = pphMedicineMapper.toDto(pphMedicine);


        restPphMedicineMockMvc.perform(post("/api/pph-medicines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineDTO)))
            .andExpect(status().isBadRequest());

        List<PphMedicine> pphMedicineList = pphMedicineRepository.findAll();
        assertThat(pphMedicineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPphMedicines() throws Exception {
        // Initialize the database
        pphMedicineRepository.saveAndFlush(pphMedicine);

        // Get all the pphMedicineList
        restPphMedicineMockMvc.perform(get("/api/pph-medicines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pphMedicine.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].masterBookId").value(hasItem(DEFAULT_MASTER_BOOK_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].packageDescription").value(hasItem(DEFAULT_PACKAGE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].eanCode").value(hasItem(DEFAULT_EAN_CODE)))
            .andExpect(jsonPath("$.[*].atcCode").value(hasItem(DEFAULT_ATC_CODE)))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND)))
            .andExpect(jsonPath("$.[*].activeSubstance").value(hasItem(DEFAULT_ACTIVE_SUBSTANCE)))
            .andExpect(jsonPath("$.[*].potency").value(hasItem(DEFAULT_POTENCY)))
            .andExpect(jsonPath("$.[*].substanceAmountTotal").value(hasItem(DEFAULT_SUBSTANCE_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].doseInPackage").value(hasItem(DEFAULT_DOSE_IN_PACKAGE)))
            .andExpect(jsonPath("$.[*].substanceAmount").value(hasItem(DEFAULT_SUBSTANCE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].doseMsmUnit").value(hasItem(DEFAULT_DOSE_MSM_UNIT)))
            .andExpect(jsonPath("$.[*].substanceMsmUnit").value(hasItem(DEFAULT_SUBSTANCE_MSM_UNIT)))
            .andExpect(jsonPath("$.[*].packageSize").value(hasItem(DEFAULT_PACKAGE_SIZE)))
            .andExpect(jsonPath("$.[*].packageMsmUnit").value(hasItem(DEFAULT_PACKAGE_MSM_UNIT)))
            .andExpect(jsonPath("$.[*].dailyDoze").value(hasItem(DEFAULT_DAILY_DOZE.doubleValue())))
            .andExpect(jsonPath("$.[*].dailyDozeMsmUnit").value(hasItem(DEFAULT_DAILY_DOZE_MSM_UNIT)))
            .andExpect(jsonPath("$.[*].ddMsmUnitFactor").value(hasItem(DEFAULT_DD_MSM_UNIT_FACTOR.doubleValue())))
            .andExpect(jsonPath("$.[*].daysOfTherapy").value(hasItem(DEFAULT_DAYS_OF_THERAPY)))
            .andExpect(jsonPath("$.[*].consumerPrice").value(hasItem(DEFAULT_CONSUMER_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].grossConsumerPrice").value(hasItem(DEFAULT_GROSS_CONSUMER_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].efficacy").value(hasItem(DEFAULT_EFFICACY)))
            .andExpect(jsonPath("$.[*].oldName").value(hasItem(DEFAULT_OLD_NAME)))
            .andExpect(jsonPath("$.[*].oepTtt").value(hasItem(DEFAULT_OEP_TTT)))
            .andExpect(jsonPath("$.[*].masterBookDeleted").value(hasItem(DEFAULT_MASTER_BOOK_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].mbookDeletedDate").value(hasItem(DEFAULT_MBOOK_DELETED_DATE.toString())))
            .andExpect(jsonPath("$.[*].available").value(hasItem(DEFAULT_AVAILABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].motivationStatus").value(hasItem(DEFAULT_MOTIVATION_STATUS)))
            .andExpect(jsonPath("$.[*].costEffectivityCode").value(hasItem(DEFAULT_COST_EFFECTIVITY_CODE)))
            .andExpect(jsonPath("$.[*].dailyTherapyCost").value(hasItem(DEFAULT_DAILY_THERAPY_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].equivalenceGroupId").value(hasItem(DEFAULT_EQUIVALENCE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].oldSubsidyType").value(hasItem(DEFAULT_OLD_SUBSIDY_TYPE)))
            .andExpect(jsonPath("$.[*].preferredPriceFlag").value(hasItem(DEFAULT_PREFERRED_PRICE_FLAG)))
            .andExpect(jsonPath("$.[*].pharmacyFlag").value(hasItem(DEFAULT_PHARMACY_FLAG)))
            .andExpect(jsonPath("$.[*].customMade").value(hasItem(DEFAULT_CUSTOM_MADE.booleanValue())))
            .andExpect(jsonPath("$.[*].medicalDeviceIso").value(hasItem(DEFAULT_MEDICAL_DEVICE_ISO)))
            .andExpect(jsonPath("$.[*].producerPrice").value(hasItem(DEFAULT_PRODUCER_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].wholesalePrice").value(hasItem(DEFAULT_WHOLESALE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].vatRate").value(hasItem(DEFAULT_VAT_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].armyPrescription").value(hasItem(DEFAULT_ARMY_PRESCRIPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].publicSupply").value(hasItem(DEFAULT_PUBLIC_SUPPLY.booleanValue())))
            .andExpect(jsonPath("$.[*].workAccidentPrescr").value(hasItem(DEFAULT_WORK_ACCIDENT_PRESCR.booleanValue())))
            .andExpect(jsonPath("$.[*].extraSubsidyPrescr").value(hasItem(DEFAULT_EXTRA_SUBSIDY_PRESCR.booleanValue())))
            .andExpect(jsonPath("$.[*].medicalSubsidyPrescr").value(hasItem(DEFAULT_MEDICAL_SUBSIDY_PRESCR.booleanValue())))
            .andExpect(jsonPath("$.[*].substancePrice").value(hasItem(DEFAULT_SUBSTANCE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].includeInHc2").value(hasItem(DEFAULT_INCLUDE_IN_HC_2)))
            .andExpect(jsonPath("$.[*].prescriptionType").value(hasItem(DEFAULT_PRESCRIPTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].medicineType").value(hasItem(DEFAULT_MEDICINE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].medicineStatus").value(hasItem(DEFAULT_MEDICINE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].normative").value(hasItem(DEFAULT_NORMATIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].ogyiId").value(hasItem(DEFAULT_OGYI_ID.intValue())))
            .andExpect(jsonPath("$.[*].normFixGroupId").value(hasItem(DEFAULT_NORM_FIX_GROUP_ID)))
            .andExpect(jsonPath("$.[*].extraSubsidyFixGroupId").value(hasItem(DEFAULT_EXTRA_SUBSIDY_FIX_GROUP_ID)))
            .andExpect(jsonPath("$.[*].medicalSubsidyPrescriptionFixGroupId").value(hasItem(DEFAULT_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID)))
            .andExpect(jsonPath("$.[*].dosageMod").value(hasItem(DEFAULT_DOSAGE_MOD)))
            .andExpect(jsonPath("$.[*].activePuphaData").value(hasItem(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllPphMedicinesWithEagerRelationshipsIsEnabled() throws Exception {
        when(pphMedicineServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPphMedicineMockMvc.perform(get("/api/pph-medicines?eagerload=true"))
            .andExpect(status().isOk());

        verify(pphMedicineServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllPphMedicinesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(pphMedicineServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPphMedicineMockMvc.perform(get("/api/pph-medicines?eagerload=true"))
            .andExpect(status().isOk());

        verify(pphMedicineServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getPphMedicine() throws Exception {
        // Initialize the database
        pphMedicineRepository.saveAndFlush(pphMedicine);

        // Get the pphMedicine
        restPphMedicineMockMvc.perform(get("/api/pph-medicines/{id}", pphMedicine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pphMedicine.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()))
            .andExpect(jsonPath("$.masterBookId").value(DEFAULT_MASTER_BOOK_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.packageDescription").value(DEFAULT_PACKAGE_DESCRIPTION))
            .andExpect(jsonPath("$.eanCode").value(DEFAULT_EAN_CODE))
            .andExpect(jsonPath("$.atcCode").value(DEFAULT_ATC_CODE))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND))
            .andExpect(jsonPath("$.activeSubstance").value(DEFAULT_ACTIVE_SUBSTANCE))
            .andExpect(jsonPath("$.potency").value(DEFAULT_POTENCY))
            .andExpect(jsonPath("$.substanceAmountTotal").value(DEFAULT_SUBSTANCE_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.doseInPackage").value(DEFAULT_DOSE_IN_PACKAGE))
            .andExpect(jsonPath("$.substanceAmount").value(DEFAULT_SUBSTANCE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.doseMsmUnit").value(DEFAULT_DOSE_MSM_UNIT))
            .andExpect(jsonPath("$.substanceMsmUnit").value(DEFAULT_SUBSTANCE_MSM_UNIT))
            .andExpect(jsonPath("$.packageSize").value(DEFAULT_PACKAGE_SIZE))
            .andExpect(jsonPath("$.packageMsmUnit").value(DEFAULT_PACKAGE_MSM_UNIT))
            .andExpect(jsonPath("$.dailyDoze").value(DEFAULT_DAILY_DOZE.doubleValue()))
            .andExpect(jsonPath("$.dailyDozeMsmUnit").value(DEFAULT_DAILY_DOZE_MSM_UNIT))
            .andExpect(jsonPath("$.ddMsmUnitFactor").value(DEFAULT_DD_MSM_UNIT_FACTOR.doubleValue()))
            .andExpect(jsonPath("$.daysOfTherapy").value(DEFAULT_DAYS_OF_THERAPY))
            .andExpect(jsonPath("$.consumerPrice").value(DEFAULT_CONSUMER_PRICE.doubleValue()))
            .andExpect(jsonPath("$.grossConsumerPrice").value(DEFAULT_GROSS_CONSUMER_PRICE.doubleValue()))
            .andExpect(jsonPath("$.efficacy").value(DEFAULT_EFFICACY))
            .andExpect(jsonPath("$.oldName").value(DEFAULT_OLD_NAME))
            .andExpect(jsonPath("$.oepTtt").value(DEFAULT_OEP_TTT))
            .andExpect(jsonPath("$.masterBookDeleted").value(DEFAULT_MASTER_BOOK_DELETED.booleanValue()))
            .andExpect(jsonPath("$.mbookDeletedDate").value(DEFAULT_MBOOK_DELETED_DATE.toString()))
            .andExpect(jsonPath("$.available").value(DEFAULT_AVAILABLE.booleanValue()))
            .andExpect(jsonPath("$.motivationStatus").value(DEFAULT_MOTIVATION_STATUS))
            .andExpect(jsonPath("$.costEffectivityCode").value(DEFAULT_COST_EFFECTIVITY_CODE))
            .andExpect(jsonPath("$.dailyTherapyCost").value(DEFAULT_DAILY_THERAPY_COST.doubleValue()))
            .andExpect(jsonPath("$.equivalenceGroupId").value(DEFAULT_EQUIVALENCE_GROUP_ID))
            .andExpect(jsonPath("$.oldSubsidyType").value(DEFAULT_OLD_SUBSIDY_TYPE))
            .andExpect(jsonPath("$.preferredPriceFlag").value(DEFAULT_PREFERRED_PRICE_FLAG))
            .andExpect(jsonPath("$.pharmacyFlag").value(DEFAULT_PHARMACY_FLAG))
            .andExpect(jsonPath("$.customMade").value(DEFAULT_CUSTOM_MADE.booleanValue()))
            .andExpect(jsonPath("$.medicalDeviceIso").value(DEFAULT_MEDICAL_DEVICE_ISO))
            .andExpect(jsonPath("$.producerPrice").value(DEFAULT_PRODUCER_PRICE.doubleValue()))
            .andExpect(jsonPath("$.wholesalePrice").value(DEFAULT_WHOLESALE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.vatRate").value(DEFAULT_VAT_RATE.doubleValue()))
            .andExpect(jsonPath("$.armyPrescription").value(DEFAULT_ARMY_PRESCRIPTION.booleanValue()))
            .andExpect(jsonPath("$.publicSupply").value(DEFAULT_PUBLIC_SUPPLY.booleanValue()))
            .andExpect(jsonPath("$.workAccidentPrescr").value(DEFAULT_WORK_ACCIDENT_PRESCR.booleanValue()))
            .andExpect(jsonPath("$.extraSubsidyPrescr").value(DEFAULT_EXTRA_SUBSIDY_PRESCR.booleanValue()))
            .andExpect(jsonPath("$.medicalSubsidyPrescr").value(DEFAULT_MEDICAL_SUBSIDY_PRESCR.booleanValue()))
            .andExpect(jsonPath("$.substancePrice").value(DEFAULT_SUBSTANCE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.includeInHc2").value(DEFAULT_INCLUDE_IN_HC_2))
            .andExpect(jsonPath("$.prescriptionType").value(DEFAULT_PRESCRIPTION_TYPE.toString()))
            .andExpect(jsonPath("$.medicineType").value(DEFAULT_MEDICINE_TYPE.toString()))
            .andExpect(jsonPath("$.medicineStatus").value(DEFAULT_MEDICINE_STATUS.toString()))
            .andExpect(jsonPath("$.normative").value(DEFAULT_NORMATIVE.booleanValue()))
            .andExpect(jsonPath("$.ogyiId").value(DEFAULT_OGYI_ID.intValue()))
            .andExpect(jsonPath("$.normFixGroupId").value(DEFAULT_NORM_FIX_GROUP_ID))
            .andExpect(jsonPath("$.extraSubsidyFixGroupId").value(DEFAULT_EXTRA_SUBSIDY_FIX_GROUP_ID))
            .andExpect(jsonPath("$.medicalSubsidyPrescriptionFixGroupId").value(DEFAULT_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID))
            .andExpect(jsonPath("$.dosageMod").value(DEFAULT_DOSAGE_MOD))
            .andExpect(jsonPath("$.activePuphaData").value(DEFAULT_ACTIVE_PUPHA_DATA.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPphMedicine() throws Exception {
        // Get the pphMedicine
        restPphMedicineMockMvc.perform(get("/api/pph-medicines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePphMedicine() throws Exception {
        // Initialize the database
        pphMedicineRepository.saveAndFlush(pphMedicine);

        int databaseSizeBeforeUpdate = pphMedicineRepository.findAll().size();

        // Update the pphMedicine
        PphMedicine updatedPphMedicine = pphMedicineRepository.findById(pphMedicine.getId()).get();
        // Disconnect from session so that the updates on updatedPphMedicine are not directly saved in db
        em.detach(updatedPphMedicine);
        updatedPphMedicine
            .productId(UPDATED_PRODUCT_ID)
            .masterBookId(UPDATED_MASTER_BOOK_ID)
            .name(UPDATED_NAME)
            .packageDescription(UPDATED_PACKAGE_DESCRIPTION)
            .eanCode(UPDATED_EAN_CODE)
            .atcCode(UPDATED_ATC_CODE)
            .brand(UPDATED_BRAND)
            .activeSubstance(UPDATED_ACTIVE_SUBSTANCE)
            .potency(UPDATED_POTENCY)
            .substanceAmountTotal(UPDATED_SUBSTANCE_AMOUNT_TOTAL)
            .doseInPackage(UPDATED_DOSE_IN_PACKAGE)
            .substanceAmount(UPDATED_SUBSTANCE_AMOUNT)
            .doseMsmUnit(UPDATED_DOSE_MSM_UNIT)
            .substanceMsmUnit(UPDATED_SUBSTANCE_MSM_UNIT)
            .packageSize(UPDATED_PACKAGE_SIZE)
            .packageMsmUnit(UPDATED_PACKAGE_MSM_UNIT)
            .dailyDoze(UPDATED_DAILY_DOZE)
            .dailyDozeMsmUnit(UPDATED_DAILY_DOZE_MSM_UNIT)
            .ddMsmUnitFactor(UPDATED_DD_MSM_UNIT_FACTOR)
            .daysOfTherapy(UPDATED_DAYS_OF_THERAPY)
            .consumerPrice(UPDATED_CONSUMER_PRICE)
            .grossConsumerPrice(UPDATED_GROSS_CONSUMER_PRICE)
            .efficacy(UPDATED_EFFICACY)
            .oldName(UPDATED_OLD_NAME)
            .oepTtt(UPDATED_OEP_TTT)
            .masterBookDeleted(UPDATED_MASTER_BOOK_DELETED)
            .mbookDeletedDate(UPDATED_MBOOK_DELETED_DATE)
            .available(UPDATED_AVAILABLE)
            .motivationStatus(UPDATED_MOTIVATION_STATUS)
            .costEffectivityCode(UPDATED_COST_EFFECTIVITY_CODE)
            .dailyTherapyCost(UPDATED_DAILY_THERAPY_COST)
            .equivalenceGroupId(UPDATED_EQUIVALENCE_GROUP_ID)
            .oldSubsidyType(UPDATED_OLD_SUBSIDY_TYPE)
            .preferredPriceFlag(UPDATED_PREFERRED_PRICE_FLAG)
            .pharmacyFlag(UPDATED_PHARMACY_FLAG)
            .customMade(UPDATED_CUSTOM_MADE)
            .medicalDeviceIso(UPDATED_MEDICAL_DEVICE_ISO)
            .producerPrice(UPDATED_PRODUCER_PRICE)
            .wholesalePrice(UPDATED_WHOLESALE_PRICE)
            .vatRate(UPDATED_VAT_RATE)
            .armyPrescription(UPDATED_ARMY_PRESCRIPTION)
            .publicSupply(UPDATED_PUBLIC_SUPPLY)
            .workAccidentPrescr(UPDATED_WORK_ACCIDENT_PRESCR)
            .extraSubsidyPrescr(UPDATED_EXTRA_SUBSIDY_PRESCR)
            .medicalSubsidyPrescr(UPDATED_MEDICAL_SUBSIDY_PRESCR)
            .substancePrice(UPDATED_SUBSTANCE_PRICE)
            .includeInHc2(UPDATED_INCLUDE_IN_HC_2)
            .prescriptionType(UPDATED_PRESCRIPTION_TYPE)
            .medicineType(UPDATED_MEDICINE_TYPE)
            .medicineStatus(UPDATED_MEDICINE_STATUS)
            .normative(UPDATED_NORMATIVE)
            .ogyiId(UPDATED_OGYI_ID)
            .normFixGroupId(UPDATED_NORM_FIX_GROUP_ID)
            .extraSubsidyFixGroupId(UPDATED_EXTRA_SUBSIDY_FIX_GROUP_ID)
            .medicalSubsidyPrescriptionFixGroupId(UPDATED_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID)
            .dosageMod(UPDATED_DOSAGE_MOD)
            .activePuphaData(UPDATED_ACTIVE_PUPHA_DATA);
        PphMedicineDTO pphMedicineDTO = pphMedicineMapper.toDto(updatedPphMedicine);

        restPphMedicineMockMvc.perform(put("/api/pph-medicines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineDTO)))
            .andExpect(status().isOk());

        // Validate the PphMedicine in the database
        List<PphMedicine> pphMedicineList = pphMedicineRepository.findAll();
        assertThat(pphMedicineList).hasSize(databaseSizeBeforeUpdate);
        PphMedicine testPphMedicine = pphMedicineList.get(pphMedicineList.size() - 1);
        assertThat(testPphMedicine.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testPphMedicine.getMasterBookId()).isEqualTo(UPDATED_MASTER_BOOK_ID);
        assertThat(testPphMedicine.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPphMedicine.getPackageDescription()).isEqualTo(UPDATED_PACKAGE_DESCRIPTION);
        assertThat(testPphMedicine.getEanCode()).isEqualTo(UPDATED_EAN_CODE);
        assertThat(testPphMedicine.getAtcCode()).isEqualTo(UPDATED_ATC_CODE);
        assertThat(testPphMedicine.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testPphMedicine.getActiveSubstance()).isEqualTo(UPDATED_ACTIVE_SUBSTANCE);
        assertThat(testPphMedicine.getPotency()).isEqualTo(UPDATED_POTENCY);
        assertThat(testPphMedicine.getSubstanceAmountTotal()).isEqualTo(UPDATED_SUBSTANCE_AMOUNT_TOTAL);
        assertThat(testPphMedicine.getDoseInPackage()).isEqualTo(UPDATED_DOSE_IN_PACKAGE);
        assertThat(testPphMedicine.getSubstanceAmount()).isEqualTo(UPDATED_SUBSTANCE_AMOUNT);
        assertThat(testPphMedicine.getDoseMsmUnit()).isEqualTo(UPDATED_DOSE_MSM_UNIT);
        assertThat(testPphMedicine.getSubstanceMsmUnit()).isEqualTo(UPDATED_SUBSTANCE_MSM_UNIT);
        assertThat(testPphMedicine.getPackageSize()).isEqualTo(UPDATED_PACKAGE_SIZE);
        assertThat(testPphMedicine.getPackageMsmUnit()).isEqualTo(UPDATED_PACKAGE_MSM_UNIT);
        assertThat(testPphMedicine.getDailyDoze()).isEqualTo(UPDATED_DAILY_DOZE);
        assertThat(testPphMedicine.getDailyDozeMsmUnit()).isEqualTo(UPDATED_DAILY_DOZE_MSM_UNIT);
        assertThat(testPphMedicine.getDdMsmUnitFactor()).isEqualTo(UPDATED_DD_MSM_UNIT_FACTOR);
        assertThat(testPphMedicine.getDaysOfTherapy()).isEqualTo(UPDATED_DAYS_OF_THERAPY);
        assertThat(testPphMedicine.getConsumerPrice()).isEqualTo(UPDATED_CONSUMER_PRICE);
        assertThat(testPphMedicine.getGrossConsumerPrice()).isEqualTo(UPDATED_GROSS_CONSUMER_PRICE);
        assertThat(testPphMedicine.getEfficacy()).isEqualTo(UPDATED_EFFICACY);
        assertThat(testPphMedicine.getOldName()).isEqualTo(UPDATED_OLD_NAME);
        assertThat(testPphMedicine.getOepTtt()).isEqualTo(UPDATED_OEP_TTT);
        assertThat(testPphMedicine.isMasterBookDeleted()).isEqualTo(UPDATED_MASTER_BOOK_DELETED);
        assertThat(testPphMedicine.getMbookDeletedDate()).isEqualTo(UPDATED_MBOOK_DELETED_DATE);
        assertThat(testPphMedicine.isAvailable()).isEqualTo(UPDATED_AVAILABLE);
        assertThat(testPphMedicine.getMotivationStatus()).isEqualTo(UPDATED_MOTIVATION_STATUS);
        assertThat(testPphMedicine.getCostEffectivityCode()).isEqualTo(UPDATED_COST_EFFECTIVITY_CODE);
        assertThat(testPphMedicine.getDailyTherapyCost()).isEqualTo(UPDATED_DAILY_THERAPY_COST);
        assertThat(testPphMedicine.getEquivalenceGroupId()).isEqualTo(UPDATED_EQUIVALENCE_GROUP_ID);
        assertThat(testPphMedicine.getOldSubsidyType()).isEqualTo(UPDATED_OLD_SUBSIDY_TYPE);
        assertThat(testPphMedicine.getPreferredPriceFlag()).isEqualTo(UPDATED_PREFERRED_PRICE_FLAG);
        assertThat(testPphMedicine.getPharmacyFlag()).isEqualTo(UPDATED_PHARMACY_FLAG);
        assertThat(testPphMedicine.isCustomMade()).isEqualTo(UPDATED_CUSTOM_MADE);
        assertThat(testPphMedicine.getMedicalDeviceIso()).isEqualTo(UPDATED_MEDICAL_DEVICE_ISO);
        assertThat(testPphMedicine.getProducerPrice()).isEqualTo(UPDATED_PRODUCER_PRICE);
        assertThat(testPphMedicine.getWholesalePrice()).isEqualTo(UPDATED_WHOLESALE_PRICE);
        assertThat(testPphMedicine.getVatRate()).isEqualTo(UPDATED_VAT_RATE);
        assertThat(testPphMedicine.isArmyPrescription()).isEqualTo(UPDATED_ARMY_PRESCRIPTION);
        assertThat(testPphMedicine.isPublicSupply()).isEqualTo(UPDATED_PUBLIC_SUPPLY);
        assertThat(testPphMedicine.isWorkAccidentPrescr()).isEqualTo(UPDATED_WORK_ACCIDENT_PRESCR);
        assertThat(testPphMedicine.isExtraSubsidyPrescr()).isEqualTo(UPDATED_EXTRA_SUBSIDY_PRESCR);
        assertThat(testPphMedicine.isMedicalSubsidyPrescr()).isEqualTo(UPDATED_MEDICAL_SUBSIDY_PRESCR);
        assertThat(testPphMedicine.getSubstancePrice()).isEqualTo(UPDATED_SUBSTANCE_PRICE);
        assertThat(testPphMedicine.getIncludeInHc2()).isEqualTo(UPDATED_INCLUDE_IN_HC_2);
        assertThat(testPphMedicine.getPrescriptionType()).isEqualTo(UPDATED_PRESCRIPTION_TYPE);
        assertThat(testPphMedicine.getMedicineType()).isEqualTo(UPDATED_MEDICINE_TYPE);
        assertThat(testPphMedicine.getMedicineStatus()).isEqualTo(UPDATED_MEDICINE_STATUS);
        assertThat(testPphMedicine.isNormative()).isEqualTo(UPDATED_NORMATIVE);
        assertThat(testPphMedicine.getOgyiId()).isEqualTo(UPDATED_OGYI_ID);
        assertThat(testPphMedicine.getNormFixGroupId()).isEqualTo(UPDATED_NORM_FIX_GROUP_ID);
        assertThat(testPphMedicine.getExtraSubsidyFixGroupId()).isEqualTo(UPDATED_EXTRA_SUBSIDY_FIX_GROUP_ID);
        assertThat(testPphMedicine.getMedicalSubsidyPrescriptionFixGroupId()).isEqualTo(UPDATED_MEDICAL_SUBSIDY_PRESCRIPTION_FIX_GROUP_ID);
        assertThat(testPphMedicine.getDosageMod()).isEqualTo(UPDATED_DOSAGE_MOD);
        assertThat(testPphMedicine.isActivePuphaData()).isEqualTo(UPDATED_ACTIVE_PUPHA_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPphMedicine() throws Exception {
        int databaseSizeBeforeUpdate = pphMedicineRepository.findAll().size();

        // Create the PphMedicine
        PphMedicineDTO pphMedicineDTO = pphMedicineMapper.toDto(pphMedicine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPphMedicineMockMvc.perform(put("/api/pph-medicines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pphMedicineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PphMedicine in the database
        List<PphMedicine> pphMedicineList = pphMedicineRepository.findAll();
        assertThat(pphMedicineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePphMedicine() throws Exception {
        // Initialize the database
        pphMedicineRepository.saveAndFlush(pphMedicine);

        int databaseSizeBeforeDelete = pphMedicineRepository.findAll().size();

        // Delete the pphMedicine
        restPphMedicineMockMvc.perform(delete("/api/pph-medicines/{id}", pphMedicine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PphMedicine> pphMedicineList = pphMedicineRepository.findAll();
        assertThat(pphMedicineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
