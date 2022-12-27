package com.demo.drone.data.cargo.request;

public class MedicationOrderRequest {

    private String medication;

    private Integer quantity;

    public String getMedication() {
        return medication;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    // // @EnumModel
    // private String model;

    // @NotNull(message = "The item weigth is required!")
    // private Double weigth;

    // public String getSerial() {
    //     return serial;
    // }

    // public void setSerial(String serial) {
    //     this.serial = serial;
    // }

    // public String getModel() {
    //     return model;
    // }

    // public void setModel(String model) {
    //     this.model = model;
    // }

    // public Double getWeigth() {
    //     return weigth;
    // }

    // public void setWeigth(Double weigth) {
    //     this.weigth = weigth;
    // }
}
