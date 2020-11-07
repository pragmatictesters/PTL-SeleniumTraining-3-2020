package com.pragmatic.examples.java;

public enum Size {

    L("large"), M("medium"), S("small");

    private String description;

    Size(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
