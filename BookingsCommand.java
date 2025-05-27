package commands;

import TicketManagament.TicketSystem;

/**
 * Клас, който имплементира командата за извеждане на всички резервации.
 * Може да се използва за извеждане на резервации по дата или по дата и име на събитие.
 */
public class BookingsCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема инстанция на системата за билети.
     * @param system Обект от типа TicketSystem, чрез който се изпълнява логиката за резервации.
     */
    public BookingsCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Метод, който изпълнява командата за показване на резервации.
     * Ако е подадена само дата, извежда всички резервации за нея.
     * Ако са подадени и дата и име на събитие, извежда само резервациите за това събитие.
     * @param args Аргументи от главното меню. Формати: <дата> или <дата> <име на събитие>
     */
    @Override
    public void execute(String[] args) {
        String date = null;
        String name = null;

        if (args.length == 1) {
            date = args[0];
        } else if (args.length >= 2) {
            date = args[0];
            name = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
        }

        system.printBookings(date, name);
    }
}
