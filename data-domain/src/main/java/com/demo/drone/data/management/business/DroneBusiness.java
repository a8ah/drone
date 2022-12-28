package com.demo.drone.data.management.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.demo.drone.data.cargo.business.OrderBusiness;
import com.demo.drone.data.cargo.entity.Order;
import com.demo.drone.data.cargo.entity.OrderMedication;
import com.demo.drone.data.cargo.execption.OrderException;
import com.demo.drone.data.cargo.model.OrderState;
import com.demo.drone.data.common.business.AbstractBusiness;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.management.execption.DroneException;
import com.demo.drone.data.management.execption.MedicationException;
import com.demo.drone.data.management.model.State;
import com.demo.drone.data.management.projection.OrderMedicationCheckProjection;
import com.demo.drone.data.management.service.DroneService;


@Service
public class DroneBusiness extends AbstractBusiness {

    @Autowired
    private DroneService droneService;

    @Autowired
    private OrderBusiness orderBusiness;

    @Value("${drone.battery.minialAvailable}")
    private Integer minialAvailable;

    // public Page<MedicationProjection> getAllMedicationByEnabled(PageRequest page, Boolean enabled ){
        
    //     return this.droneService.getAllMedicationByEnabled(page, enabled);
    // }

    @Transactional
    public String register(Drone droneInput) throws DroneException{
        Drone drone = new Drone();

        if(this.droneService.existsBySerial(droneInput.getSerial()))
            throw DroneException.existBySerialException(droneInput.getSerial());

        drone.setSerial(droneInput.getSerial());
        drone.setBattery(droneInput.getBattery());
        drone.setWeigth(droneInput.getWeigth());
        drone.setState(droneInput.getState());
        drone.setModel(droneInput.getModel());

        this.droneService.saveAndFlush(drone);

        return drone.getUuid();
    }

    public Drone findByUuid(String uuid) throws DroneException {
        Drone drone = this.droneService.findByUuid(uuid);

        if(null == drone)
            throw DroneException.droneNoExist(uuid);
        return drone;
    }

    public Drone getDrone(String uuid) throws DroneException{
        Drone drone = this.droneService.getDrone(uuid, Boolean.TRUE);

        if(null == drone)
            throw DroneException.droneNoExist(uuid);
        return drone;
    }

    @Transactional
    public String edit(String uuid, Drone droneInput) throws DroneException{
        
        Drone drone = this.findByUuid(uuid);

        drone.setSerial(droneInput.getSerial());
        drone.setBattery(droneInput.getBattery());
        drone.setWeigth(droneInput.getWeigth());
        drone.setState(droneInput.getState());
        drone.setModel(droneInput.getModel());

        this.droneService.saveAndFlush(drone);

        return drone.getUuid();
    }

    @Transactional
    public void delete(String uuid) throws DroneException{
        
        Drone drone = this.findByUuid(uuid);

        drone.setEnabled(Boolean.FALSE);

        this.droneService.saveAndFlush(drone);
    }

    public void changeState(String uuid, State new_state) throws DroneException{
        Drone drone = this.findByUuid(uuid);

        drone.setState(new_state);

        this.droneService.saveAndFlush(drone);
    }

    public Integer getDroneBattery(String uuid) throws DroneException{

        Drone drone = this.findByUuid(uuid);

        return drone.getBattery();
    }

    public List<Drone> getAvailablesDroneToDeliver() throws DroneException{

        List<State> states =new ArrayList<>();
        states.add(State.IDLE);

        return this.droneService.findAvailablesDrones(states,minialAvailable);
    }

    @Transactional
    public void loadCargo(String droneUuid, List<String> orders) throws DroneException, MedicationException, OrderException {

        Drone drone = this.findByUuid(droneUuid);

        if(!State.IDLE.equals(drone.getState()))
            throw DroneException.stausException();

        Double partialWeigth= 0.0;

        if(minialAvailable > drone.getBattery())
            throw DroneException.lowBatteryException();

        for (String uuid : orders) {
            Order order = this.orderBusiness.getOrder(uuid);
            partialWeigth += order.getWeigth();

            if(partialWeigth> drone.getWeigth())
                throw DroneException.cargoWeigthException(drone.getWeigth(), partialWeigth );

            order.setDrone(drone);
            order.setState(OrderState.LOADING);
            this.orderBusiness.save(order);
        }

        drone.setState(State.LOADING);
        this.droneService.saveAndFlush(drone);
    }

    public Set<Order> checkingLoadedMedication(String uuid) throws DroneException{
        Drone drone = this.findByUuid(uuid);

        List list = Arrays.asList(new State[]{State.LOADING,State.LOADED,State.DELIVERING,State.DELIVERED});
        if (!list.contains(drone.getState()))
            throw DroneException.operationUnavailableException();
        
        return drone.getOrders();

    }
}
