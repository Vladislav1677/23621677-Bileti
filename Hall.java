package commands;

/**
 * Клас, който представлява зала с определен брой редове и места на ред.
 */
public class Hall {
    private final String name;
    private final int rows;
    private final int seatsPerRow;

    /**
     * Конструктор, който създава нова зала с име, брой редове и места на ред.
     *
     * @param name Името на залата.
     * @param rows Броят на редовете в залата.
     * @param seatsPerRow Броят на местата във всеки ред.
     */
    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    /**
     * Връща името на залата.
     *
     * @return Името на залата.
     */
    public String getName() {
        return name;
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
