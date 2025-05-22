package commands;

/**
 * Клас, реализиращ командата за генериране на отчет за продадени билети в даден период.
 */
public class ReportCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор, който инициализира командата с дадена система за билети.
     *
     * @param system Обект на TicketSystem, върху който ще се изпълнява командата.
     */
    public ReportCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за генериране на отчет.
     * Приема като аргументи начална и крайна дата и по избор име на зала.
     * Ако аргументите са недостатъчни, извежда съобщение за правилна употреба.
     *
     * @param args Масив от аргументи, където
     *             args[0] е начална дата,
     *             args[1] е крайна дата,
     *             args[2...] (по избор) е име на зала.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: report <from> <to> [<hall>]");
            return;
        }

        String from = args[0];
        String to = args[1];
        String hall = args.length > 2 ? String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length)) : null;

        system.generateReport(from, to, hall);
    }
}
