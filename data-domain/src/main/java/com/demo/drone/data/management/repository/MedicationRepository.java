package com.demo.drone.data.management.repository;

import org.springframework.stereotype.Repository;

import com.demo.drone.data.common.repository.EntityRepository;
import com.demo.drone.data.management.entity.Medication;

@Repository
public interface MedicationRepository extends EntityRepository<Medication, String>, MedicationRepositoryCustom {

    Medication findByUuid(String uuid);

    Medication findByUuidAndEnabled(String uuid, Boolean enabled);

    Boolean existsByCode(String code); 

    Medication findByCode(String code);
}