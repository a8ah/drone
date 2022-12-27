package com.demo.drone.cargo.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.demo.drone.data.cargo.business.OrderBusiness;
import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.cargo.request.MedicationOrderRequest;
import com.demo.drone.data.management.execption.MedicationException;


@Service
public class OrderProcess {

    @Autowired
    private OrderBusiness orderBusiness;

    public String register(Map<String,Object> entity) throws MedicationException, NoSuchFieldException, SecurityException {

        List<MedicationOrderRequest> ordermedicationList = new ArrayList();

        ArrayList medicationOrder = (ArrayList) entity.get("medicationOrder");
        for( int i=0; i< medicationOrder.size(); i++){
            Map<String,Object> order  = (Map<String, Object>) medicationOrder.get(i);
            Map<String,Object> item  = (Map<String, Object>) order.get("item");
            String code = (String) item.get("medication");
            Integer quantity = (Integer) item.get("quantity");
            
            MedicationOrderRequest orderMedication = new MedicationOrderRequest();
            orderMedication.setMedication(code);
            orderMedication.setQuantity(quantity);
            ordermedicationList.add(orderMedication);
        }

        return this.orderBusiness.register(ordermedicationList);

    }

    // public Drone getDrone(String uuid) throws DroneException{

    //     return this.droneBusiness.getDrone(uuid);
    // }

    // public String editDrone(String uuid,DroneEditDto entity) throws DroneBaseException{

    //     Model model = Model.valueOf(entity.getModel());
    //     if(null== entity.getModel())
    //         throw new DroneBaseException("Error loading Drone MODEL. Allowed values 'LIGHTWEIGHT','MIDDLEWEIGHT','CRUISERWEIGHT','HEAVYWEIGHT' ");

    //     Drone drone = new Drone();
    //     drone.setSerial(entity.getSerial());
    //     drone.setModel(model);
    //     drone.setWeigth(entity.getWeigth());
    //     drone.setBattery(entity.getBattery());

    //     return this.droneBusiness.edit(uuid,drone);
    // }

    // public void deleteDrone(String uuid) throws DroneBaseException{
    //     this.droneBusiness.delete(uuid);
    // }

    // public void changeState(String uuid, String stateInput) throws DroneBaseException{
        
    //     State state = State.valueOf(stateInput);
    //     if(null== stateInput)
    //         throw new DroneBaseException("Error loading Drone STATE. Allowed values 'IDLE','LOADING','LOADED','DELIVERING','DELIVERED','RETURNING' ");

    //     this.droneBusiness.changeState(uuid,state);
    // }


}