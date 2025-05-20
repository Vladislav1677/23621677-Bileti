public class Ticket {
    public enum Status { FREE, BOOKED, SOLD }

    private int row;
    private int seat;
    private String note;
    private Status status;
    private String code;

    public Ticket(int row, int seat) {
        this.row = row;
        this.seat = seat;
        this.status = Status.FREE;
        this.note = "";
        this.code = "";
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public Status getStatus() {
        return status;
    }

    public void book(String note) {
        if (status == Status.FREE) {
            this.note = note;
            this.status = Status.BOOKED;
        }
    }

    public void unbook() {
        if (status == Status.BOOKED) {
            this.note = "";
            this.status = Status.FREE;
        }
    }

    public void buy(String code) {
        if (status != Status.SOLD) {
            this.status = Status.SOLD;
            this.code = code;
        }
    }

    public String getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }
}
