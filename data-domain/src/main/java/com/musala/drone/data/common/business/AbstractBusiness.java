package com.musala.drone.data.common.business;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractBusiness {


    protected String formatFullName (String name, String firstSurname, String secondSurname) {

        firstSurname = (null != firstSurname && !firstSurname.isEmpty()) ? firstSurname : "";

        name = (null != name && !name.isEmpty()) ? " " + name : "";
        secondSurname = (null != secondSurname && !secondSurname.isEmpty()) ?  " " + secondSurname : "";

        return firstSurname + secondSurname + name;
    }


}
