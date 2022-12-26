package com.demo.drone.data.management.repository;

import com.demo.drone.data.management.projection.MedicationProjection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
interface MedicationRepositoryCustom {

    Page<MedicationProjection> getAllMedicationByEnabled(Pageable pageable, Boolean enabled);
}