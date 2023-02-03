import com.cognizant.hotel.service.CustomerService;
import com.cognizant.hotel.ui.MainMenu;

public final class HotelReservation {
    private final CustomerService customerService = CustomerService.getInstance();

    public static void main(String[] args) {
        MainMenu.mainMenu();
    }
}
