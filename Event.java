package TicketManagament;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Клас, който представлява събитие с дата, зала и билети.
 * Съдържа информация за събитието и управление на билетите, свързани с него.
 */
public class Event {
    private final String name;
    private final String date;
    private final Hall hall;
    private final Map<String, Ticket> tickets = new HashMap<>();

    public Event(String name, String date, Hall hall) {
        this.name = name;
        this.date = date;
        this.hall = hall;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Hall getHall() {
        return hall;
    }

    public int getHallNumber() {
        return hall.getNumber();
    }

    public void addTicket(String seatCode, Ticket ticket) {
        tickets.put(seatCode, ticket);
    }

    public int countBoughtTickets() {
        int count = 0;
        for (Ticket ticket : tickets.values()) {
            if (ticket.isBought()) {
                count++;
            }
        }
        return count;
    }

    public boolean isSeatAvailable(int row, int seat) {
        String key = row + "-" + seat;
        Ticket ticket = tickets.get(key);
        return ticket == null || (!ticket.isBooked() && !ticket.isBought());
    }

    public Set<String> getOccupiedSeats() {
        return tickets.keySet();
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public boolean bookSeat(int row, int seat, String note) {
        String key = row + "-" + seat;
        if (!isSeatAvailable(row, seat)) return false;

        Ticket ticket = new Ticket(row, seat, key);
        ticket.setBooked(true);
        ticket.setNote(note);
        tickets.put(key, ticket);
        return true;
    }

    public boolean buyTicket(int row, int seat) throws IllegalArgumentException {
        if (row < 1 || seat < 1) {
            throw new IllegalArgumentException("Невалиден ред или място");
        }

        String key = row + "-" + seat;
        Ticket ticket = tickets.get(key);

        if (ticket == null) {
            ticket = new Ticket(row, seat, key);
            ticket.setBought(true);
            tickets.put(key, ticket);
            return true;
        }

        if (!ticket.isBooked() && !ticket.isBought()) {
            ticket.setBought(true);
            return true;
        }

        return false;
    }

    public boolean unbookSeat(int row, int seat) throws IllegalArgumentException {
        if (row < 1 || seat < 1) {
            throw new IllegalArgumentException("Невалиден ред или място");
        }

        String key = row + "-" + seat;
        Ticket ticket = tickets.get(key);

        if (ticket != null && ticket.isBooked()) {
            tickets.remove(key);
            return true;
        }
        return false;
    }

    public String getSeatStatus(int row, int seat) throws IllegalArgumentException {
        if (row < 1 || seat < 1) {
            throw new IllegalArgumentException("Невалиден ред или място");
        }

        String key = row + "-" + seat;
        Ticket ticket = tickets.get(key);

        if (ticket == null) return "СВОБОДНО";
        if (ticket.isBought()) return "ЗАКУПЕНО";
        if (ticket.isBooked()) return "РЕЗЕРВИРАНО";
        return "СВОБОДНО";
    }
}
