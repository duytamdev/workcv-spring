package com.funix.PRJ321x_Project2_tamFX27974.utils;

public enum StatusApply {
    NEW("New"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    private final String displayName;

    StatusApply(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}