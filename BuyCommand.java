package commands;

/**
 * Клас, който имплементира командата за закупуване на билет.
 * Командата приема информация за мястото, датата и събитието.
 */
public class BuyCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема инстанция на системата за билети.
     * @param system Обект от типа TicketSystem, чрез който се изпълнява логиката за закупуване.
     */
    public BuyCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Метод, който изпълнява командата за закупуване на билет.
     * Ако аргументите са по-малко от 4, извежда указание за правилна употреба.
     * @param args Аргументи от главното меню: <ред> <седалка> <дата> <име на събитие>
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: buy <row> <seat> <date> <event>");
            return;
        }
        System.out.println("Buying not implemented yet.");
    }
}
