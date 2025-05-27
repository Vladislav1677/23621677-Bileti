package commands;

import TicketManagament.TicketSystem;

/**
 * Клас, който имплементира командата за добавяне на събитие към системата за билети.
 */
public class AddEventCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема обект от типа TicketSystem.
     * @param system Инстанция на системата за билети, с която ще работи командата.
     */
    public AddEventCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Метод, който изпълнява логиката по добавяне на събитие.
     * Изисква минимум 3 аргумента: дата, номер на зала и име на събитието.
     * @param args Аргументи подадени от главното меню. Формат: <дата> <номер на зала> <име на събитие>
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
        } catch (NumberFormatException e) {
            System.out.println("Error: Hall number must be an integer");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}