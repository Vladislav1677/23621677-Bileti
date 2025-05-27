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

    /**
     * Конструктор, който създава ново събитие с име, дата и зала.
     * @param name Името на събитието.
     * @param date Датата на събитието.
     * @param hall Залата, в която се провежда събитието.
     */
    public Event(String name, String date, Hall hall) {
        this.name = name;
        this.date = date;
        this.hall = hall;
    }

    /**
     * Връща името на събитието.
     * @return Името на събитието.
     */
    public String getName() {
        return name;
    }

    /**
     * Връща датата на събитието.
     * @return Датата на събитието.
     */
    public String getDate() {
        return date;
    }

    /**
     * Връща залата, в която се провежда събитието.
     * @return Залата на събитието.
     */
    public Hall getHall() {
        return hall;
    }

    /**
     * Добавя билет към събитието.
     * @param seatCode Код на мястото за билета.
     * @param ticket Обектът билет, който да се добави.
     */
    public void addTicket(String seatCode, Ticket ticket) {
        tickets.put(seatCode, ticket);
    }

    /**
     * Брои всички билети, които са закупени за събитието.
     * @return Брой на закупените билети.
     */
    public int countBoughtTickets() {
        int count = 0;
        for (Ticket ticket : tickets.values()) {
            if (ticket.isBought()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Проверява дали дадено място е свободно.
     * @param row Ред на мястото.
     * @param seat Място в реда.
     * @return true ако мястото е свободно, false в противен случай.
     */
    public boolean isSeatAvailable(int row, int seat) {
        String key = row + "-" + seat;
        Ticket ticket = tickets.get(key);
        return ticket == null || (!ticket.isBooked() && !ticket.isBought());
    }

    /**
     * Връща всички заети места.
     * @return Множество от кодове на заети места.
     */
    public Set<String> getOccupiedSeats() {
        return tickets.keySet();
    }

    /**
     * Връща всички билети за събитието.
     * @return Мап с билетите.
     */
    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    /**
     * Резервира място за събитието.
     * @param row Ред на мястото.
     * @param seat Място в реда.
     * @param note Бележка за резервацията.
     * @return true ако резервацията е успешна, false в противен случай.
     */
    public boolean bookSeat(int row, int seat, String note) {
        String key = row + "-" + seat;
        if (!isSeatAvailable(row, seat)) return false;

        Ticket ticket = new Ticket();
        ticket.setBooked(true);
        ticket.setNote(note);
        tickets.put(key, ticket);
        return true;


    }

    /**
     * Купува билет за дадено място.
     * @param row Ред на мястото
     * @param seat Място в реда
     * @return true ако покупката е успешна, false ако мястото е заето
     * @throws IllegalArgumentException ако редът или мястото са невалидни
     */
    public boolean buyTicket(int row, int seat) throws IllegalArgumentException {
        if (row < 1 || seat < 1) {
            throw new IllegalArgumentException("Невалиден ред или място");
        }

        String key = row + "-" + seat;
        Ticket ticket = tickets.get(key);

        if (ticket == null) {
            ticket = new Ticket();
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

    /**
     * Отменя резервация на място.
     * @param row Ред на мястото
     * @param seat Място в реда
     * @return true ако отмяната е успешна, false ако няма резервация
     * @throws IllegalArgumentException ако редът или мястото са невалидни
     */
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

    /**
     * Проверява статуса на място.
     * @param row Ред на мястото
     * @param seat Място в реда
     * @return "СВОБОДНО", "РЕЗЕРВИРАНО" или "ЗАКУПЕНО"
     * @throws IllegalArgumentException ако редът или мястото са невалидни
     */
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