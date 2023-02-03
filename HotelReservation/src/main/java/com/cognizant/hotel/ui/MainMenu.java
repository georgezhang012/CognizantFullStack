package com.cognizant.hotel.ui;

import com.cognizant.hotel.api.HotelResource;
import com.cognizant.hotel.model.IRoom;
import com.cognizant.hotel.model.Reservation;
import com.cognizant.hotel.model.RoomType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class MainMenu {
    private static final HotelResource hotelResource=HotelResource.getHotelResource();

    public static void mainMenu() {
        String userSelect;
        Scanner scanner=new Scanner(System.in);
        displayMainMenu();

        do {
            userSelect=scanner.nextLine().trim();
            parseInput(userSelect);
        } while(userSelect.charAt(0)!='5');
    }






    public static void displayMainMenu() {
        System.out.print("################ MAIN MENU ##################### \n" +
                "1. Reserve a room\n" +
                "2. Check reservation\n" +
                "3. Register account\n" +
                "4. Admin Menu\n" +
                "5. Exit\n" +
                "##################################################\n" +
                "Please select a number from above options:\n");
    }

    public static void findAndReserveRoom() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter Check-In Date in the format of mm/dd/yyyy ");

        Date checkIn=getDateFromUserInput(scanner.nextLine());
        System.out.println("Please enter Check-Out Date in the format of mm/dd/yyyy ");
        Date checkOut=getDateFromUserInput(scanner.nextLine());

        System.out.println("please select room type: 1 for single 2 for double");
        String rType=scanner.nextLine();
        while(!rType.trim().equals("1") && !rType.equals("2")) {
            System.out.println("You can only select 1 or 2");
            rType=scanner.nextLine().trim();
        }

    
        List<IRoom> availableRooms=findAvailableRooms(checkIn, checkOut, RoomType.fromString(rType));
        if(availableRooms.size()==0) {
            System.out.println("No available rooms found, do you want to see alternative dates? y/n");
            String userInput=scanner.nextLine().toLowerCase(Locale.ROOT);
            if(userInput.charAt(0)=='y') {
                findAndReserveRoom();
            }
        } else {
            hotelResource.displayRooms(availableRooms);
            System.out.println("Which room do you want to reserve?");
            String roomNumber=scanner.nextLine();
            System.out.println("Do you have account with us? y/n");
            String haveAccount=null, email=null;

                haveAccount=scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                if(haveAccount.charAt(0)=='n') {
                    email=registerAccount();
                } else if (haveAccount.charAt(0)=='y'){
                    System.out.println("please enter your email address");
                    email=scanner.nextLine();
                }  else {
                    System.out.println(" please enter y or n");
                }


            reserveRoom(email,roomNumber,checkIn,checkOut, availableRooms);
        }

    }

    public static List<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate, RoomType roomType){

        return hotelResource.findRoom(checkInDate, checkOutDate, roomType).stream().collect(Collectors.toList());
    }


    public static String registerAccount() {
        
        String firstName, lastName, emailAddress; 
        System.out.println("Please enter email address in format of: name@domain.com");
        Scanner scanner=new Scanner(System.in);
        emailAddress=scanner.nextLine().trim();

        System.out.println("Please enter First Name:");
        firstName=scanner.nextLine().trim();
        System.out.println("Please enter Last Name:");
        lastName=scanner.nextLine().trim();
        
        try {
            hotelResource.createACustomer(emailAddress, firstName, lastName);
        } catch (IllegalArgumentException e) {
            e.getLocalizedMessage();
            registerAccount();
        }
//        System.out.println("Account created successfully");
        displayMainMenu();
        return emailAddress;

    }
    
    public static void reserveRoom(String email, String roomNumber, Date checkInDate, Date checkOutDate, Collection<IRoom> availableRooms) {
        

        Set<String> roomNumbers=new HashSet(availableRooms.stream().map(r->r.getRoomNumber()).toList());
        if(!roomNumbers.contains(roomNumber)) {
            System.out.println("ERROR: room number is not right. \n Start reservation again.");
        } else {
            IRoom room= hotelResource.getRoom(roomNumber);
            Reservation reservation=hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
            System.out.println("Room is reserved. Reservation details: \n");
            System.out.println(reservation);
        }
        displayMainMenu();
  
    }
    private static void parseInput(String userInput) {
        
        try {
            if(userInput.charAt(0) == '1') {
                findAndReserveRoom();
            } else if (userInput.charAt(0) == '2') {
                checkMyReservation();
            } else if (userInput.charAt(0) == '3') {
                registerAccount();
            } else if (userInput.charAt(0) == '4') {
                AdminMenu.adminMenu();
            } else if (userInput.charAt(0) == '5') {
                System.out.println("Exit");
            } else {
                System.out.println("Invalid input, please choose option between 1-5");
                displayMainMenu();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static void checkMyReservation() {
        System.out.println("Please enter your email in the format: name@domain.com");
        Scanner scanner=new Scanner(System.in);
        
        Collection<Reservation> reservations=hotelResource.getCustomersReservations(scanner.nextLine().trim());
        reservations.stream().forEach(r-> System.out.println(r));
        displayMainMenu();
    }
    private static Date getDateFromUserInput(String userInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        try {
            date= dateFormat.parse(userInput);
        } catch (ParseException ex){
            ex.printStackTrace();
        }

        return date;
    }
}
