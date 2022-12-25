package com.musala.drone.data.management.model;

public enum State {
	IDLE("IDLE"),
	LOADING("LOADING"),
	LOADED("LOADED"),
	DELIVERING("DELIVERING"),
	DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

	private final String name;

	State(String name) {
		this.name = name;
	}

	@Override
    public String toString() {

        switch (this.name()){

            case "IDLE":
                return "Out of service";

            case "LOADING":
                return "Loading products";

            case "LOADED":
                return "Loaded";

            case "DELIVERING":
                return "Delivering products";

            case "DELIVERED":
                return "Delivered";

            case "RETURNING":
                return "Returning to the base";

            default:
                return this.name();
        }
    }



}