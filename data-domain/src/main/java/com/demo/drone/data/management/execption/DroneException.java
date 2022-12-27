package com.demo.drone.data.management.execption;

import com.demo.drone.data.common.exception.DroneBaseException;
import com.demo.drone.data.management.model.State;

public class DroneException extends DroneBaseException {

    public DroneException(String message)
    {
        super(message);
    }

    public static DroneException droneNoExist(String uuid)
    {

        String message = "Drone with ID " + uuid + " not exist";

        return new DroneException(message);
    }


    public static DroneException existBySerialException(String serial)
    {

        String message = "Drone with serial " + serial  + " already exist";

        return new DroneException(message);
    }

    public static DroneException stausException()
    {

        String message = "Drone unavailable to load, Only Drons in " + State.IDLE.toString() + " state are availables for this operation";

        return new DroneException(message);
    }

    public static DroneException lowBatteryException()
    {

        String message = "Drone unavailable to deliver. LOW Battery";

        return new DroneException(message);
    }

    public static DroneException cargoWeigthException(Double drone_weigth, Double partial_weigth)
    {

        String message = "Drone has exceeded the weight limit. Drone max weigth " + drone_weigth + ", actual weigth " + partial_weigth;

        return new DroneException(message);
    }

}