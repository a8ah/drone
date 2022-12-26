package com.demo.drone.data.management.projection;

import com.demo.drone.data.common.projection.CustomProjection;

public class MedicationProjection extends CustomProjection {

    String uuid;

    String name;

    String code;

    Double weigth;

    String image;

    Boolean enabled;

    public MedicationProjection(){}

    public MedicationProjection(String uuid, String name, String code, Double weigth) {
        this.uuid = uuid;
        this.name = name;
        this.code = code;
        this.weigth = weigth;
    }

    public MedicationProjection(String uuid, String name, String code, Double weigth, String image) {
        this.uuid = uuid;
        this.name = name;
        this.code = code;
        this.weigth = weigth;
        this.image = image;
    }
    
}
