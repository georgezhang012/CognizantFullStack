package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    private static ReservationService reservationService=new ReservationService();

    private List<Reservation> reservationList;
    private List<IRoom> rooms;


    private ReservationService() {
    }

    public static ReservationService getReservationService() {
        return reservationService;
    }
    public void addRoom(IRoom room) {

        rooms.add(room);
    }


    public IRoom getARoom(String roomId) {

        for(IRoom iRoom:rooms) {
            if(iRoom.getRoomNumber().equals(roomId)) {
                return iRoom;
            }
        }
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        List<IRoom> nonAvailableRooms=new ArrayList<>();

        for(Reservation reservation: reservationList) {
            if(isReservationConflict(reservation, checkInDate, checkOutDate)) {
                nonAvailableRooms.add(reservation.getRoom());
            }
        }

        return rooms.stream().filter(room-> !nonAvailableRooms.contains(room)).collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {

        return reservationList.stream().filter(reservation -> reservation.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    public void printAllReservation() {

        for(Reservation reservation:reservationList) {
            System.out.println(reservation.toString());
        }

    }

    private boolean isReservationConflict(Reservation reservation, Date checkInDate, Date checkOutDate) {
        if(reservation.getCheckInDate().before(checkOutDate) || reservation.getCheckOutDate().before(checkInDate)) {
            return true;
        }

        return false;
    }

}
