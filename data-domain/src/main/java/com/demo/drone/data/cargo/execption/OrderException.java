package com.demo.drone.data.cargo.execption;
import com.demo.drone.data.common.exception.DroneBaseException;

public class OrderException extends DroneBaseException {

    public OrderException(String message)
    {
        super(message);
    }

    public static OrderException orderNoExist(String uuid)
    {

        String message = "Order with ID " + uuid + " not exist";

        return new OrderException(message);
    }

    public static OrderException notExistByCodeException(String code)
    {

        String message = "Order with code " + code  + " do not exist";

        return new OrderException(message);
    }

}