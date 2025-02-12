package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class AlbumMainScreen2 extends JFrame {
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, addButtonLabel, photoLabel, homeLabel, commuLabel, voteLabel;
	private JPanel albumPanel; // 앨범 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private AlbumAddDialog pc;

	public AlbumMainScreen2() {
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
					if (pc == null) {
						pc = new AlbumAddDialog();
						pc.setLocation(getX() + 25, getY() + 150);
					} else {
						pc.setLocation(getX() + 25, getY() + 150);
						pc.setVisible(true);
					}
				} else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
				} else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
				} else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
				} else if (source == voteLabel) {
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

		// ✅ 추가 버튼을 JFrame에 직접 추가하고 최상위 레이어에 배치
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 80, 80);
		addButtonLabel.setBounds(290, 685, 80, 80);
		addButtonLabel.addMouseListener(commonMouseListener);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER); // 스크롤 영향 안 받음

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

		// 🔹 앨범 패널 설정 (BoxLayout + FlowLayout 조합)
		albumPanel = new JPanel();
		albumPanel.setLayout(new BoxLayout(albumPanel, BoxLayout.Y_AXIS)); // 세로로 정렬
		albumPanel.setBackground(Color.WHITE);

		// 🔹 각 행(한 줄에 2개씩)을 위한 패널
		JPanel rowPanel = null;

		// 🔹 스크롤 패널 추가
		scrollPane = new JScrollPane(albumPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(22, 165, 357, 615);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // 스크롤바 숨기기
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 설정

		// ✅ 앨범 라벨 중앙 정렬
		for (int i = 1; i <= 12; i++) {
		    if (i % 2 == 1) { // 2개씩 한 줄
		        rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		        rowPanel.setBackground(Color.WHITE);
		        albumPanel.add(rowPanel);
		    }
		    JLabel albumLabel = new JLabel("앨범 " + i, SwingConstants.CENTER);
		    albumLabel.setPreferredSize(new Dimension(150, 120));
		    albumLabel.setOpaque(true);
		    albumLabel.setBackground(Color.LIGHT_GRAY);
		    albumLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 🔹 중앙 정렬
		    rowPanel.add(albumLabel);
		    JLabel tagLabel = new JLabel("태그: 00, 00", SwingConstants.CENTER);
		    tagLabel.setPreferredSize(new Dimension(150, 30));  // 태그 라벨 크기 변경
		    tagLabel.setOpaque(true);
		    rowPanel.add(tagLabel);
		}

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
				g.drawLine(22, 780, 379, 780);
				g.drawLine(111, 780, 111, 851);
				g.drawLine(200, 780, 200, 851);
				g.drawLine(289, 780, 289, 851);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.black);
				g2.setStroke(new BasicStroke(5));
				g2.drawLine(135, 841, 262, 841);
			}
		};

		panel.add(scrollPane);
		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

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
	 * 앨범 추가 메서드
	 */
	private void addAlbum() {
		JLabel albumLabel = new JLabel("앨범 " + (albumPanel.getComponentCount() + 1));
		albumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		albumLabel.setOpaque(true);
		albumLabel.setBackground(Color.LIGHT_GRAY);
		albumLabel.setPreferredSize(new Dimension(150, 100));
		albumPanel.add(albumLabel);

		// 패널 업데이트
		albumPanel.revalidate();
		albumPanel.repaint();
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
		new AlbumMainScreen2();
	}
}
