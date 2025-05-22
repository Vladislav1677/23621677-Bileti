package commands;

/**
 * Клас, който реализира командата за изход от системата за билети.
 * При изпълнение прекратява работата на програмата.
 */
public class ExitCommand implements Command {

    /**
     * Изпълнява командата за изход.
     * Извежда съобщение и прекратява програмата.
     *
     * @param args Аргументи към командата (не се използват).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting Ticket System.");
        System.exit(0);
    }
}
