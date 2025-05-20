import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Utils {
    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String generateTicketCode(Event event, Ticket ticket) {
        return event.getDate() + "-" + event.getHall().getNumber() + "-" +
                ticket.getRow() + "-" + ticket.getSeat() + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
