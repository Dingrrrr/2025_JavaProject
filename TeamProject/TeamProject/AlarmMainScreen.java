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

public class AlarmMainScreen extends JFrame {
	private BufferedImage image;
	private JLabel alarmLabel, profileLabel, backLabel, menuLabel, sendMsgLabel, receiveMsgLabel, logoLabel,
			nonReadMsgLabel, isRead;
	RoundedImageLabel imageProfileLabel;
	private JPanel alarmPanel; // 알람 패널
	private JScrollPane scrollPane; // 스크롤 패널
	private JButton SendButton;
	private boolean flag;
	private String name;
	TPMgr mgr = new TPMgr();
	Vector<MsgBean> vlist;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd   HH:mm");

	public AlarmMainScreen(JFrame preFrame) {
		setTitle("프레임 설정");
		setSize(402, 874);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vlist = mgr.showMsgList(StaticData.user_id);
		flag = true;
		StaticData.jf = preFrame;
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
					new AlarmMainScreen(preFrame);
				} else if (source == imageProfileLabel) {
					System.out.println("👤 프로필 클릭됨!");
					dispose();
					new UpdateUserScreen(AlarmMainScreen.this);
				} else if (source == backLabel) {
					System.out.println("뒤로가기 버튼 클릭됨");
					if (preFrame instanceof PetAddMainScreen) {
						dispose();
						new PetAddMainScreen();
					} else if (preFrame instanceof UserHomeScreen) {
						dispose();
						new UserHomeScreen();
					} else if (preFrame instanceof PetHomeScreen) {
						dispose();
						new PetHomeScreen(StaticData.pet_id);
					} else if (preFrame instanceof AlbumScreen) {
						dispose();
						new AlbumScreen();
					} else if (preFrame instanceof AlbumMainScreen) {
						dispose();
						new AlbumMainScreen();
					} else if (preFrame instanceof DiaryScreen) {
						dispose();
						new DiaryScreen();
					} else if (preFrame instanceof DiaryMainScreen) {
						dispose();
						new DiaryMainScreen();
					} else if (preFrame instanceof CommuMainScreen) {
						dispose();
						new CommuMainScreen();
					} else if (preFrame instanceof VoteMainScreen) {
						dispose();
						new VoteMainScreen();
					} else {
						dispose();
						preFrame.setVisible(true);
					}
				} else if (source == SendButton) {
					System.out.println("쪽지 보내기 버튼 클릭됨");

					if (StaticData.user_id.equals("admin")) {
						System.out.println("관리자 확인");
						setEnabled(false);
						new adminSendScreen(preFrame, AlarmMainScreen.this);
					} else {
						setEnabled(false);
						new NoteSendScreen(preFrame, AlarmMainScreen.this);
					}
				} else if (source == menuLabel) {
					System.out.println("메뉴 버튼 클릭됨");
					if (sendMsgLabel.isVisible() && receiveMsgLabel.isVisible()) {
						sendMsgLabel.setVisible(false);
						receiveMsgLabel.setVisible(false);
						nonReadMsgLabel.setVisible(false);
					} else {
						sendMsgLabel.setVisible(true);
						receiveMsgLabel.setVisible(true);
						nonReadMsgLabel.setVisible(true);
					}
				} else if (source == sendMsgLabel) {
					System.out.println("보낸 쪽지 출력");
					vlist = mgr.showSendMsgList(StaticData.user_id);
					sendMsgLabel.setVisible(false);
					receiveMsgLabel.setVisible(false);
					nonReadMsgLabel.setVisible(false);
					flag = false;
					addAlarm();
				} else if (source == receiveMsgLabel) {
					System.out.println("받은 쪽지 출력");
					vlist = mgr.showMsgList(StaticData.user_id);
					sendMsgLabel.setVisible(false);
					receiveMsgLabel.setVisible(false);
					nonReadMsgLabel.setVisible(false);
					flag = true;
					addAlarm();
				} else if (source == nonReadMsgLabel) {
					System.out.println("안읽은 쪽지 출력");
					vlist = mgr.showNonReadMsg(StaticData.user_id);
					sendMsgLabel.setVisible(false);
					receiveMsgLabel.setVisible(false);
					nonReadMsgLabel.setVisible(false);
					flag = true;
					addAlarm();
				}
			}
		};

