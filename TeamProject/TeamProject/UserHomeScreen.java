package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class UserHomeScreen extends JFrame {
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, mainProfileLabel, addButtonLabel;
	private JButton logoutButton;
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
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
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
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		// 🔹 유저 프로필 이미지 - DB에서 가져온 이미지를 사용
		String userSmallImageUrl = bean.getUser_image(); // DB에서 가져온 이미지 URL
		// 이미지 URL을 통해 ImageIcon 객체 생성
		try {
			// URL을 사용하여 이미지를 로드하고 ImageIcon 생성
			ImageIcon petImageIcon = new ImageIcon(new URL(userSmallImageUrl)); // petImageUrl에서 이미지 로드

			// 이미지 크기 조정 (150x150으로 크기 변경)
			petImageIcon = new ImageIcon(petImageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

			// petProfileLabel을 ImageIcon을 이용한 JLabel로 변경
			JLabel petProfileLabel = new JLabel(petImageIcon); // ImageIcon을 JLabel로 설정
			petProfileLabel.setBounds(330, 120, 40, 40); // 위치 및 크기 설정

			// 마우스 클릭 이벤트 처리
			petProfileLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose(); // 현재 화면 닫기
					new UpdateUserScreen(UserHomeScreen.this);
				}
			});

			// JLabel을 화면에 추가
			add(petProfileLabel);

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 발생 시 에러 메시지 출력
		}

		// 🔹 유저 프로필 이미지 - DB에서 가져온 이미지를 사용
		String userImageUrl = bean.getUser_image(); // DB에서 가져온 이미지 URL
		// 이미지 URL을 통해 ImageIcon 객체 생성
		try {
			// URL을 사용하여 이미지를 로드하고 ImageIcon 생성
			ImageIcon petImageIcon = new ImageIcon(new URL(userImageUrl)); // petImageUrl에서 이미지 로드

			// 이미지 크기 조정 (150x150으로 크기 변경)
			petImageIcon = new ImageIcon(petImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));

			// petProfileLabel을 ImageIcon을 이용한 JLabel로 변경
			JLabel petProfileLabel = new JLabel(petImageIcon); // ImageIcon을 JLabel로 설정
			petProfileLabel.setBounds(101, 178, 200, 200); // 위치 및 크기 설정

			// 마우스 클릭 이벤트 처리
			petProfileLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose(); // 현재 화면 닫기
					new UpdateUserScreen(UserHomeScreen.this);
				}
			});

			// JLabel을 화면에 추가
			add(petProfileLabel);

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 발생 시 에러 메시지 출력
		}

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
		addButtonLabel.setBounds(280, 730, 70, 70);
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
		logoutButton = new RoundedButton("로그아웃");
		logoutButton.setBounds(30, 122, 85, 36);
		logoutButton.setBackground(new Color(91, 91, 91));
		logoutButton.setForeground(Color.WHITE);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen();
			}
		});
		add(logoutButton);

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
		closeButton.addActionListener(e -> System.exit(0));
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
