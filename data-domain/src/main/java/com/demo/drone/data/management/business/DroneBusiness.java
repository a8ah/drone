package com.demo.drone.data.management.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.demo.drone.data.common.business.AbstractBusiness;
import com.demo.drone.data.management.entity.Drone;
import com.demo.drone.data.management.execption.DroneException;
import com.demo.drone.data.management.model.State;
import com.demo.drone.data.management.service.DroneService;


@Service
public class DroneBusiness extends AbstractBusiness {

    @Autowired
    private DroneService droneService;

    // public Page<MedicationProjection> getAllMedicationByEnabled(PageRequest page, Boolean enabled ){
        
    //     return this.droneService.getAllMedicationByEnabled(page, enabled);
    // }

    public String register(Drone droneInput) throws DroneException{
        Drone drone = new Drone();

        if(this.droneService.existsBySerial(droneInput.getSerial()))
            throw DroneException.existBySerialException(droneInput.getSerial());

        drone.setSerial(droneInput.getSerial());
        drone.setBatery(droneInput.getBatery());
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

    public String edit(String uuid, Drone droneInput) throws DroneException{
        
        Drone drone = this.findByUuid(uuid);

        drone.setSerial(droneInput.getSerial());
        drone.setBatery(droneInput.getBatery());
        drone.setWeigth(droneInput.getWeigth());
        drone.setState(droneInput.getState());
        drone.setModel(droneInput.getModel());

        this.droneService.saveAndFlush(drone);

        return drone.getUuid();
    }

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
}