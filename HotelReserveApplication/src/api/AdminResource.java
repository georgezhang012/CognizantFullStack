package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static CustomerService customerService;
    private static ReservationService reservationService;

    public List<IRoom> rooms;
    private static final AdminResource adminResource=new AdminResource();
    private AdminResource () {
    }

    public static AdminResource getAdminResource() {
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {

        for(IRoom room:rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return this.rooms;
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }

    private static void displayAllReservation() {
        reservationService.printAllReservation();
    }
}
