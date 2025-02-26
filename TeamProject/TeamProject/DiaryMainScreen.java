package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

public class DiaryMainScreen extends JFrame {
	// 추가 중

	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, photoLabel, homeLabel, commuLabel, voteLabel, imageProfileLabel, menuLabel,
			addDiaryLabel, newLineUpLabel, oldLineUpLabel, logoLabel;
	private JPanel diaryPanel; // 다이어리 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private DiaryAddDialog pc;
	TPMgr mgr = new TPMgr();

	Vector<DiaryBean> vlist;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd   HH:mm");

	public DiaryMainScreen() {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vlist = mgr.showDiary(StaticData.pet_id);
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
					new AlarmMainScreen(DiaryMainScreen.this);
				} else if (source == profileLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(DiaryMainScreen.this);
				} else if (source == photoLabel) {
					System.out.println("앨범 & 일기 버튼 클릭됨");
					setEnabled(false);
					new AlbumChooseDialog(DiaryMainScreen.this);
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
				} else if (source == menuLabel) {
					System.out.println("메뉴 버튼 클릭됨!");
					if (addDiaryLabel.isVisible()) {
						addDiaryLabel.setVisible(false);
						newLineUpLabel.setVisible(false);
						oldLineUpLabel.setVisible(false);
					} else {
						addDiaryLabel.setVisible(true);
						newLineUpLabel.setVisible(true);
						oldLineUpLabel.setVisible(true);
					}
				} else if (source == addDiaryLabel) {
					System.out.println("일기 추가 버튼 클릭됨");
					if (pc == null) {
						pc = new DiaryAddDialog(DiaryMainScreen.this);
						// ZipcodeFrame의 창의 위치를 MemberAWT 옆에 지정
						pc.setLocation(getX() + 25, getY() + 270);
					} else {
						pc.setLocation(getX() + 25, getY() + 270);
						pc.setVisible(true);
					}
					setEnabled(false);
					addDiaryLabel.setVisible(false);
					newLineUpLabel.setVisible(false);
					oldLineUpLabel.setVisible(false);
				} else if (source == newLineUpLabel) {
					System.out.println("최신순 버튼 클릭됨");
					vlist = mgr.newDiary(StaticData.pet_id);
					addDiary();
					addDiaryLabel.setVisible(false);
					newLineUpLabel.setVisible(false);
					oldLineUpLabel.setVisible(false);
				} else if (source == oldLineUpLabel) {
					System.out.println("오래된순 버튼 클릭됨");
					vlist = mgr.oldDiary(StaticData.pet_id);
					addDiary();
					addDiaryLabel.setVisible(false);
					newLineUpLabel.setVisible(false);
					oldLineUpLabel.setVisible(false);
				}
			}
		};

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		if(mgr.nonReadMsg(StaticData.user_id))
			alarmLabel = createScaledImageLabel("TeamProject/alarm_in.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);
		
		// 로고 아이콘
		logoLabel = createScaledImageLabel("TeamProject/logo2.png", 180, 165);
		logoLabel.setBounds(5, 54, 180, 165);
		logoLabel.setVisible(true);
		add(logoLabel);

		// 상단 프로필 아이디
		byte[] imgBytes = bean.getUser_image();
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
					new UpdateUserScreen(DiaryMainScreen.this);
				}
			});
			add(roundedProfileImageLabel);
		}

		// 🔹 앨범 & 일기 버튼
		photoLabel = createScaledImageLabel("TeamProject/photo_click.png", 60, 60);
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

		// 🔹 일기 추가 버튼
		addDiaryLabel = createScaledImageLabel("TeamProject/diary.png", 40, 40);
		addDiaryLabel.setBounds(308, 670, 40, 40);
		addDiaryLabel.addMouseListener(commonMouseListener);
		add(addDiaryLabel);
		addDiaryLabel.setVisible(false);

		// 🔹 최신순 정렬
		newLineUpLabel = createScaledImageLabel("TeamProject/new.png", 40, 40);
		newLineUpLabel.setBounds(308, 610, 40, 40);
		newLineUpLabel.addMouseListener(commonMouseListener);
		add(newLineUpLabel);
		newLineUpLabel.setVisible(false);

		// 🔹 오래된순 정렬
		oldLineUpLabel = createScaledImageLabel("TeamProject/old.png", 40, 40);
		oldLineUpLabel.setBounds(308, 550, 40, 40);
		oldLineUpLabel.addMouseListener(commonMouseListener);
		add(oldLineUpLabel);
		oldLineUpLabel.setVisible(false);

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
				g.drawLine(22, 164, 379, 164);
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

		// 🔹 스크롤 가능한 일기 패널 설정
		diaryPanel = new JPanel();
		diaryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // 아이템이 정렬되도록 설정
		diaryPanel.setBackground(Color.WHITE);
		diaryPanel.setBorder(new LineBorder(Color.WHITE, 1));

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		scrollPane = new JScrollPane(diaryPanel);
		scrollPane.setBounds(23, 165, 357, 615);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		scrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));
		panel.add(scrollPane);

		// 🔹 추가 버튼 (화면에 고정)
		menuLabel = createScaledImageLabel("TeamProject/diary_menu.png", 85, 45);
		menuLabel.setBounds(280, 725, 85, 45);
		menuLabel.addMouseListener(commonMouseListener);
		menuLabel.setOpaque(true);
		menuLabel.setBackground(new Color(255, 255, 255, 0));
		menuLabel.setVisible(true);
		getLayeredPane().add(menuLabel, JLayeredPane.PALETTE_LAYER);

		addDiary();

		// 🔹 닫기 버튼
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
	 * 일기 추가 메서드
	 */
	public void addDiary() {
		diaryPanel.removeAll();
		for (DiaryBean db : vlist) {
			// 날짜 라벨 생성
			JLabel diaryDateLabel = new JLabel("날짜: " + sdf.format(db.getDiary_date()));
			diaryDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
			diaryDateLabel.setPreferredSize(new Dimension(160, 20)); // 크기 고정
			diaryDateLabel.setOpaque(true);
			diaryDateLabel.setBackground(Color.white);

			// 내용 입력된 창 설정
			JLabel diaryTitle = new JLabel(db.getDiary_name());
			diaryTitle.setPreferredSize(new Dimension(60, 30)); // 크기 고정
//			JTextArea diaryContentArea = new JTextArea(db.getDiary_name());
//			diaryContentArea.setPreferredSize(new Dimension(160, 75)); // 크기 고정
//			diaryContentArea.setOpaque(true); // 배경을 흰색으로 설정하려면 true
//			diaryContentArea.setBackground(Color.WHITE); // 배경을 흰색으로 설정

			// 텍스트 영역에서 줄바꿈 허용 및 단어 단위로 줄바꿈
//			diaryContentArea.setLineWrap(true);
//			diaryContentArea.setWrapStyleWord(true);

			// 텍스트 길이 제한 및 "..." 추가
//			int maxLength = 50; // 최대 문자 길이 설정 -> 50으로 해서 50이상이 되면 ...이 되게 함
//			String text = diaryContentArea.getText();
//			if (text.length() > maxLength) {
//				text = text.substring(0, maxLength) + "...";
//				diaryContentArea.setText(text);
//			}

			// 일기 날짜와 내용을 하나의 패널로 묶기
			JPanel diaryWithContentPanel = new JPanel();

			diaryWithContentPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setEnabled(false);
					new DiaryResultDialog(db, DiaryMainScreen.this);
				}
			});

			// 일기 날짜 + 일기 내용 패널 (albumWithTagPanel) 설정
			diaryWithContentPanel.setBackground(Color.WHITE); // 패널 배경을 흰색으로 설정

			diaryWithContentPanel.setLayout(new BoxLayout(diaryWithContentPanel, BoxLayout.Y_AXIS)); // 세로로 배치
			diaryWithContentPanel.add(diaryDateLabel);
			diaryWithContentPanel.add(diaryTitle);
//			diaryWithContentPanel.add(diaryContentArea);

			// 일기 날짜 + 일기 내용 패널 크기 고정
			diaryWithContentPanel.setPreferredSize(new Dimension(176, 100)); // 일기 날짜와 내용 합친 크기

			// 테두리 추가
			diaryWithContentPanel.setBorder(new LineBorder(Color.lightGray, 1)); // 회색 1픽셀 두께의 테두리 추가

			// FlowLayout 사용하여 여백 없애기
			diaryWithContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 1)); // 수평 간격과 수직 간격을 0으로 설정

			diaryPanel.add(diaryWithContentPanel);

			// 패널 크기 갱신 (일기 개수에 따라 스크롤 가능하도록 조정)
			int rows = (diaryPanel.getComponentCount() + 1) / 2;
			diaryPanel.setPreferredSize(new Dimension(338, rows * 100)); // 크기 유지

			// 패널 업데이트
			diaryPanel.revalidate();
			diaryPanel.repaint();

			// 🔹 스크롤 패널의 크기를 동적으로 맞추기
			scrollPane.revalidate();
		}

		diaryPanel.revalidate();
		diaryPanel.repaint();
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
