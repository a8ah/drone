package com.demo.drone.management.dto;

import com.demo.drone.data.common.validators.medication.MedicationCode;
import com.demo.drone.data.common.validators.medication.MedicationName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedicationCreateDto {
    
    @NotNull(message = "The item Name is required!")
    @Size(min = 2, max = 15)
    @MedicationName
    private String name;
    @NotNull(message = "The item code is required!")
    @MedicationCode
    private String code;

    @NotNull(message = "The item weigth is required!")
    private Double weigth;

    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
