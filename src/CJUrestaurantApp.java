import java.io.*;
import java.util.*;

public class CJUrestaurantApp {
    private static final String MENU_FILE = "menu.txt";

    private static Map<String, List<String>> dailyMenus = new HashMap<>();

    public static void main(String[] args) {
        loadMenuFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;


}
