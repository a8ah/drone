package com.demo.drone.data.cargo.repository;

import org.springframework.stereotype.Repository;

import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.common.repository.EntityRepository;

@Repository
public interface OrderMedicationRepository extends EntityRepository<OrderMedication, String>, OrderMedicationRepositoryCustom {

    // Medication findByUuid(String uuid);

    // Medication findByUuidAndEnabled(String uuid, Boolean enabled);

    // Boolean existsByCode(String code); 
}