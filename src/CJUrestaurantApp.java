import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CJUrestaurantApp {
    private static final String MENU_FILE = "menu.txt";

    private static Map<String, List<String>> dailyMenus = new HashMap<>();

    public static void main(String[] args) {
        loadMenuFromFile();


        SwingUtilities.invokeLater(CJURestaurantApp::createAndShowGUI);
    }

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

        viewButton.addActionListener(e -> viewMenuGUI());
        addButton.addActionListener(e -> addMenuGUI());
        exitButton.addActionListener(e -> {
            saveMenuToFile();
            frame.dispose();
        });

        frame.setVisible(true);
    }
    private static void viewMenuGUI() {
        String day = JOptionPane.showInputDialog(null, "조회할 요일 입력 (월, 화, 수, 목, 금):", "메뉴 조회", JOptionPane.QUESTION_MESSAGE);
        if (day != null) {
            List<String> menu = dailyMenus.get(day);
            if (menu != null) {
                JOptionPane.showMessageDialog(null, day + "요일 메뉴: " + String.join(", ", menu), "메뉴 조회 결과", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, day + "요일의 메뉴가 존재하지 않습니다.", "메뉴 조회 결과", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private static void addMenuGUI() {
        String day = JOptionPane.showInputDialog(null, "추가할 요일 입력 (월, 화, 수, 목, 금):", "메뉴 추가", JOptionPane.QUESTION_MESSAGE);
        if (day != null) {
            String items = JOptionPane.showInputDialog(null, "추가할 메뉴 입력 (쉼표로 구분):", "메뉴 추가", JOptionPane.QUESTION_MESSAGE);
            if (items != null) {
                String[] newItems = items.split(",");
                dailyMenus.computeIfAbsent(day, k -> new ArrayList<>()).addAll(Arrays.asList(newItems));
                JOptionPane.showMessageDialog(null, day + "요일 메뉴가 업데이트되었습니다.", "메뉴 추가 결과", JOptionPane.INFORMATION_MESSAGE);
            }
        }
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