//		// 🔹 알람 아이콘
//		alarmLabel = createScaledImageLabel("TeamProject/alarm_in.png", 40, 40);
//		alarmLabel.setBounds(280, 120, 40, 40);
//		alarmLabel.addMouseListener(commonMouseListener);
//		add(alarmLabel);

		// 로고 아이콘
		logoLabel = createScaledImageLabel("TeamProject/logo2.png", 180, 165);
		logoLabel.setBounds(105, 54, 180, 165);
		add(logoLabel);

		// 메인 프로필 이미지
		byte[] imgBytes = bean.getUser_image();
		// 상단 프로필 아이디
		if (imgBytes == null || imgBytes.length == 0) { // 330 120 40 40
			// 기본 프로필 이미지 사용
			ImageIcon icon = new ImageIcon("TeamProject/profile.png");
			Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

			// RoundedImageLabel 사용
			imageProfileLabel = new RoundedImageLabel(img, 40, 40, 3); // 200은 크기, 3은 둥근 정도
			imageProfileLabel.setBounds(330, 120, 40, 40);
			imageProfileLabel.addMouseListener(commonMouseListener);
			add(imageProfileLabel);
		} else {
			// 사용자 이미지가 있을 경우
			ImageIcon icon1 = new ImageIcon(imgBytes);
			Image img = icon1.getImage();

			// 원본 이미지 크기
			int imgWidth = icon1.getIconWidth();
			int imgHeight = icon1.getIconHeight();

			// 타겟 크기 (40x40)
			int targetWidth = 40;
			int targetHeight = 40;

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
			g.drawImage(img, 0, 0, null);
			g.dispose();

			BufferedImage croppedImage = bufferedImage.getSubimage(x, y, cropWidth, cropHeight);

			// 이미지 크기 조정 (200x200)
			Image resizedImg = croppedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

			// RoundedImageLabel 사용
			imageProfileLabel = new RoundedImageLabel(resizedImg, 40, 40, 3); // 40은 크기, 3은 둥근 정도
			imageProfileLabel.setBounds(330, 120, 40, 40);
			imageProfileLabel.addMouseListener(commonMouseListener);
			add(imageProfileLabel);
		}

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

		// 🔹 메뉴 아이콘
		menuLabel = createScaledImageLabel("TeamProject/note_menu.png", 85, 45);
		menuLabel.setBounds(280, 725, 85, 45);
		menuLabel.addMouseListener(commonMouseListener);
		menuLabel.setVisible(true);
		getLayeredPane().add(menuLabel, JLayeredPane.PALETTE_LAYER);

		// 🔹 보낸 알림 아이콘
		sendMsgLabel = createScaledImageLabel("TeamProject/send_msg.png", 40, 40);
		sendMsgLabel.setBounds(305, 675, 40, 40);
		sendMsgLabel.addMouseListener(commonMouseListener);
		add(sendMsgLabel);
		sendMsgLabel.setVisible(false);

		// 🔹 받은 알림 아이콘
		receiveMsgLabel = createScaledImageLabel("TeamProject/read2.png", 40, 40);
		receiveMsgLabel.setBounds(305, 615, 40, 40);
		receiveMsgLabel.addMouseListener(commonMouseListener);
		add(receiveMsgLabel);
		receiveMsgLabel.setVisible(false);

		// 🔹 안읽은 알림 아이콘
		nonReadMsgLabel = createScaledImageLabel("TeamProject/non_read3.png", 50, 50);
		nonReadMsgLabel.setBounds(299, 550, 50, 50);
		nonReadMsgLabel.addMouseListener(commonMouseListener);
		add(nonReadMsgLabel);
		nonReadMsgLabel.setVisible(false);

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
				g.drawLine(22, 785, 379, 785);
			}
		};

		panel.setOpaque(false);
		panel.setLayout(null);
		add(panel);

		// 🔹 스크롤 가능한 알람 패널 설정
		alarmPanel = new JPanel();
		alarmPanel.setLayout(new BoxLayout(alarmPanel, BoxLayout.Y_AXIS)); // 세로로 쌓이게 설정
		alarmPanel.setBackground(Color.WHITE);
		alarmPanel.setBorder(new LineBorder(Color.WHITE, 1));
		// alarmPanel의 레이아웃을 FlowLayout으로 설정하여 항목들이 수직으로 정렬되게 함
		alarmPanel.setLayout(new BoxLayout(alarmPanel, BoxLayout.Y_AXIS)); // 수직 정렬

		// 🔹 스크롤 패널 추가 (23, 165, 357, 615 영역에 배치)
		scrollPane = new JScrollPane(alarmPanel);
		scrollPane.setBounds(23, 165, 357, 620);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // 스크롤바 숨기기
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 부드러운 스크롤 유지
		scrollPane.setBorder(new MatteBorder(0, 0, 0, 0, Color.white));
		panel.add(scrollPane);

		addAlarm();

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
	 * 알림 추가 메서드
	 */
	public void addAlarm() {
		alarmPanel.removeAll();
		for (MsgBean mb : vlist) {
			name = mgr.showOneUserName(mb.getSender_id());

			// 알람 항목 패널
			JPanel alarmItemPanel = new JPanel();
			alarmItemPanel.setPreferredSize(new Dimension(353, 120));
			alarmItemPanel.setMaximumSize(new Dimension(353, 120));
			alarmItemPanel.setBackground(Color.WHITE);
			alarmItemPanel.setBorder(new LineBorder(Color.black, 1));
			alarmItemPanel.setLayout(new BorderLayout(10, 10)); // 여백 포함 레이아웃
			alarmItemPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setEnabled(false);
					if (flag) {// 받은 쪽지이기 때문에 쪽지 확인 화면
						setEnabled(false);
						mgr.readMsg(mb.getMsg_id());
						new NoteCheckScreen(AlarmMainScreen.this, mb);
					} else {
						setEnabled(false);
						new NoteModifyScreen(AlarmMainScreen.this, mb);
					}
				}
			});

			// 1) 상단 영역: USER_ID, 날짜
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(Color.WHITE);
			topPanel.setPreferredSize(new Dimension(353, 25)); // 상단 패널 높이 증가
			isRead = createScaledImageLabel("TeamProject/non_read.png", 22, 22);
			if (mgr.isReadMsg(mb.getMsg_id())) { // 읽었으면 true 출력
				isRead = createScaledImageLabel("TeamProject/read.png", 22, 22);
			}

			JLabel userIdLabel = new JLabel("from. " + name);
			userIdLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5)); // 위/아래 여백 추가
			if (!flag) {
				name = mgr.showOneUserName(mb.getReceiver_id());
				userIdLabel = new JLabel("to. " + name);
				userIdLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5)); // 위/아래 여백 추가
			}

			JLabel dateLabel = new JLabel(sdf.format(mb.getMsg_date()), SwingConstants.RIGHT);
			dateLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5)); // 위/아래 여백 추가

			topPanel.add(isRead, BorderLayout.WEST);
			topPanel.add(userIdLabel);
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
			JLabel titleLabel = new JLabel(mb.getMsg_title());
			titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 왼쪽 정렬

			// 내용
			JLabel contentLabel = new JLabel(mb.getMsg_content());
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

		alarmPanel.revalidate();
		alarmPanel.repaint();
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

	public void updRead() {
		isRead = createScaledImageLabel("TeamProject/read.png", 22, 22);
	}

	public static void main(String[] args) {
		new LoginScreen();
	}
}
