package com.demo.drone.data.management.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.demo.drone.data.common.service.AbstractEntityService;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.management.model.State;
import com.demo.drone.data.management.repository.DroneRepository;

@Service
public class DroneService extends AbstractEntityService<Drone, String, DroneRepository> {


    public Drone findByUuid(String uuid){
        return this.repository.findByUuid(uuid);
    }

    public Drone getDrone(String uuid, Boolean enabled){
        return this.repository.findByUuidAndEnabled(uuid, enabled);
    }

    public Boolean existByUuid(String uuid){
        return this.repository.existsById(uuid);
    }

    public Boolean existsBySerial(String serial){
        return this.repository.existsBySerial(serial);
    }

    public Integer getBattery(String uuid){
        return this.repository.getBatteryByUuid(uuid);
    }

    public List<Drone> findAvailablesDrones(List<State> states,Integer batteryWarning){
        return this.repository.findByStateInAndBatteryGreaterThanEqual(states,batteryWarning);
    }

    public List<Drone> findAllByEnabled(Boolean enabled){
        return this.repository.findAllByEnabled(enabled);
    }
}