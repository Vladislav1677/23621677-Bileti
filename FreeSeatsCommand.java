package commands;

/**
 * Клас, който реализира командата за показване на свободните места за дадено събитие.
 */
public class FreeSeatsCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор, който задава системата за билети, върху която ще се изпълнява командата.
     * @param system Обект на TicketSystem, съдържащ данните за събитията и местата.
     */
    public FreeSeatsCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за показване на свободните места.
     * Приема дата и име на събитието като аргументи.
     * Извежда списък със свободни места за зададеното събитие.
     *
     * @param args Аргументи към командата. Трябва да съдържа поне дата и име на събитието.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: freeseats <date> <name>");
            return;
        }
        String date = args[0];
        String name = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
        system.printFreeSeats(date, name);
    }
}
