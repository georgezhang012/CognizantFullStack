package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {

    private List<Customer> customerList=new ArrayList<>();

    private static final CustomerService customerService=new CustomerService();

    private CustomerService() {}

    public static CustomerService getCustomerService() {
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer=new Customer(firstName,lastName,email);
        customerList.add(customer);
    }

    public Customer getCustomer(String customerEmail) {

        for(Customer customer:customerList) {
            if(customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }

        return null;
    }

    public Collection<Customer> getAllCustomers() {

        return customerList;
    }



}
