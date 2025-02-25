package TeamProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

public class AlbumMainScreen extends JFrame {

	private BufferedImage image;
	private JTextField tagField;
	private JLabel alarmLabel, profileLabel, menuLabel, photoLabel, homeLabel, commuLabel, voteLabel, imageProfileLabel,
			addAlbumLabel, newLineUpLabel, oldLineUpLabel;
	private JPanel albumPanel; // 앨범 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private AlbumAddDialog pc;
	TPMgr mgr = new TPMgr();
	boolean flag = true;

	Vector<AlbumBean> vlist = mgr.showAlbum(StaticData.pet_id);

	public AlbumMainScreen() {
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
					new AlarmMainScreen(AlbumMainScreen.this);
				} else if (source == imageProfileLabel) {
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
				} else if (source == menuLabel) {
					System.out.println("메뉴 버튼 클릭됨!");
					if (addAlbumLabel.isVisible()) {
						addAlbumLabel.setVisible(false);
						newLineUpLabel.setVisible(false);
						oldLineUpLabel.setVisible(false);
					} else {
						addAlbumLabel.setVisible(true);
						newLineUpLabel.setVisible(true);
						oldLineUpLabel.setVisible(true);
					}

				} else if (source == addAlbumLabel) {
					System.out.println("앨범 추가 버튼 클릭됨");
					if (pc == null) {
						pc = new AlbumAddDialog(AlbumMainScreen.this);
						// ZipcodeFrame의 창의 위치를 MemberAWT 옆에 지정
						pc.setLocation(getX() + 25, getY() + 150);
					} else {
						pc.setLocation(getX() + 25, getY() + 150);
						pc.setVisible(true);
					}
					setEnabled(false);
				} else if (source == newLineUpLabel) {
					System.out.println("최신순 정렬 클릭됨");
					vlist = mgr.showAlbum(StaticData.pet_id);
					addAlbum();
					addAlbumLabel.setVisible(false);
					newLineUpLabel.setVisible(false);
					oldLineUpLabel.setVisible(false);
				} else if (source == oldLineUpLabel) {
					System.out.println("오래된순 정렬 클릭됨");
					vlist = mgr.showOldAlbum(StaticData.pet_id);
					addAlbum();
					addAlbumLabel.setVisible(false);
					newLineUpLabel.setVisible(false);
					oldLineUpLabel.setVisible(false);
				}
			}
		};

		// 🔹 검색 텍스트 필드
		tagField = new JTextField();
		tagField.setOpaque(false);
		tagField.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(20), new EmptyBorder(10, 15, 10, 15) // 내부
																														// 여백
																														// (위,
																														// 왼쪽,
																														// 아래,
																														// 오른쪽)
		));
		tagField.setBounds(37, 120, 220, 40); // (x, y, 너비, 높이)
		tagField.setText("찾고 싶은 태그를 입력하세요");
		tagField.setForeground(Color.GRAY);
		tagField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (tagField.getText().isEmpty()) {
					tagField.setForeground(Color.GRAY);
					tagField.setText("찾고 싶은 태그를 입력하세요");
					flag = true;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tagField.getText().isEmpty() || flag) {
					tagField.setForeground(Color.GRAY);
					tagField.setText("찾고 싶은 태그를 입력하세요");
					flag = true;
				}
			}
		});

		tagField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (flag) {
					tagField.setText("");
					setForeground(Color.BLACK);
					flag = false;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (tagField.getText().isEmpty()) {
					tagField.setForeground(Color.GRAY);
					tagField.setText("찾고 싶은 태그를 입력하세요");
					flag = true;
				}
			}
		});
		tagField.addActionListener(new ActionListener() { // 태그 필드에 앤터키를 누르면 실행

			@Override
			public void actionPerformed(ActionEvent e) {
				if (flag) {
					vlist = mgr.showAlbum(StaticData.pet_id);
					addAlbum();
				} else {
					vlist = mgr.showAlbumByTags(StaticData.pet_id, tagField.getText().trim());
					addAlbum();
				}
			}
		});
		add(tagField);

		// 🔹 알람 아이콘
		alarmLabel = createScaledImageLabel("TeamProject/alarm.png", 40, 40);
		alarmLabel.setBounds(280, 120, 40, 40);
		alarmLabel.addMouseListener(commonMouseListener);
		add(alarmLabel);

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
					new UpdateUserScreen(AlbumMainScreen.this);
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
		addAlbumLabel = createScaledImageLabel("TeamProject/add_album.png", 40, 40);
		addAlbumLabel.setBounds(313, 670, 40, 40);
		addAlbumLabel.addMouseListener(commonMouseListener);
		add(addAlbumLabel);
		addAlbumLabel.setVisible(false);

		// 🔹 최신순 정렬
		newLineUpLabel = createScaledImageLabel("TeamProject/new.png", 40, 40);
		newLineUpLabel.setBounds(313, 610, 40, 40);
		newLineUpLabel.addMouseListener(commonMouseListener);
		add(newLineUpLabel);
		newLineUpLabel.setVisible(false);

		// 🔹 오래된순 정렬
		oldLineUpLabel = createScaledImageLabel("TeamProject/old.png", 40, 40);
		oldLineUpLabel.setBounds(313, 550, 40, 40);
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
		menuLabel = createScaledImageLabel("TeamProject/album_menu.png", 100, 100);
		menuLabel.setBounds(280, 690, 100, 100);
		menuLabel.addMouseListener(commonMouseListener);
		menuLabel.setOpaque(true);
		menuLabel.setBackground(new Color(255, 255, 255, 0));
		menuLabel.setVisible(true);
		getLayeredPane().add(menuLabel, JLayeredPane.PALETTE_LAYER);

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

	public void addAlbum() {
		albumPanel.removeAll();
		for (AlbumBean ab : vlist) {
			/**
			 * 앨범 추가
			 */
			StaticData.album_id = ab.getAlbum_id();

			// 앨범 레이블 생성
			byte[] imgBytes = ab.getAlbum_image();
			JLabel albumLabel = new JLabel(); // JLabel을 먼저 생성
			if (imgBytes == null || imgBytes.length == 0) {
				albumLabel = createScaledImageLabel("TeamProject/photo_frame.png", 173, 100);
				albumLabel.setPreferredSize(new Dimension(173, 100)); // 크기 고정
				albumLabel.setMaximumSize(new Dimension(173, 100)); // 크기 고정
			} else {
				ImageIcon icon1 = new ImageIcon(imgBytes);
				Image img1 = icon1.getImage();

				// 원본 이미지 크기
				int imgWidth = icon1.getIconWidth();
				int imgHeight = icon1.getIconHeight();

				// 타겟 크기 (173x100)
				int targetWidth = 173;
				int targetHeight = 100;

				// 비율 유지하면서 자르기 위해 더 많이 필요한 쪽 기준으로 크기 조정
				double targetRatio = (double) targetWidth / targetHeight;
				double imgRatio = (double) imgWidth / imgHeight;

				int cropWidth = imgWidth;
				int cropHeight = imgHeight;

				if (imgRatio > targetRatio) {
					// 원본이 더 넓은 경우 → 가로를 자름
					cropWidth = (int) (imgHeight * targetRatio);
				} else {
					// 원본이 더 높은 경우 → 세로를 자름
					cropHeight = (int) (imgWidth / targetRatio);
				}

				// 중심을 기준으로 자를 영역 계산
				int x = (imgWidth - cropWidth) / 2;
				int y = (imgHeight - cropHeight) / 2;

				// BufferedImage로 자르기
				BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bufferedImage.getGraphics();
				g.drawImage(img1, 0, 0, null);
				g.dispose();

				BufferedImage croppedImage = bufferedImage.getSubimage(x, y, cropWidth, cropHeight);

				// 173x100으로 크기 조정
				Image scaledImage = croppedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
				ImageIcon croppedIcon = new ImageIcon(scaledImage);

				// 이미지를 알림 레이블에 설정
				albumLabel.setIcon(croppedIcon);
				albumLabel.setPreferredSize(new Dimension(targetWidth, targetHeight)); // 크기 고정
				albumLabel.setMaximumSize(new Dimension(targetWidth, targetHeight)); // 크기 고정
			}

			/*
			 * // 앨범 레이블 생성 JLabel albumLabel = new JLabel("📸 앨범 " +
			 * (albumPanel.getComponentCount() + 1));
			 * albumLabel.setHorizontalAlignment(SwingConstants.CENTER);
			 * albumLabel.setOpaque(true); albumLabel.setBackground(Color.white);
			 * albumLabel.setPreferredSize(new Dimension(173, 100)); // 크기 고정
			 * albumLabel.setMaximumSize(new Dimension(173, 100)); // 크기 고정
			 */

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
					new AlbumResultDialog(ab, AlbumMainScreen.this); // 매개변수로 ab 넣어야함 -> albumResultDialog 수정되면 수정
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
		albumPanel.revalidate();
		albumPanel.repaint();
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

