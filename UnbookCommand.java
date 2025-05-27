package commands;

import TicketManagament.Event;
import TicketManagament.Hall;
import TicketManagament.TicketSystem;

/**
 * Клас, който имплементира командата за отмяна на резервация.
 */
public class UnbookCommand implements Command {
    private final TicketSystem system;

    /**
     * Конструктор на класа, който приема обект от типа TicketSystem.
     * @param system Инстанция на системата за билети, с която ще работи командата.
     */
    public UnbookCommand(TicketSystem system) {
        this.system = system;
    }

    /**
     * Изпълнява командата за отмяна на резервация.
     * @param args Аргументи подадени от главното меню. Формат: <ред> <място> <дата> <събитие>
     * @throws IllegalArgumentException при невалидни аргументи
     */
    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length < 4) {
            System.out.println("Грешка: Недостатъчно аргументи. Използване: unbook <ред> <място> <дата> <събитие>");
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
                        hall.getNumber(), hall.getRows(), hall.getSeatsPerRow());
                return;
            }

            if (event.unbookSeat(row, seat)) {
                System.out.printf("Успешна отмяна на резервация за ред %d място %d за %s на %s%n",
                        row, seat, eventName, date);
            } else {
                System.out.printf("Грешка: Няма активна резервация за това място. Статус: %s%n",
                        event.getSeatStatus(row, seat));
            }
        } catch (NumberFormatException e) {
            System.out.println("Грешка: Невалиден номер на ред или място. Моля използвайте числа.");
        } catch (Exception e) {
            System.out.println("Грешка: " + e.getMessage());
        }
    }
}