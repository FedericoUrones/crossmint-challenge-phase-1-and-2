package com.crossmint.challenge.createmegaverse.domain.entities;

public enum EntitiesColors {
    WHITE("WHITE"),
    BLUE("BLUE"),
    PURPLE("PURPLE"),
    RED("RED");

    private final String text;

    EntitiesColors(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static EntitiesColors fromString(String text) {
        for (EntitiesColors name : EntitiesColors.values()) {
            if (name.toString().equals(text)) {
                return name;
            }
        }
        return null;
    }
}
