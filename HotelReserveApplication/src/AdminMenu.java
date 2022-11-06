import api.AdminResource;
import model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class AdminMenu {

    private static final AdminResource adminResource=AdminResource.getAdminResource();

    public static void adminMenu() {

        printAdminMenu();

        Scanner scanner=new Scanner(System.in);
        String line="";

        do{
            line=scanner.nextLine().trim();
            if(line.length()==1) {
                switch(line.charAt(0)) {
                    case '1':
                        displayAllCustomers();
                        break;
                    case '2':
                        displayAllRooms();
                        break;
                    case '3':
                        displayAllReservations();
                        break;
                    case '4':
                        addRoom();
                        break;
                    case '5':
                        MainMenu.printMainMenu();
                        break;
                    default:
                        System.out.println("Unknown action\n");
                        break;
                }
            }

        } while (line.charAt(0)!='5' || line.length() != 1);

    }

    private static void printAdminMenu() {
        System.out.print("\nAdmin Menu\n" +
                "--------------------------------------------\n" +
                "1. See all Customers\n" +
                "2. See all Rooms\n" +
                "3. See all Reservations\n" +
                "4. Add a Room\n" +
                "5. Back to Main Menu\n" +
                "--------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }

    private static void displayAllCustomers() {
        Collection<Customer> customers=adminResource.getAllCustomers();

        if(customers.size()>=1) {
            for(Customer customer:customers) {
                System.out.println(customer);
            }
        } else {
            System.out.println("No customers found");
        }
    }

    private static void displayAllRooms() {
        Collection<IRoom> rooms=adminResource.getAllRooms();

        if(rooms.size()>=1){
            for(IRoom room:rooms) {
                System.out.println(room);
            }
        } else {
            System.out.println("No rooms found");
        }
    }

    private static void displayAllReservations() {

        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Please enter room number");
        String roomNumber=scanner.nextLine();

        System.out.println("Please enter price");
        double price=Double.parseDouble(scanner.nextLine());

        System.out.println("Please enter room type: 1 for single bed, 2 for double bed");
        RoomType roomType=RoomType.valueOf(scanner.nextLine());

        Room room=new Room(roomNumber, price, roomType);

        adminResource.addRoom(Collections.singletonList(room));

        System.out.println("Room " + room.getRoomNumber()+" added successfully");


    }
}
