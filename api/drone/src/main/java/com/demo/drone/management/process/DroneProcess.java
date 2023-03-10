package com.demo.drone.management.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.common.exception.DroneBaseException;
import com.demo.drone.data.management.business.DroneBusiness;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.management.execption.DroneException;
import com.demo.drone.data.management.model.Model;
import com.demo.drone.data.management.model.State;
import com.demo.drone.data.management.projection.DroneProjection;
import com.demo.drone.data.management.projection.OrderMedicationCheckProjection;
import com.demo.drone.data.management.projection.OrderMedicationProjection;
import com.demo.drone.management.dto.DroneCreateDto;
import com.demo.drone.management.dto.DroneEditDto;


@Service
public class DroneProcess {

    @Autowired
    private DroneBusiness droneBusiness;

    public List<DroneProjection> getEnabledDrones(){
        List<Drone>  doneList = this.droneBusiness.getAllDroneByEnabled(Boolean.TRUE);

        List<DroneProjection> resultList = new ArrayList<>();

        for (Drone drone : doneList) {
            DroneProjection droneProjection = new DroneProjection(drone);
            resultList.add(droneProjection);
        }

        return resultList;
    }

    public String registerDrone(DroneCreateDto entity) throws DroneBaseException{

        Model model = Model.valueOf(entity.getModel());
        if(null== entity.getModel())
            throw new DroneBaseException("Error loading Drone MODEL. Allowed values 'LIGHTWEIGHT','MIDDLEWEIGHT','CRUISERWEIGHT','HEAVYWEIGHT' ");

        Drone drone = new Drone();
        drone.setSerial(entity.getSerial());
        drone.setModel(model);
        drone.setWeigth(entity.getWeigth());

        return this.droneBusiness.register(drone);
    }

    public Drone getDrone(String uuid) throws DroneException{

        return this.droneBusiness.getDrone(uuid);
    }

    public String editDrone(String uuid,DroneEditDto entity) throws DroneBaseException{

        Model model = Model.valueOf(entity.getModel());
        if(null== entity.getModel())
            throw new DroneBaseException("Error loading Drone MODEL. Allowed values 'LIGHTWEIGHT','MIDDLEWEIGHT','CRUISERWEIGHT','HEAVYWEIGHT' ");

        Drone drone = new Drone();
        drone.setSerial(entity.getSerial());
        drone.setModel(model);
        drone.setWeigth(entity.getWeigth());
        drone.setBattery(entity.getBattery());

        return this.droneBusiness.edit(uuid,drone);
    }

    public void deleteDrone(String uuid) throws DroneBaseException{
        this.droneBusiness.delete(uuid);
    }

    public void changeState(String uuid, String stateInput) throws DroneBaseException{
        
        State state = State.valueOf(stateInput);
        if(null== stateInput)
            throw new DroneBaseException("Error loading Drone STATE. Allowed values 'IDLE','LOADING','LOADED','DELIVERING','DELIVERED','RETURNING' ");

        this.droneBusiness.changeState(uuid,state);
    }

    public Integer getDroneBattery(String uuid) throws DroneException{
        return this.droneBusiness.getDroneBattery(uuid);
    }

    public List<Drone> availablesDroneToDeliver() throws DroneException{
        return this.droneBusiness.getAvailablesDroneToDeliver();
    }

    public List<OrderMedicationCheckProjection> checkingLoadedMedication(String uuid) throws DroneException{
        Set<Order> ordersSet =  this.droneBusiness.checkingLoadedMedication(uuid);
    
        List<OrderMedicationCheckProjection> orderMedicationCheck = new ArrayList();

        for (Order order : ordersSet) {
            OrderMedicationCheckProjection orderMedicationCheckProjection = new OrderMedicationCheckProjection();
            Set<OrderMedication> ordermedicationSet = order.getMedications();
            List<OrderMedicationProjection> ordermedicationList = new ArrayList<>();
            for (OrderMedication oMedication : ordermedicationSet) {
                OrderMedicationProjection orderMedicationProjection = new OrderMedicationProjection();
                orderMedicationProjection.setMedication(oMedication.getMedication().getName());
                orderMedicationProjection.setQuantity(oMedication.getQuantity());
                ordermedicationList.add(orderMedicationProjection);
            }
            orderMedicationCheckProjection.setMedicationName(ordermedicationList);
            orderMedicationCheckProjection.setWeigth(order.getWeigth());
            orderMedicationCheckProjection.setUuid(order.getUuid());
            orderMedicationCheckProjection.setCode(order.getCode());

            orderMedicationCheck.add(orderMedicationCheckProjection);
        }

        return orderMedicationCheck;
    }
}