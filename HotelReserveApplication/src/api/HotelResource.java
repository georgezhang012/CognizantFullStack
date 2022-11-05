package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HotelResource {

    private CustomerService customerService=CustomerService.getCustomerService();
    private ReservationService reservationService=ReservationService.getReservationService();

    private HotelResource hotelResource = new HotelResource();

    private HotelResource()  { }

    public HotelResource getHotelResource() {
        return hotelResource;
    }

    public Customer getCustomer(String email) {

        return customerService.getCustomer(email);
    }

    public Collection<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    public void createACustomer(String email, String firstName, String lastName) {

        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {

        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer=customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate );
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer=customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public  Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return  reservationService.findRooms(checkInDate, checkOutDate);
    }

}
