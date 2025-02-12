package TeamProject;

import javax.swing.*;
import java.awt.*;

public class ProfileUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("회원 프로필");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);
        frame.setLayout(new BorderLayout());

        // 프로필 패널 (원형 이미지)
        CircularProfilePanel prop = new CircularProfilePanel("dproject/profile.png");
        prop.setBounds(66, 126, 270, 270);
        
        // 정보 입력 패널
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1, 5, 5));
        
        JTextField nameField = new JTextField("이름");
        JTextField emailField = new JTextField("이메일");
        JTextField phoneField = new JTextField("휴대폰 번호");

        JButton saveButton = new JButton("저장");

        infoPanel.add(nameField);
        infoPanel.add(emailField);
        infoPanel.add(phoneField);
        infoPanel.add(saveButton);

        frame.add(prop, BorderLayout.NORTH);
        frame.add(infoPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

