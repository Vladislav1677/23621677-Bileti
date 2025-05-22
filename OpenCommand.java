package commands;

/**
 * Клас, реализиращ командата за отваряне на файл със запазени данни на системата.
 */
public class OpenCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор, който инициализира командата с дадена система за билети.
     *
     * @param system Обект на TicketSystem, върху който ще се изпълнява командата.
     */
    public OpenCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за отваряне на файл. Очаква като аргумент името на файла.
     * Ако аргументите са невалидни, извежда съобщение за правилна употреба.
     * При грешка при отварянето на файла извежда съобщение с описание на грешката.
     *
     * @param args Масив от аргументи, където първият е името на файла.
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: open <filename>");
            return;
        }
        String filename = args[0];
        try {
            system.open(filename);
        } catch (Exception e) {
            System.out.println("Failed to open file: " + e.getMessage());
        }
    }
}
