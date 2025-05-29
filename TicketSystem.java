package TicketManagament;

import java.io.*;
import java.util.*;

/**
 * Клас, който представлява система за управление на зали, събития и билети.
 * Позволява добавяне на зали, събития, проверка на свободни места, управление на кодове за билети,
 * запис и зареждане на състоянието на системата.
 */
public class TicketSystem {
    private final Map<Integer, Hall> halls = new HashMap<>();
    private final Map<String, Map<String, Event>> events = new HashMap<>();
    private final Map<String, String> codes = new HashMap<>();

    /**
     * Конструктор, който инициализира системата със зададени зали.
     */
    public TicketSystem() {
        addHall(1, 5, 10);
        addHall(2, 10, 15);
        addHall(3, 8, 12);
    }

    /**
     * Добавя зала в системата с определени редове и места на ред.
     * @param number Номер на залата.
     * @param rows Брой редове в залата.
     * @param seatsPerRow Брой места на ред.
     */
    public void addHall(int number, int rows, int seatsPerRow) {
        halls.put(number, new Hall(number, rows, seatsPerRow));
    }

    /**
     * Връща зала по нейния номер.
     * @param number Номер на залата.
     * @return Залата или null ако не съществува.
     */
    public Hall getHall(int number) {
        return halls.get(number);
    }

    /**
     * Добавя събитие за дадена дата и зала.
     * Проверява дали залата не е заета на тази дата.
     * @param date Дата на събитието.
     * @param hallNumber Номер на залата.
     * @param name Име на събитието.
     * @throws Exception Ако залата не съществува или вече е резервирана за тази дата.
     */
    public void addEvent(String date, int hallNumber, String name) throws Exception {
        Hall hall = halls.get(hallNumber);
        if (hall == null) throw new Exception("Hall not found: " + hallNumber);

        events.putIfAbsent(date, new HashMap<>());
        Map<String, Event> dayEvents = events.get(date);

        for (Event ev : dayEvents.values()) {
            if (ev.getHall().getNumber() == hallNumber) {
                throw new Exception("Hall already booked on " + date);
            }
        }

        Event newEvent = new Event(name, date, hall);
        dayEvents.put(name, newEvent);
        System.out.println("Event added successfully.");
    }

    /**
     * Връща събитие по дата и име.
     * @param date Дата на събитието.
     * @param name Име на събитието.
     * @return Събитието или null ако не е намерено.
     */
    public Event getEvent(String date, String name) {
        Map<String, Event> dayEvents = events.get(date);
        if (dayEvents == null) return null;
        return dayEvents.get(name);
    }

    /**
     * Извежда списък със свободните места за дадено събитие.
     * @param date Дата на събитието.
     * @param name Име на събитието.
     */
    public void printFreeSeats(String date, String name) {
        Event event = getEvent(date, name);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }

        Hall hall = event.getHall();
        int rows = hall.getRows();
        int seats = hall.getSeatsPerRow();

        System.out.println("\nFree seats for '" + name + "' on " + date + " in Hall " + hall.getNumber() + ":");
        System.out.println("Legend: [A] Available, [B] Booked, [S] Sold\n");

        boolean hasFreeSeats = false;
        int availableCount = 0;
        int bookedCount = 0;
        int soldCount = 0;

        for (int r = 1; r <= rows; r++) {
            System.out.print("Row " + r + ": ");
            for (int s = 1; s <= seats; s++) {
                String seatKey = r + "-" + s;
                if (event.isSeatAvailable(r, s)) {
                    System.out.print("[A] ");
                    availableCount++;
                    hasFreeSeats = true;
                } else {
                    Ticket ticket = event.getTickets().get(seatKey);
                    if (ticket.isBought()) {
                        System.out.print("[S] ");
                        soldCount++;
                    } else {
                        System.out.print("[B] ");
                        bookedCount++;
                    }
                }
            }
            System.out.println();
        }

        System.out.println("\nSummary:");
        System.out.println("Available: " + availableCount);
        System.out.println("Booked: " + bookedCount);
        System.out.println("Sold: " + soldCount);

