package com.crossmint.challenge.createmegaverse.domain.entities;

public enum EntitiesNames {
    SPACE("SPACE"),
    SOLOON("SOLOON"),
    POLYANET("POLYANET"),
    COMETH("COMETH");

    private final String text;

    EntitiesNames(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static EntitiesNames fromString(String text) {
        for (EntitiesNames name : EntitiesNames.values()) {
            if (name.toString().equals(text)) {
                return name;
            }
        }
        return null;
    }
}
