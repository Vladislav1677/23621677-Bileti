package commands;

import TicketManagament.TicketSystem;

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
     * Приема като аргументи начална и крайна дата и по избор номер на зала.
     * Ако аргументите са недостатъчни или неправилни, извежда съобщение за правилна употреба.
     *
     * @param args Масив от аргументи, където
     *             args[0] е начална дата,
     *             args[1] е крайна дата,
     *             args[2] (по избор) е номер на зала (цяло число).
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: report <from> <to> [<hallNumber>]");
            return;
        }

        String from = args[0];
        String to = args[1];
        int hallNumber = 0;

        if (args.length >= 3) {
            try {
                hallNumber = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid hall number. It must be an integer.");
                return;
            }
        }

        system.generateReport(from, to, hallNumber);
    }
}
