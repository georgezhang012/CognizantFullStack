package model;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Room implements IRoom{


    String roomNumber;
    double price;
    RoomType roomType;
    Boolean isFree;

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
        return isFree;
    }

    @Override
    public String toString() {
        return "Room number: "+this.roomNumber+" Room price: "+ this.price+" roomType: "+this.roomType;
    }

}


