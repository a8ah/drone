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

            case "UKMG002":
                return "A drone with this serial allready exist";

            //------------------- Management --------------------------
            // Medication Delete
            case "MSMG001":
                return "Medication successfully deleted";

            //Drone Delete
            case "MSMG002":
                return "Drone successfully deleted";

            //Drone Delete
            case "MSMG003":
                return "Drone STATE updated";

            //Drone Register
            case "MSMG004":
                return "Drone registred";

            //Drone Edited
            case "MSMG005":
                return "Drone edited";

            // ------------ Cargo ----------------
            case "MSCA001":
                return "Order registred";

        }

        return "";
    }
}
