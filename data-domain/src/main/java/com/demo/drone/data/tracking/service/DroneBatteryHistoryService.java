package com.demo.drone.data.tracking.service;

import org.springframework.stereotype.Service;

import com.demo.drone.data.common.service.AbstractEntityService;
import com.demo.drone.data.tracking.model.DroneBatteryHistory;
import com.demo.drone.data.tracking.repositury.DroneBatteryHistoryRepository;

@Service
public class DroneBatteryHistoryService extends AbstractEntityService<DroneBatteryHistory, String, DroneBatteryHistoryRepository> {


}