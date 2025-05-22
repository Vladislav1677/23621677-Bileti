package commands;

/**
 * Клас, който имплементира командата за резервиране на място за събитие.
 */
public class BookCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема обект от типа TicketSystem.
     * @param system Инстанция на системата за билети, с която ще работи командата.
     */
    public BookCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Метод, който изпълнява логиката по резервиране на място.
     * Изисква минимум 5 аргумента: ред, място, дата, име на събитие и бележка.
     * @param args Аргументи подадени от главното меню. Формат: <ред> <място> <дата> <събитие> <бележка>
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: book <row> <seat> <date> <event> <note>");
            return;
        }
        System.out.println("Booking not implemented yet.");
    }
}
