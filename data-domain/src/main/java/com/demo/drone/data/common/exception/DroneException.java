package com.demo.drone.data.common.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.demo.drone.data.common.model.DroneConstraintKey;

public class DroneException extends Exception{

    private String keyHash = "";

    //Error Unique Contraint "Entrada Duplicada"
    static String ER_DUP_ENTRY = "23505"; //(23505); MySql (1062)

    //Error Not Null Contraint "Entrada NUll"
    static String ER_NULL_ENTRY = "23502"; //(23502); MySql (?)

    //Error Foreining Key Violation "Falta Llave Foranea"
    static String ER_FK_ENTRY = "23503"; //(23503); MySql (?)

    public DroneException() {
    }

    public DroneException(String message) {
        super(message);

        if(null != message ) {
            keyHash = this.getMd5(message);
        }
    }


    private String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public String getKeyHash() {
        return keyHash;
    }

    public static DroneConstraintKey contraintKey(Exception e){

        if (e instanceof DataIntegrityViolationException){

            if(e.getCause() instanceof ConstraintViolationException){

                try{

                    String contraint = ((ConstraintViolationException)e.getCause()).getConstraintName();

                    if(null == contraint){
                        return DroneConstraintKey.UK00000;
                    }

                    return DroneConstraintKey.valueOf(contraint.toUpperCase());
                }
                catch (Exception ex){

                    return DroneConstraintKey.UK00000;
                }
            }
        }

        return DroneConstraintKey.UK00000;
    }

    public static ErrorCode errorCode(Exception e){

        if (e instanceof DataIntegrityViolationException){

            //DataIntegrityViolationException
            try {
                if (e.getCause() instanceof ConstraintViolationException) {

                    String code = ((ConstraintViolationException) e.getCause()).getSQLException().getSQLState(); //Postgres


                    if (code.equals(DroneException.ER_DUP_ENTRY)) {

                        return ErrorCode.ER_DUP_ENTRY;
                    }

                    if (code.equals(DroneException.ER_NULL_ENTRY)) {

                        return ErrorCode.ER_NULL_ENTRY;
                    }

                    if (code.equals(DroneException.ER_FK_ENTRY)) {

                        return ErrorCode.ER_FK_ENTRY;
                    }

                }
            }
            catch (Exception ex){

                return ErrorCode.NONE;
            }

        }

        return ErrorCode.NONE;
    }
}