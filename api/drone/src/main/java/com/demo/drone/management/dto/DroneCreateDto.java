package com.demo.drone.management.dto;

import com.demo.drone.data.common.validators.drone.DroneSerial;
import com.demo.drone.data.common.validators.drone.EnumModel;
import com.demo.drone.data.management.model.Model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DroneCreateDto {

    @NotNull(message = "The item SERIAL is required!")
    @Length(min = 1, max = 100)
    @DroneSerial
    private String serial;

    private String model;

    @NotNull(message = "The item weigth is required!")
    @Min(value = 1, message="must be equal or greater than 1g")
    @Max(value = 500, message="must be equal or less than 500g")
    private Double weigth;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }
}
