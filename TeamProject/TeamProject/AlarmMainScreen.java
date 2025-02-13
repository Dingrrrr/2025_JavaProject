package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class AlarmMainScreen extends JFrame {
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, backLabel;
	private JPanel alarmPanel; // 알람 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private JButton SendButton;

	public AlarmMainScreen() {
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
				} else if (source == backLabel) {
					System.out.println("뒤로가기 버튼 클릭됨");
				} else if (source == SendButton) {
					System.out.println("알림 추가 버튼 클릭됨");
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

		// 🔹 상단 뒤로가기 아이콘
		backLabel = createScaledImageLabel("TeamProject/back_button.png", 40, 40);
		backLabel.setBounds(25, 120, 40, 40);
		backLabel.addMouseListener(commonMouseListener);
		add(backLabel);

		// 🔹 쪽지 보내기 버튼 (화면에 고정)
		SendButton = new RoundedButton("쪽지 보내기");
		SendButton.setBounds(140, 792, 120, 40);
		SendButton.setBackground(new Color(91, 91, 91));
		SendButton.setForeground(Color.WHITE);
		SendButton.addMouseListener(commonMouseListener);
		add(SendButton);

		// 🔹 배경 패널
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					Image scaledImage = image.getScaledInstance(402, 874, Image.SCALE_SMOOTH);
					g.drawImage(scaledImage, 0, 0, this);
				}
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(22, 165, 379, 165);
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 🔹 스크롤 가능한 알람 패널 설정
		alarmPanel = new JPanel();
		alarmPanel.setLayout(new BoxLayout(alarmPanel, BoxLayout.Y_AXIS)); // 세로로 쌓이게 설정
		alarmPanel.setBackground(Color.WHITE);

		// 🔹 더미 알람 데이터 추가
		for (int i = 1; i <= 15; i++) {
			addAlarm();
		}

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		scrollPane = new JScrollPane(alarmPanel);
		scrollPane.setBounds(23, 165, 357, 620);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane);

		// 🔹 닫기 버튼
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
	 * 알림 추가 메서드
	 */
	private void addAlarm() {
		// 알람 하나를 담을 패널
		JPanel alarmItemPanel = new JPanel(null); // 자유 배치(null 레이아웃)
		alarmItemPanel.setPreferredSize(new Dimension(353, 120)); // 예시로 높이 120
		alarmItemPanel.setBackground(Color.WHITE);
		alarmItemPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1)); // 바깥 테두리

		// 1) 상단 영역: USER_ID, 날짜
		JLabel userIdLabel = new JLabel("User_ID");
		userIdLabel.setBounds(10, 5, 100, 20);
		alarmItemPanel.add(userIdLabel);

		JLabel dateLabel = new JLabel("20XX.XX.XX");
		dateLabel.setBounds(280, 5, 100, 20);
		// (가로폭 353 - 73 = 280 위치에 날짜를 놓는 예시)
		alarmItemPanel.add(dateLabel);

		// 구분선(가로줄) : USER_ID와 제목 사이
		JSeparator sep1 = new JSeparator();
		sep1.setBounds(0, 30, 353, 1); // 크기와 위치를 명확히 설정
		sep1.setPreferredSize(new Dimension(353, 1)); // 크기 설정
		sep1.setForeground(Color.GRAY); // 선 색상 설정
		sep1.setOpaque(true); // 배경을 설정해 주면 보이게 될 수 있음
		alarmItemPanel.add(sep1);

		// 2) 제목
		JLabel titleLabel = new JLabel("제목");
		titleLabel.setBounds(10, 40, 333, 20);
		alarmItemPanel.add(titleLabel);

		// 3) 내용
		JLabel contentLabel = new JLabel("내용");
		// 혹은 JTextArea를 써도 됨
		contentLabel.setBounds(10, 70, 333, 20);
		alarmItemPanel.add(contentLabel);

		alarmPanel.add(alarmItemPanel);

		// 각 알람 항목 간에 간격을 둔다
		alarmPanel.add(Box.createVerticalStrut(5)); // 10px 간격
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
		new AlarmMainScreen();
	}
}
