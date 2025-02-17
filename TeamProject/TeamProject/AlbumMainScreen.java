package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;


public class AlbumMainScreen extends JFrame {
	
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, addButtonLabel, photoLabel, homeLabel, commuLabel, voteLabel;
	private JPanel albumPanel; // 앨범 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private AlbumAddDialog pc;
	TPMgr mgr = new TPMgr();

	Vector<AlbumBean> vlist = mgr.showAlbum(StaticData.pet_id);

	public AlbumMainScreen() {
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
					dispose();
					new AlarmMainScreen(AlbumMainScreen.this);
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(AlbumMainScreen.this);
				} else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
					setEnabled(false);
					new AlbumChooseDialog(AlbumMainScreen.this);
				} else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
					dispose();
					new PetHomeScreen(StaticData.pet_id);
				} else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
					dispose();
					new CommuMainScreen();
				} else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
					dispose();
					new VoteMainScreen();
				} else if (source == addButtonLabel) {
					System.out.println("➕ 추가 버튼 클릭됨!");
					if(pc==null) {
						pc = new AlbumAddDialog(AlbumMainScreen.this);
						//ZipcodeFrame의 창의 위치를 MemberAWT 옆에 지정
						pc.setLocation(getX()+25, getY()+150);
					}else {
						pc.setLocation(getX()+25, getY()+150);
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

		// 🔹 스크롤 가능한 앨범 패널 설정
		albumPanel = new JPanel();
		albumPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 아이템이 정렬되도록 설정
		albumPanel.setBackground(Color.WHITE);

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		scrollPane = new JScrollPane(albumPanel);
		scrollPane.setBounds(23, 165, 357, 615);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane);
		
		addAlbum();
		

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
	
	public void addAlbum() {
		for (AlbumBean ab : vlist) {
			/**
			 * 앨범 추가
			 */
			StaticData.album_id = ab.getAlbum_id();
			// 앨범 레이블 생성
			JLabel albumLabel = new JLabel("📸 앨범 " + (albumPanel.getComponentCount() + 1));
			albumLabel.setHorizontalAlignment(SwingConstants.CENTER);
			albumLabel.setOpaque(true);
			albumLabel.setBackground(Color.white);
			albumLabel.setPreferredSize(new Dimension(173, 100)); // 크기 고정
			albumLabel.setMaximumSize(new Dimension(173, 100)); // 크기 고정

			// 검정색 외각선 추가
			Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
			albumLabel.setBorder(blackBorder);

			// 태그 레이블 생성
			JLabel tagLabel = new JLabel("태그");
			tagLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tagLabel.setPreferredSize(new Dimension(165, 20)); // 크기 고정
			tagLabel.setOpaque(true);
			tagLabel.setBackground(Color.white);

			// 태그 입력창 (JTextArea) 설정
			JLabel tagText = new JLabel(ab.getAlbum_tags());
			tagText.setPreferredSize(new Dimension(160, 20)); // 크기 고정
			tagText.setOpaque(true); // 배경을 흰색으로 설정하려면 true
			tagText.setBackground(Color.WHITE); // 배경을 흰색으로 설정

			// 앨범과 태그를 하나의 패널로 묶기
			JPanel albumWithTagPanel = new JPanel();
			
			albumWithTagPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setEnabled(false);
					new AlbumResultDialog(ab, AlbumMainScreen.this);	//매개변수로 ab 넣어야함 -> albumResultDialog 수정되면 수정
				}
			});
			
			// 앨범 + 태그 패널 (albumWithTagPanel) 설정
			albumWithTagPanel.setBackground(Color.WHITE); // 패널 배경을 흰색으로 설정

			albumWithTagPanel.setLayout(new BoxLayout(albumWithTagPanel, BoxLayout.Y_AXIS)); // 세로로 배치
			albumWithTagPanel.add(albumLabel);
			albumWithTagPanel.add(tagLabel);
			albumWithTagPanel.add(tagText);

			// 앨범 + 태그 패널 크기 고정
			albumWithTagPanel.setPreferredSize(new Dimension(176, 140)); // 앨범과 태그 합친 크기

			// 테두리 추가
			albumWithTagPanel.setBorder(new LineBorder(Color.lightGray, 1)); // 회색 1픽셀 두께의 테두리 추가

			// FlowLayout 사용하여 여백 없애기
			albumPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1)); // 수평 간격과 수직 간격을 0으로 설정

			albumPanel.add(albumWithTagPanel);

			// 패널 크기 갱신 (앨범 개수에 따라 스크롤 가능하도록 조정)
			int rows = (albumPanel.getComponentCount() + 1) / 2;
			albumPanel.setPreferredSize(new Dimension(338, rows * 141)); // 크기 유지

			// 패널 업데이트
			albumPanel.revalidate();
			albumPanel.repaint();

			// 🔹 스크롤 패널의 크기를 동적으로 맞추기
			scrollPane.revalidate();
		}
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
	}
}
