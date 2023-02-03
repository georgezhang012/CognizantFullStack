package com.cognizant.hotel.model;

public enum RoomType {
    SINGLEROOM("1"),
    DOUBLEROOM("2");

    private String label;
    private RoomType(String label) {
        this.label=label;
    }

    public static RoomType fromString(String label) {
        for(RoomType rt:values()) {
            if(rt.label.equals(label)) {
                return rt;
            }
        }

        return null;
    }

}
