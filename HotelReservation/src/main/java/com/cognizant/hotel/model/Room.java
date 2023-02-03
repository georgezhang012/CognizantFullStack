package com.cognizant.hotel.model;

public class Room implements IRoom {

    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;


    public Room(String rNumber, Double price, RoomType type) {
        this.roomNumber=rNumber;
        this.roomPrice=price;
        this.roomType=type;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public String getRoomType() {
        return roomType.toString();
    }

    @Override
    public boolean isFree() {
        return getRoomPrice()!=null && getRoomPrice()==0;
    }

    @Override
    public String toString() {
        String output=String.format("Room number: %s, price: %s, type: %s ", this.roomNumber, this.roomPrice.toString(), this.roomType.toString());
        return output;
    }


}
