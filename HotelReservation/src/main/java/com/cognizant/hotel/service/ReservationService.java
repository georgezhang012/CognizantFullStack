package com.cognizant.hotel.service;

import com.cognizant.hotel.model.Customer;
import com.cognizant.hotel.model.IRoom;
import com.cognizant.hotel.model.Reservation;
import com.cognizant.hotel.model.RoomType;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

//    Singleton class

    private static final ReservationService reservationService=new ReservationService();
    private static List<Reservation> reservations = new ArrayList<>();
    private static Map<String, IRoom> rooms=new HashMap<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return reservationService;
    }

    public IRoom getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<IRoom> getAvailableRooms(Date checkInDate, Date checkOutDate, RoomType roomType) {

        Set<IRoom> occupiedRooms=new HashSet<>();

        for(Reservation reservation: reservations) {
            if(isBookingConflict(reservation, checkInDate,checkOutDate)) {
                occupiedRooms.add(reservation.getRoom());
            }
        }

        return rooms.values().stream().filter(r->!occupiedRooms.contains(r)).filter(r->r.getRoomType().equalsIgnoreCase(roomType.toString())).collect(Collectors.toList());

    }

    public void printAllReservation() {
        reservations.stream().forEach(r-> System.out.println(r));
    }

    private boolean isBookingConflict(Reservation reservation, Date checkInDate, Date checkOutDate) {
        return reservation.getCheckInDate().before(checkOutDate) || reservation.getCheckOutDate().before(checkInDate);
    }

    public void addRoom( IRoom room) {
        if(rooms!=null&&rooms.get(room.getRoomNumber())!=null) {
            System.out.println("Room  already exist! \n");
            throw new IllegalArgumentException("Room %s already exist");
        }
        rooms.put(room.getRoomNumber(), room);
        System.out.printf("Room %s added successfully. \n", room.getRoomNumber());
    }


    public Collection<Reservation> getCustomersReservation(Customer customer) {

        return reservations.stream().filter(reservation -> reservation.getCustomer().equals(customer)).collect(Collectors.toList());
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation=new Reservation(customer, room, checkInDate,checkOutDate);
        reservations.add(reservation);

        return reservation;
    }


}
