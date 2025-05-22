import commands.*;
import java.util.*;

/**
 * Основен клас за стартиране на приложението.
 * Създава обект от класа Menu и стартира командния интерфейс.
 */
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.run();
    }
}