        if (!hasFreeSeats) {
            System.out.println("\nWARNING: No free seats available for this event!");
        }
    }

    /**
     * Регистрира нов билетен код с информация за място.
     * @param code Код на билета.
     * @param info Информация за мястото.
     */
    public void registerCode(String code, String info) {
        codes.put(code, info);
    }

    /**
     * Проверява валидността на билетен код.
     * @param code Код на билета.
     */
    public void checkCode(String code) {
        String info = codes.get(code);
        if (info != null) {
            System.out.println("Valid ticket: " + info);
        } else {
            System.out.println("Invalid or unknown ticket code.");
        }
    }

    /**
     * Извежда информация за всички резервации за дадена дата и/или събитие.
     * @param date Дата на събитията (може да е null).
     * @param name Име на събитието (може да е null).
     */
    public void printBookings(String date, String name) {
        if (date == null) {
            for (String d : events.keySet()) {
                printBookings(d, name);
            }
            return;
        }

        Map<String, Event> dayEvents = events.get(date);
        if (dayEvents == null) {
            System.out.println("No events on " + date);
            return;
        }

        if (name != null) {
            Event e = dayEvents.get(name);
            if (e != null) {
                System.out.println("Bookings for " + name + " on " + date + ":");
                Map<String, Ticket> tickets = e.getTickets();
                if (tickets.isEmpty()) {
                    System.out.println("  No bookings.");
                } else {
                    for (Ticket t : tickets.values()) {
                        String status = t.isBought() ? "Sold" : "Booked";
                        System.out.printf("  Seat %d-%d: %s, Code: %s%n",
                                t.getRow(), t.getSeatNumber(), status, t.getCode());
                    }
                }
            } else {
                System.out.println("No event named " + name + " on " + date);
            }
        } else {
            for (String eventName : dayEvents.keySet()) {
                printBookings(date, eventName);
            }
        }
    }


    /**
     * Запазва текущото състояние на системата във файл "save.dat".
     */
    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
            out.writeObject(this);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving system: " + e.getMessage());
        }
    }

    /**
     * Изчиства всички събития и регистрирани кодове от системата.
     */
    public void close() {
        events.clear();
        codes.clear();
        System.out.println("System cleared.");
    }

    /**
     * Запазва текущото състояние на системата в текстов файл.
     * @param filename Име на файла.
     */
    public void saveAs(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (String date : events.keySet()) {
                Map<String, Event> dailyEvents = events.get(date);
                for (Event event : dailyEvents.values()) {
                    writer.println("addevent " + event.getDate() + " " + event.getHall().getNumber() + " " + event.getName());
                }
            }
            System.out.println("Saved current state to: " + filename);
        } catch (Exception e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }

    /**
     * Зарежда системата от файл.
     * @param filename Име на файла.
     */
    public void open(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            halls.clear();
            events.clear();
            codes.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("addevent ")) {
                    String[] parts = line.split(" ", 4);
                    if (parts.length < 4) {
                        System.out.println("Invalid line format: " + line);
                        continue;
                    }

                    String date = parts[1];
                    int hallNumber = Integer.parseInt(parts[2]);
                    String name = parts[3];

                    if (!halls.containsKey(hallNumber)) {
                        addHall(hallNumber, 10, 15);
                    }

                    try {
                        addEvent(date, hallNumber, name);
                    } catch (Exception e) {
                        System.out.println("Error adding event from file: " + e.getMessage());
                    }
                }
            }
            System.out.println("System loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    /**
     * Генерира отчет за събитията в даден период и по зала.
     * @param from Начална дата (включително).
     * @param to Крайна дата (включително).
     * @param hallNumber Номер на залата (може да е 0 за всички зали).
     */
    public void generateReport(String from, String to, int hallNumber) {
        for (String date : events.keySet()) {
            if (date.compareTo(from) < 0 || date.compareTo(to) > 0) continue;

            Map<String, Event> dayEvents = events.get(date);
            if (dayEvents == null) continue;

            for (Event event : dayEvents.values()) {
                if (hallNumber != -1 && event.getHall().getNumber() != hallNumber) continue;

                int count = event.countBoughtTickets();
                System.out.printf("%s (%s) in Hall %d: %d tickets sold%n",
                        event.getName(), event.getDate(), event.getHall().getNumber(), count);
            }
        }
    }
}