package com.cognizant.hotel.api;

import com.cognizant.hotel.model.Customer;
import com.cognizant.hotel.model.IRoom;
import com.cognizant.hotel.model.Reservation;
import com.cognizant.hotel.model.RoomType;
import com.cognizant.hotel.service.CustomerService;
import com.cognizant.hotel.service.ReservationService;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HotelResource {

    private CustomerService customerService=CustomerService.getInstance();
    private ReservationService reservationService=ReservationService.getInstance();

    private static final HotelResource hotelResource=new HotelResource();

    private HotelResource()  { }

    public static HotelResource getHotelResource() {
        return hotelResource;
    }

    public Customer getCustomer(String email) {

        return customerService.getCustomer(email);
    }

    public Collection<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    public void createACustomer(String email, String firstName, String lastName) throws IllegalArgumentException {

        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {

        return reservationService.getRoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer=customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate );
    }

    public void displayRooms(List<IRoom> rooms) {
        rooms.stream().forEach(room -> System.out.println(room));
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer=customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public  Collection<IRoom> findRoom(Date checkInDate, Date checkOutDate, RoomType roomType) {
        return  reservationService.getAvailableRooms(checkInDate, checkOutDate, roomType);
    }

}