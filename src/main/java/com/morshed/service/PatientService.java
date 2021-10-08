package com.morshed.service;

import com.morshed.domain.Patient;
import com.morshed.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Patient}.
 */
@Service
public class PatientService {

    private final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Save a patient.
     *
     * @param patient the entity to save.
     * @return the persisted entity.
     */
    public Mono<Patient> save(Patient patient) {
        log.debug("Request to save Patient : {}", patient);
        return patientRepository.save(patient);
    }

    /**
     * Partially update a patient.
     *
     * @param patient the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<Patient> partialUpdate(Patient patient) {
        log.debug("Request to partially update Patient : {}", patient);

        return patientRepository
            .findById(patient.getId())
            .map(existingPatient -> {
                if (patient.getName() != null) {
                    existingPatient.setName(patient.getName());
                }
                if (patient.getAddress() != null) {
                    existingPatient.setAddress(patient.getAddress());
                }
                if (patient.getHospital() != null) {
                    existingPatient.setHospital(patient.getHospital());
                }
                if (patient.getAdmissionDate() != null) {
                    existingPatient.setAdmissionDate(patient.getAdmissionDate());
                }
                if (patient.getReasonOfAdmission() != null) {
                    existingPatient.setReasonOfAdmission(patient.getReasonOfAdmission());
                }
                if (patient.getWordNo() != null) {
                    existingPatient.setWordNo(patient.getWordNo());
                }
                if (patient.getBedNo() != null) {
                    existingPatient.setBedNo(patient.getBedNo());
                }
                if (patient.getHealthCondition() != null) {
                    existingPatient.setHealthCondition(patient.getHealthCondition());
                }
                if (patient.getMentalStatus() != null) {
                    existingPatient.setMentalStatus(patient.getMentalStatus());
                }
                if (patient.getAge() != null) {
                    existingPatient.setAge(patient.getAge());
                }
                if (patient.getSex() != null) {
                    existingPatient.setSex(patient.getSex());
                }
                if (patient.getWeight() != null) {
                    existingPatient.setWeight(patient.getWeight());
                }
                if (patient.getWeightType() != null) {
                    existingPatient.setWeightType(patient.getWeightType());
                }
                if (patient.getHeight() != null) {
                    existingPatient.setHeight(patient.getHeight());
                }
                if (patient.getHeightMeasureType() != null) {
                    existingPatient.setHeightMeasureType(patient.getHeightMeasureType());
                }
                if (patient.getIbw() != null) {
                    existingPatient.setIbw(patient.getIbw());
                }
                if (patient.getBmi() != null) {
                    existingPatient.setBmi(patient.getBmi());
                }
                if (patient.getRecentWeightGainLoss() != null) {
                    existingPatient.setRecentWeightGainLoss(patient.getRecentWeightGainLoss());
                }
                if (patient.getGainLossMeasure() != null) {
                    existingPatient.setGainLossMeasure(patient.getGainLossMeasure());
                }
                if (patient.getGainLossTimeFrame() != null) {
                    existingPatient.setGainLossTimeFrame(patient.getGainLossTimeFrame());
                }
                if (patient.getGainLossType() != null) {
                    existingPatient.setGainLossType(patient.getGainLossType());
                }

                return existingPatient;
            })
            .flatMap(patientRepository::save);
    }

    /**
     * Get all the patients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<Patient> findAll(Pageable pageable) {
        log.debug("Request to get all Patients");
        return patientRepository.findAllBy(pageable);
    }

    /**
     * Returns the number of patients available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return patientRepository.count();
    }

    /**
     * Get one patient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Mono<Patient> findOne(String id) {
        log.debug("Request to get Patient : {}", id);
        return patientRepository.findById(id);
    }

    /**
     * Delete the patient by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Patient : {}", id);
        return patientRepository.deleteById(id);
    }
}
