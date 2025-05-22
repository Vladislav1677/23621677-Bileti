package commands;

/**
 * Клас, който реализира командата за помощ, показваща наличните команди.
 */
public class HelpCommand implements Command {

    /**
     * Изпълнява командата, като извежда списък с наличните команди и техния синтаксис.
     *
     * @param args Аргументи, подадени на командата (не се използват).
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Available commands:");
        System.out.println("  open <file>");
        System.out.println("  close");
        System.out.println("  save");
        System.out.println("  saveas <file>");
        System.out.println("  addevent <date> <hall> <name>");
        System.out.println("  freeseats <date> <name>");
        System.out.println("  book <row> <seat> <date> <name> <note>");
        System.out.println("  unbook <row> <seat> <date> <name>");
        System.out.println("  buy <row> <seat> <date> <name>");
        System.out.println("  bookings [<date>] [<name>]");
        System.out.println("  check <code>");
        System.out.println("  report <from> <to> [<hall>]");
        System.out.println("  exit");
    }
}
