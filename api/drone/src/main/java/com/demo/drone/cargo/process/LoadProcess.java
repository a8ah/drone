package com.demo.drone.cargo.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.demo.drone.data.cargo.business.OrderBusiness;
import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.cargo.execption.OrderException;
import com.demo.drone.data.cargo.request.MedicationOrderRequest;
import com.demo.drone.data.management.business.DroneBusiness;
import com.demo.drone.data.management.execption.DroneException;
import com.demo.drone.data.management.execption.MedicationException;


@Service
public class LoadProcess {

    @Autowired
    private DroneBusiness droneBusiness;

    public void load(String droneUuid,  Map<String,Object> entity) throws DroneException, MedicationException, OrderException {

        List<String> ordersList = new ArrayList<>();
        ArrayList orders = (ArrayList) entity.get("orders");
        for( int i=0; i< orders.size(); i++){
            Map<String,Object> order  = (Map<String, Object>) orders.get(i);
            String uuid = (String) order.get("uuid");
            ordersList.add(uuid);
        }

        this.droneBusiness.loadCargo( droneUuid,ordersList);

    }
    


}