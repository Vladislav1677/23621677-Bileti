import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicketOffice office = new TicketOffice();
        Scanner scanner = new Scanner(System.in);
        String cmd;

        System.out.println("Ticket System ready. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            cmd = scanner.nextLine();
            String[] parts = cmd.split(" ", 2);
            String action = parts[0];

            try {
                switch (action) {
                    case "addevent":
                        String[] addArgs = parts[1].split(" ", 3);
                        office.addEvent(addArgs[0], Integer.parseInt(addArgs[1]), addArgs[2]);
                        break;
                    case "freeseats":
                        String[] freeArgs = parts[1].split(" ", 2);
                        office.freeSeats(freeArgs[0], freeArgs[1]);
                        break;
                    case "book":
                        String[] bookArgs = parts[1].split(" ", 5);
                        office.book(Integer.parseInt(bookArgs[0]), Integer.parseInt(bookArgs[1]),
                                bookArgs[2], bookArgs[3], bookArgs[4]);
                        break;
                    case "unbook":
                        String[] unbookArgs = parts[1].split(" ", 4);
                        office.unbook(Integer.parseInt(unbookArgs[0]), Integer.parseInt(unbookArgs[1]),
                                unbookArgs[2], unbookArgs[3]);
                        break;
                    case "buy":
                        String[] buyArgs = parts[1].split(" ", 4);
                        office.buy(Integer.parseInt(buyArgs[0]), Integer.parseInt(buyArgs[1]),
                                buyArgs[2], buyArgs[3]);
                        break;
                    case "bookings":
                        String[] bookListArgs = parts.length > 1 ? parts[1].split(" ", 2) : new String[0];
                        String date = bookListArgs.length > 0 ? bookListArgs[0] : null;
                        String name = bookListArgs.length > 1 ? bookListArgs[1] : null;
                        office.bookings(date, name);
                        break;
                    case "check":
                        office.check(parts[1]);
                        break;
                    case "report":
                        String[] reportArgs = parts[1].split(" ");
                        Integer hallNum = reportArgs.length == 3 ? Integer.parseInt(reportArgs[2]) : null;
                        office.report(reportArgs[0], reportArgs[1], hallNum);
                        break;
                    case "exit":
                        System.out.println("Bye!");
                        return;
                    case "help":
                        System.out.println("Commands: addevent, freeseats, book, unbook, buy, bookings, check, report, exit");
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Invalid command or parameters.");
            }
        }
    }
}
