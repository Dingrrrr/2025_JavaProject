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
					System.out.println("쪽지 보내기 버튼 클릭됨");
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
		for (int i = 1; i <= 6; i++) {
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
	    // alarmPanel의 레이아웃을 FlowLayout으로 설정하여 항목들이 수직으로 정렬되게 함
	    alarmPanel.setLayout(new BoxLayout(alarmPanel, BoxLayout.Y_AXIS)); // 수직 정렬

	    // 알람 항목 패널
	    JPanel alarmItemPanel = new JPanel();
	    alarmItemPanel.setPreferredSize(new Dimension(353, 120));
	    alarmItemPanel.setMaximumSize(new Dimension(353, 120));
	    alarmItemPanel.setBackground(Color.WHITE);
	    alarmItemPanel.setBorder(new LineBorder(Color.black, 1));
	    alarmItemPanel.setLayout(new BorderLayout(10, 10)); // 여백 포함 레이아웃

	    // 1) 상단 영역: USER_ID, 날짜
	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.setBackground(Color.WHITE);
	    topPanel.setPreferredSize(new Dimension(353, 25)); // 상단 패널 높이 증가

	    JLabel userIdLabel = new JLabel("User_Id");
	    userIdLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5)); // 위/아래 여백 추가

	    JLabel dateLabel = new JLabel("20XX.XX.XX", SwingConstants.RIGHT);
	    dateLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5)); // 위/아래 여백 추가

	    topPanel.add(userIdLabel, BorderLayout.WEST);
	    topPanel.add(dateLabel, BorderLayout.EAST);

	    // 구분선
	    JSeparator separator = new JSeparator();
	    separator.setForeground(Color.GRAY);

	    // 2) 본문 패널 (이미지 + 텍스트)
	    JPanel contentPanel = new JPanel();
	    contentPanel.setLayout(new BorderLayout()); // BorderLayout 사용하여 상단 정렬 가능
	    contentPanel.setBackground(Color.WHITE);
	    contentPanel.setPreferredSize(new Dimension(353, 70)); // 본문 영역 크기 설정

	 // 제목 & 내용
	    JPanel textPanel = new JPanel();
	    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); // 세로 배치
	    textPanel.setBackground(Color.WHITE);
	    textPanel.setAlignmentY(Component.TOP_ALIGNMENT); // 상단 정렬 유지
	    textPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // 왼쪽 여백 추가

	    // 제목
	    JLabel titleLabel = new JLabel("제목");
	    titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 왼쪽 정렬

	    // 내용
	    JLabel contentLabel = new JLabel("내용");
	    contentLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 왼쪽 정렬

	    // 제목과 내용 사이 간격 추가
	    textPanel.add(titleLabel);
	    textPanel.add(Box.createVerticalStrut(5)); // 제목과 내용 사이 여백
	    textPanel.add(contentLabel);

	    // textPanel을 contentPanel의 상단에 배치
	    contentPanel.add(textPanel, BorderLayout.NORTH);


	    // 알람 항목을 패널에 추가
	    alarmItemPanel.add(topPanel, BorderLayout.NORTH);
	    alarmItemPanel.add(separator, BorderLayout.CENTER);
	    alarmItemPanel.add(contentPanel, BorderLayout.SOUTH);

	    // 알람 패널에 추가
	    alarmPanel.add(alarmItemPanel);
	    alarmPanel.add(Box.createVerticalStrut(5)); // 알람 항목 간 간격 추가
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
