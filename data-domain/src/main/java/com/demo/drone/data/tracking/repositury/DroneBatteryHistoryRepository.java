package com.demo.drone.data.tracking.repositury;

import org.springframework.stereotype.Repository;

import com.demo.drone.data.common.repository.EntityRepository;
import com.demo.drone.data.tracking.model.DroneBatteryHistory;

@Repository
public interface DroneBatteryHistoryRepository extends EntityRepository<DroneBatteryHistory, String> {

    
}