package TeamProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class temaproject {
    public static void main(String[] args) {
        Frame frame = new Frame("회원가입");
        frame.setSize(400, 500);
        frame.setLayout(null);

        // 아이디 라벨
        Label lblId = new Label("아이디");
        lblId.setBounds(50, 50, 80, 20);  // 아이디 라벨 위치
        frame.add(lblId);

        // 아이디 텍스트 필드
        TextField txtId = new TextField();
        txtId.setBounds(150, 50, 150, 20);  // 아이디 텍스트 필드 위치
        frame.add(txtId);

        // 중복 체크 버튼
        Button btnCheck = new Button("중복");
        btnCheck.setBounds(310, 50, 50, 20);  // 중복 체크 버튼 위치
        frame.add(btnCheck);

        // 비밀번호 라벨
        Label lblPassword = new Label("비밀번호");
        lblPassword.setBounds(50, 90, 80, 20);  // 비밀번호 라벨 위치
        frame.add(lblPassword);

        // 비밀번호 텍스트 필드
        TextField txtPassword = new TextField();
        txtPassword.setEchoChar('*');
        txtPassword.setBounds(150, 90, 150, 20);  // 비밀번호 텍스트 필드 위치
        frame.add(txtPassword);

        // 비밀번호 확인 라벨
        Label lblPasswordConfirm = new Label("비밀번호 확인");
        lblPasswordConfirm.setBounds(50, 130, 100, 20);  // 비밀번호 확인 라벨 위치
        frame.add(lblPasswordConfirm);

        // 비밀번호 확인 텍스트 필드
        TextField txtPasswordConfirm = new TextField();
        txtPasswordConfirm.setEchoChar('*');
        txtPasswordConfirm.setBounds(150, 130, 150, 20);  // 비밀번호 확인 텍스트 필드 위치
        frame.add(txtPasswordConfirm);

        // 이름 라벨
        Label lblName = new Label("이름");
        lblName.setBounds(50, 170, 80, 20);  // 이름 라벨 위치
        frame.add(lblName);

        // 이름 텍스트 필드
        TextField txtName = new TextField();
        txtName.setBounds(150, 170, 150, 20);  // 이름 텍스트 필드 위치
        frame.add(txtName);

        // 이메일 라벨
        Label lblEmail = new Label("이메일");
        lblEmail.setBounds(50, 210, 80, 20);  // 이메일 라벨 위치
        frame.add(lblEmail);

        // 이메일 텍스트 필드
        TextField txtEmail = new TextField();
        txtEmail.setBounds(150, 210, 150, 20);  // 이메일 텍스트 필드 위치
        frame.add(txtEmail);

        // 휴대폰 번호 라벨
        Label lblPhone = new Label("휴대폰 번호");
        lblPhone.setBounds(50, 250, 80, 20);  // 휴대폰 번호 라벨 위치
        frame.add(lblPhone);

        // 휴대폰 번호 텍스트 필드
        TextField txtPhone = new TextField();
        txtPhone.setBounds(150, 250, 150, 20);  // 휴대폰 번호 텍스트 필드 위치
        frame.add(txtPhone);

        // 회원 가입 버튼
        Button btnSignup = new Button("회원 가입");
        btnSignup.setBounds(100, 300, 200, 40);  // 회원 가입 버튼 위치
        frame.add(btnSignup);

        // 프레임 표시
        frame.setVisible(true);

        // 윈도우 닫을 때 종료 처리
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
}
