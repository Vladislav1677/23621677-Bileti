import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TicketOffice {
    private ArrayList<Hall> halls;
    private ArrayList<Event> events;

    public TicketOffice() {
        halls = new ArrayList<>();
        events = new ArrayList<>();
        preloadHalls();
    }

    private void preloadHalls() {
        halls.add(new Hall(1, 5, 10));
        halls.add(new Hall(2, 6, 12));
        halls.add(new Hall(3, 8, 15));
    }

    public Hall findHall(int number) {
        for (Hall h : halls) {
            if (h.getNumber() == number) return h;
        }
        return null;
    }

    public Event findEvent(LocalDate date, String name) {
        for (Event e : events) {
            if (e.getDate().equals(date) && e.getName().equalsIgnoreCase(name)) return e;
        }
        return null;
    }

    public void addEvent(String dateStr, int hallNum, String name) {
        LocalDate date = Utils.parseDate(dateStr);
        Hall hall = findHall(hallNum);
        if (hall == null) {
            System.out.println("No such hall.");
            return;
        }
        for (Event e : events) {
            if (e.getDate().equals(date) && e.getHall().getNumber() == hallNum) {
                System.out.println("Hall already booked for this date.");
                return;
            }
        }
        events.add(new Event(name, date, hall));
        System.out.println("Event added.");
    }

    public void freeSeats(String dateStr, String name) {
        Event event = findEvent(Utils.parseDate(dateStr), name);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }
        for (Ticket t : event.getTickets()) {
            if (t.getStatus() == Ticket.Status.FREE) {
                System.out.println("Row " + t.getRow() + " Seat " + t.getSeat());
            }
        }
    }

    public void book(int row, int seat, String dateStr, String name, String note) {
        Event event = findEvent(Utils.parseDate(dateStr), name);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }
        Ticket ticket = event.findTicket(row, seat);
        if (ticket == null || ticket.getStatus() != Ticket.Status.FREE) {
            System.out.println("Ticket not available.");
            return;
        }
        ticket.book(note);
        System.out.println("Ticket booked.");
    }

    public void unbook(int row, int seat, String dateStr, String name) {
        Event event = findEvent(Utils.parseDate(dateStr), name);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }
        Ticket ticket = event.findTicket(row, seat);
        if (ticket == null || ticket.getStatus() != Ticket.Status.BOOKED) {
            System.out.println("Ticket not booked.");
            return;
        }
        ticket.unbook();
        System.out.println("Booking cancelled.");
    }

    public void buy(int row, int seat, String dateStr, String name) {
        Event event = findEvent(Utils.parseDate(dateStr), name);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }
        Ticket ticket = event.findTicket(row, seat);
        if (ticket == null || ticket.getStatus() == Ticket.Status.SOLD) {
            System.out.println("Ticket not available.");
            return;
        }
        String code = Utils.generateTicketCode(event, ticket);
        ticket.buy(code);
        System.out.println("Ticket purchased. Code: " + code);
    }

    public void bookings(String dateStr, String name) {
        LocalDate date = dateStr == null ? null : Utils.parseDate(dateStr);
        for (Event e : events) {
            if ((date == null || e.getDate().equals(date)) &&
                    (name == null || e.getName().equalsIgnoreCase(name))) {
                System.out.println("Event: " + e.getName() + " on " + e.getDate());
                for (Ticket t : e.getTickets()) {
                    if (t.getStatus() == Ticket.Status.BOOKED) {
                        System.out.println("Row " + t.getRow() + " Seat " + t.getSeat() + " Note: " + t.getNote());
                    }
                }
            }
        }
    }

    public void check(String code) {
        for (Event e : events) {
            for (Ticket t : e.getTickets()) {
                if (code.equals(t.getCode())) {
                    System.out.println("Valid ticket for Row " + t.getRow() + " Seat " + t.getSeat());
                    return;
                }
            }
        }
        System.out.println("Invalid ticket code.");
    }

    public void report(String fromStr, String toStr, Integer hallNum) {
        LocalDate from = Utils.parseDate(fromStr);
        LocalDate to = Utils.parseDate(toStr);
        for (Event e : events) {
            if (!e.getDate().isBefore(from) && !e.getDate().isAfter(to)) {
                if (hallNum == null || e.getHall().getNumber() == hallNum) {
                    long soldCount = e.getTickets().stream()
                            .filter(t -> t.getStatus() == Ticket.Status.SOLD)
                            .count();
                    System.out.println("Event: " + e.getName() + " on " + e.getDate() + " Sold tickets: " + soldCount);
                }
            }
        }
    }
}
