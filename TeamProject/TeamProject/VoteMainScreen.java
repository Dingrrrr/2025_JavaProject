package TeamProject;

import javax.imageio.ImageIO;
import javax.sql.CommonDataSource;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class VoteMainScreen extends JFrame {
	private BufferedImage image;
	private ImageIcon image2;
	private JLabel alarmLabel, profileLabel, voteLabel, addButtonLabel, photoLabel, homeLabel, commuLabel;
	private JPanel votePanel; // 투표 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private VoteAddDialog va;

	public VoteMainScreen() {
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
				} else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨!");
				} else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
				} else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
				} else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
				} else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
				} else if (source == addButtonLabel) {
					System.out.println("투표 추가 버튼 클릭됨!");
					if(va==null) {
						va = new VoteAddDialog();
						va.setLocation(getX()+25, getY()+300);
					}else {
						va.setLocation(getX()+25, getY()+300);
						va.setVisible(true);
					}
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

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 🔹 추가 버튼 (화면에 고정)
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
		addButtonLabel.setBounds(300, 700, 70, 70);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setOpaque(true);
		addButtonLabel.setBackground(new Color(255, 255, 255, 0));
		addButtonLabel.setVisible(true);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 votePanel 설정 수정
		votePanel = new JPanel();
		votePanel.setLayout(new GridLayout(0, 2, 2, 2)); // 2열 정렬
		votePanel.setBackground(Color.WHITE);

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		// 이전 코드에서는 scrollPane이 이 부분 앞에 있을 수 있어, 여기에 잘못된 위치에서 접근되고 있었을 가능성이 있습니다.
		scrollPane = new JScrollPane(votePanel);
		scrollPane.setBounds(23, 165, 357, 615);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane);

		// 🔹 더미 투표 데이터 추가
		for (int i = 1; i <= 15; i++) {
			addVote();
		}

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
	 * 투표 추가 메서드
	 */
	/**
	 * 투표 추가 메서드
	 */
	private void addVote() {
		// 1️⃣ 개별 투표 아이템을 담을 패널 생성
		JPanel contentPanel = new JPanel(null); // 직접 위치 설정을 위해 null 레이아웃 사용
		contentPanel.setPreferredSize(new Dimension(176, 150)); // 크기 설정
		contentPanel.setBackground(Color.WHITE);

		// 2️⃣ 이미지 라벨 추가 (배경 역할)
		JLabel imageLabel = new JLabel("투표용 이미지");
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setBounds(0, 0, 176, 150); // 패널 전체 크기 설정
		imageLabel.setOpaque(true);
		imageLabel.setBackground(Color.white);

		// contentPanel의 아랫부분에만 검정색 테두리 추가
		Border blackBottomBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
		imageLabel.setBorder(blackBottomBorder);

		if (image2 != null) {
			imageLabel.setIcon(image2);
		}

		// 🔹 `JLayeredPane`을 사용해 이미지 위에 하트 버튼을 배치
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 176, 150); // 전체 크기 맞춤

		// 🔹 이미지 추가 (기본 레이어)
		layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

		// 3️⃣ 투표 버튼 (하트 아이콘) 추가 → 이미지 내부의 오른쪽 아래에 배치
		JLabel voteLabel = createScaledImageLabel("TeamProject/vote.png", 40, 40);
		voteLabel.setBounds(130, 105, 40, 40); // 💡 오른쪽 아래로 이동
		voteLabel.setOpaque(false);
		voteLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("투표 버튼이 클릭됨!");
			}
		});

		// 🔹 하트 버튼을 이미지 위에 추가 (위쪽 레이어)
		layeredPane.add(voteLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 contentPanel에 `layeredPane` 추가 (이미지 & 버튼 함께 추가됨)
		contentPanel.add(layeredPane);

		// 4️⃣ 전체 투표 목록 패널 (votePanel)에 추가
		votePanel.add(contentPanel);
		votePanel.revalidate();
		votePanel.repaint();
		scrollPane.revalidate();
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
		new VoteMainScreen();
	}
}
