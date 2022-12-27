package com.demo.drone.data.tracking.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.demo.drone.data.common.business.AbstractBusiness;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.management.execption.DroneException;
import com.demo.drone.data.management.model.State;
import com.demo.drone.data.management.service.DroneService;
import com.demo.drone.data.tracking.model.OrderHistory;
import com.demo.drone.data.tracking.service.OrderHistoryService;


@Service
public class OrderHistoryBusiness extends AbstractBusiness {

    @Autowired
    private OrderHistoryService orderHistoryService;

    private OrderHistory findByUuid(String uuid) throws DroneException{
        OrderHistory orderHistory = this.orderHistoryService.findByUuid(uuid);

        if(null== orderHistory)
            throw new DroneException("OrderHistory not exist");

        return orderHistory;
    }

    public OrderHistory getOrderHistory(String uuid) throws DroneException{

        return this.findByUuid(uuid);
    }

    public List<OrderHistory> findByOrderUuid(String orderUuid) throws DroneException{

        return this.orderHistoryService.findByOrderUuid(orderUuid);
    }

    public List<OrderHistory> findByDroneUuid(String droneUuid) throws DroneException{

        return this.orderHistoryService.findByDroneUuid(droneUuid);
    }

    public List<OrderHistory> findByDroneUuidAndOrderUuid(String droneUuid, String orderUuid) throws DroneException{

        return this.orderHistoryService.findByDroneUuidAndOrderUuid(droneUuid, orderUuid);
    }
}
