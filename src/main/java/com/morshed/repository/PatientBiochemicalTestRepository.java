package com.morshed.repository;

import com.morshed.domain.PatientBiochemicalTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the PatientBiochemicalTest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientBiochemicalTestRepository extends ReactiveMongoRepository<PatientBiochemicalTest, String> {
    Flux<PatientBiochemicalTest> findAllBy(Pageable pageable);
}
