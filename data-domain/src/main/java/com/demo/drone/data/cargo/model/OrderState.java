package com.demo.drone.data.cargo.model;

public enum OrderState {
	PENDING("PENDING"),
	LOADING("LOADING"),
	LOADED("LOADED"),
	DELIVERING("DELIVERING"),
	DELIVERED("DELIVERED");

	private final String name;

	OrderState(String name) {
		this.name = name;
	}

	@Override
    public String toString() {

        switch (this.name()){

            case "PENDING":
                return "Waithing for an available drone";

            case "LOADING":
                return "Loading products";

            case "LOADED":
                return "Loaded";

            case "DELIVERING":
                return "Delivering products";

            case "DELIVERED":
                return "Delivered";

            default:
                return this.name();
        }
    }



}