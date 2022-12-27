package com.demo.drone.tracking.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.demo.drone.data.cargo.business.OrderBusiness;
import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.cargo.request.MedicationOrderRequest;
import com.demo.drone.data.management.execption.DroneException;
import com.demo.drone.data.management.execption.MedicationException;
import com.demo.drone.data.tracking.business.OrderHistoryBusiness;
import com.demo.drone.data.tracking.model.OrderHistory;


@Service
public class OrderHistoryProcess {

    @Autowired
    private OrderHistoryBusiness orderHistoryBusiness;

    public OrderHistory getOrderHistory(String uuid) throws DroneException  {

        return this.orderHistoryBusiness.getOrderHistory(uuid);

    }

    public List<OrderHistory> findByOrderUuid(String uuid) throws DroneException  {

        return this.orderHistoryBusiness.findByOrderUuid(uuid);

    }
    public List<OrderHistory> findByDroneUuid(String uuid) throws DroneException  {

        return this.orderHistoryBusiness.findByDroneUuid(uuid);

    }
    public List<OrderHistory> findByDroneUuidAndOrderUuid(String droneUuid, String orderUuid) throws DroneException  {

        return this.orderHistoryBusiness.findByDroneUuidAndOrderUuid(droneUuid, orderUuid);

    }


}