package commands;

import java.util.*;

/**
 * Клас Menu управлява командния интерфейс на системата за билети.
 * Съдържа регистър на всички възможни команди и отговаря за въвеждането и изпълнението им.
 * Командите се регистрират веднъж при създаване на Menu.
 * При стартиране на run() се очаква вход от потребителя и се изпълняват съответните команди.
 */
public class Menu {
    private final Map<String, Command> commands = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор, който регистрира всички налични команди в системата.
     */
    public Menu() {
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        TicketSystem system = new TicketSystem();
        commands.put("open", new OpenCommand(system));
        commands.put("close", new CloseCommand(system));
        commands.put("save", new SaveCommand(system));
        commands.put("saveas", new SaveAsCommand(system));
        commands.put("addevent", new AddEventCommand(system));
        commands.put("freeseats", new FreeSeatsCommand(system));
        commands.put("book", new BookCommand(system));
        commands.put("unbook", new UnbookCommand(system));
        commands.put("buy", new BuyCommand(system));
        commands.put("bookings", new BookingsCommand(system));
        commands.put("check", new CheckCommand(system));
        commands.put("report", new ReportCommand(system));
    }

    /**
     * Методът стартира цикъл за четене на потребителски команди от конзолата.
     * Всяка команда се изпълнява чрез съответния Command обект.
     * При въвеждане на неизвестна команда се показва съобщение.
     */
    public void run() {
        System.out.println("Welcome to the Ticket System. Type 'help' for commands.");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length == 0 || tokens[0].isEmpty()) continue;

            String cmd = tokens[0].toLowerCase();
            Command command = commands.get(cmd);

            if (command != null) {
                try {
                    command.execute(Arrays.copyOfRange(tokens, 1, tokens.length));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command: " + cmd);
            }
        }
    }
}
