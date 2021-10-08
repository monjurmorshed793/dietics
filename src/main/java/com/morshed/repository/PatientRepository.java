package com.morshed.repository;

import com.morshed.domain.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {
    Flux<Patient> findAllBy(Pageable pageable);
}
