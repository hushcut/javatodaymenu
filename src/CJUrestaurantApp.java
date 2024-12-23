import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * CJU 식당 메뉴 조회 및 관리 애플리케이션
 * <p>
 * 이 애플리케이션은 사용자에게 식당의 메뉴를 조회하고 새로운 메뉴를 추가할 수 있는 기능을 제공합니다.
 * 메뉴는 요일별로 구분되며, 메뉴 데이터를 파일에서 로드하고 저장합니다.
 * </p>
 */
public class CJURestaurantApp {
    private static final String MENU_FILE = "menu.txt"; // 메뉴 파일 경로
    private static Map<String, List<String>> dailyMenus = new HashMap<>(); // 요일별 메뉴 저장

    /**
     * 애플리케이션의 메인 메서드입니다.
     * 메뉴 파일에서 데이터를 로드하고 GUI를 초기화하여 실행합니다.
     *
     * @param args 프로그램 실행 시 전달되는 인수
     */
    public static void main(String[] args) {
        // 메뉴 파일에서 데이터 로드
        loadMenuFromFile();

        // GUI 초기화
        SwingUtilities.invokeLater(CJURestaurantApp::createAndShowGUI);
    }

    /**
     * GUI를 생성하고 화면에 표시하는 메서드입니다.
     * 사용자가 메뉴를 조회하거나 추가하는 기능을 제공하는 버튼을 표시합니다.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CJU 식당 메뉴 조회 앱");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton viewButton = new JButton("메뉴 조회");
        JButton addButton = new JButton("메뉴 추가");
        JButton exitButton = new JButton("종료");

        panel.add(viewButton);
        panel.add(addButton);
        panel.add(exitButton);

        frame.add(panel);

        // 버튼 이벤트 리스너
        viewButton.addActionListener(e -> viewMenuGUI());
        addButton.addActionListener(e -> addMenuGUI());
        exitButton.addActionListener(e -> {
            saveMenuToFile();
            frame.dispose();
        });

        frame.setVisible(true);
    }

    /**
     * 사용자가 입력한 요일에 대한 메뉴를 조회하는 GUI를 표시하는 메서드입니다.
     *
     * @see JOptionPane
     */
    private static void viewMenuGUI() {
        String day = JOptionPane.showInputDialog(null, "조회할 요일 입력 (월, 화, 수, 목, 금):", "메뉴 조회", JOptionPane.QUESTION_MESSAGE);
        if (day != null && !day.isEmpty()) {
            List<String> menu = dailyMenus.get(day);
            if (menu != null && !menu.isEmpty()) {
                JOptionPane.showMessageDialog(null, day + "요일 메뉴: " + String.join(", ", menu), "메뉴 조회 결과", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, day + "요일의 메뉴가 존재하지 않습니다.", "메뉴 조회 결과", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * 사용자가 입력한 요일에 새로운 메뉴 항목을 추가하는 GUI를 표시하는 메서드입니다.
     *
     * @see JOptionPane
     */
    private static void addMenuGUI() {
        String day = JOptionPane.showInputDialog(null, "추가할 요일 입력 (월, 화, 수, 목, 금):", "메뉴 추가", JOptionPane.QUESTION_MESSAGE);
        if (day != null && !day.isEmpty()) {
            String items = JOptionPane.showInputDialog(null, "추가할 메뉴 입력 (쉼표로 구분):", "메뉴 추가", JOptionPane.QUESTION_MESSAGE);
            if (items != null && !items.isEmpty()) {
                String[] newItems = items.split(",");
                dailyMenus.computeIfAbsent(day, k -> new ArrayList<>()).addAll(Arrays.asList(newItems));
                JOptionPane.showMessageDialog(null, day + "요일 메뉴가 업데이트되었습니다.", "메뉴 추가 결과", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * 메뉴 파일에서 데이터를 로드하는 메서드입니다.
     * 파일이 존재하면 각 요일에 해당하는 메뉴를 읽어와 `dailyMenus` 맵에 저장합니다.
     *
     * @see File
     * @see BufferedReader
     */
    private static void loadMenuFromFile() {
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            System.out.println("메뉴 파일이 존재하지 않습니다. 새로 생성합니다.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue; // 잘못된 데이터 무시
                String day = parts[0];
                String[] items = parts[1].split(",");
                dailyMenus.put(day, new ArrayList<>(Arrays.asList(items)));
            }
        } catch (IOException e) {
            System.out.println("메뉴 파일을 불러오는 데 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 메뉴 데이터를 파일에 저장하는 메서드입니다.
     * `dailyMenus` 맵에 저장된 데이터를 파일에 기록합니다.
     *
     * @see FileWriter
     * @see BufferedWriter
     */
    private static void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (Map.Entry<String, List<String>> entry : dailyMenus.entrySet()) {
                writer.write(entry.getKey() + "," + String.join(",", entry.getValue()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("메뉴 파일 저장에 실패했습니다: " + e.getMessage());
        }
    }
}