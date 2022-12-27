package com.demo.drone.data.management.execption;

import com.demo.drone.data.common.exception.DroneBaseException;

public class MedicationException extends DroneBaseException {

    public MedicationException(String message)
    {
        super(message);
    }

    public static MedicationException medicationNoExist(String uuid)
    {

        String message = "Medication with ID " + uuid + " not exist";

        return new MedicationException(message);
    }

    // public static MedicationException notPermited(String uuid)
    // {

    //     String message = "La operacion especificada no esta permitida para el prospecto con ID " + uuid;

    //     return new MedicationException(message);
    // }

    public static MedicationException existByCodeException(String code)
    {

        String message = "Medication with code " + code  + " already exist";

        return new MedicationException(message);
    }

    public static MedicationException notExistByCodeException(String code)
    {

        String message = "Medication with code " + code  + " not registred";

        return new MedicationException(message);
    }

}