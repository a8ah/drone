package com.demo.drone.data.tracking.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.demo.drone.data.common.service.AbstractEntityService;
import com.demo.drone.data.tracking.model.OrderHistory;
import com.demo.drone.data.tracking.repositury.OrderHistoryRepository;

@Service
public class OrderHistoryService extends AbstractEntityService<OrderHistory, String, OrderHistoryRepository> {

    public  List<OrderHistory> findByOrderUuid(String orderUuid){
        return this.repository.findByOrderUuid(orderUuid);
    }

    public  List<OrderHistory> findByDroneUuid(String droneUuid){
        return this.repository.findByDroneUuid(droneUuid);
    }

    public List<OrderHistory> findByDroneUuidAndOrderUuid(String droneUuid, String orderUuid){
        return this.repository.findByDroneUuidAndOrderUuid(droneUuid, orderUuid);
    }

    public OrderHistory findByUuid(String uuid){
        return this.repository.findByUuid(uuid);
    }

}