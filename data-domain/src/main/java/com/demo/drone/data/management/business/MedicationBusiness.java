package com.demo.drone.data.management.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.demo.drone.data.common.business.AbstractBusiness;
import com.demo.drone.data.management.entity.Medication;
import com.demo.drone.data.management.execption.MedicationException;
import com.demo.drone.data.management.projection.MedicationProjection;
import com.demo.drone.data.management.service.MedicationService;


@Service
public class MedicationBusiness extends AbstractBusiness {

    @Autowired
    private MedicationService medicationService;

    public Page<MedicationProjection> getAllMedicationByEnabled(PageRequest page, Boolean enabled ){
        
        return this.medicationService.getAllMedicationByEnabled(page, enabled);
    }

    @Transactional
    public String registerMedication(Medication medicationInput) throws MedicationException{
        Medication medication = new Medication();

        if(this.medicationService.existsByCode(medicationInput.getCode()))
            throw MedicationException.existByCodeException(medicationInput.getCode());

        medication.setCode(medicationInput.getCode());
        medication.setName(medicationInput.getName());
        medication.setWeigth(medicationInput.getWeigth());
        medication.setImage(medicationInput.getImage());

        this.medicationService.saveAndFlush(medication);

        return medication.getUuid();
    }

    public Medication findByUuid(String uuid) throws MedicationException{
        Medication medication = this.medicationService.findByUuid(uuid);

        if(null == medication)
            throw MedicationException.medicationNoExist(uuid);
        return medication;
    }

    public Medication getMedication(String uuid) throws MedicationException{
        Medication medication = this.medicationService.getMedication(uuid, Boolean.TRUE);

        if(null == medication)
            throw MedicationException.medicationNoExist(uuid);
        return medication;
    }

    @Transactional
    public String editMedication(String uuid, Medication medicationInput) throws MedicationException{
        
        Medication medication = this.findByUuid(uuid);

        medication.setCode(medicationInput.getCode());
        medication.setName(medicationInput.getName());
        medication.setWeigth(medicationInput.getWeigth());
        medication.setImage(medicationInput.getImage());

        this.medicationService.saveAndFlush(medication);

        return medication.getUuid();
    }

    @Transactional
    public void deleteMedication(String uuid) throws MedicationException{
        
        Medication medication = this.findByUuid(uuid);

        medication.setEnabled(Boolean.FALSE);

        this.medicationService.saveAndFlush(medication);
    }

    public Medication findByCode(String code) throws MedicationException{
        Medication medication = this.medicationService.findByCode(code);

        if(null == medication)
            throw MedicationException.notExistByCodeException(code);
        return medication;
    }
}
