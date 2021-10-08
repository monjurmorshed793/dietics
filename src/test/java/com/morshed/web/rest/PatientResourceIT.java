package com.morshed.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.morshed.IntegrationTest;
import com.morshed.domain.Patient;
import com.morshed.domain.enumeration.GainLossType;
import com.morshed.domain.enumeration.Gender;
import com.morshed.domain.enumeration.HeightMeasureType;
import com.morshed.domain.enumeration.WeightType;
import com.morshed.repository.PatientRepository;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link PatientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient
@WithMockUser
class PatientResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_HOSPITAL = "AAAAAAAAAA";
    private static final String UPDATED_HOSPITAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ADMISSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADMISSION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REASON_OF_ADMISSION = "AAAAAAAAAA";
    private static final String UPDATED_REASON_OF_ADMISSION = "BBBBBBBBBB";

    private static final String DEFAULT_WORD_NO = "AAAAAAAAAA";
    private static final String UPDATED_WORD_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BED_NO = "AAAAAAAAAA";
    private static final String UPDATED_BED_NO = "BBBBBBBBBB";

    private static final String DEFAULT_HEALTH_CONDITION = "AAAAAAAAAA";
    private static final String UPDATED_HEALTH_CONDITION = "BBBBBBBBBB";

    private static final String DEFAULT_MENTAL_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MENTAL_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Gender DEFAULT_SEX = Gender.MALE;
    private static final Gender UPDATED_SEX = Gender.FEMALE;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final WeightType DEFAULT_WEIGHT_TYPE = WeightType.UNDERWEIGHT;
    private static final WeightType UPDATED_WEIGHT_TYPE = WeightType.NORMAL;

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;

    private static final HeightMeasureType DEFAULT_HEIGHT_MEASURE_TYPE = HeightMeasureType.CM;
    private static final HeightMeasureType UPDATED_HEIGHT_MEASURE_TYPE = HeightMeasureType.INCH;

    private static final Double DEFAULT_IBW = 1D;
    private static final Double UPDATED_IBW = 2D;

    private static final Double DEFAULT_BMI = 1D;
    private static final Double UPDATED_BMI = 2D;

    private static final Boolean DEFAULT_RECENT_WEIGHT_GAIN_LOSS = false;
    private static final Boolean UPDATED_RECENT_WEIGHT_GAIN_LOSS = true;

    private static final Double DEFAULT_GAIN_LOSS_MEASURE = 1D;
    private static final Double UPDATED_GAIN_LOSS_MEASURE = 2D;

    private static final Double DEFAULT_GAIN_LOSS_TIME_FRAME = 1D;
    private static final Double UPDATED_GAIN_LOSS_TIME_FRAME = 2D;

    private static final GainLossType DEFAULT_GAIN_LOSS_TYPE = GainLossType.INTENTIONAL;
    private static final GainLossType UPDATED_GAIN_LOSS_TYPE = GainLossType.UNINTENTIONAL;

    private static final String ENTITY_API_URL = "/api/patients";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Patient patient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createEntity() {
        Patient patient = new Patient()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .hospital(DEFAULT_HOSPITAL)
            .admissionDate(DEFAULT_ADMISSION_DATE)
            .reasonOfAdmission(DEFAULT_REASON_OF_ADMISSION)
            .wordNo(DEFAULT_WORD_NO)
            .bedNo(DEFAULT_BED_NO)
            .healthCondition(DEFAULT_HEALTH_CONDITION)
            .mentalStatus(DEFAULT_MENTAL_STATUS)
            .age(DEFAULT_AGE)
            .sex(DEFAULT_SEX)
            .weight(DEFAULT_WEIGHT)
            .weightType(DEFAULT_WEIGHT_TYPE)
            .height(DEFAULT_HEIGHT)
            .heightMeasureType(DEFAULT_HEIGHT_MEASURE_TYPE)
            .ibw(DEFAULT_IBW)
            .bmi(DEFAULT_BMI)
            .recentWeightGainLoss(DEFAULT_RECENT_WEIGHT_GAIN_LOSS)
            .gainLossMeasure(DEFAULT_GAIN_LOSS_MEASURE)
            .gainLossTimeFrame(DEFAULT_GAIN_LOSS_TIME_FRAME)
            .gainLossType(DEFAULT_GAIN_LOSS_TYPE);
        return patient;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Patient createUpdatedEntity() {
        Patient patient = new Patient()
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .hospital(UPDATED_HOSPITAL)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .reasonOfAdmission(UPDATED_REASON_OF_ADMISSION)
            .wordNo(UPDATED_WORD_NO)
            .bedNo(UPDATED_BED_NO)
            .healthCondition(UPDATED_HEALTH_CONDITION)
            .mentalStatus(UPDATED_MENTAL_STATUS)
            .age(UPDATED_AGE)
            .sex(UPDATED_SEX)
            .weight(UPDATED_WEIGHT)
            .weightType(UPDATED_WEIGHT_TYPE)
            .height(UPDATED_HEIGHT)
            .heightMeasureType(UPDATED_HEIGHT_MEASURE_TYPE)
            .ibw(UPDATED_IBW)
            .bmi(UPDATED_BMI)
            .recentWeightGainLoss(UPDATED_RECENT_WEIGHT_GAIN_LOSS)
            .gainLossMeasure(UPDATED_GAIN_LOSS_MEASURE)
            .gainLossTimeFrame(UPDATED_GAIN_LOSS_TIME_FRAME)
            .gainLossType(UPDATED_GAIN_LOSS_TYPE);
        return patient;
    }

    @BeforeEach
    public void initTest() {
        patientRepository.deleteAll().block();
        patient = createEntity();
    }

    @Test
    void createPatient() throws Exception {
        int databaseSizeBeforeCreate = patientRepository.findAll().collectList().block().size();
        // Create the Patient
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate + 1);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPatient.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPatient.getHospital()).isEqualTo(DEFAULT_HOSPITAL);
        assertThat(testPatient.getAdmissionDate()).isEqualTo(DEFAULT_ADMISSION_DATE);
        assertThat(testPatient.getReasonOfAdmission()).isEqualTo(DEFAULT_REASON_OF_ADMISSION);
        assertThat(testPatient.getWordNo()).isEqualTo(DEFAULT_WORD_NO);
        assertThat(testPatient.getBedNo()).isEqualTo(DEFAULT_BED_NO);
        assertThat(testPatient.getHealthCondition()).isEqualTo(DEFAULT_HEALTH_CONDITION);
        assertThat(testPatient.getMentalStatus()).isEqualTo(DEFAULT_MENTAL_STATUS);
        assertThat(testPatient.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testPatient.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testPatient.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testPatient.getWeightType()).isEqualTo(DEFAULT_WEIGHT_TYPE);
        assertThat(testPatient.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPatient.getHeightMeasureType()).isEqualTo(DEFAULT_HEIGHT_MEASURE_TYPE);
        assertThat(testPatient.getIbw()).isEqualTo(DEFAULT_IBW);
        assertThat(testPatient.getBmi()).isEqualTo(DEFAULT_BMI);
        assertThat(testPatient.getRecentWeightGainLoss()).isEqualTo(DEFAULT_RECENT_WEIGHT_GAIN_LOSS);
        assertThat(testPatient.getGainLossMeasure()).isEqualTo(DEFAULT_GAIN_LOSS_MEASURE);
        assertThat(testPatient.getGainLossTimeFrame()).isEqualTo(DEFAULT_GAIN_LOSS_TIME_FRAME);
        assertThat(testPatient.getGainLossType()).isEqualTo(DEFAULT_GAIN_LOSS_TYPE);
    }

    @Test
    void createPatientWithExistingId() throws Exception {
        // Create the Patient with an existing ID
        patient.setId("existing_id");

        int databaseSizeBeforeCreate = patientRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPatients() {
        // Initialize the database
        patientRepository.save(patient).block();

        // Get all the patientList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(patient.getId()))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].address")
            .value(hasItem(DEFAULT_ADDRESS.toString()))
            .jsonPath("$.[*].hospital")
            .value(hasItem(DEFAULT_HOSPITAL))
            .jsonPath("$.[*].admissionDate")
            .value(hasItem(DEFAULT_ADMISSION_DATE.toString()))
            .jsonPath("$.[*].reasonOfAdmission")
            .value(hasItem(DEFAULT_REASON_OF_ADMISSION.toString()))
            .jsonPath("$.[*].wordNo")
            .value(hasItem(DEFAULT_WORD_NO))
            .jsonPath("$.[*].bedNo")
            .value(hasItem(DEFAULT_BED_NO))
            .jsonPath("$.[*].healthCondition")
            .value(hasItem(DEFAULT_HEALTH_CONDITION.toString()))
            .jsonPath("$.[*].mentalStatus")
            .value(hasItem(DEFAULT_MENTAL_STATUS.toString()))
            .jsonPath("$.[*].age")
            .value(hasItem(DEFAULT_AGE))
            .jsonPath("$.[*].sex")
            .value(hasItem(DEFAULT_SEX.toString()))
            .jsonPath("$.[*].weight")
            .value(hasItem(DEFAULT_WEIGHT.doubleValue()))
            .jsonPath("$.[*].weightType")
            .value(hasItem(DEFAULT_WEIGHT_TYPE.toString()))
            .jsonPath("$.[*].height")
            .value(hasItem(DEFAULT_HEIGHT.doubleValue()))
            .jsonPath("$.[*].heightMeasureType")
            .value(hasItem(DEFAULT_HEIGHT_MEASURE_TYPE.toString()))
            .jsonPath("$.[*].ibw")
            .value(hasItem(DEFAULT_IBW.doubleValue()))
            .jsonPath("$.[*].bmi")
            .value(hasItem(DEFAULT_BMI.doubleValue()))
            .jsonPath("$.[*].recentWeightGainLoss")
            .value(hasItem(DEFAULT_RECENT_WEIGHT_GAIN_LOSS.booleanValue()))
            .jsonPath("$.[*].gainLossMeasure")
            .value(hasItem(DEFAULT_GAIN_LOSS_MEASURE.doubleValue()))
            .jsonPath("$.[*].gainLossTimeFrame")
            .value(hasItem(DEFAULT_GAIN_LOSS_TIME_FRAME.doubleValue()))
            .jsonPath("$.[*].gainLossType")
            .value(hasItem(DEFAULT_GAIN_LOSS_TYPE.toString()));
    }

    @Test
    void getPatient() {
        // Initialize the database
        patientRepository.save(patient).block();

        // Get the patient
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, patient.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(patient.getId()))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME))
            .jsonPath("$.address")
            .value(is(DEFAULT_ADDRESS.toString()))
            .jsonPath("$.hospital")
            .value(is(DEFAULT_HOSPITAL))
            .jsonPath("$.admissionDate")
            .value(is(DEFAULT_ADMISSION_DATE.toString()))
            .jsonPath("$.reasonOfAdmission")
            .value(is(DEFAULT_REASON_OF_ADMISSION.toString()))
            .jsonPath("$.wordNo")
            .value(is(DEFAULT_WORD_NO))
            .jsonPath("$.bedNo")
            .value(is(DEFAULT_BED_NO))
            .jsonPath("$.healthCondition")
            .value(is(DEFAULT_HEALTH_CONDITION.toString()))
            .jsonPath("$.mentalStatus")
            .value(is(DEFAULT_MENTAL_STATUS.toString()))
            .jsonPath("$.age")
            .value(is(DEFAULT_AGE))
            .jsonPath("$.sex")
            .value(is(DEFAULT_SEX.toString()))
            .jsonPath("$.weight")
            .value(is(DEFAULT_WEIGHT.doubleValue()))
            .jsonPath("$.weightType")
            .value(is(DEFAULT_WEIGHT_TYPE.toString()))
            .jsonPath("$.height")
            .value(is(DEFAULT_HEIGHT.doubleValue()))
            .jsonPath("$.heightMeasureType")
            .value(is(DEFAULT_HEIGHT_MEASURE_TYPE.toString()))
            .jsonPath("$.ibw")
            .value(is(DEFAULT_IBW.doubleValue()))
            .jsonPath("$.bmi")
            .value(is(DEFAULT_BMI.doubleValue()))
            .jsonPath("$.recentWeightGainLoss")
            .value(is(DEFAULT_RECENT_WEIGHT_GAIN_LOSS.booleanValue()))
            .jsonPath("$.gainLossMeasure")
            .value(is(DEFAULT_GAIN_LOSS_MEASURE.doubleValue()))
            .jsonPath("$.gainLossTimeFrame")
            .value(is(DEFAULT_GAIN_LOSS_TIME_FRAME.doubleValue()))
            .jsonPath("$.gainLossType")
            .value(is(DEFAULT_GAIN_LOSS_TYPE.toString()));
    }

    @Test
    void getNonExistingPatient() {
        // Get the patient
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putNewPatient() throws Exception {
        // Initialize the database
        patientRepository.save(patient).block();

        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();

        // Update the patient
        Patient updatedPatient = patientRepository.findById(patient.getId()).block();
        updatedPatient
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .hospital(UPDATED_HOSPITAL)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .reasonOfAdmission(UPDATED_REASON_OF_ADMISSION)
            .wordNo(UPDATED_WORD_NO)
            .bedNo(UPDATED_BED_NO)
            .healthCondition(UPDATED_HEALTH_CONDITION)
            .mentalStatus(UPDATED_MENTAL_STATUS)
            .age(UPDATED_AGE)
            .sex(UPDATED_SEX)
            .weight(UPDATED_WEIGHT)
            .weightType(UPDATED_WEIGHT_TYPE)
            .height(UPDATED_HEIGHT)
            .heightMeasureType(UPDATED_HEIGHT_MEASURE_TYPE)
            .ibw(UPDATED_IBW)
            .bmi(UPDATED_BMI)
            .recentWeightGainLoss(UPDATED_RECENT_WEIGHT_GAIN_LOSS)
            .gainLossMeasure(UPDATED_GAIN_LOSS_MEASURE)
            .gainLossTimeFrame(UPDATED_GAIN_LOSS_TIME_FRAME)
            .gainLossType(UPDATED_GAIN_LOSS_TYPE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedPatient.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedPatient))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPatient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPatient.getHospital()).isEqualTo(UPDATED_HOSPITAL);
        assertThat(testPatient.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testPatient.getReasonOfAdmission()).isEqualTo(UPDATED_REASON_OF_ADMISSION);
        assertThat(testPatient.getWordNo()).isEqualTo(UPDATED_WORD_NO);
        assertThat(testPatient.getBedNo()).isEqualTo(UPDATED_BED_NO);
        assertThat(testPatient.getHealthCondition()).isEqualTo(UPDATED_HEALTH_CONDITION);
        assertThat(testPatient.getMentalStatus()).isEqualTo(UPDATED_MENTAL_STATUS);
        assertThat(testPatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPatient.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testPatient.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPatient.getWeightType()).isEqualTo(UPDATED_WEIGHT_TYPE);
        assertThat(testPatient.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPatient.getHeightMeasureType()).isEqualTo(UPDATED_HEIGHT_MEASURE_TYPE);
        assertThat(testPatient.getIbw()).isEqualTo(UPDATED_IBW);
        assertThat(testPatient.getBmi()).isEqualTo(UPDATED_BMI);
        assertThat(testPatient.getRecentWeightGainLoss()).isEqualTo(UPDATED_RECENT_WEIGHT_GAIN_LOSS);
        assertThat(testPatient.getGainLossMeasure()).isEqualTo(UPDATED_GAIN_LOSS_MEASURE);
        assertThat(testPatient.getGainLossTimeFrame()).isEqualTo(UPDATED_GAIN_LOSS_TIME_FRAME);
        assertThat(testPatient.getGainLossType()).isEqualTo(UPDATED_GAIN_LOSS_TYPE);
    }

    @Test
    void putNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();
        patient.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, patient.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();
        patient.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();
        patient.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePatientWithPatch() throws Exception {
        // Initialize the database
        patientRepository.save(patient).block();

        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();

        // Update the patient using partial update
        Patient partialUpdatedPatient = new Patient();
        partialUpdatedPatient.setId(patient.getId());

        partialUpdatedPatient
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .hospital(UPDATED_HOSPITAL)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .wordNo(UPDATED_WORD_NO)
            .sex(UPDATED_SEX)
            .heightMeasureType(UPDATED_HEIGHT_MEASURE_TYPE)
            .ibw(UPDATED_IBW)
            .gainLossTimeFrame(UPDATED_GAIN_LOSS_TIME_FRAME)
            .gainLossType(UPDATED_GAIN_LOSS_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatient.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatient))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPatient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPatient.getHospital()).isEqualTo(UPDATED_HOSPITAL);
        assertThat(testPatient.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testPatient.getReasonOfAdmission()).isEqualTo(DEFAULT_REASON_OF_ADMISSION);
        assertThat(testPatient.getWordNo()).isEqualTo(UPDATED_WORD_NO);
        assertThat(testPatient.getBedNo()).isEqualTo(DEFAULT_BED_NO);
        assertThat(testPatient.getHealthCondition()).isEqualTo(DEFAULT_HEALTH_CONDITION);
        assertThat(testPatient.getMentalStatus()).isEqualTo(DEFAULT_MENTAL_STATUS);
        assertThat(testPatient.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testPatient.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testPatient.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testPatient.getWeightType()).isEqualTo(DEFAULT_WEIGHT_TYPE);
        assertThat(testPatient.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testPatient.getHeightMeasureType()).isEqualTo(UPDATED_HEIGHT_MEASURE_TYPE);
        assertThat(testPatient.getIbw()).isEqualTo(UPDATED_IBW);
        assertThat(testPatient.getBmi()).isEqualTo(DEFAULT_BMI);
        assertThat(testPatient.getRecentWeightGainLoss()).isEqualTo(DEFAULT_RECENT_WEIGHT_GAIN_LOSS);
        assertThat(testPatient.getGainLossMeasure()).isEqualTo(DEFAULT_GAIN_LOSS_MEASURE);
        assertThat(testPatient.getGainLossTimeFrame()).isEqualTo(UPDATED_GAIN_LOSS_TIME_FRAME);
        assertThat(testPatient.getGainLossType()).isEqualTo(UPDATED_GAIN_LOSS_TYPE);
    }

    @Test
    void fullUpdatePatientWithPatch() throws Exception {
        // Initialize the database
        patientRepository.save(patient).block();

        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();

        // Update the patient using partial update
        Patient partialUpdatedPatient = new Patient();
        partialUpdatedPatient.setId(patient.getId());

        partialUpdatedPatient
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .hospital(UPDATED_HOSPITAL)
            .admissionDate(UPDATED_ADMISSION_DATE)
            .reasonOfAdmission(UPDATED_REASON_OF_ADMISSION)
            .wordNo(UPDATED_WORD_NO)
            .bedNo(UPDATED_BED_NO)
            .healthCondition(UPDATED_HEALTH_CONDITION)
            .mentalStatus(UPDATED_MENTAL_STATUS)
            .age(UPDATED_AGE)
            .sex(UPDATED_SEX)
            .weight(UPDATED_WEIGHT)
            .weightType(UPDATED_WEIGHT_TYPE)
            .height(UPDATED_HEIGHT)
            .heightMeasureType(UPDATED_HEIGHT_MEASURE_TYPE)
            .ibw(UPDATED_IBW)
            .bmi(UPDATED_BMI)
            .recentWeightGainLoss(UPDATED_RECENT_WEIGHT_GAIN_LOSS)
            .gainLossMeasure(UPDATED_GAIN_LOSS_MEASURE)
            .gainLossTimeFrame(UPDATED_GAIN_LOSS_TIME_FRAME)
            .gainLossType(UPDATED_GAIN_LOSS_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPatient.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPatient))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
        Patient testPatient = patientList.get(patientList.size() - 1);
        assertThat(testPatient.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPatient.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPatient.getHospital()).isEqualTo(UPDATED_HOSPITAL);
        assertThat(testPatient.getAdmissionDate()).isEqualTo(UPDATED_ADMISSION_DATE);
        assertThat(testPatient.getReasonOfAdmission()).isEqualTo(UPDATED_REASON_OF_ADMISSION);
        assertThat(testPatient.getWordNo()).isEqualTo(UPDATED_WORD_NO);
        assertThat(testPatient.getBedNo()).isEqualTo(UPDATED_BED_NO);
        assertThat(testPatient.getHealthCondition()).isEqualTo(UPDATED_HEALTH_CONDITION);
        assertThat(testPatient.getMentalStatus()).isEqualTo(UPDATED_MENTAL_STATUS);
        assertThat(testPatient.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testPatient.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testPatient.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPatient.getWeightType()).isEqualTo(UPDATED_WEIGHT_TYPE);
        assertThat(testPatient.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testPatient.getHeightMeasureType()).isEqualTo(UPDATED_HEIGHT_MEASURE_TYPE);
        assertThat(testPatient.getIbw()).isEqualTo(UPDATED_IBW);
        assertThat(testPatient.getBmi()).isEqualTo(UPDATED_BMI);
        assertThat(testPatient.getRecentWeightGainLoss()).isEqualTo(UPDATED_RECENT_WEIGHT_GAIN_LOSS);
        assertThat(testPatient.getGainLossMeasure()).isEqualTo(UPDATED_GAIN_LOSS_MEASURE);
        assertThat(testPatient.getGainLossTimeFrame()).isEqualTo(UPDATED_GAIN_LOSS_TIME_FRAME);
        assertThat(testPatient.getGainLossType()).isEqualTo(UPDATED_GAIN_LOSS_TYPE);
    }

    @Test
    void patchNonExistingPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();
        patient.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, patient.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();
        patient.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPatient() throws Exception {
        int databaseSizeBeforeUpdate = patientRepository.findAll().collectList().block().size();
        patient.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(patient))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Patient in the database
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePatient() {
        // Initialize the database
        patientRepository.save(patient).block();

        int databaseSizeBeforeDelete = patientRepository.findAll().collectList().block().size();

        // Delete the patient
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, patient.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Patient> patientList = patientRepository.findAll().collectList().block();
        assertThat(patientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
