package model;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Room implements IRoom{


    String roomNumber;
    private Double price;
    RoomType enumeration;

    public Room(String roomNumber, double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public Double getRoomPrice() {
        return this.getRoomPrice();
    }

    public RoomType getRoomType() {
        return this.getRoomType();
    }

    public boolean isFree(){
        return this.price!=null && this.price.equals(0.0);
    }

    @Override
    public String toString() {
        return "Room number: "+this.roomNumber+" Room price: "+ this.price+" roomType: "+this.enumeration;
    }

}


