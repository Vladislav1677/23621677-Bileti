import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private String name;
    private LocalDate date;
    private Hall hall;
    private ArrayList<Ticket> tickets;

    public Event(String name, LocalDate date, Hall hall) {
        this.name = name;
        this.date = date;
        this.hall = hall;
        this.tickets = new ArrayList<>();

        for (int i = 1; i <= hall.getRows(); i++) {
            for (int j = 1; j <= hall.getSeatsPerRow(); j++) {
                tickets.add(new Ticket(i, j));
            }
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Hall getHall() {
        return hall;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public Ticket findTicket(int row, int seat) {
        for (Ticket t : tickets) {
            if (t.getRow() == row && t.getSeat() == seat) return t;
        }
        return null;
    }
}
