package commands;

/**
 * Команда за освобождаване (отмяна на резервация) на билет за дадено място, дата и събитие.
 * Все още не е имплементирана функционалността за отмяна на резервация.
 */
public class UnbookCommand implements Command {
    private final TicketSystem system;

    /**
     * Създава нова команда за отмяна на резервация с препратка към системата.
     * @param system Инстанция на TicketSystem, върху която ще се извършват операциите.
     */
    public UnbookCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата "unbook".
     * Изисква параметри: ред, място, дата и име на събитие.
     * В момента методът само извежда, че функционалността не е имплементирана.
     *
     * @param args Масив от аргументи на командния ред.
     *             args[0] - ред (row)
     *             args[1] - място (seat)
     *             args[2] - дата (date)
     *             args[3] - име на събитие (event)
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: unbook <row> <seat> <date> <event>");
            return;
        }
        System.out.println("Unbooking not implemented yet.");
    }
}
