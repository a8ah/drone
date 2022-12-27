package com.demo.drone.management.dto;

import com.demo.drone.data.common.validators.drone.DroneSerial;
import com.demo.drone.data.common.validators.drone.EnumModel;
import com.demo.drone.data.management.model.Model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DroneEditDto {
    
    @NotNull(message = "The item SERIAL is required!")
    @Length(min = 1, max = 100)
    @DroneSerial
    private String serial;

    private String model;

    @NotNull(message = "The item weigth is required!")
    private Double weigth;

    @Min(value=0, message="must be equal or greater than 0")  
    @Max(value=100, message="must be equal or less than 100")  
    private Integer battery;

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

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }
}
