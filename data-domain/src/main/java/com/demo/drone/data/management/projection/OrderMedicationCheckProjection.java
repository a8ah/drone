package com.demo.drone.data.management.projection;

import java.util.List;

import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.common.projection.CustomProjection;

public class OrderMedicationCheckProjection extends CustomProjection {

    String uuid;

    List<OrderMedicationProjection> medications;

    Double weigth;

    String code;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<OrderMedicationProjection> getMedicationName() {
        return medications;
    }

    public void setMedicationName(List<OrderMedicationProjection> medications) {
        this.medications = medications;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

    public List<OrderMedicationProjection> getMedications() {
        return medications;
    }

    public void setMedications(List<OrderMedicationProjection> medications) {
        this.medications = medications;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    
}
