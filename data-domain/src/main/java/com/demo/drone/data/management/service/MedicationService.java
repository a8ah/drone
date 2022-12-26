package com.demo.drone.data.management.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.drone.data.common.service.AbstractEntityService;
import com.demo.drone.data.management.entity.Medication;
import com.demo.drone.data.management.projection.MedicationProjection;
import com.demo.drone.data.management.repository.MedicationRepository;

@Service
public class MedicationService extends AbstractEntityService<Medication, String, MedicationRepository> {

    public Page<MedicationProjection> getAllMedicationByEnabled(Pageable pageable, Boolean enabled){
        return this.repository.getAllMedicationByEnabled(pageable, enabled);
    }

    public Medication findByUuid(String uuid){
        return this.repository.findByUuid(uuid);
    }

    public Medication getMedication(String uuid, Boolean enabled){
        return this.repository.findByUuidAndEnabled(uuid, enabled);
    }

    public Boolean existByUuid(String uuid){
        return this.repository.existsById(uuid);
    }

    public Boolean existsByCode(String code){
        return this.repository.existsByCode(code);
    }
}