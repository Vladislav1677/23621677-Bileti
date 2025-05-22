package commands;

/**
 * Клас, който имплементира командата за закупуване на билет.
 */
public class BuyCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема обект от типа TicketSystem.
     * @param system Инстанция на системата за билети, с която ще работи командата.
     */
    public BuyCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за закупуване на билет.
     * @param args Аргументи подадени от главното меню. Формат: <ред> <място> <дата> <събитие>
     * @throws IllegalArgumentException при невалидни аргументи
     */
    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length < 4) {
            System.out.println("Грешка: Недостатъчно аргументи. Използване: buy <ред> <място> <дата> <събитие>");
            return;
        }

        try {
            int row = Integer.parseInt(args[0]);
            int seat = Integer.parseInt(args[1]);
            String date = args[2];
            String eventName = args[3];

            Event event = system.getEvent(date, eventName);
            if (event == null) {
                System.out.println("Грешка: Събитието не е намерено.");
                return;
            }

            Hall hall = event.getHall();
            if (row < 1 || row > hall.getRows() || seat < 1 || seat > hall.getSeatsPerRow()) {
                System.out.printf("Грешка: Невалидно място. Зала '%s' има %d реда и %d места на ред.%n",
                        hall.getName(), hall.getRows(), hall.getSeatsPerRow());
                return;
            }

            if (event.buyTicket(row, seat)) {
                String code = generateTicketCode(date, eventName, row, seat, "SOLD");
                system.registerCode(code, String.format("Закупен: Ред %d Място %d за %s на %s",
                        row, seat, eventName, date));
                System.out.printf("Успешна покупка на билет. Код: %s%n", code);
            } else {
                System.out.printf("Грешка: Неуспешна покупка. Мястото е %s%n",
                        event.getSeatStatus(row, seat));
            }
        } catch (NumberFormatException e) {
            System.out.println("Грешка: Невалиден номер на ред или място. Моля използвайте числа.");
        } catch (Exception e) {
            System.out.println("Грешка: " + e.getMessage());
        }
    }

    /**
     * Генерира уникален код за билет.
     * @param date Дата на събитието
     * @param event Име на събитието
     * @param row Ред на мястото
     * @param seat Място в реда
     * @param type Тип на билета (BOOK/BUY)
     * @return Генериран код за билет
     */
    private String generateTicketCode(String date, String event, int row, int seat, String type) {
        return String.format("%s-%s-%s%d%d", type,
                date.replace("-", ""),
                event.substring(0, Math.min(3, event.length())),
                row, seat);
    }
}