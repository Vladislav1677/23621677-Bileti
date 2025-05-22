package commands;

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
     * Изисква минимум 3 аргумента: дата, зала и име на събитието.
     * @param args Аргументи подадени от главното меню. Формат: <дата> <зала> <име на събитие>
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: addevent <date> <hall> <name>");
            return;
        }
        String date = args[0];
        String hall = args[1];
        String name = String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length));
        try {
            system.addEvent(date, hall, name);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
