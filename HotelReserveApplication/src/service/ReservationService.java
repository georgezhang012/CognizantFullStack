package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {

    private static final ReservationService reservationService=new ReservationService();

    private static List<Reservation> reservationList = new ArrayList<>();
    private static List<IRoom> rooms= new ArrayList<>();


    private ReservationService() {
    }

    public static ReservationService getReservationService() {
        return reservationService;
    }
    public static void addRoom(IRoom room) {

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

    public Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {

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

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {

        Reservation reservation=new Reservation(customer, room, checkInDate,checkOutDate);
        reservationList.add(reservation);

        return reservation;
    }

    private boolean isReservationConflict(Reservation reservation, Date checkInDate, Date checkOutDate) {
        if(reservation.getCheckInDate().before(checkOutDate) || reservation.getCheckOutDate().before(checkInDate)) {
            return true;
        }

        return false;
    }

}
