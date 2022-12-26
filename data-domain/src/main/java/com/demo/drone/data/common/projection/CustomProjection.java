package com.demo.drone.data.common.projection;

public abstract class CustomProjection {

    public CustomProjection() {}

    protected String prepareWhitCode(String codeInput, String text){

        return this.prepareWhitCode(codeInput, text, true);
    }

    protected String prepareWhitCode(String codeInput, String text, Boolean showEmptyCode){

        if(null == text){

            return "";
        }

        if(text == codeInput || text.isEmpty()){

            return "";
        }

        String code = showEmptyCode ? "[---] " : "";
        if(null != codeInput && !codeInput.isEmpty()){

            code = "[" + codeInput + "] ";
        }

        return code + text;
    }

    protected String formatFullName (String name, String firstSurname, String secondSurname) {
        return this.formatFullName(name, firstSurname, secondSurname, Boolean.TRUE);
    }

    protected String formatFullName (String name, String firstSurname, String secondSurname, Boolean secretaryName) {

        secondSurname = (null != secondSurname && !secondSurname.isEmpty()) ?  " " + secondSurname : "";

        if(secretaryName){

            name = (null != name && !name.isEmpty()) ? " " + name : "";
            firstSurname = (null != firstSurname && !firstSurname.isEmpty()) ? firstSurname : "";

            return firstSurname + secondSurname + name;
        }
        else{

            name = (null != name && !name.isEmpty()) ? name : "";
            firstSurname = (null != firstSurname && !firstSurname.isEmpty()) ? " " + firstSurname : "";

            return name + firstSurname + secondSurname;
        }

    }

    protected String getFormatFullAdress(String street, String outdoorNumber, String interiorNumber,
                                  String betweenStreet1, String betweenStreet2, String location,
                                  String colony, String municipality, String state, String zipCode){

        street = (null != street && !street.isEmpty()) ? street + " " : "";

        interiorNumber = (null != interiorNumber && !interiorNumber.isEmpty()) ? " (NI:" + interiorNumber + ")" : "";
        outdoorNumber = (null != outdoorNumber && !outdoorNumber.isEmpty()) ? outdoorNumber + interiorNumber + ", " : "";



        String betweenStreet = "";

        if(null != betweenStreet1 && !betweenStreet1.isEmpty() && null != betweenStreet2 && !betweenStreet2.isEmpty()){

            betweenStreet = "entre " + betweenStreet1 + " y " + betweenStreet2 + ", ";
        }

        location = (null != location && !location.isEmpty()) ? location + ", " : "";

        colony = (null != colony && !colony.isEmpty()) ? colony + ", " : "";
        municipality = (null != municipality && !municipality.isEmpty()) ? municipality + ", " : "";
        state = (null != state && !state.isEmpty()) ? state + ", " : "";

        zipCode = (null != zipCode && !zipCode.isEmpty()) ? "C.P." + zipCode : "";


        return street + outdoorNumber + betweenStreet + location + colony + municipality + state + zipCode;
    }

    protected String getFormatShortAdress(String street, String outdoorNumber, String interiorNumber,
                                         String betweenStreet1, String betweenStreet2, String location,
                                         String colony, String municipality, String state, String zipCode){

        street = (null != street && !street.isEmpty()) ? street + " " : "";

        outdoorNumber = (null != outdoorNumber && !outdoorNumber.isEmpty()) ? outdoorNumber + ", " : "";

        colony = (null != colony && !colony.isEmpty()) ? colony + ", " : "";
        municipality = (null != municipality && !municipality.isEmpty()) ? municipality + ", " : "";
        state = (null != state && !state.isEmpty()) ? state + ", " : "";

        zipCode = (null != zipCode && !zipCode.isEmpty()) ? "C.P." + zipCode : "";


        return street + outdoorNumber + colony + municipality + state + zipCode;
    }

    // protected String convertToYorNName(Boolean choice){

    //     if(null == choice)
    //         return ChoiseYesOrNo.NO.toString();

    //     return choice ? ChoiseYesOrNo.YES.toString() : ChoiseYesOrNo.NO.toString();
    // }
}
