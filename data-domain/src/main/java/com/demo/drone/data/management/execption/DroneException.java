package com.demo.drone.data.management.execption;

import com.demo.drone.data.common.exception.DroneBaseException;

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

}