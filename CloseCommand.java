package commands;

/**
 * Клас, който имплементира командата за затваряне на текущия файл в системата за билети.
 * Командата не приема аргументи и извиква метода {@code close()} на системата.
 */
public class CloseCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема инстанция на TicketSystem.
     * @param system Обект на системата за билети, чрез който се изпълнява операцията по затваряне.
     */
    public CloseCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Метод, който изпълнява командата за затваряне.
     * Не се изискват аргументи. Затваря текущо заредения файл.
     * @param args Аргументи от главното меню (игнорирани при тази команда).
     */
    @Override
    public void execute(String[] args) {
        system.close();
    }
}
