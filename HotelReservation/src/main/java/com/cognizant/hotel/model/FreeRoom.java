package com.cognizant.hotel.model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType type) {
        super(roomNumber, 0.0, type);
    }

    @Override
    public String toString() {
        return "### Free room! ### \n " +super.toString();
    }
}
