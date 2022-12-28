package com.demo.drone.data.management.projection;

import com.demo.drone.data.common.projection.CustomProjection;
import com.demo.drone.data.management.entity.Drone;

public class DroneProjection extends CustomProjection {

    String uuid;

    String serial;

    Integer battery ;

    String state;

    String model;

    public DroneProjection(Drone drone) {
        this.uuid = drone.getUuid();
        this.serial = drone.getSerial();
        this.battery = drone.getBattery();
        this.state = drone.getState().toString();
        this.model = drone.getModel().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    
    
}
