package commands;

/**
 * Клас, който реализира командата за записване на текущото състояние на системата.
 */
public class SaveCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор, който задава системата за билети, върху която ще се изпълнява командата.
     *
     * @param system Обект на TicketSystem, който се използва за записване.
     */
    public SaveCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за записване на текущото състояние.
     * При възникване на грешка при записването, извежда съобщение с причината.
     *
     * @param args Аргументи на командата (не се използват).
     */
    @Override
    public void execute(String[] args) {
        try {
            system.save();
        } catch (Exception e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }
}
