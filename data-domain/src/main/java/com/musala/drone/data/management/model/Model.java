package com.musala.drone.data.management.model;

public enum Model {
	LIGHTWEIGHT("LIGHTWEIGHT"),
	MIDDLEWEIGHT("MIDDLEWEIGHT"),
	CRUISERWEIGHT("CRUISERWEIGHT"),
	HEAVYWEIGHT("HEAVYWEIGHT");

	private final String name;

	Model(String name) {
		this.name = name;
	}

	@Override
    public String toString() {

        switch (this.name()){

            case "LIGHTWEIGHT":
                return "Lightweight";

            case "MIDDLEWEIGHT":
                return "Middleweight";

            case "CRUISERWEIGHT":
                return "Cruiserweight";

            case "HEAVYWEIGHT":
                return "Heavyweight";

            default:
                return this.name();
        }
    }



}