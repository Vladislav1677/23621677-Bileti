package commands;

/**
 * Клас, който имплементира командата за проверка на код за резервация или покупка.
 * Командата приема един аргумент – кодът за проверка.
 */
public class CheckCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема обект от типа TicketSystem.
     * @param system Инстанция на системата за билети, чрез която се извършва проверката на кода.
     */
    public CheckCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Метод, който изпълнява командата за проверка на код.
     * Ако не е подаден код, извежда съобщение за правилна употреба.
     * @param args Аргументи подадени от главното меню: <код за проверка>
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: check <code>");
            return;
        }
        system.checkCode(args[0]);
    }
}
