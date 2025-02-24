package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class UserHomeScreen extends JFrame {
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, mainProfileLabel, addButtonLabel, imageLabel, imageProfileLabel, logoutLabel;
	private JLabel welcomeLabel, additionLabel;
	private PetChooseDialog pc;
	TPMgr mgr = new TPMgr();

	public UserHomeScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mgr = new TPMgr();
		UserBean bean = mgr.showUser(StaticData.user_id);

		try {
			image = ImageIO.read(new File("TeamProject/phone_frame.png")); // 투명 PNG 불러오기
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 🔹 공통 마우스 클릭 이벤트 리스너
		MouseAdapter commonMouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object source = e.getSource(); // 클릭된 컴포넌트 확인

				if (source == alarmLabel) {
					System.out.println("🔔 알람 클릭됨!");
					dispose();
					new AlarmMainScreen(UserHomeScreen.this);
				} else if (source == imageLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(UserHomeScreen.this);
				} else if (source == imageProfileLabel) {
					System.out.println("👤 상단 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(UserHomeScreen.this);
				} else if (source == addButtonLabel) {
					System.out.println("➕ 추가 버튼 클릭됨!");
					if (pc == null) {
						pc = new PetChooseDialog(UserHomeScreen.this);
						// ZipcodeFrame의 창의 위치를 MemberAWT 옆에 지정
						pc.setLocation(getX() + 25, getY() + 300);
					} else {
						pc.setLocation(getX() + 25, getY() + 300);
						pc.setVisible(true);
					}
					setEnabled(false);
				} else if (source == logoutLabel) {
					dispose();
					mgr.userOut(StaticData.user_id);
					new LoginScreen();
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		// 메인 프로필 이미지
		byte[] imgBytes = bean.getUser_image();
		if (imgBytes == null || imgBytes.length == 0) {
			// 기본 프로필 이미지 사용
			imageLabel = new JLabel();
			imageLabel = createScaledImageLabel("TeamProject/profile.png", 200, 200);
			imageLabel.setBounds(101, 178, 200, 200);
			imageLabel.addMouseListener(commonMouseListener);
			add(imageLabel);
		} else {
			// 사용자 이미지가 있을 경우
			ImageIcon icon = new ImageIcon(imgBytes);
			Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

			// RoundedImageLabel 사용
			RoundedImageLabel roundedImageLabel = new RoundedImageLabel(img, 200, 200, 3); // 100은 둥근 정도
			roundedImageLabel.setBounds(101, 185, 200, 200);
			roundedImageLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(UserHomeScreen.this);
				}
			});
			add(roundedImageLabel);
		}

		// 상단 프로필 아이디
		if (imgBytes == null || imgBytes.length == 0) {
			imageProfileLabel = new JLabel();
			imageProfileLabel = createScaledImageLabel("TeamProject/profile.png", 40, 40);
			imageProfileLabel.setBounds(330, 120, 40, 40);
			imageProfileLabel.addMouseListener(commonMouseListener);
			add(imageProfileLabel);
		} else {
			// 사용자 이미지가 있을 경우
			ImageIcon icon1 = new ImageIcon(imgBytes);
			Image img = icon1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

			// RoundedImageLabel 사용
			RoundedImageLabel roundedProfileImageLabel = new RoundedImageLabel(img, 40, 40, 3); // 100은 둥근 정도
			roundedProfileImageLabel.setBounds(330, 120, 40, 40);
			roundedProfileImageLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(UserHomeScreen.this);
				}
			});
			add(roundedProfileImageLabel);
		}

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/pet_menu.png", 140, 140);
		addButtonLabel.setBounds(260, 675, 140, 140);
		addButtonLabel.addMouseListener(commonMouseListener);
		add(addButtonLabel);

		// 환영 문구
		welcomeLabel = new JLabel("어서오세요, " + mgr.userName(StaticData.user_id) + "님");
		welcomeLabel.setBounds(155, 401, 134, 20);
		welcomeLabel.setForeground(Color.BLACK);
		add(welcomeLabel);

		// 반려동물 추가 문구
		additionLabel = new JLabel("<html><div style='text-align: center;'>처음 오셨다면<br>아이를 등록해주세요</html>");
		additionLabel.setBounds(146, 470, 151, 80);
		additionLabel.setForeground(Color.BLACK);
		add(additionLabel);

		// 로그아웃 버튼
		logoutLabel = createScaledImageLabel("TeamProject/logout_icon.png", 40, 40);
		logoutLabel.setBounds(30, 122, 40, 40);
		logoutLabel.addMouseListener(commonMouseListener);
		add(logoutLabel);

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
				g.drawLine(22, 165, 379, 165);
				g.drawLine(22, 443, 379, 443);
				g.drawLine(22, 574, 379, 574);
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

	/**
	 * 이미지 크기를 조정하여 JLabel을 생성하는 메서드
	 */
	private JLabel createScaledImageLabel(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(imagePath);
		Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}

	public static void main(String[] args) {
		new LoginScreen();
		//
	}
}
