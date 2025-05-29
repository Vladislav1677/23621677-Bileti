package commands;

import TicketManagament.TicketSystem;

/**
 * Клас, който имплементира командата за добавяне на събитие към системата за билети.
 * Позволява добавяне на ново представление на дадена дата и зала.
 */
public class AddEventCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор, който приема обект от типа TicketSystem.
     * @param system Инстанция на системата за билети, с която ще работи командата.
     */
    public AddEventCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за добавяне на събитие.
     * Формат на аргументите: <date> <hall_number> <name>
     * Пример: addevent 2025-06-01 2 "Летящият холандец"
     * @param args Аргументи, подадени от потребителя.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: addevent <date> <hall_number> <name>");
            return;
        }

        String date = args[0];
        String name = String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length));

        try {
            int hallNumber = Integer.parseInt(args[1]);
            system.addEvent(date, hallNumber, name);
            System.out.println("Събитието е добавено успешно.");
        } catch (NumberFormatException e) {
            System.out.println("Грешка: Номерът на залата трябва да е цяло число.");
        } catch (IllegalArgumentException e) {
            System.out.println("Грешка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неочаквана грешка: " + e.getMessage());
        }
    }
}
