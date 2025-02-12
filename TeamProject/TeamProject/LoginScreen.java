package TeamProject;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

class RoundedBorder extends AbstractBorder {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY); // 테두리 색상
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius); // 둥근 테두리 그리기
        g2.dispose();
    }
}

public class LoginScreen extends JFrame {
	private BufferedImage image;
	private BufferedImage logoImage;
	private JTextField id_textField;
	private JPasswordField pw_textField;
	private JButton loginButton;
	private JLabel registerLabel, warningLabel;
	static String id;
	boolean flag1 = true, flag2 = true;
	TPMgr mgr;

	public LoginScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();

		try {
			image = ImageIO.read(new File("TeamProject/phone_frame.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			logoImage = ImageIO.read(new File("TeamProject/logo.png")); // 로고 이미지 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}


		// 텍스트 필드 추가
		id_textField = new JTextField();
		id_textField.setOpaque(false);
		id_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		id_textField.setBounds(60, 466, 281, 64); // (x, y, 너비, 높이)
		id_textField.setText(" 아이디를 입력하세요");
		id_textField.setForeground(Color.GRAY);
		id_textField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(id_textField.getText().isEmpty()) {
					id_textField.setText("아이디를 입력하세요");
					id_textField.setForeground(Color.GRAY);
					flag1 = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(flag1) {
					id_textField.setText("");
					id_textField.setForeground(Color.BLACK);
				}
				flag1 = false;
			}
		});
		
		
		
		
		pw_textField = new JPasswordField();
		pw_textField.setBounds(60, 535, 281, 64); // (x, y, 너비, 높이)
		pw_textField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 추가
		    ));
		pw_textField.setText(" 비밀번호를 입력하세요"); // 기본 텍스트로 '아이디' 설정
		pw_textField.setForeground(Color.GRAY);
		pw_textField.setEchoChar((char) 0);
		pw_textField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(pw_textField.getPassword().length == 0) {
					pw_textField.setText(" 비밀번호를 입력하세요");
					pw_textField.setForeground(Color.GRAY);
					pw_textField.setEchoChar((char) 0);
					flag2 = true;
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(flag2) {
					pw_textField.setText("");
					pw_textField.setForeground(Color.BLACK);
					pw_textField.setEchoChar('*');
				}
				flag2 = false;
			}
		});
		
		pw_textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mgr.loginChk(id_textField.getText().trim(), pw_textField.getText().trim())) {
					id = id_textField.getText().trim();
					new UserHomeScreen();
				} else {
					id_textField.setText(" 아이디를 입력하세요");
					id_textField.setForeground(Color.GRAY);
					pw_textField.setText(" 비밀번호를 입력하세요");
					pw_textField.setForeground(Color.GRAY);
					pw_textField.setEchoChar((char) 0);
					flag1 = true;
					flag2 = true;
					warningLabel.setVisible(true);
					
				}
			}
		});
		
		warningLabel = new JLabel("아이디 또는 비밀번호가 틀렸습니다");
		warningLabel.setForeground(Color.RED);
		warningLabel.setBounds(61, 580, 250, 60);

		// 로그인 버튼 추가
		loginButton = new RoundedButton("로그인");
		loginButton.setBounds(61, 625, 281, 58); // (x, y, 너비, 높이)
		loginButton.setBackground(new Color(0, 123, 255)); // 버튼 배경 색 (파란색)
		loginButton.setForeground(Color.WHITE); // 버튼 텍스트 색 (하얀색)
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mgr.loginChk(id_textField.getText().trim(), pw_textField.getText().trim())) {
					id = id_textField.getText().trim();
					new UserHomeScreen();
				} else {
					id_textField.setText(" 아이디를 입력하세요");
					id_textField.setForeground(Color.GRAY);
					pw_textField.setText(" 비밀번호를 입력하세요");
					pw_textField.setForeground(Color.GRAY);
					pw_textField.setEchoChar((char) 0);
					flag1 = true;
					flag2 = true;
					warningLabel.setVisible(true);
				}
			}
		});

		// 회원가입 라벨 추가
		registerLabel = new JLabel("회원가입");
		registerLabel.setBounds(175, 701, 67, 60); // (x, y, 너비, 높이)
		registerLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		registerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterScreen();
			}
		});

		// 컴포넌트들을 프레임에 추가
		add(id_textField);
		add(pw_textField);
		add(loginButton);
		add(registerLabel);
		add(warningLabel);
		warningLabel.setVisible(false);

		// JPanel 추가
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					// 이미지 크기 조정 후 그리기
					Image scaledImage = image.getScaledInstance(402, 874, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 닫기 버튼 추가
		JButton closeButton = new JButton("X");
		closeButton.setBounds(370, 10, 20, 20); // 오른쪽 상단에 위치
		closeButton.setBackground(Color.RED); // 버튼 배경 색
		closeButton.setForeground(Color.WHITE); // 버튼 텍스트 색
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setFocusPainted(false);
		closeButton.addActionListener(e -> System.exit(0)); // 애플리케이션 종료

		panel.add(closeButton);

		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); // 부모 클래스의 paint() 호출, 배경을 그리기 위함

		if (logoImage != null) {
			// 로고 이미지 크기 조정 (예: 402x402)
			Image scaledLogo = logoImage.getScaledInstance(402, 402, Image.SCALE_SMOOTH);
			g.drawImage(scaledLogo, 0, 98, this); // 위치도 조정 가능
		}
	}

	
	
	
	public static void main(String[] args) {
		new LoginScreen();
	}
}
