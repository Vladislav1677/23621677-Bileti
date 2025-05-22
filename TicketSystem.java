package commands;

import java.io.*;
import java.util.*;

/**
 * Клас, който представлява система за управление на зали, събития и билети.
 * Позволява добавяне на зали, събития, проверка на свободни места, управление на кодове за билети,
 * запис и зареждане на състоянието на системата.
 */
public class TicketSystem {
    private final Map<String, Hall> halls = new HashMap<>();
    private final Map<String, Map<String, Event>> events = new HashMap<>(); // дата -> име на събитие -> Event
    private final Map<String, String> codes = new HashMap<>(); // код -> информация за място

    /**
     * Конструктор, който инициализира системата със зададени зали.
     */
    public TicketSystem() {
        addHall("Hall1", 5, 10);
        addHall("Hall2", 10, 15);
        addHall("Hall3", 8, 12);
    }

    /**
     * Добавя зала в системата с определени редове и места на ред.
     * @param name Име на залата.
     * @param rows Брой редове в залата.
     * @param seatsPerRow Брой места на ред.
     */
    public void addHall(String name, int rows, int seatsPerRow) {
        halls.put(name, new Hall(name, rows, seatsPerRow));
    }

    /**
     * Връща зала по нейното име.
     * @param name Име на залата.
     * @return Залата или null ако не съществува.
     */
    public Hall getHall(String name) {
        return halls.get(name);
    }

    /**
     * Добавя събитие за дадена дата и зала.
     * Проверява дали залата не е заета на тази дата.
     * @param date Дата на събитието.
     * @param hallName Име на залата.
     * @param name Име на събитието.
     * @throws Exception Ако залата не съществува или вече е резервирана за тази дата.
     */
    public void addEvent(String date, String hallName, String name) throws Exception {
        Hall hall = halls.get(hallName);
        if (hall == null) throw new Exception("Hall not found: " + hallName);

        events.putIfAbsent(date, new HashMap<>());
        Map<String, Event> dayEvents = events.get(date);

        for (Event ev : dayEvents.values()) {
            if (ev.getHall().getName().equals(hallName)) {
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

        for (int r = 1; r <= rows; r++) {
            for (int s = 1; s <= seats; s++) {
                System.out.println("Row " + r + ", Seat " + s + " is available.");
            }
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
                System.out.println("Bookings for " + name + " on " + date);
                // TODO: Добави реална информация за резервираните билети
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
     * Регистрира нов билетен код с информация за място.
     * @param code Код на билета.
     * @param info Информация за мястото.
     */
    public void registerCode(String code, String info) {
        codes.put(code, info);
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
                    writer.println("addevent " + event.getDate() + " " + event.getHall().getName() + " " + event.getName());
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
        System.out.println("Opened file: " + filename);
        // TODO: Имплементирай зареждането от файл
    }

    /**
     * Генерира отчет за събитията в даден период и по зала.
     * @param from Начална дата (включително).
     * @param to Крайна дата (включително).
     * @param hall Име на залата (може да е null за всички зали).
     */
    public void generateReport(String from, String to, String hall) {
        for (String date : events.keySet()) {
            if (date.compareTo(from) < 0 || date.compareTo(to) > 0) continue;

            Map<String, Event> dayEvents = events.get(date);
            if (dayEvents == null) continue;

            for (Event event : dayEvents.values()) {
                if (hall != null && !event.getHall().getName().equals(hall)) continue;

                int count = event.countBoughtTickets();
                System.out.printf("%s (%s): %d tickets sold%n", event.getName(), event.getDate(), count);
            }
        }
    }
}
