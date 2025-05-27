package TicketManagament;

/**
 * Клас, който представлява билет с информация за резервация, покупка и бележка.
 */
public class Ticket {
    private boolean booked;
    private boolean bought;
    private String note;

    /**
     * Проверява дали билетът е резервиран.
     * @return true ако билетът е резервиран, false в противен случай.
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * Задава статуса на резервацията на билета.
     * @param booked true за резервиран билет, false за не резервиран.
     */
    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    /**
     * Проверява дали билетът е закупен.
     * @return true ако билетът е закупен, false в противен случай.
     */
    public boolean isBought() {
        return bought;
    }

    /**
     * Задава статуса на закупуване на билета.
     * @param bought true за закупен билет, false за не закупен.
     */
    public void setBought(boolean bought) {
        this.bought = bought;
    }

    /**
     * Връща бележката, свързана с билета.
     * @return Текстова бележка за билета.
     */
    public String getNote() {
        return note;
    }

    /**
     * Задава бележка към билета.
     * @param note Текстова бележка.
     */
    public void setNote(String note) {
        this.note = note;
    }
}
