package com.cognizant.hotel.service;

import com.cognizant.hotel.model.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerService {

    private Map<String, Customer> customers=new HashMap<>();

//  implement singleton
    private static final CustomerService customerService=new CustomerService();

    public static CustomerService getInstance() {
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) throws IllegalArgumentException {
        
        Customer customer=new Customer(firstName, lastName, email);
        customer.validateEmailAccount(email);
        if(customers.containsKey(email)) {
            System.out.println("Customer already exist");
            return;
        }
        customers.put(email, customer);
        System.out.println("customer created successfully");
    }

    public Customer getCustomer(String email) {
        if(email !=null) {
            return customers.get(email);
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values().stream().collect(Collectors.toList());
    }

}
