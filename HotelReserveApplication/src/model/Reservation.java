package model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;
    @Override
    public String toString() {
        String reservationInfo=String.format("Customer %s room %s checkInDate %s checkOutDate %s",
                customer.firstName+" "+customer.lastName,
                room.getRoomNumber(),
                checkInDate.toString(),
                checkOutDate.toString()
        );

        return reservationInfo;
    }
}
