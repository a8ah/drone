package com.demo.drone.data.common.model;

public class CustomMessages {

    static public String message(String code){

        return getDefaultMessage(code);
    }

    static private String getDefaultMessage(String code){

        switch (code){

            //General Message
            case "U0000":
                return "An Error has ocurred";

            //Management Message
            case "UKMG001":
                return "A medication with this code allready exist";

            //Management Medication Delete
            case "MSMG001":
                return "Medication successfully deleted";

        }

        return "";
    }
}
