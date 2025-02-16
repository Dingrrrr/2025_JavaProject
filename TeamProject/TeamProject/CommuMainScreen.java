 package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class CommuMainScreen extends JFrame {
	// 추가중
	
	private BufferedImage image;
	private ImageIcon image2;
	private JLabel alarmLabel, profileLabel, addButtonLabel, photoLabel, homeLabel, commuLabel, voteLabel;
	private JPanel commuPanel; // 커뮤니티 게시글 패널
	private JScrollPane scrollPane; // 스크롤 패널
	Vector<ComuBean> vlist;
	TPMgr mgr = new TPMgr();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd   HH:mm");

	public CommuMainScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vlist = mgr.showComu();
		
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
					new AlarmMainScreen(CommuMainScreen.this);
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(CommuMainScreen.this);
				} else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
					setEnabled(false);
					new AlbumChooseDialog(CommuMainScreen.this);
				} else if (source == homeLabel) {
					System.out.println("홈 버튼 클릭됨");
					dispose();
					new PetAddMainScreen();
				} else if (source == commuLabel) {
					System.out.println("커뮤 버튼 클릭됨");
					dispose();
					new CommuMainScreen();
				} else if (source == voteLabel) {
					System.out.println("투표 버튼 클릭됨");
					dispose();
					new VoteMainScreen();
				} else if (source == addButtonLabel) {
					System.out.println("커뮤니티 게시글 추가 버튼 클릭됨");
					setEnabled(false);
					new CommuAddScreen(CommuMainScreen.this);
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

		// 🔹 스크롤 가능한 게시글 패널 설정
		commuPanel = new JPanel();
		commuPanel.setLayout(new BoxLayout(commuPanel, BoxLayout.Y_AXIS)); // 세로로 쌓이게 설정
		commuPanel.setBackground(Color.WHITE);

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		scrollPane = new JScrollPane(commuPanel);
		scrollPane.setBounds(23, 165, 357, 615);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		panel.add(scrollPane);

		// 🔹 추가 버튼 (화면에 고정)
		addButtonLabel = createScaledImageLabel("TeamProject/add_button.png", 70, 70);
		addButtonLabel.setBounds(300, 700, 70, 70);
		addButtonLabel.addMouseListener(commonMouseListener);
		addButtonLabel.setOpaque(true);
		addButtonLabel.setBackground(new Color(255, 255, 255, 0));
		addButtonLabel.setVisible(true);
		getLayeredPane().add(addButtonLabel, JLayeredPane.PALETTE_LAYER);
		
		addCommu();

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
	 * 커뮤니티 게시글 추가 메서드
	 */
	// 커뮤니티 게시글 추가
	private void addCommu() {
	    // commuPanel의 레이아웃을 FlowLayout으로 설정하여 항목들이 수직으로 정렬되게 합니다.
	    commuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5)); // 0px 간격, 항목 간 여백 5px

	    for (ComuBean cb : vlist) {
	        // 1) 전체 항목을 감싸는 패널
	        JPanel commuItemPanel = new JPanel();
	        commuItemPanel.setPreferredSize(new Dimension(353, 99)); // 크기 고정
	        commuItemPanel.setMaximumSize(new Dimension(353, 99)); // 최대 크기 고정
	        commuItemPanel.setBackground(Color.WHITE);
	        commuItemPanel.setBorder(new LineBorder(Color.black, 1)); // 외곽 테두리
	        commuItemPanel.setLayout(new BorderLayout(10, 10)); // 여백 포함

	        // 2) 상단 패널 (USER_ID + 날짜)
	        JPanel topPanel = new JPanel(new BorderLayout());
	        topPanel.setBackground(Color.WHITE);
	        topPanel.setPreferredSize(new Dimension(353, 20)); // 상단 패널 크기
	        topPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // 아래쪽만 테두리

	        JLabel userIdLabel = new JLabel(cb.getUser_id());
	        userIdLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0)); // 왼쪽에 여백

	        JLabel dateLabel = new JLabel(sdf.format(cb.getComu_date()), SwingConstants.RIGHT);
	        dateLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 3)); // 오른쪽에 여백
	        topPanel.add(userIdLabel, BorderLayout.WEST);
	        topPanel.add(dateLabel, BorderLayout.EAST);

	        // 3) 구분선
	        JSeparator separator = new JSeparator();
	        separator.setForeground(Color.GRAY);

	        // 4) 본문 패널 (이미지 + 텍스트)
	        JPanel contentPanel = new JPanel(new BorderLayout(10, 0));
	        contentPanel.setBackground(Color.WHITE);

	        // 왼쪽 - 이미지
	        JLabel imageLabel = new JLabel();
	        imageLabel.setPreferredSize(new Dimension(70, 70));
	        if (image2 != null) {
	            imageLabel.setIcon(image2);
	        } else {
	            imageLabel.setOpaque(true);
	            imageLabel.setBackground(Color.LIGHT_GRAY); // 이미지 없을 경우 기본 배경
	        }
	        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 3, 0)); // 위, 왼쪽, 아래 여백
	        contentPanel.add(imageLabel, BorderLayout.WEST);

	        // 오른쪽 - 제목 & 내용
	        JPanel textPanel = new JPanel();
	        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
	        textPanel.setBackground(Color.WHITE);

	        JLabel titleLabel = new JLabel(cb.getComu_title());
	        JLabel contentLabel = new JLabel(cb.getComu_content());

	        textPanel.add(titleLabel);
	        textPanel.add(Box.createVerticalStrut(10)); // 10px 간격
	        textPanel.add(contentLabel);

	        contentPanel.add(textPanel, BorderLayout.CENTER);

	        // 5) 전체 구성
	        commuItemPanel.add(topPanel, BorderLayout.NORTH);
	        commuItemPanel.add(separator, BorderLayout.CENTER);
	        commuItemPanel.add(contentPanel, BorderLayout.SOUTH);

	        // commuPanel에 추가
	        commuPanel.add(commuItemPanel);

	        // 각 커뮤니티 게시글 항목 간에 간격을 둡니다
	        commuPanel.add(Box.createVerticalStrut(5)); // 5px 간격
	    }

	    // commuPanel 크기 갱신
	    commuPanel.setPreferredSize(new Dimension(353, commuPanel.getComponentCount() * 99 + 5 * (commuPanel.getComponentCount() - 1))); // 항목 수에 맞게 크기 설정

	    // 스크롤 패널의 크기를 동적으로 맞추기
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
		new LoginScreen();
	}
}
