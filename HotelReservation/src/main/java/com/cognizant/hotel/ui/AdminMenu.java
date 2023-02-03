package com.cognizant.hotel.ui;


import com.cognizant.hotel.api.AdminResource;
import com.cognizant.hotel.model.Customer;
import com.cognizant.hotel.model.IRoom;
import com.cognizant.hotel.model.Room;
import com.cognizant.hotel.model.RoomType;
import java.util.*;

public class AdminMenu {

    private static final AdminResource adminResource=AdminResource.getAdminResource();

    public static void adminMenu() {
        String userSelect;
        Scanner scanner=new Scanner(System.in);

        displayAdminMenu();

        do {
            userSelect=scanner.nextLine().trim();
            parseInput(userSelect);
        } while(userSelect.charAt(0)!='5');


    }
    private static void displayAdminMenu() {
        System.out.print("################ ADMIN MENU ##################### \n" +
                        "1. Display All Customers\n" +
                        "2. Show All Rooms\n" +
                        "3. Check All Reservations\n" +
                        "4. Add a Room\n" +
                        "5. Back to Main Menu\n" +
                        "##################################################\n" +
                        "Please select a number for the menu option:\n");
    }

    private static void displayAllCustomers() {
        Collection<Customer> allCustomers=adminResource.getAllCustomers();
        allCustomers.forEach(c-> System.out.println(c));
    }

    private static void displayAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        String roomNumber;
        String roomPrice;
        RoomType roomType;


        Scanner scanner=new Scanner(System.in);

        System.out.println("Please select room type: 1 for single, 2 for double");
        String rt=scanner.nextLine().trim();

        while(!rt.equals("1") && !rt.equals("2")) {
            System.out.println("You can only select 1 or 2");
            rt=scanner.nextLine().trim();
        }

        roomType= RoomType.fromString(rt);

        System.out.println("Input room number");
        roomNumber= scanner.nextLine();
        while(!isInteger(roomNumber)) {
            System.out.println("Room number can only be integer, please enter again");
            roomNumber= scanner.nextLine();
        }
        while(adminResource.rooms!=null&&adminResource.rooms.containsKey(Integer.parseInt(roomNumber))) {
            System.out.printf("room with number %s already exist, please enter a different number \n", roomNumber);
            roomNumber= scanner.nextLine();
        }

        System.out.println("Input room price");
        roomPrice=scanner.nextLine();
        while(!isNumeric(roomPrice)) {
            System.out.println("Room price can only be numerical values, please enter again");
            roomPrice= scanner.nextLine();
        }

        IRoom newRoom =new Room(roomNumber, Double.parseDouble(roomPrice), roomType);
        Map<Integer, IRoom> newRoomMap=new HashMap();
        newRoomMap.put(Integer.parseInt(roomNumber), newRoom);
        try{
            adminResource.addRoom(newRoomMap);
        } catch(RuntimeException e) {
            addRoom();
            return;
        }


        System.out.println("Add another room? y/n");
        String response=scanner.nextLine().trim();

        if(response.equalsIgnoreCase("y")) {
            addRoom();
        } else {
            displayAdminMenu();
        }

    }

    private static void displayAllRooms() {
        Collection<IRoom> allRooms=adminResource.getAllRooms();
        allRooms.stream().forEach(r-> System.out.println(r));
    }

    private static void parseInput(String userInput) {
        if(userInput.charAt(0) == '1') {
            displayAllCustomers();
        } else if (userInput.charAt(0) == '2') {
            displayAllRooms();
        } else if (userInput.charAt(0) == '3') {
            displayAllReservations();
        } else if (userInput.charAt(0) == '4') {
            addRoom();
        } else if (userInput.charAt(0) == '5') {
            MainMenu.displayMainMenu();
        } else {
            System.out.println("Invalid input, please choose option between 1-5");
            displayAdminMenu();
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


}
