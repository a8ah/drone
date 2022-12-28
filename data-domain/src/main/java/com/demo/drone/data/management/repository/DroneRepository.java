package com.demo.drone.data.management.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.demo.drone.data.common.repository.EntityRepository;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.management.model.State;

@Repository
public interface DroneRepository extends EntityRepository<Drone, String>, MedicationRepositoryCustom {

    Drone findByUuid(String uuid);

    Drone findByUuidAndEnabled(String uuid, Boolean enabled);

    List<Drone> findAllByEnabled(Boolean enabled);

    Boolean existsBySerial(String serial); 

    Integer getBatteryByUuid(String uuid);

    List<Drone> findByStateInAndBatteryGreaterThanEqual(List<State> states, Integer batteryWarning);
}