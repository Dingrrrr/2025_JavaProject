package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame {

    private JTextField useridField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    
    public Main() {
        setTitle("토닥토닥 로그인");
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 패널 설정
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        add(p);

        // 로고 이미지 추가
        ImageIcon logoIcon = new ImageIcon("dproject/main.png"); // 로고 이미지 경로
        Image img = logoIcon.getImage().getScaledInstance(350, 350, Image.SCALE_AREA_AVERAGING); // 크기 조정
        ImageIcon resizedIcon = new ImageIcon(img);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(100, 130, 250, 150);
        p.add(logoLabel);
        
        //아이디
        useridField = new JTextField();
        useridField.setBounds(80, 350, 300, 50);
        p.add(useridField);
        
        //비밀번호
        passwordField = new JPasswordField();
        passwordField.setBounds(80, 395, 300, 50);
        p.add(passwordField);

        // 로그인 버튼
        loginButton = new JButton("로그인");
        loginButton.setBounds(80, 520, 300, 50);
        loginButton.setBackground(Color.DARK_GRAY);
        loginButton.setForeground(Color.WHITE);
        p.add(loginButton);

        // 회원가입 버튼
        signUpButton = new JButton("회원가입");
        signUpButton.setBounds(80, 600, 300, 50);
        signUpButton.setForeground(Color.BLACK);
        signUpButton.setContentAreaFilled(false);
        p.add(signUpButton);

        // 버튼 이벤트
        loginButton.addActionListener(new LoginAction());
        signUpButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "회원가입 페이지로 이동"));

        setVisible(true);
    }
    
    // 로그인 버튼 액션
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = useridField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(Main.this, "아이디와 비밀번호를 입력하세요.");
            } else if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(Main.this, "로그인 성공!");
            } else {
                JOptionPane.showMessageDialog(Main.this, "로그인 실패!");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
