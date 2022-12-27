package com.demo.drone.data.management.repository;

import org.springframework.stereotype.Repository;

import com.demo.drone.data.common.repository.EntityRepository;
import com.demo.drone.data.management.entity.Drone;

@Repository
public interface DroneRepository extends EntityRepository<Drone, String>, MedicationRepositoryCustom {

    Drone findByUuid(String uuid);

    Drone findByUuidAndEnabled(String uuid, Boolean enabled);

    Boolean existsBySerial(String serial); 
}