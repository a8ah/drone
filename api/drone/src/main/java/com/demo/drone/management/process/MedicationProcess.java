package com.demo.drone.management.process;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.drone.data.common.exception.DroneException;
import com.demo.drone.data.management.business.MedicationBusiness;
import com.demo.drone.data.management.entity.Medication;
import com.demo.drone.data.management.execption.MedicationException;
import com.demo.drone.data.management.projection.MedicationProjection;
import com.demo.drone.management.dto.MedicationCreateDto;


@Service
public class MedicationProcess {

    @Autowired
    private MedicationBusiness medicationBusiness;


    public Page<MedicationProjection> getAllEnabledMedication(Integer pageNumber, Integer pageSize){
        PageRequest page = PageRequest.of(pageNumber, pageSize);
        return this.medicationBusiness.getAllMedicationByEnabled(page, Boolean.TRUE);
    }

    public String registerMedication(MedicationCreateDto entity) throws MedicationException{

        Medication medication = new Medication();
        medication.setName(entity.getName());
        medication.setCode(entity.getCode());
        medication.setWeigth(entity.getWeigth());
        medication.setImage(entity.getImage());

        return this.medicationBusiness.registerMedication(medication);
    }

    public Medication getMedication(String uuid) throws MedicationException{

        return this.medicationBusiness.getMedication(uuid);
    }

    public String editMedication(String uuid,MedicationCreateDto entity) throws DroneException{

        Medication medication = new Medication();
        medication.setUuid(uuid);
        medication.setName(entity.getName());
        medication.setCode(entity.getCode());
        medication.setWeigth(entity.getWeigth());
        medication.setImage(entity.getImage());

        return this.medicationBusiness.editMedication(uuid,medication);
    }

    public void deleteMedication(String uuid) throws DroneException{

        this.medicationBusiness.deleteMedication(uuid);
    }


}