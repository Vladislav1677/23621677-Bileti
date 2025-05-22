package commands;

import java.util.HashMap;
import java.util.Map;

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
}
