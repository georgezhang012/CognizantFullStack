package com.cognizant.hotel.model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }


    @Override
    public String toString() {
        String reservationDetails=String.format(" Customer Name:  %s  \n Room number %s  \n checkInDate %s \n checkOutDate %s \n",
                customer.firstName+" "+customer.lastName,
                room.getRoomNumber(),
                checkInDate.toString(),
                checkOutDate.toString()
        );

        return reservationDetails;
    }

}
