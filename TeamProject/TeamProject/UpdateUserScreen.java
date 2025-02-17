package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class UpdateUserScreen extends JFrame {

	private BufferedImage image;
	private JLabel nameLabel, pwLabel, emailLabel, phoneLabel, profileLabel, delLabel, backLabel;
	private JTextField nameField, emailField, phoneField;
	private JPasswordField pwField;
	private JButton updataButton, fisButton, addButton;
	boolean flag = false;
	private JFrame previousFrame;  // 이전 프레임 저장
	TPMgr mgr;
	private UserPhotoModifyDialog upm;

	public UpdateUserScreen(JFrame previousFrame) {
		setTitle("회원정보 수정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		UserBean bean = mgr.showUser(StaticData.user_id);

		try {
			image = ImageIO.read(new File("TeamProject/phone_frame.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 🔹 공통 마우스 클릭 이벤트 리스너
		MouseAdapter commonMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object source = e.getSource(); // 클릭된 컴포넌트 확인

				if (source == backLabel) {
					System.out.println("뒤로가기 클릭됨");
					if(mgr.isPet(StaticData.user_id)) {		//반려동물 정보가 있는 경우
						dispose();
						previousFrame.setVisible(true);
					} else {
						dispose();						
						new UserHomeScreen();
					}
				} else if (source == addButton) {
					System.out.println("유저 프로필 사진 추가 클릭됨!");
					if (upm == null) {
						upm = new UserPhotoModifyDialog(UpdateUserScreen.this);
						upm.setLocation(getX()+22, getY() + 630);
					} else {
						upm.setLocation(getX()+22, getY() + 630);
						upm.setVisible(true);
					}
				} else if (source == updataButton) {
					System.out.println("유저 정보 수정 버튼 클릭됨!");
					nameField.setEnabled(true);
					pwField.setEnabled(true);
					emailField.setEnabled(true);
					phoneField.setEnabled(true);
					flag = true;
				} else if (source == fisButton) {
					System.out.println("유저 정보 완료 버튼 클릭됨!");
					if(flag) {
						UserBean bb = new UserBean();
						bb.setUsername(nameField.getText().trim());
						bb.setPassword(pwField.getText().trim());
						bb.setEmail(emailField.getText().trim());
						bb.setPhone(phoneField.getText().trim());
//						bb.setUser_image();
						if(mgr.userUpd(StaticData.user_id, bb)) {
							nameField.setEnabled(false);
							pwField.setEnabled(false);
							emailField.setEnabled(false);
							phoneField.setEnabled(false);
						}
					}
				}
			}
		};

		// 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_Button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);

		// 메인 프로필 이미지
		profileLabel = createScaledImageLabel("TeamProject/profile.png", 270, 270);
		profileLabel.setBounds(70, 189, 270, 270);
		add(profileLabel);

		// 이미지 추가 버튼
		addButton = new RoundedButton("추가");
		addButton.setBounds(277, 450, 80, 35);
		addButton.setBackground(new Color(91, 91, 91));
		addButton.setForeground(Color.WHITE);
		addButton.addMouseListener(commonMouseListener);
		add(addButton);

		// 이름
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(43, 469, 32, 60);
		nameLabel.setForeground(Color.BLACK);
		add(nameLabel);

		nameField = new JTextField(bean.getUsername());
		nameField.setBounds(43, 510, 220, 40);
		nameField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		add(nameField);

		// 비밀번호
		pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(43, 539, 65, 60);
		pwLabel.setForeground(Color.BLACK);
		add(pwLabel);

		pwField = new JPasswordField(bean.getPassword());
		pwField.setBounds(43, 580, 320, 40);
		pwField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		add(pwField);

		// 이메일
		emailLabel = new JLabel("이메일");
		emailLabel.setBounds(43, 609, 49, 60);
		emailLabel.setForeground(Color.BLACK);
		add(emailLabel);

		emailField = new JTextField(bean.getEmail());
		emailField.setBounds(43, 650, 320, 40);
		emailField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		add(emailField);

		// 휴대폰 번호
		phoneLabel = new JLabel("휴대폰 번호");
		phoneLabel.setBounds(43, 679, 86, 60);
		phoneLabel.setForeground(Color.BLACK);
		add(phoneLabel);

		phoneField = new JTextField(bean.getPhone());
		phoneField.setBounds(43, 720, 320, 40);
		phoneField.setBorder(BorderFactory.createCompoundBorder(
		        new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부 여백 (위, 왼쪽, 아래, 오른쪽)
		    ));
		add(phoneField);

		// 수정 버튼
		updataButton = new RoundedButton("수정");
		updataButton.setBounds(98, 770, 91, 43);
		updataButton.setBackground(new Color(91, 91, 91));
		updataButton.setForeground(Color.WHITE);
		updataButton.addMouseListener(commonMouseListener);
		updataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nameField.setEnabled(true);
				pwField.setEnabled(true);
				emailField.setEnabled(true);
				phoneField.setEnabled(true);
				flag = true;
			}
		});
		add(updataButton);

		// 완료 버튼
		fisButton = new RoundedButton("완료");
		fisButton.setBounds(215, 770, 91, 43);
		fisButton.setBackground(new Color(91, 91, 91));
		fisButton.setForeground(Color.WHITE);
		fisButton.addMouseListener(commonMouseListener);
		add(fisButton);
		
		nameField.setEnabled(false);
		pwField.setEnabled(false);
		emailField.setEnabled(false);
		phoneField.setEnabled(false);

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
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mgr.userOut(StaticData.user_id);
				System.exit(0);
			}
		});
		panel.add(closeButton);

		setVisible(true);
	}

	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public static void main(String[] args) {
		new LoginScreen();
	}
}
