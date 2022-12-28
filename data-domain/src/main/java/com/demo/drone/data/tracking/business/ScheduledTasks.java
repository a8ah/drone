package com.demo.drone.data.tracking.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.drone.data.management.business.DroneBusiness;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.tracking.model.DroneBatteryHistory;
import com.demo.drone.data.tracking.service.DroneBatteryHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class ScheduledTasks {

    @Autowired
    DroneBusiness droneBusiness;

    @Autowired
    DroneBatteryHistoryService droneBatteryHistoryService;

    private void registerDroneBattery(){

        List<Drone> dorneList = this.droneBusiness.getAllDrones();

        List<DroneBatteryHistory> droneBatteryHistoryList = new ArrayList<>();
        for (Drone drone : dorneList) {
            DroneBatteryHistory droneBatteryHistory = new DroneBatteryHistory();
            droneBatteryHistory.setDroneId(drone.getUuid());
            droneBatteryHistory.setDroneSerial(drone.getSerial());
            droneBatteryHistory.setBattery(drone.getBattery());

            droneBatteryHistoryList.add(droneBatteryHistory);
        }

        this.droneBatteryHistoryService.save(droneBatteryHistoryList);
        System.out.println("Drones Battery checked at  "+ LocalDateTime.now());
    }
    
    @Scheduled(fixedRate = 60000)
    public void computePrice() throws InterruptedException {
    
        this.registerDroneBattery();
    }

}
