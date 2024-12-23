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
        private static void loadMenuFromFile(){
            try {BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE)){
                String line;
                while ((line = reader.readLine()) ! = null){
                    String[] parts = line.split(",",2);
                    String day = parts[0];
                    String[] items = parts[1].split(",");
                    dailyMenus.put(day,new ArrayList<>(Array.asList(items)));

                }
            }   catch (IOException e){
                System.out.println("메뉴 파일을 불러오는 데 실패했습니다:" + e.getMessage());
            }

            }

         private static void saveMenuToFile(){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
                    for (Map.Entry<String, List<String>> entry : dailyMenus.entrySet()) {
                        writer.write(entry.getKey() + "," + String.join(",", entry.getValue()));
                        writer.newLine();
            }
                    catch (IOException e) {
                        System.out.println("메뉴 파일 저장에 실패했습니다: " + e.getMessage());

    }
}