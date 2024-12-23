import java.io.*;
import java.util.*;

public class CJUrestaurantApp {
    private static final String MENU_FILE = "menu.txt";

    private static Map<String, List<String>> dailyMenus = new HashMap<>();

    public static void main(String[] args) {
        loadMenuFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n=== CJU 식당 메뉴 조회 앱 ===");
            System.out.println("1. 메뉴 조회");
            System.out.println("2. 메뉴 추가");
            System.out.println("3. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    viewMenu(scanner);
                    break;
                case 2:
                    addMenu(scanner);
                    break;
                case 3:
                    saveMenuToFile();
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");

            }

        }

        scanner.close();
}
