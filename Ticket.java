package TicketManagament;

import java.io.Serializable;

/**
 * Клас, който представлява билет с информация за резервация, покупка и бележка.
 */
public class Ticket implements Serializable {
    private int row;
    private int seatNumber;
    private String code;

    private boolean booked;
    private boolean bought;
    private String note;

    public Ticket(int row, int seatNumber, String code) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.code = code;
        this.booked = false;
        this.bought = false;
        this.note = "";
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
