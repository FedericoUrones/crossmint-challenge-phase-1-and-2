package com.crossmint.challenge.createmegaverse.domain.entities;

public enum EntitiesDirections {
    RIGHT("RIGHT"),
    LEFT("LEFT"),
    UP("UP"),
    DOWN("DOWN");

    private final String text;

    EntitiesDirections(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static EntitiesDirections fromString(String text) {
        for (EntitiesDirections name : EntitiesDirections.values()) {
            if (name.toString().equals(text)) {
                return name;
            }
        }
        return null;
    }
}
