package TicketManagament;

/**
 * Клас, който представлява зала с определен брой редове и места на ред.
 */
public class Hall {
    private final int number;
    private final int rows;
    private final int seatsPerRow;

    /**
     * Конструктор, който създава нова зала с номер, брой редове и места на ред.
     *
     * @param number Номерът на залата.
     * @param rows Броят на редовете в залата.
     * @param seatsPerRow Броят на местата във всеки ред.
     */
    public Hall(int number, int rows, int seatsPerRow) {
        this.number = number;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    /**
     * Връща номера на залата.
     *
     * @return Номера на залата.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Връща броя на редовете в залата.
     *
     * @return Броят на редовете.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Връща броя на местата във всеки ред.
     *
     * @return Броят на местата на ред.
     */
    public int getSeatsPerRow() {
        return seatsPerRow;
    }
}