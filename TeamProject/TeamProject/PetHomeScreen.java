package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class PetHomeScreen extends JFrame {
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, petProfileLabel, addButtonLabel;
	private JLabel petNameLabel, petSpecLabel, petBirthLabel, petGenderLabel;
	private JLabel petRcDateLabel, petRcWHLabel, petRecordLabel, petRcVcLabel, petRcCheckLabel, petRcTimeLabel;
	private JLabel photoLabel, homeLabel, commuLabel, voteLabel;

	public PetHomeScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
				} else if (source == addButtonLabel) {
					System.out.println("➕ 추가 버튼 클릭됨!");
				}else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
				}else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
				}else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
				}else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

		// 🔹 상단 프로필 아이콘
		profileLabel = createScaledImageLabel("TeamProject/profile.png", 40, 40);
		profileLabel.setBounds(330, 120, 40, 40);
		profileLabel.addMouseListener(commonMouseListener);
		add(profileLabel);

		// 🔹 반려동물 프로필 이미지
		petProfileLabel = createScaledImageLabel("TeamProject/profile.png", 150, 150);
		petProfileLabel.setBounds(40, 190, 150, 150);
		add(petProfileLabel);

		// 🔹 반려동물 이름 라벨
		petNameLabel = new JLabel("이름: OO");
		petNameLabel.setBounds(230, 210, 150, 27); // (x, y, 너비, 높이)
		petNameLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petNameLabel);

		// 🔹 반려동물 종 라벨
		petSpecLabel = new JLabel("종: 골든 리트리버");
		petSpecLabel.setBounds(230, 240, 150, 27); // (x, y, 너비, 높이)
		petSpecLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petSpecLabel);

		// 🔹 반려동물 생년월일 라벨
		petBirthLabel = new JLabel("생년월일: 20XX.XX.XX");
		petBirthLabel.setBounds(230, 270, 150, 27); // (x, y, 너비, 높이)
		petBirthLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petBirthLabel);

		// 🔹 반려동물 성별 라벨
		petGenderLabel = new JLabel("성별: 남");
		petGenderLabel.setBounds(230, 300, 150, 27); // (x, y, 너비, 높이)
		petGenderLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petGenderLabel);

		// 🔹 반려동물 진료기록 작성일 라벨
		petRcDateLabel = new JLabel("20xx.xx.xx");
		petRcDateLabel.setBounds(40, 385, 300, 27); // (x, y, 너비, 높이)
		petRcDateLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petRcDateLabel);

		// 🔹 반려동물 키 / 몸무게 라벨
		petRcWHLabel = new JLabel("키: 000cm 몸무게: 00kg");
		petRcWHLabel.setBounds(40, 420, 300, 27); // (x, y, 너비, 높이)
		petRcWHLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petRcWHLabel);

		// 🔹 반려동물 진료 기록 설명 라벨
		petRecordLabel = new JLabel("진료 기록: ");
		petRecordLabel.setBounds(40, 455, 300, 27); // (x, y, 너비, 높이)
		petRecordLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petRecordLabel);

		// 🔹 반려동물 예방접종 상태 라벨
		petRcVcLabel = new JLabel("체크해야 할 정보: ");
		petRcVcLabel.setBounds(40, 490, 300, 27); // (x, y, 너비, 높이)
		petRcVcLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petRcVcLabel);

		// 🔹 반려동물 체크해야 할 정보 라벨
		petRcCheckLabel = new JLabel("진료 관련 시간: ");
		petRcCheckLabel.setBounds(40, 525, 300, 27); // (x, y, 너비, 높이)
		petRcCheckLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petRcCheckLabel);

		// 🔹 반려동물 진료 관련 시간 라벨
		petRcTimeLabel = new JLabel("성별: 남");
		petRcTimeLabel.setBounds(230, 300, 150, 27); // (x, y, 너비, 높이)
		petRcTimeLabel.setForeground(Color.BLACK); // 텍스트 색 설정
		add(petRcTimeLabel);

		// 🔹 추가 버튼
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 92, 92);
		addButtonLabel.setBounds(155, 604, 92, 92);
		addButtonLabel.addMouseListener(commonMouseListener);
		add(addButtonLabel);

		// 🔹 앨범 & 일기 버튼
		photoLabel = createScaledImageLabel("TeamProject/photo.png", 60, 60);
		photoLabel.setBounds(37, 785, 60, 60);
		photoLabel.addMouseListener(commonMouseListener);
		add(photoLabel);

		// 🔹 홈 버튼
		homeLabel = createScaledImageLabel("TeamProject/home.png", 58, 58);
		homeLabel.setBounds(125, 787, 58, 58);
		homeLabel.addMouseListener(commonMouseListener);
		add(homeLabel);

		// 🔹 커뮤니티 버튼
		commuLabel = createScaledImageLabel("TeamProject/commu.png", 58, 58);
		commuLabel.setBounds(215, 788, 58, 58);
		commuLabel.addMouseListener(commonMouseListener);
		add(commuLabel);

		// 🔹 투표 버튼
		voteLabel = createScaledImageLabel("TeamProject/vote.png", 55, 55);
		voteLabel.setBounds(305, 789, 55, 55);
		voteLabel.addMouseListener(commonMouseListener);
		add(voteLabel);

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
				g.drawLine(22, 370, 379, 370);
				g.drawLine(22, 574, 379, 574);
				g.drawLine(22, 780, 379, 780);
				g.drawLine(111, 780, 111, 851);
				g.drawLine(200, 780, 200, 851);
				g.drawLine(289, 780, 289, 851);
				
				Graphics2D g2 = (Graphics2D) g; // Graphics를 Graphics2D로 캐스팅
				g2.setColor(Color.black);
				g2.setStroke(new BasicStroke(5)); // 선 두께 5px 설정
				g2.drawLine(135, 841, 262, 841); // (x1, y1) -> (x2, y2)까지 선 그리기
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
		new PetHomeScreen();
	}
}
