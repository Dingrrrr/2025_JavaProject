package TeamProject;

import javax.swing.*;
import java.awt.*;

public class Join extends MainFrame {
	
	private JTextField useridField, usernameField, useremailField, userphoneField;
	private JPasswordField userpwField, userpwchkField;
	private JButton joinButton, idchkButton;
	
	public Join() {
		
		setTitle("토닥토닥 회원가입");
        
		//패널 설정
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setPreferredSize(new Dimension(400, 800));
        
        //아이디
        JLabel idlabel = new JLabel("아이디");
        idlabel.setBounds(41, 119, 49, 60);
        idlabel.setOpaque(true);
        idlabel.setBackground(Color.WHITE);
        idlabel.setForeground(Color.BLACK);
        p.add(idlabel);
        
        useridField = new JTextField();
        useridField.setBounds(41, 165, 220, 40);
        p.add(useridField);
        
        //아이디 중복 확인 버튼
        idchkButton = new JButton("중복");
        idchkButton.setBounds(268, 164, 91, 43);
        idchkButton.setBackground(Color.BLACK);
        idchkButton.setForeground(Color.WHITE);
        p.add(idchkButton);
        
        //비밀번호
        JLabel pwlabel = new JLabel("비밀번호");
        pwlabel.setBounds(41, 211, 65, 60);
        pwlabel.setOpaque(true);
        pwlabel.setBackground(Color.WHITE);
        pwlabel.setForeground(Color.BLACK);
        p.add(pwlabel);
        
        userpwField = new JPasswordField();
        userpwField.setBounds(41, 257, 320, 40);
        p.add(userpwField);
        
        //비밀번호 확인
        JLabel pwchklabel = new JLabel("비밀번호 확인");
        pwchklabel.setBounds(41, 300, 105, 60);
        pwchklabel.setOpaque(true);
        pwchklabel.setBackground(Color.WHITE);
        pwchklabel.setForeground(Color.BLACK);
        p.add(pwchklabel);
        
        userpwchkField = new JPasswordField();
        userpwchkField.setBounds(41, 349, 320, 40);
        p.add(userpwchkField);
        
        //이름
        JLabel namelabel = new JLabel("이름");
        namelabel.setBounds(41, 392, 32, 60);
        namelabel.setOpaque(true);
        namelabel.setBackground(Color.WHITE);
        namelabel.setForeground(Color.BLACK);
        p.add(namelabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(41, 441, 320, 40);
        p.add(usernameField);
        
        //이메일
        JLabel emaillabel = new JLabel("이메일");
        emaillabel.setBounds(41, 484, 49, 60);
        emaillabel.setOpaque(true);
        emaillabel.setBackground(Color.WHITE);
        emaillabel.setForeground(Color.BLACK);
        p.add(emaillabel);
        
        useremailField = new JTextField();
        useremailField.setBounds(41, 533, 320, 40);
        p.add(useremailField);
        
        //휴대폰 번호
        JLabel phonelabel = new JLabel("휴대폰 번호");
        phonelabel.setBounds(41, 576, 86, 60);
        phonelabel.setOpaque(true);
        phonelabel.setBackground(Color.WHITE);
        phonelabel.setForeground(Color.BLACK);
        p.add(phonelabel);
        
        userphoneField = new JTextField();
        userphoneField.setBounds(41, 625, 320, 40);
        p.add(userphoneField);
        
        //회원가입
        joinButton = new JButton("회원가입");
        joinButton.setBounds(60, 710, 281, 58);
        joinButton.setBackground(Color.BLACK);
        joinButton.setForeground(Color.WHITE);
        p.add(joinButton);
        
        p.setComponentZOrder(idlabel, 0);
		add(p);
        setVisible(true);
	}

	public static void main(String[] args) {
		new Join();
	}
	
}
