package com.cognizant.hotel.api;


import com.cognizant.hotel.model.Customer;
import com.cognizant.hotel.model.IRoom;
import com.cognizant.hotel.service.CustomerService;
import com.cognizant.hotel.service.ReservationService;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminResource {

    private static CustomerService customerService=CustomerService.getInstance();
    private static ReservationService reservationService=ReservationService.getInstance();

    public HashMap<Integer, IRoom> rooms;
    private static final AdminResource adminResource=new AdminResource();
    private AdminResource () {
    }

    public static AdminResource getAdminResource() {
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(Map<Integer, IRoom> rooms) {

        for(IRoom room:rooms.values()) {
            try {
                this.reservationService.addRoom(room);
            } catch (IllegalArgumentException  e) {
                System.out.printf("room with number %s already exist, please start over with a different number \n", room.getRoomNumber());
                throw new RuntimeException();
            }

        }
    }

    public Collection<IRoom> getAllRooms() {
        return this.rooms.values();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }

}