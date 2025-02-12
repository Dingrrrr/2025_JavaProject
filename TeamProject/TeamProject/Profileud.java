package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Profileud extends JFrame {

	private BufferedImage image;
	private JLabel nameLabel, pwLabel, emailLabel, phoneLabel, profileLabel, delLabel, backLabel;
	private JTextField nameField, emailField, phoneField;
	private JPasswordField pwField;
	private JButton updataButton, fisButton, addButton;

	public Profileud() {
		setTitle("회원정보 수정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			image = ImageIO.read(new File("dproject/phone_frame.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 뒤로가기 아이콘
		backLabel = createScaledImageLabel("dproject/back_Button.png", 40, 40);
		backLabel.setBounds(30, 115, 40, 40);
		add(backLabel);

		// 삭제 아이콘
		delLabel = createScaledImageLabel("dproject/delete_button.png", 28, 28);
		delLabel.setBounds(332, 115, 28, 28);
		add(delLabel);

		// 메인 프로필 이미지
		profileLabel = createScaledImageLabel("dproject/profile.png", 270, 270);
		profileLabel.setBounds(66, 126, 270, 270);
		add(profileLabel);

		// 추가 버튼
		addButton = new JButton("추가");
		addButton.setBounds(269, 396, 91, 43);
		addButton.setBackground(new Color(91, 91, 91));
		addButton.setForeground(Color.WHITE);
		add(addButton);

		// 이름
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(34, 415, 32, 60);
		nameLabel.setForeground(Color.BLACK);
		add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(33, 461, 220, 40);
		add(nameField);

		// 비밀번호
		pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(33, 501, 65, 60);
		pwLabel.setForeground(Color.BLACK);
		add(pwLabel);

		pwField = new JPasswordField();
		pwField.setBounds(33, 547, 320, 40);
		add(pwField);

		// 이메일
		emailLabel = new JLabel("이메일");
		emailLabel.setBounds(35, 587, 49, 60);
		emailLabel.setForeground(Color.BLACK);
		add(emailLabel);

		emailField = new JTextField();
		emailField.setBounds(35, 636, 320, 40);
		add(emailField);

		// 휴대폰 번호
		phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(33, 679, 86, 60);
		phoneLabel.setForeground(Color.BLACK);
		add(phoneLabel);

		phoneField = new JTextField();
		phoneField.setBounds(33, 728, 320, 40);
		add(phoneField);

		// 수정 버튼
		updataButton = new JButton("수정");
		updataButton.setBounds(75, 780, 91, 43);
		updataButton.setBackground(new Color(91, 91, 91));
		updataButton.setForeground(Color.WHITE);
		add(updataButton);

		// 완료 버튼
		fisButton = new JButton("완료");
		fisButton.setBounds(232, 780, 91, 43);
		fisButton.setBackground(new Color(91, 91, 91));
		fisButton.setForeground(Color.WHITE);
		add(fisButton);

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

				// y=158 위치에 가로로 회색 선 그리기
				g.setColor(Color.LIGHT_GRAY); // 선 색을 회색으로 설정
			}
		};
		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 닫기 버튼
		JButton closeButton = new JButton("X");
		closeButton.setBounds(370, 10, 20, 20);
		closeButton.setBackground(Color.RED);
		closeButton.setForeground(Color.WHITE);
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setFocusPainted(false);
		closeButton.addActionListener(e -> System.exit(0));
		panel.add(closeButton);

		setVisible(true);
	}

	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public static void main(String[] args) {
		new Profileud();
	}
}
