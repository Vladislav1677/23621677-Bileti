public class Hall {
    private int number;
    private int rows;
    private int seatsPerRow;

    public Hall(int number, int rows, int seatsPerRow) {
        this.number = number;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    public int getNumber() {
        return number;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    @Override
    public String toString() {
        return "Hall " + number + " (" + rows + " rows, " + seatsPerRow + " seats)";
    }
}
