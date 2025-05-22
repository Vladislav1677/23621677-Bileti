package commands;

/**
 * Клас, който реализира командата за записване на състоянието на системата в нов файл.
 */
public class SaveAsCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор, който задава системата за билети, върху която ще се изпълнява командата.
     *
     * @param system Обект на TicketSystem, който се използва за записване.
     */
    public SaveAsCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за записване на състоянието в зададен файл.
     * Ако не е подадено име на файл, извежда съобщение за правилна употреба.
     * При възникване на грешка при записването, извежда съобщение с причината.
     *
     * @param args Аргументи, където args[0] трябва да е името на файла за запис.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: saveas <filename>");
            return;
        }
        String filename = args[0];
        try {
            system.saveAs(filename);
        } catch (Exception e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }
}
