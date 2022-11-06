import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainMenu {

    static int DEFAULT_PLUS_DATE=7;
    private static final HotelResource hotelResource=HotelResource.getHotelResource();

    public static void main(String[] args) {
        String line="";
        Scanner scanner=new Scanner(System.in);

        printMainMenu();

        try{
            do {
                line=scanner.nextLine().trim();

                if(line.length()==1) {
                    switch(line.charAt(0)) {
                        case '1':
                            findAndReserveARoom();
                            break;
                        case '2':
                            seeMyReservations();
                            break;
                        case '3':
                            createAccount();
                        case '4':
                            AdminMenu.adminMenu();
                        case '5':
                            System.out.println("Exit");
                            break;
                        default:
                            System.out.println("Unknown action\n");
                            break;


                    }
                } else{
                    System.out.println("Error: Invalid action");
                }
            } while(line.charAt(0)!='5' || line.length()!=1);

        } catch(Exception e) {
            System.out.println("Exception occurs " + e.getMessage());
        }


    }


    public static void findAndReserveARoom()  {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter Check-In Date in the format of mm/dd/yyyy ");

        SimpleDateFormat formatter=new SimpleDateFormat("MM-dd-yyyy");
        Date checkIn=getFormattedDate(scanner.nextLine());
        System.out.println("Please enter Check-Out Date in the format of mm/dd/yyyy ");
        Date checkOut=getFormattedDate(scanner.nextLine());


        if(checkIn!=null && checkOut!=null && checkIn.compareTo(checkOut)<0){
            Collection<IRoom> availableRooms=hotelResource.findARoom(checkIn, checkOut);

            if(availableRooms.size()==0) {
                    //                no rooms in the date range, check the alternate date
                Date newCheckIn=getAlternateDate(checkIn, DEFAULT_PLUS_DATE);
                Date newCheckOut=getAlternateDate(checkOut, DEFAULT_PLUS_DATE);
                Collection<IRoom> altAvailableRoom=hotelResource.findARoom(newCheckIn, newCheckOut);
                if(altAvailableRoom.size()==0) {
                    System.out.println("No available room found");
                } else {

                    System.out.println("We've only found rooms on alternative dates:" +
                            "\nCheck-In Date:" + newCheckIn +
                            "\nCheck-Out Date:" + newCheckOut);
                    printRooms(availableRooms);
                    reserveRoom(scanner, newCheckIn, newCheckOut, availableRooms);
                }

            } else {
                printRooms(availableRooms);

                reserveRoom(scanner, checkIn, checkOut, availableRooms);
            }
        }

    }

    private static Date getFormattedDate(String d) {
        SimpleDateFormat  formatter=new SimpleDateFormat("MM-dd-yyyy");
        Date date;
        try {
            date= (Date) formatter.parse(d);
        } catch ( ParseException parseException  ) {
            String errLog=String.format("failed to parse date %s", d);
            System.out.println(errLog);
            throw new RuntimeException(errLog);
        }

        return date;
    }

    private static Date getAlternateDate(Date date, int deltaDays) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, deltaDays);
        return calendar.getTime();


    }
    public static void seeMyReservations() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your Email format: name@domain.com");
        String email=scanner.nextLine();

        Collection<Reservation> reservations=hotelResource.getCustomersReservations(email);

        for(Reservation reservation:reservations) {
            System.out.println(reservation);
        }
    }


    public static void createAccount() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Email format: name@domain.com");
        String email = scanner.nextLine();

        System.out.println("First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        String lastName = scanner.nextLine();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");

            printMainMenu();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
            createAccount();
        }

    }

//
//    public AdminMenu openAdminMenu() {
//
//    }

    public static void reserveRoom(Scanner scanner, Date checkIn, Date checkOut, Collection<IRoom> rooms) {
        System.out.println("Would you like to book? y/n");

        String bookResponse=scanner.nextLine().strip();

        if(Character.toLowerCase(bookResponse.charAt(0))=='y') {
            System.out.println("do you have an account with us? y/n");
            String accountResponse= scanner.nextLine();
            if(Character.toLowerCase(accountResponse.charAt(0))=='y') {
                String email=scanner.nextLine();
                if(hotelResource.getCustomer(email)!=null) {
                    System.out.println("Which room do you want to reserve");
                    String roomNumber=scanner.nextLine();

                    if(rooms.stream().filter(room->room.getRoomNumber().equals(roomNumber)).collect(Collectors.toList())!=null) {
                        IRoom room=hotelResource.getRoom(roomNumber);

                        Reservation reservation=hotelResource.bookARoom(email, room, checkIn, checkOut);
                        System.out.println("Reservation created successfully");
                        System.out.println(reservation);

                    } else {
                        System.out.println("Error: room number not available.\nStart reservation again.");
                    }
                }
            } else {
                System.out.println("Please create an account");
                printMainMenu();
            }
        } else if(Character.toLowerCase(bookResponse.charAt(0))=='n') {
            printMainMenu();
        } else {
            reserveRoom(scanner, checkIn, checkOut, rooms);
        }
    }


    private static void printRooms(Collection<IRoom> rooms) {
        for(IRoom room:rooms) {
            System.out.println(room);
        }
    }


    public static void printMainMenu() {
        System.out.print("\nWelcome to the Hotel Reservation Application\n" +
                "------------------------------------------\n" +
                "1. Find and reserve a room\n" +
                "2. See my reservations\n" +
                "3. Create an Account\n" +
                "4. Admin\n" +
                "5. Exit\n" +
                "------------------------------------------\n" +
                "Please select a number for the menu option:\n");
    }

}
